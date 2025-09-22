import { DatePipe } from '@angular/common';
import { AfterViewInit, Component, Inject, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatSnackBar } from '@angular/material';
import { ImportService } from '@app/core/services/account/import.service';
import { faUpload } from '@fortawesome/free-solid-svg-icons';
import { scheduled } from 'rxjs';

@Component({
  selector: 'app-changeupload',
  templateUrl: './changeupload.component.html',
  styleUrls: ['./changeupload.component.css']
})
export class ChangeuploadComponent implements OnInit, AfterViewInit {


  public errorMsg: string;
    public loading : boolean;
    reportForm: FormGroup;
    private datePipe: DatePipe;
    includeAll: boolean = false;
    inputTime: any;
    private snackbarTimeout = 4;

  
    updateForm = new FormGroup({
     scheduleDate: new FormControl(''),
      scheduleTime: new FormControl(''),
    uploadType: new FormControl(''),
    projectId: new FormControl('')
    });

    constructor(  private fb: FormBuilder,
      private dialogRef: MatDialogRef<ChangeuploadComponent>,
       private snackBar: MatSnackBar,
      public importService: ImportService,
      @Inject(MAT_DIALOG_DATA) public data: any) { 
        this.datePipe = new DatePipe('en-US')
      }

      ngAfterViewInit(): void {
        this.inputTime = document.getElementById('timeBox');
        if (this.inputTime) {
          let classSelected = this.inputTime.querySelector('.mat-form-field-wrapper');
          if (classSelected) {
            classSelected.classList.remove('mat-form-field-wrapper');
          }
        }
      
        let params = {
          projectId: this.data.projectId,
          action: "GET",
          scheduleDate: "",
          scheduleTime: "",
          uploadType: this.data.uploadType
        };
      
        this.importService.changeScheduleUpload(params).subscribe(
          (response: Blob) => {
            const reader = new FileReader();
            reader.onloadend = () => {
              try {
                const jsonResponse = JSON.parse(reader.result as string);
                console.log('Parsed Response:', jsonResponse);
      
                if (jsonResponse.returnStatus !== '00000') {
                  this.errorMsg = jsonResponse.returnErrorMsg;
                  console.error('Error Message:', this.errorMsg);
                  return; 
                }
      
                let formattedDate = this.formatDate(jsonResponse.scheduleDate.trim());
                const newDate = new Date(formattedDate);
                let formattedTime = this.formatTime(jsonResponse.scheduleTime.trim());
      
                this.updateForm = this.fb.group({
                  scheduleDate: [newDate, [Validators.required]],
                  scheduleTime: [formattedTime, [Validators.required]]
                });
              } catch (e) {
                console.error('Error parsing response:', e);
                this.errorMsg = 'Failed to load data.';
              }
            };
            reader.readAsText(response);
          },
          (error: any) => {
            this.loading = false;
      
            // Handle HTTP errors
            const reader = new FileReader();
            reader.onloadend = () => {
              try {
                const errorObj = JSON.parse(reader.result.toString());
                this.errorMsg = errorObj.returnErrorMsg || 'An unknown error occurred.';
                console.log('Error Message:', this.errorMsg);
              } catch (e) {
                console.error('Error parsing error response:', e);
                this.errorMsg = 'An unknown error occurred.';
              }
            };
            reader.readAsText(error.error);
            console.log('HTTP Error:', error);
          }
        );
      }

ngOnInit(): void {
  this.updateForm = this.fb.group({
    scheduleDate: ['', [Validators.required]],
    scheduleTime: ['', [Validators.required]],
    projectId: [''],
    uploadType: ['']
  });

}

formatDate(input: string): string {
  // Ensure the input string is in the correct format (yyyyMMdd)
  if (input.length === 8) {
    const year = input.substring(0, 4);
    const month = input.substring(4, 6);
    const day = input.substring(6, 8);
    return `${month}/${day}/${year}`;
  }
}

formatTime(input: string): string {
  // Ensure the input string is in the correct format (HHmmss)
  if (input.length === 6) {
    const hours = input.substring(0, 2);
    const minutes = input.substring(2, 4);
    return `${hours}:${minutes}`;
  }}

  save() {
    const selectedDay = this.datePipe.transform(this.updateForm.get("scheduleDate").value, 'yyyyMMdd');
    const selectedTime = this.updateForm.get("scheduleTime").value + "00";
    const formatTime = selectedTime.replace(":", "");
    const projectId = this.data.projectId;
    const uploadType = this.data.uploadType;
    const action = "CHANGE";
  
    this.importService.changeScheduleUpload({
      projectId: projectId,
      scheduleDate: selectedDay,
      scheduleTime: formatTime,
      uploadType: uploadType,
      action: action
    }).subscribe(
      (response: Blob) => {
        const reader = new FileReader();
        reader.onloadend = () => {
          try {
            const jsonResponse = JSON.parse(reader.result as string);
            console.log('Parsed Response:', jsonResponse);
  
            if (jsonResponse.returnStatus === '00000') {
              console.log('dialogRef closed');
              this.changeUploadMsg('Upload to InfoPro has been Updated');
              this.dialogRef.close();
            } else {
              this.errorMsg = jsonResponse.returnErrorMsg;
              console.error('Error Message:', this.errorMsg);
            }
          } catch (e) {
            console.error('Error parsing response:', e);
            this.errorMsg = 'Failed to process the response.';
          }
        };
        reader.readAsText(response);
      },
      (error: any) => {
        this.loading = false;
  
        // Handle HTTP errors
        const reader = new FileReader();
        reader.onloadend = () => {
          try {
            const errorObj = JSON.parse(reader.result.toString());
            this.errorMsg = errorObj.changeUploadInfoMessage || 'An unknown error occurred.';
            console.log('Error Message:', this.errorMsg);
          } catch (e) {
            console.error('Error parsing error response:', e);
            this.errorMsg = 'An unknown error occurred.';
          }
        };
        reader.readAsText(error.error);
        console.log('HTTP Error:', error);
      }
    );
  }

  changeUploadMsg(successMsg: string): void{
        this.snackBar.open("Success!  "+successMsg,null,
        {duration: this.snackbarTimeout * 1000, panelClass: ['success-snackbar']});
    };
  
      
  cancel(){
    const formatTime = "";
    const projectId = this.data.projectId;
    const uploadType = this.data.uploadType;
    const action = "CANCEL";
    const selectedDay = "";

    this.importService.changeScheduleUpload({
      projectId: projectId,
      scheduleDate: selectedDay,
      scheduleTime: formatTime,
      uploadType: uploadType,
      action: action
    }).subscribe(
    (response: Blob) => {
      const reader = new FileReader();
      reader.onloadend = () => {
        try {
          const jsonResponse = JSON.parse(reader.result as string);
          console.log('Parsed Response:', jsonResponse);

          if (jsonResponse.returnStatus === '00000') {
            console.log('dialogRef closed');
            this.changeUploadMsg('Upload to InfoPro has been Cancelled');
            this.dialogRef.close();
          } else {
            this.errorMsg = jsonResponse.returnErrorMsg;
            console.error('Error Message:', this.errorMsg);
          }
        } catch (e) {
          console.error('Error parsing response:', e);
          this.errorMsg = 'Failed to process the response.';
        }
      };
      reader.readAsText(response);
    },
    (error: any) => {
      this.loading = false;

      // Handle HTTP errors
      const reader = new FileReader();
      reader.onloadend = () => {
        try {
          const errorObj = JSON.parse(reader.result.toString());
          this.errorMsg = errorObj.changeUploadInfoMessage || 'An unknown error occurred.';
          console.log('Error Message:', this.errorMsg);
        } catch (e) {
          console.error('Error parsing error response:', e);
          this.errorMsg = 'An unknown error occurred.';
        }
      };
      reader.readAsText(error.error);
      console.log('HTTP Error:', error);
    }
  );
}
      
}

