import { AfterViewInit, Component, ViewChild, OnInit } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { MatInputModule } from '@angular/material/input';

import { FormControl, FormBuilder, FormGroupDirective, FormGroup, NgForm, Validators } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { CsSearchServiceService } from '../../core/services/cs-search/cs-search-service.service';

export interface ResultItems {
  company: string;
  locationId: string;
  accountNumber: string;
  accountAddress: string;
  accountCity: string;
  accountState: string;
  accountZipCode: string;
  account: string;
  siteNumber: string;
  site: string;
  siteStatusDescription: string;
  siteAddress1: string;
  siteAddress2: string;
  siteCity: string;
  siteState: string;
  siteZipCode: string;
  nationalAccount: string;
}

const TABLE_DATA: ResultItems[] = [
];

@Component({
  selector: 'app-cs-search',
  templateUrl: './cs-search.component.html',
  styleUrls: ['./cs-search.component.css']
})
export class CsSearchComponent implements AfterViewInit {
  displayedColumns: string[] = ['company', 'locationId', 'accountNumber', 'account',
    'siteNumber', 'site'];
  form: FormGroup;
  public dataSource = new MatTableDataSource(TABLE_DATA);
  public searchItems: Array<ResultItems>;
  showMessage: boolean = false;
  localStorage: Storage;
  searchExecuted: boolean = false;
  hasUrlParms: boolean = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private csSearchService: CsSearchServiceService,
    private route: ActivatedRoute
  ) {
    this.localStorage = window.localStorage;
  }

  onEnter() {
    this.searchData(true);
  }
  handleKey(e) {
    if (e.keyCode === 13) {
      this.searchData(true);
    }

  }

  template = new FormControl('', [
    Validators.required
  ]);

  async ngOnInit() {
    this.form = this.fb.group({
      state: [null],
      division: [null],
      includeClosedSites: [null],
      nationalAccountsOnly: [null],
      locationId: [null, Validators.pattern('^[a-zA-Z0-9- ]*$')],
      siteNumber: [null, Validators.pattern('^[0-9]*$')],
      accountNumber: [null, [Validators.pattern('^[0-9]*$'), Validators.maxLength(7)]],
      streetAddress: [null],
      zipCode: [null, Validators.pattern('^[0-9-]*$')],
      siteName: [null],
      city: [null, Validators.pattern('^[a-zA-Z ]*$')],
      phone: [null, [Validators.pattern('^[0-9]*$'), Validators.maxLength(10)]],
      accountEmail: [null, Validators.pattern('^[a-zA-Z0-9@.]*$')],
      openObligationBalance: [null, Validators.pattern('^[0-9.]*$')],
      purchaseOrderNum: [null, Validators.pattern('^[0-9]*$')]
    });
    this.form.get('division').setValue("all");

    this.route.queryParamMap.subscribe(params => {
      const objParms = params['params'];
      const keys = Object.keys(objParms) as (keyof typeof objParms)[]
      this.hasUrlParms = keys.length > 0;
      keys.forEach((element:string) => {
        this.form?.get(element).setValue(params.get(element));
        if(element == 'includeClosedSites'){
          this.form?.get(element).setValue(params.get(element) === 'true')
        }
      });

  });

  if(this.hasUrlParms)
    this.searchData(false);
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  public searchData(updateUrlParms:boolean): void {

    //for deep linking, we want the URL to have the search data
    if(updateUrlParms)
      this.updateUrl();

    const locationId = this.form.get('locationId').value;
    const siteNumber = this.form.get('siteNumber').value;
    const accountNumber = this.form.get('accountNumber').value;
    const streetAddress = this.form.get('streetAddress').value;
    const zipCode = this.form.get('zipCode').value;
    const siteName = this.form.get('siteName').value;
    const city = this.form.get('city').value;
    const phone = this.form.get('phone').value;
    const state = this.form.get('state').value;
    const division = this.form.get('division').value;
    // const accountEmail = this.form.get('accountEmail').value;
    // const openObligationBalance = this.form.get('openObligationBalance').value;
    // const purchaseOrderNum = this.form.get('purchaseOrderNum').value;
    var includeClosedSites = this.form.get('includeClosedSites').value;
    var nationalAccountsOnly = this.form.get('nationalAccountsOnly').value;
    if (includeClosedSites) {
      includeClosedSites = "Y";
    }
    if (nationalAccountsOnly) {
      nationalAccountsOnly = "Y";
    }
    if (locationId || (siteName && accountNumber && locationId) || siteName  || phone ||
    // accountEmail ||
    // openObligationBalance ||
    //  purchaseOrderNum ||
     (streetAddress && accountNumber)|| (accountNumber) || (siteNumber && city && state) || (streetAddress && city && state))
     {
      this.csSearchService.getSearchAccountsProps(locationId, siteNumber, accountNumber,
        streetAddress, zipCode, siteName, city, phone,
        // accountEmail, openObligationBalance, purchaseOrderNum,
        division, state,includeClosedSites, nationalAccountsOnly).subscribe((data: Array<ResultItems>) => {
          this.searchItems = data;
          this.dataSource = new MatTableDataSource(data);
          this.dataSource.sort = this.sort;
          this.dataSource.paginator = this.paginator;
          this.searchExecuted = true;

        });
      this.showMessage = false;
    } else {
      this.showMessage = true;
      this.searchItems = [];
    }
  }
  public recoverData(): void {

  }
  public clearData(): void {
    this.form.get('accountNumber').reset();
    this.form.get('locationId').reset();
    this.form.get('siteNumber').reset();
    this.form.get('accountNumber').reset();
    this.form.get('streetAddress').reset();
    this.form.get('zipCode').reset();
    this.form.get('siteName').reset();
    this.form.get('city').reset();
    this.form.get('phone').reset();
    this.form.get('state').reset();
    this.form.get('division').setValue("all");
    this.form.get('includeClosedSites').reset();
    this.form.get('nationalAccountsOnly').reset();
    // this.form.get('accountEmail').reset();
    // this.form.get('openObligationBalance').reset();
    // this.form.get('purchaseOrderNum').reset();
    this.searchItems = [];
  }

  public accountInformation(): void {
    this.router.navigate(['/cs-search-account-info'], {
      queryParams: { accountNumber: this.form.get('accountNumber') }
   });
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


    ///update url
    this.router.navigate(
      [],
      {
        relativeTo: this.route,
        queryParams: params,
        queryParamsHandling: 'merge', // remove to replace all query params by provided
      });
  }

}


