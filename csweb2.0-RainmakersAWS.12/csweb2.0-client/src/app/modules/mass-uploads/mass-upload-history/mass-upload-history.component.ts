import {AfterViewInit, Component, ViewChild, OnChanges, SimpleChanges, Input, OnInit} from '@angular/core';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import { MuHistoryServiceService  } from '../../../core/services/mass-uploads/mu-history-service.service';
import { MuHistoryItem } from '../../../core/interfaces/massuploads-history-interface';
import { MuTemplateServiceService } from '../../../core/services/mass-uploads/mu-template-service.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import * as fileSaver from 'file-saver';

@Component({
  selector: 'app-mass-upload-history',
  templateUrl: './mass-upload-history.component.html',
  styleUrls: ['./mass-upload-history.component.css']
})
export class MassUploadHistoryComponent implements AfterViewInit, OnInit {
  displayedColumns: string[] = ['templateName', 'submitted', 'lastUpdated',
  'totalRecords','totalAdded', 'totalChanged', 'totalClosed', 'errorCount', 'filename'];
  dataSource: any;
  historyItems: Array<MuHistoryItem>;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private historyService: MuHistoryServiceService,
    private templateService: MuTemplateServiceService,
    private _snackBar: MatSnackBar) {

   this.loadHistory(false);
  }

  public async ngOnInit(): Promise<void> {
     this.initAddMassUploadListener();
  }

    public async initAddMassUploadListener(): Promise<void>{
      console.log('initing add listener inside history component');
      this.templateService.uploadTemplateCompleted.subscribe((data: boolean) => {
        let tmsg = (this.dataSource.data.length === 0) ? 'No history to load' : 'History has been changed, reloading...';
          this._snackBar.open(tmsg,' ', {
          duration: 6000,
          panelClass: ['blue-snackbar1']
          });
      });
  }

  public loadHistory(showToast: boolean): void{
    console.log('REloading history');
     this.historyService.getHistoryItems()
      .subscribe((data: Array<MuHistoryItem>) => {
        this.historyItems = data;
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;

      });
  }

  downloadErrorFile(historyItem : MuHistoryItem): any {
    console.log('fileName: '+historyItem.submittedFile + '  logId: '+ historyItem.logId);
    this.templateService
      .downloadErrorFile(historyItem.logId)
      .subscribe(response => {
        const name = this.getFilename(response);
        const blob: any = new Blob([response.body], { type: 'text/json; charset=utf-8' });
        const url = window.URL.createObjectURL(blob);
        fileSaver.saveAs(blob, name);
      },
      err => {
        this._snackBar.open('Failed to find error file for download.',' ', {
          duration: 4000,
          panelClass: ['red-snackbar']
          });
      }),
      () => console.info('File downloaded successfully');
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

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngAfterViewInit(): void{

  }
}
