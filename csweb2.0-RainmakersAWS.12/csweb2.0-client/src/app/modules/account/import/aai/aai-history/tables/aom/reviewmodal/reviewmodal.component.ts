import { DatePipe } from '@angular/common';
import { Component, Inject, OnInit, AfterViewInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatSnackBar } from '@angular/material';
import { ImportService } from '@app/core/services/account/import.service';
import { faUpload } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-reviewmodal',
  templateUrl: './reviewmodal.component.html',
  styleUrls: ['./reviewmodal.component.css'],
  providers: [DatePipe]
})
export class ReviewmodalComponent implements OnInit, AfterViewInit {
  
    private snackbarTimeout = 4;
    public errorMsg: string;
    public loading : boolean;
    reportForm: FormGroup;
    private datePipe: DatePipe;
    includeAll: boolean = false;
    inputTime : any;
    updateForm = new FormGroup({
     scheduleDate: new FormControl(''),
      scheduleTime: new FormControl(''),
    uploadType: new FormControl(''),
    projectId: new FormControl('')
    });

    constructor(  private fb: FormBuilder,
      private dialogRef: MatDialogRef<ReviewmodalComponent>,
      public importService: ImportService,
      private snackBar: MatSnackBar,
      @Inject(MAT_DIALOG_DATA) public data: any) { 
        this.datePipe = new DatePipe('en-US')
      }

      ngAfterViewInit(): void {
        this.inputTime = document.getElementById('timeBox2');
         let classSelected = this.inputTime.querySelector('.mat-form-field-wrapper');
         classSelected.classList.remove('mat-form-field-wrapper');
      }

    ngOnInit(): void {
      this.updateForm = this.fb.group({
        scheduleDate: ['', [Validators.required]],
        scheduleTime: ['', [Validators.required]],
        projectId: [''],
        uploadType: ['']
      });
}

    save(){
      const selectedDay = this.datePipe.transform(this.updateForm.get("scheduleDate").value, 'yyyyMMdd');
      const selectedTime = this.updateForm.get("scheduleTime").value+"00";
      const formatTime = selectedTime.replace(":","");
      const projectId = this.data.projectId;
      const uploadType = this.data.uploadType;
   
      this.importService.scheduleUpload({
        projectId: projectId,
        scheduleDate: selectedDay,
        scheduleTime: formatTime,
        uploadType: uploadType
      })
    .subscribe(response =>{
      this.loading = false;
      if(response.status == 200){
        this.changeUploadMsg('Upload to InfoPro has been Scheduled');
        this.dialogRef.close();
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
      }
      )
  }

  changeUploadMsg(successMsg: string): void{
    this.snackBar.open("Success!  "+successMsg,null,
    {duration: this.snackbarTimeout * 1000, panelClass: ['success-snackbar']});
};
      
}

