import { AccountImportComponent } from './../account-import.component';
import {AfterViewInit, Component, ViewChild, OnChanges, SimpleChanges, Input, OnInit, ChangeDetectorRef, Output, EventEmitter} from '@angular/core';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSnackBar} from '@angular/material/snack-bar';
import * as fileSaver from 'file-saver';
import { ImportService } from '@app/core/services/account/import.service';
import { Subscription } from 'rxjs';
import { DialogCommunicationService } from '@app/dialog-communication.service';

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
  status: string;

  }

@Component({
  selector: 'app-account-import-history',
  templateUrl: './account-import-history.component.html',
  styleUrls: ['./account-import-history.component.css']
})
export class AccountImportHistoryComponent implements OnInit, OnChanges {
  displayedColumns: string[] = ['changeTicketNum','type','division','excelName','submittedDate'
  ,'lastModified','addressCount','errorsCount','status'];
  dataSource: any;
  historyItems: Array<HistoryItem>;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  @ViewChild(MatSort) sort: MatSort;

  @Output() reuploadEvent = new EventEmitter();
  @Input() currentFileTypeName:any;
  updatedFileTypeName: any;

  private dialogClosedSubscription: Subscription;
  constructor(
    private _snackBar: MatSnackBar,
    public importService: ImportService,
    private cdr: ChangeDetectorRef,
    private dialogCommService: DialogCommunicationService) {


  }

  ngOnChanges(changes: SimpleChanges) {
    if(changes['currentFileTypeName']) {
      this.updatedFileTypeName = changes['currentFileTypeName'].currentValue;
      if(this.updatedFileTypeName == 'AOB'){
        this.displayedColumns = ['changeTicketNum','type','excelName','submittedDate'
          ,'lastModified','addressCount','errorsCount','status'];
      } else {
                  this.displayedColumns = ['changeTicketNum','type','division','excelName','submittedDate'
            ,'lastModified','addressCount','errorsCount','status'];
      }
      this.loadHistory(this.currentFileTypeName);
    }
  }

  ngOnInit(): void {
    this.dialogClosedSubscription = this.dialogCommService.dialogClosed$.subscribe(() => {
      this.loadHistory(this.currentFileTypeName);
    });
  }
    
  ngOnDestroy(): void {
    if (this.dialogClosedSubscription) {
      this.dialogClosedSubscription.unsubscribe();
    }
  }

  public loadHistory(updatedFileTypeName: string): void{
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
      });

  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  doDownloadErrors(elem){
    if(this.currentFileTypeName == "AOB"){
      this.importService.getAobErrorFile(elem)
      .subscribe(response => {
        const name = this.getFilename(response);
        const blob: any = new Blob([response.body], { type: 'text/json; charset=utf-8' });
        const url = window.URL.createObjectURL(blob);
        fileSaver.saveAs(blob, name);
      });
      return;
    }
    this.importService.getErrorFile(elem)
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

}
