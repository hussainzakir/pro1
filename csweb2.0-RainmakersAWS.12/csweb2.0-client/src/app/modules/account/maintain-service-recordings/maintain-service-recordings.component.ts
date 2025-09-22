import { AfterViewInit, Component, Inject, OnInit, ViewChild, ViewChildren } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { WindowbuttonsDirective } from '@app/windowbuttons.directive';
import { ServiceRecordingService } from 'src/app/core/services/cs-search/service-recording.service';


@Component({
  selector: 'app-maintain-service-recordings',
  templateUrl: './maintain-service-recordings.component.html',
  styleUrls: ['./maintain-service-recordings.component.scss'],
})
export class MaintainServiceRecordingsComponent implements OnInit {

  public company: any;
  public account: any;
  public site: any;
  public serviceRecording: any;
  public date: any;
  public recordingDetail: any;
  public hideMaximumButtonGroup: boolean = false;
  disableSelect = new FormControl(false);

  maintainForm = new FormGroup({
    recordingNumber: new FormControl(''),
    subject: new FormControl(''),
    status: new FormControl(''),
    priority: new FormControl(''),
    recordingType: new FormControl(''),
    recordingDescription: new FormControl(''),
    assignTo: new FormControl(''),
    escalation: new FormControl(''),
    description: new FormControl(''),
    resolution: new FormControl(''),
    employeeFirstName: new FormControl(''),
    employeeLastName: new FormControl(''),
    svcToBeCompleted: new FormControl('')
  });

  @ViewChildren(WindowbuttonsDirective) windowsbuttondirective;
  constructor (
    public recordingService: ServiceRecordingService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log(data);
      console.log('data above');
      this.company = data.company;
      this.account = data.account;
      this.site = data.site;
      this.serviceRecording = data.serviceRecording;
      this.date = data.date;
      this.recordingDetail = data.recordingDetail;
      this.hideMaximumButtonGroup = data.hideMaximumButtonGroup != undefined ? data.hideMaximumButtonGroup:true;
    }

   ngOnInit() {
    this.recordingService.getMaintainServiceRecording(this.company, this.account, this.site, this.serviceRecording, this.date)
        .subscribe((data: any) =>{
          this.recordingDetail = data;
        });

  this.maintainForm
  .patchValue({
    recordingNumber: this.recordingDetail.serviceRecordNumber,
    subject: this.recordingDetail.reportedByName,
    status: this.recordingDetail.status,
    priority: this.recordingDetail.recordingPriority,
    recordingType: this.recordingDetail.transactionCode,
    recordingDescription: this.recordingDetail.transactionDescription,
    assignTo: this.recordingDetail.assignToUser,
    escalation: this.recordingDetail.satelliteLocation,
    description: this.recordingDetail.serviceRecordNote,
    resolution: this.recordingDetail.serviceResolutionNote,
    employeeFirstName: this.recordingDetail.firstName,
    employeeLastName: this.recordingDetail.lastName,
    svcToBeCompleted: this.recordingDetail.scheduleCompletionDate,
    });

}
  

  ngAfterViewInit(): void {

  }

  maximize(){
    this.windowsbuttondirective.forEach(directive => directive.maximize());
  }
  restore(){
    this.windowsbuttondirective.forEach(directive => directive.restore());
  }
}
