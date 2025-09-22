import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';

@Component({
  selector: 'app-error-report',
  templateUrl: './error-report.component.html',
  styleUrls: ['./error-report.component.css']
})
export class ErrorReportComponent implements OnInit {

  public errorMsg: string;
  public loading : boolean;
  private datePipe: DatePipe;
  reportForm: FormGroup;
  includeAll: boolean = false;
  isExceptionForm = false; 

  updateForm = new FormGroup({
    reportToDate: new FormControl(''),
    reportFromDate: new FormControl(''),
    includeAll: new FormControl(''),
  });

  exceptionForm = new FormGroup({
    reportToDate: new FormControl(''),
    reportFromDate: new FormControl(''),
    includeAll: new FormControl(''),
  });

  constructor(private fb: FormBuilder, private aaeEntryService: AaeEntryService, 
    private dialogRef: MatDialogRef<ErrorReportComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log(data);
      this.datePipe = new DatePipe('en-US'); 
    }

    ngOnInit(): void {
      this.updateForm = this.fb.group({
        reportToDate: ['', [Validators.required, this.noFutureDateValidator()]],
        reportFromDate: ['', [Validators.required, this.noFutureDateValidator()]],
        includeAll: false
      });

      this.exceptionForm = this.fb.group({
        reportToDate: ['', [Validators.required, this.noFutureDateValidator()]],
        reportFromDate: ['', [Validators.required, this.noFutureDateValidator()]]
      });
    }

    onToggleChange(): void {
      this.errorMsg = null;
    }

    noFutureDateValidator(): ValidatorFn {
      return (control: AbstractControl): { [key: string]: any } | null => {
        const selectedDate = new Date(control.value);
        const today = new Date();
        today.setHours(0, 0, 0, 0);
        return selectedDate > today ? { 'futureDate': { value: control.value } } : null;
      };
    }

    onSubmit(): void {
      if (this.isExceptionForm) {
        this.saveException();
      } else {
        this.save();
      }
    }

  save(){

    this.errorMsg = null;
    this.loading = true;

    const formValue = { ...this.updateForm.value };

    const reportToDate = formValue.reportToDate.toString();
    const reportFromDate = formValue.reportFromDate.toString();
    
    if (!isNaN(new Date(reportToDate).getTime())) {
      formValue.reportToDate = this.datePipe.transform(new Date(reportToDate), 'yyyy-MM-dd');
    }

    if (!isNaN(new Date(reportFromDate).getTime())) {
      formValue.reportFromDate = this.datePipe.transform(new Date(reportFromDate), 'yyyy-MM-dd');
    }

    let includeAllStr = this.updateForm.controls.includeAll.value ? "Y" : "N";
    formValue['includeAll'] = includeAllStr;

    this.aaeEntryService.getReport(formValue)
    .subscribe(response =>{
      this.loading = false;
      if(response.status == 200){
        this.dialogRef.close({success:true});
      }
      },
      error => {
        this.loading = false;
        const reader = new FileReader();
        reader.onloadend = () => {
          const errorObj = JSON.parse(reader.result.toString());
          this.errorMsg = errorObj.errorMsg;
          console.log('errorMsg: ', this.errorMsg);
        };
        reader.readAsText(error.error);
        console.log('error: ', error);

        console.log('errorMsg: ' + this.errorMsg);
      }
      )
  }

  saveException(){

    this.errorMsg = null;
    this.loading = true;

    const formValue = { ...this.exceptionForm.value };

    const reportToDate = formValue.reportToDate.toString();
    const reportFromDate = formValue.reportFromDate.toString();
    
    if (!isNaN(new Date(reportToDate).getTime())) {
      formValue.reportToDate = this.datePipe.transform(new Date(reportToDate), 'yyyy-MM-dd');
    }

    if (!isNaN(new Date(reportFromDate).getTime())) {
      formValue.reportFromDate = this.datePipe.transform(new Date(reportFromDate), 'yyyy-MM-dd');
    }

    this.aaeEntryService.getExceptionReport(formValue)
    .subscribe(response =>{
      this.loading = false;
      if(response.status == 200){
        this.dialogRef.close({success:true});
      }
      },
      error => {
        this.loading = false;
        const reader = new FileReader();
        reader.onloadend = () => {
          const errorObj = JSON.parse(reader.result.toString());
          this.errorMsg = errorObj.errorMsg;
          console.log('errorMsg: ', this.errorMsg);
        };
        reader.readAsText(error.error);
        console.log('error: ', error);

        console.log('errorMsg: ' + this.errorMsg);
      }
      )
  }
}
