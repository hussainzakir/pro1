import {Component, ViewChild, OnChanges, SimpleChanges, Input, OnInit, ChangeDetectorRef, Output, EventEmitter, Inject, HostListener} from '@angular/core';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSnackBar} from '@angular/material/snack-bar';
import * as fileSaver from 'file-saver';
import { ImportService } from '@app/core/services/account/import.service';
import { Subscription } from 'rxjs';
import { DialogCommunicationService } from '@app/dialog-communication.service';
import { MatDialog } from '@angular/material';
import { MatSlideToggle, MatSlideToggleChange } from '@angular/material/slide-toggle';
import { APP_BASE_HREF } from '@angular/common';
import { ActivatedRoute, NavigationStart, Router } from '@angular/router';
import { LocalStorageService } from '@app/core/services/LocalStorage.service';
import { filter, skip } from 'rxjs/operators';

export interface HistoryItem {
  changeTicket: string;
  projectid: string;
  billToCounts: string;
  projectname: string;
  submitteddate: string;
  numofaccounts: string;
  numoferrors: string;
  status: string;
  projectName: string;
  type: string;
  }

@Component({
  selector: 'app-aob3-projects',
  templateUrl: './aob3-projects.component.html',
  styleUrls: ['./aob3-projects.component.css']
})

export class Aob3ProjectsComponent implements OnInit, OnChanges {

  displayedColumns: string[] = ['projectId','projectName','submittedDate','numOfAccounts'
    ,'numOfErrors','status','actions'];
  displayedActiveColumns: string[] = ['userId','projectId','projectName','submittedDate','numOfAccounts'
      ,'numOfErrors','status','actions'];
  dataSource: any;
  historyItems: Array<HistoryItem>;
  isLoading: boolean = false; 
  isToggleActive: boolean = false;
  private readonly TOGGLE_KEY = 'aob3-active-toggle';
  private readonly FILETYPE_KEY = 'aob3-last-fileType';

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild('toggle') toggle: MatSlideToggle;
  @Output() reuploadEvent = new EventEmitter();
  @Input() currentFileTypeName:any;


  private dialogClosedSubscription: Subscription;
  constructor(
    private dialog: MatDialog,
    private router: Router,
    private _snackBar: MatSnackBar,
    public importService: ImportService,
    private cdr: ChangeDetectorRef,
    private dialogCommService: DialogCommunicationService,
    private route: ActivatedRoute,
    private storage: LocalStorageService,
    @Inject(APP_BASE_HREF) private baseHref: string) {
}

  ngOnInit() {
    const savedToggle = this.storage.get(this.TOGGLE_KEY);
    this.isToggleActive = savedToggle != null ? savedToggle : false;

    setTimeout(() => {
      this.toggle.checked = this.isToggleActive;
      this.loadHistory(this.currentFileTypeName);
    });
  }

   ngOnChanges(changes: SimpleChanges) {
    if (changes.currentFileTypeName && !changes.currentFileTypeName.firstChange) {
      this.loadHistory(changes.currentFileTypeName.currentValue);
    }
  }
    
   ngOnDestroy(): void {
    if (this.dialogClosedSubscription) {
      this.dialogClosedSubscription.unsubscribe();
    }
  }  

  public loadHistory(fileType: string): void {
    this.storage.set(this.FILETYPE_KEY, fileType);

    if (this.isToggleActive) {
      return this.loadAllHistory(fileType + '_ALL');
    }

    this.importService.getProjectHistory(fileType)
      .subscribe((data: HistoryItem[] = []) => {
        const filtered = data.filter(item => item.type === fileType);
        this.historyItems = filtered;
        this.dataSource = new MatTableDataSource(filtered);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
        this.cdr.detectChanges();
         this.dataSource.sort = this.sort;
        this.paginator.pageSize = 10;
        this.dataSource.paginator = this.paginator;
       },
      err => console.error(`Error retrieving user history for uploadType: ${fileType}`, err)
    );
  }

  public loadAllHistory(fileTypeAll: string): void {
  this.importService.getAllProjectHistory(fileTypeAll)
    .subscribe(
      (data: HistoryItem[] = []) => {
        const filtered = data.filter(item => item.type === fileTypeAll.replace('_ALL',''));
        this.historyItems = filtered;
        this.dataSource = new MatTableDataSource(filtered);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
        this.cdr.detectChanges();
        this.dataSource.sort = this.sort;
        this.paginator.pageSize = 10;
        this.dataSource.paginator = this.paginator;
      },
      err => console.error(`Error retrieving all history for uploadType: ${fileTypeAll}`, err)
    );
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  doDownloadErrors(elem){
      this.importService.getOpenMarketErrorFile(elem)
      .subscribe(response => {
        const name = this.getFilename(response);
        const blob: any = new Blob([response.body], { type: 'text/json; charset=utf-8' });
        const url = window.URL.createObjectURL(blob);
        fileSaver.saveAs(blob, name);
      });
  }

  reupload(elem: any){
    this.reuploadEvent.emit(elem);
  }

  reviewModal(element: any){
    // this.dialog.open(ReviewmodalComponent, {
    //   data: {
    //     projectId: element.projectid,
    //     uploadType: element.type
    //   }
    // })
  }

  changeUpload(element: any){
    // this.dialog.open(ChangeuploadComponent, {
    //   data: {
    //     projectId: element.projectid,
    //     uploadType: element.type
    //   }
    // })
  }

  getFilename(response): string{
    let fileName = 'file';
    const contentDisposition = response.headers.get('Content-Disposition');
    console.log( response.headers);
    if (contentDisposition) {
      const fileNameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
      const matches = fileNameRegex.exec(contentDisposition);
      if (matches != null && matches[1]) {
        fileName = matches[1].replace(/['"]/g, '');
      }
    }
    return fileName;
  }

  onToggleChange(ev: MatSlideToggleChange) {
    this.isLoading = true; 
    this.isToggleActive = ev.checked;
    this.storage.set(this.TOGGLE_KEY, this.isToggleActive);
    this.loadHistory(this.currentFileTypeName);
    setTimeout(() => {
      this.isLoading = false; 
    }, 2000); 
  }
  
 }
