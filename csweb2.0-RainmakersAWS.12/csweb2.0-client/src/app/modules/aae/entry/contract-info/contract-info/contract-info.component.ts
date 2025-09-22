import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { AaeContract, ContractRates } from '@app/core/interfaces/aae-contractInfo-interface';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';

@Component({
  selector: 'app-contract-info',
  templateUrl: './contract-info.component.html',
  styleUrls: ['./contract-info.component.css']
})
export class ContractInfoComponent implements OnInit {

  public contractInfo: AaeContract = {} as AaeContract;

  public contractRateData: ContractRates = {} as ContractRates;

  public rates: any;
  isLoadingContractResults = false;
  panelOpenState = false;

  displayedRatesColumns: string[] = ['containerGroup','effectiveDate', 'stopCode', 'rateType', 'districtCode', 'chargeCode', 'chargeRate'];
  displayedInfoColumns: string[] = ['contractNumber', 'contractGroup', 'contractName'];
  dataSourceRates: MatTableDataSource<any>;
  dataSourceInfo: MatTableDataSource<any>;

  public quoteId: string;
  public dataForContractRatesTable =[];
  public dataForContractInfoTable =[];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private router: Router, private route: ActivatedRoute,
    private aaeService: AaeEntryService,) { }

  fetchContractDetails(){
    this.isLoadingContractResults = true;
    this.aaeService.getContractResponse(this.quoteId).subscribe( (data: any) => {
      this.contractInfo = data;
      this.dataSourceRates = new MatTableDataSource(this.contractInfo.rates);
      this.dataSourceInfo = new MatTableDataSource(this.contractInfo.contract);
      this.isLoadingContractResults = false;
         });
   }

  ngOnInit(): void {

    this.route.paramMap.subscribe( paramMap => {
      this.quoteId = paramMap.get('quoteId');
      console.log('Quote ID: ' + this.quoteId);

      this.fetchContractDetails();
    });

  }
}
