import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { ActivatedRoute, Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { FormControl, FormBuilder, FormGroup, Validators, NgControl } from '@angular/forms';
import { MatFormFieldControl } from '@angular/material/form-field';
import { AuditserviceService } from '../../core/services/audit/audit-service.service';
import { Observable, of as observableOf } from 'rxjs';
import { AccountNumberParts, ServicenumberfieldComponent } from '@app/core/layouts/servicenumberfield/servicenumberfield.component';
import { As400DatePipePipe } from '@app/common/pipes/as400-date-pipe.pipe';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import {PageEvent} from '@angular/material/paginator';
export class AuditRequest {
  cbs: string;
  fromDate: string;
  toDate: string;
  division: string;
  accountNumber: string;
  site: string;
  group: string;
  fileFilter: string;
  startIndex: number;
  endIndex: number;
  sort: string;
  totalRows: number;
}

export interface ResultItems {
  cbs: string;
  serviceAccount: string;
  serviceName: string;
  locId: string;
  site: string;
  gValue: string;
  field: string;
  oldValue: string;
  newValue: string;
  date: string;
  time: string;
  userId: string;
  totalRows: string;
}

const TABLE_DATA: ResultItems[] = [
];

@Component({
  selector: 'app-audit',
  templateUrl: './audit.component.html',
  styleUrls: ['./audit.component.css'],
  providers: [DatePipe, As400DatePipePipe, { provide: MatFormFieldControl, useExisting: ServicenumberfieldComponent }]
})

export class AuditComponent implements AfterViewInit, MatFormFieldControl<ServicenumberfieldComponent> {
  @ViewChild('division') divisionInput: HTMLInputElement;
  @ViewChild('serviceAccount') serviceAccountInput: HTMLInputElement;
  @ViewChild('site') siteInput: HTMLInputElement;
  @ViewChild('grp') grpInput: HTMLInputElement;
  @ViewChild(ServicenumberfieldComponent) childComponent: ServicenumberfieldComponent;
  displayedColumns: string[] = ['edit', 'cbs', 'serviceAccountNumber', 'serviceName', 'locId',
    'site', 'gValue', 'field', 'oldValue', 'newValue', 'date', 'time', 'userId'];
  form: FormGroup;
  myForm: FormGroup;
  public auditRequest = new AuditRequest();
  public dataSource = new MatTableDataSource(TABLE_DATA);
  public searchItems: Array<ResultItems>;
  public cbsItems: Array<ResultItems>;
  showMessage: boolean = false;
  hasUrlParms: boolean = false;
  searchExecuted: boolean = false;
  exportEnabled: boolean =false;
  public allRecordsChecked = false;
  public otherRecordsDisabled = false;
  public today: Date = new Date();
  public loading: boolean;
  public totalData: number = 0;
  public pageSize: number = 50;
  public pageIndex: number = 0;
  pageEvent: PageEvent;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private fb: FormBuilder,
    private auditService: AuditserviceService,
    private route: ActivatedRoute,
    private datePipe: DatePipe) {
  }
  value: ServicenumberfieldComponent;
  stateChanges: Observable<void>;
  id: string;
  placeholder: string;
  ngControl: NgControl;
  focused: boolean;
  empty: boolean;
  shouldLabelFloat: boolean;
  required: boolean;
  disabled: boolean;
  errorState: boolean;
  controlType?: string;
  autofilled?: boolean;
  exportDate: string;
  searchDate: string;
  setDescribedByIds(ids: string[]): void {
    throw new Error('Method not implemented.');
  }

  onContainerClick(event: MouseEvent): void {
    throw new Error('Method not implemented.');
  }

  onEnter() {
    this.searchData();
  }

  handleKey(e) {
    if (e.keyCode === 13) {
      this.searchData();
    }
  }
  template = new FormControl('', [
    Validators.required
  ]);

  async ngOnInit() {
    this.form = this.fb.group({
      allRecords: [null],
      rateRecords: [null],
      accountRecords: [null],
      serviceRecords: [null],
      containerRecords: [null],
      siteRecords: [null],
      cbs: [null, Validators.pattern('^[0-9]*$')],
      fromDate: [null],
      toDate: [null],
      accountNumber: [null],
      division: [null],
      site: [null],
      group: [null],
    });

    this.myForm = this.fb.group({
      san: new FormControl(new AccountNumberParts('', '', '', '')),
    });
    //Retrive stored form values from the LocalStorage
    const storedFormValues = localStorage.getItem('auditFormValues');
    if(storedFormValues){
      const formValues = JSON.parse(storedFormValues);
      this.form.patchValue(formValues);
      const savedAuditRequest = localStorage.getItem("auditRequest");
      if(savedAuditRequest){
        this.auditRequest = JSON.parse(savedAuditRequest);
        this.loadServiceAccountNumber(this.auditRequest);
        this.loading = true;
        this.auditService.getAuditRecords(this.auditRequest).subscribe(
        (response: { auditResults: ResultItems[]; totalCount: number; }) => {
        this.searchItems = response.auditResults;
        this.dataSource.data =  response.auditResults;
        setTimeout(() => {
          this.paginator.pageIndex = Math.trunc(this.auditRequest.startIndex/50);
          this.paginator.length = response.totalCount;
        });
        this.loading = false;
      });
      }
    }
    this.route.queryParamMap.subscribe(params => {
      const objParms = params['params'];
      const keys = Object.keys(objParms) as (keyof typeof objParms)[]
      this.hasUrlParms = keys.length > 0;
      keys.forEach((element: string) => {
        this.form?.get(element).setValue(params.get(element))
      });

    });

    if (this.hasUrlParms)
      this.searchData();
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  public searchData(): void {
    this.loading = true;
    const cbs = this.form.get('cbs').value;
    let fromDate = this.form.get('fromDate').value;
    let toDate = this.form.get('toDate').value;
    let updatedFromDate = this.updateFromDate(fromDate, toDate);
    let allRecordsChecked = this.form.get('allRecords').value;
    let accountRecordsChecked = this.form.get('accountRecords').value;
    let rateRecordsChecked = this.form.get('rateRecords').value;
    let serviceRecordsChecked = this.form.get('serviceRecords').value;
    let containerRecordsChecked = this.form.get('containerRecords').value;
    let siteRecordsChecked = this.form.get('siteRecords').value;
    const formData = this.childComponent.myForm.value;
    console.log(formData);
    let division = formData.division;
    let grp = formData.grp;
    let site = formData.site;
    let acNumber = formData.serviceAccount;
    let fileFilter = this.getFileFilter();
    if (updatedFromDate) {
      this.searchDate = this.datePipe.transform(updatedFromDate, 'yyyy-MM-dd');
      fromDate = this.searchDate;
    }
    if (toDate) {
      toDate = this.datePipe.transform(toDate, 'yyyy-MM-dd')
    }
    this.auditRequest.startIndex = 1;
    this.auditRequest.endIndex = this.auditRequest.startIndex+49;
    this.auditRequest.cbs = cbs;
    this.auditRequest.fromDate = fromDate;
    this.auditRequest.toDate = toDate;
    this.auditRequest.group = grp;
    this.auditRequest.site = site;
    this.auditRequest.accountNumber = acNumber;
    this.auditRequest.fileFilter = fileFilter;
    this.auditRequest.division = division;
    if ((this.auditRequest.fromDate && this.auditRequest.toDate) && (this.auditRequest.division || this.auditRequest.cbs || allRecordsChecked || accountRecordsChecked || rateRecordsChecked || serviceRecordsChecked || containerRecordsChecked || siteRecordsChecked)) {
      this.auditService.getAuditRecords(this.auditRequest).subscribe(
        (response: { auditResults: ResultItems[]; totalCount: number; }) => {
          this.searchItems = response.auditResults;
          this.dataSource = new MatTableDataSource(this.searchItems);
          this.dataSource.sort = this.sort;
          this.dataSource.paginator = this.paginator;
          this.totalData = response.totalCount;
          this.loading = false;
          this.searchExecuted = true;
          this.exportEnabled = this.dateRangeAudit(this.form.get('fromDate').value, this.form.get('toDate').value);
        });
      this.showMessage = false;
    } else {
      this.loading = false;
      this.showMessage = true;
      this.exportEnabled = false;
      this.searchItems = [];
    }
  }

  //this method is only for testing the pagination
  public newSearchData() {
    return this.auditService.getAuditRecords(this.auditRequest);
  }

  //method to clear the data
  public clearData(): void {
    this.form.reset();
    this.form.controls['cbs'].setValue('');
    this.form.controls['fromDate'].setValue(null);
    this.form.controls['toDate'].setValue(null);
    this.form.controls['allRecords'].setValue(false);
    this.form.controls['rateRecords'].setValue(false);
    this.form.controls['accountRecords'].setValue(false);
    this.form.controls['serviceRecords'].setValue(false);
    this.form.controls['containerRecords'].setValue(false);
    this.form.controls['siteRecords'].setValue(false);
    this.myForm = this.fb.group({
      san: new FormControl(new AccountNumberParts('', '', '', '')),
    });
    this.searchItems = [];
    this.showMessage = false;
    this.exportEnabled = false;
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;


  }

  handlePageEvent(e: PageEvent) {

    this.pageEvent = e;
    this.totalData = e.length;
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;


    var start =  1 + (this.pageIndex * this.pageSize);
    var end = (this.pageIndex + 1) * this.pageSize;
    this.doFetch(start, end);
  }

  doFetch( startIndex: number, endIndex: number){
    this.auditRequest.endIndex = endIndex;
    this.auditRequest.startIndex = startIndex;
    this.loading = true;
    this.auditService.getAuditRecords(this.auditRequest).subscribe(
      (response: { auditResults: ResultItems[]; totalCount: number; }) => {
        this.searchItems = response.auditResults;
        this.dataSource.data =  response.auditResults;
        setTimeout(() => {
          this.paginator.pageIndex = this.pageIndex;
          this.paginator.length = response.totalCount;
        });
        this.loading = false;
      });
  }

  //method disable the checkboxes if allRecords is checked
  toggleOtherCheckboxes(checked: boolean) {
    if (checked) {
      this.form.get('rateRecords').disable();
      this.form.get('accountRecords').disable();
      this.form.get('serviceRecords').disable();
      this.form.get('containerRecords').disable();
      this.form.get('siteRecords').disable();
    } else {
      this.form.get('rateRecords').enable();
      this.form.get('accountRecords').enable();
      this.form.get('serviceRecords').enable();
      this.form.get('containerRecords').enable();
      this.form.get('siteRecords').enable();
    }
  }

  //To get the filter type from checkboxes
  getFileFilter(): string {

    let res = "";

    if (!this.form.get('allRecords').value) {
      let comma = false;
      if (this.form.get('accountRecords').value) {
        res = "'ARPCU','BIPCU','BIPIF'";
        comma = true;
      }
      if (this.form.get('containerRecords').value) {
        res = res + (comma ? "," : "") + "'BIPAC','BIPACC','BIPACL'";
        comma = true;
      }
      if (this.form.get('rateRecords').value) {
        res = res + (comma ? "," : "") + "'BIPCC'";
        comma = true;
      }

      if (this.form.get('serviceRecords').value) {
        res = res + (comma ? "," : "") + "'BIPFS'";
        comma = true;
      }

      if (this.form.get('siteRecords').value) {
        res = res + (comma ? "," : "") + "'BIPAS'";
      }


    }

    return res;
  }
  updateFromDate(fromDate: Date, toDate: Date):Date {
      const threeYearsAgo = new Date(toDate);
      threeYearsAgo.setFullYear(threeYearsAgo.getFullYear()-3);
      if(fromDate > threeYearsAgo){
        return fromDate;
      }else{
        return threeYearsAgo;
      }
  }
  exportData(){
    this.auditRequest.fromDate = this.exportDate;
    this.auditRequest.startIndex  = 1;
    this.auditRequest.endIndex = 99999;
    this.auditService.exportAuditRecords(this.auditRequest).subscribe(
        (response) => {
      this.auditRequest.fromDate = this.searchDate;
      const downloadLink = document.createElement('a');
      downloadLink.href = URL.createObjectURL(response);
      downloadLink.setAttribute('download', 'audit_results.csv');
      document.body.appendChild(downloadLink);
      downloadLink.click();
      document.body.removeChild(downloadLink);
        });
      }

    dateRangeAudit(fromDate: Date, toDate: Date): boolean {
      const threeYearsAgo = new Date(toDate);
      threeYearsAgo.setFullYear(threeYearsAgo.getFullYear()-3);
      if(fromDate < threeYearsAgo){
        this.exportDate = this.datePipe.transform(fromDate, 'yyyy-MM-dd');
        return true;
      }else{
        return false;
      }
    }
    
    saveFormData(){
      localStorage.setItem('auditFormValues', JSON.stringify(this.form.value));
      localStorage.setItem('auditRequest', JSON.stringify(this.auditRequest));
    }

    loadServiceAccountNumber(fetchedAuditRequest: AuditRequest){
      let division = fetchedAuditRequest.division ? fetchedAuditRequest.division : "";
      let accountNumber = fetchedAuditRequest.accountNumber ? fetchedAuditRequest.accountNumber : "";
      let site = fetchedAuditRequest.site ? fetchedAuditRequest.site : "";
      let group = fetchedAuditRequest.group ? fetchedAuditRequest.group : "";
      this.myForm = this.fb.group({
        san: new FormControl(new AccountNumberParts(division, accountNumber, site, group))
      });
    }
}


