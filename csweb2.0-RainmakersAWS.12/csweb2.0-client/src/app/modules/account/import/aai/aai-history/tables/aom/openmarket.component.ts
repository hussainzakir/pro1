import {Component, ViewChild, OnChanges, SimpleChanges, Input, OnInit, ChangeDetectorRef, Output, EventEmitter} from '@angular/core';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSnackBar} from '@angular/material/snack-bar';
import * as fileSaver from 'file-saver';
import { ImportService } from '@app/core/services/account/import.service';
import { Subscription } from 'rxjs';
import { DialogCommunicationService } from '@app/dialog-communication.service';
import { MatDialog } from '@angular/material';
import { ReviewmodalComponent } from './reviewmodal/reviewmodal.component';
import { ChangeuploadComponent } from './changeupload/changeupload.component';
import { MatSlideToggle, MatSlideToggleChange } from '@angular/material/slide-toggle';

export interface HistoryItem {
  changeTicketNum: string;
  type: string;
  division: string;
  seq: string;
  excelName: string;
  submittedDate: string;
  lastModified: string;
  addressCount: string;
  errorsCount: string;
  changeTicket: string;
  status: string;
  projectName: string;

  }

  @Component({
    selector: 'app-openmarket',
    templateUrl: './openmarket.component.html',
    styleUrls: ['./openmarket.component.css']
  })
export class OpenmarketComponent implements OnInit {
  displayedColumns: string[] = ['changeTicketNum','type','division','excelName','submittedDate'
  ,'lastModified','addressCount','errorsCount','status'];
  displayedActiveColumns: string[] = ['userId','changeTicketNum','type','division','excelName','submittedDate'
    ,'lastModified','addressCount','errorsCount','status'];
  dataSource: any;
  historyItems: Array<HistoryItem>;
  isLoading: boolean = false; 
  isToggleActive: boolean = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  @ViewChild(MatSort) sort: MatSort;

  @ViewChild('toggle') toggle: MatSlideToggle;

  @Output() reuploadEvent = new EventEmitter();
  @Input() currentFileTypeName:any;
  updatedFileTypeName: any;

  private dialogClosedSubscription: Subscription;
  constructor(
    private dialog: MatDialog,
    private _snackBar: MatSnackBar,
    public importService: ImportService,
    private cdr: ChangeDetectorRef,
    private dialogCommService: DialogCommunicationService) {


  }

  ngOnInit(): void {
    console.log("OPEN_MARKET");
    this.loadHistory(this.currentFileTypeName);
    this.dialogClosedSubscription = this.dialogCommService.dialogClosed$.subscribe(() => {
      this.loadHistory(this.currentFileTypeName);
    });
  }
    
  ngOnDestroy(): void {
    if (this.dialogClosedSubscription) {
      this.dialogClosedSubscription.unsubscribe();
    }
  }

  public loadHistory(updatedFileTypeName: string): void {
    if (this.isToggleActive) {
      this.loadAllHistory(updatedFileTypeName + '_ALL');
      return; 
    }
    this.importService.getHistory(updatedFileTypeName).subscribe(
      (data: any) => {
        if (data === null || data === undefined || typeof(data) === "undefined") {
          data = [];
        }
        const results = data.filter((item, index) => {
          return item.type == this.updatedFileTypeName;
        });
        this.historyItems = results;
        this.dataSource = new MatTableDataSource(results);
        this.historyItems = data;
        this.dataSource = new MatTableDataSource(data);
        this.cdr.detectChanges();
        this.dataSource.sort = this.sort;
        this.paginator.pageSize = 10;
        this.dataSource.paginator = this.paginator;
      }
    );
  }

  public loadAllHistory(updatedFileTypeName: string): void{
    this.importService.getAllHistory(updatedFileTypeName).subscribe(
      (data: any) => {
        if (data === null || data === undefined || typeof(data) === "undefined") {
          data = [];
        }
        const results = data.filter((item, index) => {
          return item.type == this.updatedFileTypeName;
       });
       this.historyItems = results;
        this.dataSource = new MatTableDataSource(results);
        this.historyItems = data;
        this.dataSource = new MatTableDataSource(data);
        this.cdr.detectChanges();
        this.dataSource.sort = this.sort;
        this.paginator.pageSize = 10;
        this.dataSource.paginator = this.paginator;
      }
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
    this.dialog.open(ReviewmodalComponent, {
      data: {
        projectId: element.projectid,
        uploadType: element.type
      }
    })
  }

  changeUpload(element: any){
    this.dialog.open(ChangeuploadComponent, {
      data: {
        projectId: element.projectid,
        uploadType: element.type
      }
    })
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

  onToggleChange(event: MatSlideToggleChange): void {
    this.isLoading = true; 
    this.isToggleActive = event.checked;
    if (event.checked) {
      this.loadAllHistory(this.currentFileTypeName + '_ALL');
    } else {
      this.loadHistory(this.currentFileTypeName); 
    }
    setTimeout(() => {
      this.isLoading = false; 
    }, 2000); 
  }
}