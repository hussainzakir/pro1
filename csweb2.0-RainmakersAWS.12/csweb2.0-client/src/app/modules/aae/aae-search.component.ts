import { LocalStorageService } from './../../core/services/LocalStorage.service';
import {AfterViewInit, Component, ViewChild, OnChanges, SimpleChanges, Input, OnInit, ChangeDetectorRef, Output, EventEmitter} from '@angular/core';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {MatSnackBar} from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { As400DatePipePipe } from '@app/common/pipes/as400-date-pipe.pipe';
import { AaeSearchService } from '@app/core/services/aae-entry/aae-search.service';
import { AaeSearchRecord, AaeSearchResult } from '@app/core/interfaces/aae-search-interfaces';
import { ActivatedRoute } from '@angular/router';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { UserAuths } from '@app/core/services/authentication/userAuths';
import { ErrorReportComponent } from './entry/error-report/error-report/error-report.component';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AddNotesComponent } from './add-notes/add-notes.component';
import { ViewNotesComponent } from './view-notes/view-notes.component';


@Component({
  selector: 'app-aae',
  templateUrl: './aae-search.component.html',
  styleUrls: ['./aae-search.component.css'],
  providers: [DatePipe, As400DatePipePipe]
})
export class AaeSearchComponent implements OnInit {

  displayedColumns: string[] = ['oracleDivision','companyNumber','accountNumber','quoteID','quoteType','accountName','siteAddress','statusCode','assignee', 'notes', 'createDate'];
  dataSource: any;
  historyItems: Array<AaeSearchRecord>;
  searchForm: FormGroup;
  includeOnHold: boolean = false;
  includeDeleted: boolean = false;
  totalPagedResults = 0;
  isLoadingResults = false;
  data: AaeSearchRecord[] = [];
  initialValues = null;
  fromEntry = false;
  private snackbarTimeout = 4;

  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  @ViewChild(MatSort) sort: MatSort;

  isConsolidatedAae: any;

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute,
    private datePipe: DatePipe,
    private searchService: AaeSearchService,
    private bottomSheet: MatBottomSheet,
    private storageService: LocalStorageService,
    private snackBar: MatSnackBar,
    public dialog: MatDialog,
    private userAuth: UserAuths) { }



  ngOnInit() {

    //this.paginator.pageSize = 10;
    let uploadRequiredFields: any;
    uploadRequiredFields = {};

    this.searchForm =  this.formBuilder.group({
      company:  ['', ],
      customerAccount:  [''],
      oracleDivision:  [''],
      quoteID:  [''],
      salesRep:  [''],
      statusCode:  [' '],
      effectiveFrom:  [''],
      effectiveTo:  [''],
      quoteType:  [' '],
      createDate:  [''],
      assignee:  [''],
      formatType: [' '],
      includeDeleted: false,
      includeOnHold: false
    });

    this.initSearch();
  }

  resetTheForm(){
    this.searchForm.reset();
    this.searchForm.patchValue({
      company:  [''],
      customerAccount:  [''],
      oracleDivision:  [''],
      quoteID:  [''],
      salesRep:  [''],
      statusCode:  [''],
      quoteType:  [''],
      assignee:  [''],
      formatType: [''],
      includeDeleted: false,
      includeOnHold: false
    });
  }

   doInitialSearch(){

    let pgsz = !this.paginator || this.paginator.pageSize == 0 ? 10 : this.paginator.pageSize;
    let formObj = this.getSearchForm(pgsz,1);
    this.storageService.saveAaeSearchData(JSON.stringify(formObj));
    this.callSearch(formObj);
   }

   doSearch(){

    let pgsz = !this.paginator || this.paginator.pageSize == 0 ? 10 : this.paginator.pageSize;
    let formObj = this.getSearchForm(pgsz,1);
    this.storageService.saveAaeSearchData(JSON.stringify(formObj));
    this.paginator.pageIndex = 0; 
    this.callSearch(formObj);
   }

   private callSearch(formObj : any){

    this.isLoadingResults = true;
    this.searchService.doSearch(formObj).subscribe( (data: AaeSearchResult) => {
      this.totalPagedResults = data.totalRecords;
      this.historyItems = data.records;
      this.paginator.length = data.totalRecords;
      this.data = data.records;
      this.isLoadingResults = false;


    });
   }

  private getSearchForm(pageSize: number, pageNumber: number){
    let formObj = this.searchForm.getRawValue();

    let effectiveFrom = this.datePipe.transform(this.searchForm.controls.effectiveFrom.value, 'yyyy-MM-dd');
    let effectiveTo = this.datePipe.transform(this.searchForm.controls.effectiveTo.value, 'yyyy-MM-dd');
    let createDate = this.datePipe.transform(this.searchForm.controls.createDate.value, 'yyyy-MM-dd');
    let includeOnHoldStr = this.searchForm.controls.includeOnHold.value ? "Y" : "N";
    let includeDeletedStr = this.searchForm.controls.includeDeleted.value ? "Y" : "N";
    let consolidatedAae =  this.isConsolidatedAae ? "Y" : "N";
    let assignee = this.searchForm.get('assignee').value;
    formObj.assignee = (typeof assignee === 'string') ? assignee.toUpperCase() : '';
    formObj.effectiveFrom = effectiveFrom ? effectiveFrom : "";
    formObj.effectiveTo = effectiveTo ? effectiveTo : "";
    formObj.createDate = createDate ? createDate : "";

   formObj['includeOnHold'] = includeOnHoldStr;
   formObj['includeDeleted'] = includeDeletedStr;
   formObj['consolidatedAae'] = consolidatedAae;
   formObj['pageSize'] = pageSize;
   formObj['pageNumber'] = pageNumber;

   return formObj;
  }


  onChangePage(event: PageEvent) {
    const pageSize = +event.pageSize; // get the pageSize
    const currentPage = +event.pageIndex + 1; // get the current page

    console.log('pageSize '+pageSize + ' currentPage='+currentPage);
    //call the sevice here
    let formObj = this.getSearchForm(pageSize,currentPage);
    this.callSearch(formObj);
   }

   onConsolidatedAaeToggle($event: any) {
    this.isConsolidatedAae = $event.checked;
    this.doSearch();
    }

    initSearch() {
      this.route.queryParams.subscribe(params => {
       if('1' == params['search']){
        this.initialValues = this.searchForm.value;
        const storedFormValues = this.storageService.getAaeSearchData();
        if(storedFormValues ){
          const formValues = JSON.parse(storedFormValues);
          formValues['includeOnHold'] = formValues['includeOnHold'] == 'N'?false:true;
          formValues['includeDeleted'] = formValues['includeDeleted']=='N'?false:true;
          formValues['consolidatedAae'] = formValues['consolidatedAae']=='N'?false:true;
          this.searchForm.patchValue(formValues);
        }
       }
       this.doInitialSearch(); //default search onload with empty values
    });
    }

    assignQuote(quoteId: string){
      console.log('assigning quote '+ quoteId);
        this.searchService.updateAssignee('ASSIGN', quoteId)
          .subscribe( (dataResp: any) => {
            if(dataResp.errorMsg === null || dataResp.errorMsg === undefined || dataResp.errorMsg.trim() === ''){
              this.showSnack("Quote has been assigned to you.");
            } else {
              this.showSnackWarning(dataResp.errorMsg);
            }
            this.data.forEach(record => {
              if(record.quoteID==quoteId){
                record.assignedTo=dataResp.username;
              }
            });
          });
    }

    unassignQuote(quoteId: string){
      console.log('assigning quote '+ quoteId);
        this.searchService.updateAssignee('UNASSIGN', quoteId)
          .subscribe( (data: any) => {
            this.showSnack("Quote has been unassigned.");
            this.data.forEach(record => {
              if(record.quoteID==quoteId){
                record.assignedTo="UNASSIGNED";
              }
            });
          });
    }

    showSnack(msg: string){
      this.snackBar.open("Success!  "+msg,null,
      {duration: 3.5 * 1000, panelClass: ['success-snackbar']});
    }

    showSnackWarning(msg: string){
      this.snackBar.open(msg,null,
      {duration: 5.5 * 1000, panelClass: ['blue-snackbar']});
    }

    addNote(statusCode: string, projectId: string, quoteId: string){
      const dialogRef = this.dialog.open(AddNotesComponent, {
        width:'550px',
        maxHeight:'100vh',
        height:'200px',
        data: {statusCode, projectId, quoteId, list: this.data},
        position: {
          top: '',
          bottom: '',
          left: '',
          right: ''
        }
      });
    }

    canUnassign(username: any){
      return this.userAuth.isSuperUser(); //|| username == this.storageService.getUsername();
    }

    openErrorReport(): void {
          const dialogRef = this.dialog.open(ErrorReportComponent, {
            width:'550px',
            maxHeight:'100vh',
            data: {},
            position: {
              top: '10px'
            }
          });
          this.handleOnCloseFinal(dialogRef, 'You should receieve an email to download the report.');
        }; 
        
        handleOnCloseFinal(dialogRef:  MatDialogRef<any, any>, successMsg: string): void{
          dialogRef.afterClosed().subscribe(result => {
            if(result && result.success) {
              this.snackBar.open("Success!  "+successMsg,null,
              {duration: this.snackbarTimeout * 2000, panelClass: ['success-snackbar']});
              }   
          });
        }    

        viewQuoteNotes(quoteId: string){
          const dialogRef = this.dialog.open(ViewNotesComponent, {
            width:'1550px',
            height:'500px',
            data: { quoteId },
            position: {
              top: '',
              bottom: '',
              left: '',
              right: ''
            }
          }); 
        };
}



