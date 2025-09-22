import { AfterViewInit, Component, ViewChild, OnInit } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { ObligationsSearchServiceService } from '../../core/services/obligations-search/obligations-search-service.service';
import { RegionsServiceService } from '../../core/services/obligations-search/regions-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { LoaderBtnDirective } from '@app/common/directives/loader-btn.directive';

import {
  FormControl,
  FormBuilder,
  FormGroupDirective,
  FormGroup,
  NgForm,
  Validators,
} from '@angular/forms';

export interface ResultItems {
  company: string;
  account: string;
  companyName: string;
  obligationId: string;
  obligationDate: Date;
  openAmount: string;
  totalAmount: string;
  transType: string;
  status: string;
  region: string;
  accountingPeriod: Date;
  site: string;
}

export interface Region {
  region: string;
  description: string;
}

const TABLE_DATA: ResultItems[] = [];

@Component({
  selector: 'app-obligations-search',
  templateUrl: './obligations-search.component.html',
  styleUrls: ['./obligations-search.component.css']
})

export class ObligationsSearchComponent implements AfterViewInit {
  displayedColumns: string[] = [
    'company',
    'account',
    'site',
    'companyName',
    'obligationId',
    'obligationDate',
    'openAmount',
    'totalAmount',
    'transType',
    'status',
    'region',
    'accountingPeriod'
  ];
  form: FormGroup;
  public dataSource = new MatTableDataSource<ResultItems>(TABLE_DATA);
  public searchItems: Array<ResultItems>;
  showMessage: boolean = false;
  localStorage: Storage;
  searchExecuted: boolean = false;
  hasUrlParms: boolean = false;
  public errorMsg: string;
  public regions: Array<Region>;
  public loading : boolean;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private obligationsSearchService: ObligationsSearchServiceService,
    private route: ActivatedRoute,
    private regionsService: RegionsServiceService  )
  {

  }

  onEnter() {
    this.searchData(true);
  }
  handleKey(e) {
    if (e.keyCode === 13) {
      this.searchData(true);

    }
  }

  async ngOnInit() {
    this.form = this.fb.group({
      region: [null],
      obligationId: [null, [Validators.pattern('^[0-9]*$'), Validators.minLength(3)] ],
      accountingPeriodFrom: [null, Validators.pattern("^(1[0-2]|0[1-9]|\d)\/(20|19)[0-9][0-9]$")],
      accountingPeriodTo:   [null, Validators.pattern("^(1[0-2]|0[1-9]|\d)\/(20|19)[0-9][0-9]$")],
      amountRangeFrom: [null, Validators.pattern('^[0-9.]*$')],
      amountRangeTo: [null, Validators.pattern('^[0-9.]*$')],
    });

    this.route.queryParamMap.subscribe((params) => {
      const objParms = params['params'];
      const keys = Object.keys(objParms) as (keyof typeof objParms)[];
      this.hasUrlParms = keys.length > 0;
      keys.forEach((element: string) => {
        this.form?.get(element).setValue(params.get(element));
      });
    });

    this.regionsService.getRegionsAll().subscribe((data: Array<Region>) => {
      this.regions = data;
    });

    if (this.hasUrlParms) this.searchData(false);

  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  public searchData(updateUrlParms: boolean): void {
    //for deep linking, we want the URL to have the search data
    if (updateUrlParms) this.updateUrl();
    const accountingPeriodFromVal = this.form.get('accountingPeriodFrom').value;
    const accountingPeriodToVal = this.form.get('accountingPeriodTo').value

    if (accountingPeriodFromVal == null || accountingPeriodToVal == null){
      this.errorMsg = 'Error: Invalid search criteria. A Region, Obligation Number, and Period Range are required.';
    }

    const obligationId = this.form.get('obligationId').value;
    const accountingPeriodFrom = this.toYearMonthAccountingPeriod(accountingPeriodFromVal);
    const accountingPeriodTo = this.toYearMonthAccountingPeriod(accountingPeriodToVal);
    const amountRangeFrom = this.form.get('amountRangeFrom').value;
    const amountRangeTo = this.form.get('amountRangeTo').value;
    const regionCode = this.form.get('region').value;
    this.errorMsg = null;
    this.showMessage = false;

    if (
      regionCode &&
      accountingPeriodFrom &&
      accountingPeriodTo &&
      obligationId
    ) {
      this.loading=true;
      this.obligationsSearchService
        .getSearchObligationsProps(
          regionCode,
          obligationId,
          accountingPeriodFrom,
          accountingPeriodTo,
          amountRangeFrom,
          amountRangeTo
        )
        .subscribe({
          next: (data: Array<ResultItems>) => {
            this.loading=false;
            this.searchItems = data;
            this.dataSource = new MatTableDataSource(data);
            this.dataSource.sort = this.sort;
            this.dataSource.paginator = this.paginator;
            this.searchExecuted = true;
          },
          error: (err: HttpErrorResponse) => {
            this.loading=false;
            this.errorMsg = err.error.errorMsg;
            this.searchItems = [];
            this.dataSource = new MatTableDataSource(TABLE_DATA);
            this.searchExecuted = true;
          }
        });

      this.showMessage = false;
    } else {
      this.showMessage = true;
      this.searchItems = [];
    }
  }


  public recoverData(): void {}

  public clearData(): void {
    this.form.get('obligationId').reset();
    this.form.get('region').reset();
    this.form.get('accountingPeriodFrom').reset();
    this.form.get('accountingPeriodTo').reset();
    this.form.get('amountRangeFrom').reset();
    this.form.get('amountRangeTo').reset();
    this.searchItems = [];
    this.dataSource = new MatTableDataSource(TABLE_DATA);
    this.errorMsg = null;
    this.searchExecuted=false;
    this.showMessage = false;
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  updateUrl(): void {
    const params = {};
    const formValue = this.form.value; // this.form should be a FormGroup

    for (const key in formValue) {
      params[key] = formValue[key];
    }
  }

  // screen input is to be MM/YYYY proc needs YYYYMM
  toYearMonthAccountingPeriod(monthYear: string):string
  {
    let splitted = monthYear.split("/", 2);
    let yearMonth = splitted[1].concat(splitted[0]);
    return yearMonth;   }

}

