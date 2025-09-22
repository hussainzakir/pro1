import { Component, OnInit, ViewChild, AfterViewInit, Inject } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator, MatSort, MAT_DIALOG_DATA } from '@angular/material';
import { AccountService } from 'src/app/core/services/account/account.service';

@Component({
  selector: 'app-obligation-invoice-detail',
  templateUrl: './obligation-invoice-detail.component.html',
  styleUrls: ['./obligation-invoice-detail.component.css']
})
export class ObligationInvoiceDetailComponent implements OnInit, AfterViewInit {

  public dataSource: MatTableDataSource<any>;
  public displayedColumns: string[] = ['site','containerGroup','quantity','containerType','containerSize','date','chargeDescription','fromDate','toDate','receipt','amount'];

  public invoiceDetails: any;
  public company: any;
  public account: any;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public accountService: AccountService) {
    this.invoiceDetails = data.details;
    this.company = data.company;
    this.account = data.account;
    }

  ngOnInit() {
    this.loadTableData(this.invoiceDetails.invoiceDetails);
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  loadTableData(data: any){
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

}
