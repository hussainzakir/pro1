import { AfterViewInit, Component, Inject, OnInit, ViewChild, ViewChildren } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import {MatPaginatorModule} from '@angular/material/paginator';
import { MatSort} from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ServiceRecordingService } from 'src/app/core/services/cs-search/service-recording.service';
import { MaintainServiceRecordingsComponent } from '../maintain-service-recordings/maintain-service-recordings.component';
import { ServiceRecording } from '../service-recording-model';
import { WindowbuttonsDirective } from '@app/windowbuttons.directive';

@Component({
  selector: 'app-view-all-service-recordings',
  templateUrl: './view-all-service-recordings.component.html',
  styleUrls: ['./view-all-service-recordings.component.scss']
})
export class ViewAllServiceRecordingsComponent implements OnInit, AfterViewInit {

  public company: any;
  public account: any;
  public site: any;
  public date: any;
  public allRecordings: ServiceRecording[];
  public dataSource: MatTableDataSource<any>;
  public isNationalAccount: boolean = false;
  public displayedColumns: string[] = ['serviceRecordingNumber', 'transactionDescription','scheduleCompletionDate','status'];
 
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChildren(WindowbuttonsDirective) windowsbuttondirective;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private recordingService: ServiceRecordingService,
  public dialog: MatDialog) {
    this.company = data.company;
    this.account = data.account;
    this.site = data.site; 
    this.date = data.date; 
   }

   ngOnInit() {
    this.recordingService.getServieRecordingsAll(this.company, this.account, this.site)
        .subscribe((data: any) =>{
          this.allRecordings = data;
          this.dataSource = new MatTableDataSource(this.allRecordings);
          this.dataSource.sort = this.sort;
          this.dataSource.paginator = this.paginator;       
        });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sortingDataAccessor = (item, property) => {
      switch (property) {
         case 'scheduleCompletionDate': return new Date(item.scheduleCompletionDate);
         default: return item[property];
      }
    };
    this.dataSource.sort = this.sort;
  }

  maximize(){
    this.windowsbuttondirective.forEach(directive => directive.maximize());
  }
  restore(){
    this.windowsbuttondirective.forEach(directive => directive.restore());
  }
  
  openMaintainDialog(serviceRecording: any, completionDate: any): void{
    console.log('Recordings to be shown');
    console.log("completion date to popup: "+ completionDate);
    let dateParts = completionDate.split("/");
    let formatedDate = "20"+dateParts[2]+dateParts[0]+dateParts[1];
    const dialogRef = this.dialog.open(MaintainServiceRecordingsComponent, {
      width: '550px',
      data: {company: this.company, account: this.account, site: this.site, serviceRecording, date: formatedDate, hideMaximumButtonGroup: false},
      position: {
        top: '10px'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed',result);
      }
    );
}}
