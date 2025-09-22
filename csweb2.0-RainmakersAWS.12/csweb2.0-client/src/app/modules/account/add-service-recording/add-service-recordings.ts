import { EmployeeService } from './../../../core/services/employee/employee.service';
import { ServiceRecordingsService } from 'src/app/core/services/service-recordings/service-recordings.service';
import { Component, OnInit, Inject } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import {FormControl, FormGroup} from '@angular/forms';
import {DatePipe} from '@angular/common';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-service-recordings',
  templateUrl: './add-service-recordings.html',
  styleUrls: ['./add-service-recordings.css'],
  providers: [ DatePipe ]
})
export class AddServiceRecordingComponent implements OnInit {

  readonly routeToRecordingTypes: string[] = ['NACP','NADL','NAEI','NAIN','NARM','NACA'];

  filteredEmployees: CswebModel.Employee[];
  textareaMaxLength = 480;
  company: string;
  account: string;
  site: string;
  recordingTypes: CswebModel.TableCode [];
  escalationTypes: CswebModel.TableCode [];
  employees: CswebModel.Employee[];
  showRouteTo: boolean = false;
  isNationalAccount: boolean = false;
  durationInSeconds: number = 3;

  recordingForm = new FormGroup({
    employee: new FormControl(''),
    department: new FormControl(''),
    description: new FormControl(''),
    resolution: new FormControl(''),
    recordingType: new FormControl(''),
    subject: new FormControl(''),
    priority: new FormControl(''),
    assignTo: new FormControl(''),
    routeTo: new FormControl(''),
    escalation: new FormControl(''),
    completedDate: new FormControl(''),
    status: new FormControl('')
  });


  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
    private serviceRecordingsService:ServiceRecordingsService,
    private employeeService: EmployeeService,
    private dialogRef: MatDialogRef<AddServiceRecordingComponent>,
    private datePipe: DatePipe,
    private _snackBar: MatSnackBar) {
      this.company = data.company;
      this.isNationalAccount = data.isNationalAccount;
      this.account = data.account;
      this.site = data.site;

  }

  ngOnInit() {
    this.loadDropDownValues();

  }



  loadDropDownValues(){
    //fetch recording types
    this.serviceRecordingsService.getTableCodes(this.company, 'DSC').
      subscribe((data: CswebModel.TableCode[]) =>{
      this.recordingTypes = data;
    });

    this.serviceRecordingsService.getTableCodes(this.company, 'ESC').
        subscribe((data2: CswebModel.TableCode[]) =>{
        this.escalationTypes = data2;
      });

    this.employeeService.getActiveEmployees(this.company).
      subscribe((data: CswebModel.Employee[]) =>{
      this.employees = data;
      this.filteredEmployees = this.employees.slice();
    });

  }

  departmentChanged(event:any) {
   //this.department = event.value;
}


  recordingTypeChanged(event:any){
    //this.recordingType = event.value;

    this.showRouteTo = this.routeToRecordingTypes.includes(event.value);
  }

  save() {
    const body = this.recordingForm.value;
    body.completedDate = this.datePipe.transform(body.completedDate, 'yyyy-MM-dd');
    body.nationalAccount = this.isNationalAccount;
    body.account = this.account;
    body.site = this.site;
    body.company = this.company;

    this.serviceRecordingsService.saveRecording(this.recordingForm)
      .subscribe(response =>{
        if(response.status == 200){
          this._snackBar.open("Success!  Service Recording created.",null,
          {duration: this.durationInSeconds * 1000,panelClass: ['success-snackbar']});
          //only if the save succeeds
          this.dialogRef.close(this.recordingForm.value);
        }
        },
        error => {
          console.log("Error saving!");
          this._snackBar.open("Error:  Failed to create service recording - " + error.body.msg,null,
          {duration: this.durationInSeconds * 1000, panelClass: ['error-snackbar']});
        }
        );


}

}

