import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators, FormBuilder, FormGroup, AbstractControl } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material/core';
import { MuTemplateServiceService } from '../../../core/services/mass-uploads/mu-template-service.service';
import { MuTemplate } from '../../../core/interfaces/massupload-template-interface';
import { AuthService } from '../../../core/services/authentication/auth.service';
import * as fileSaver from 'file-saver';
import { HttpEventType, HttpResponse, HttpErrorResponse, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import {MatStepper} from '@angular/material';
import { formatDate} from '@angular/common';
import { MassUploadHistoryComponent } from '../mass-upload-history/mass-upload-history.component';

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-submit-mass-upload',
  templateUrl: './submit-mass-upload.component.html',
  styleUrls: ['./submit-mass-upload.component.css']
})
export class SubmitMassUploadComponent implements OnInit {
  matcher = new MyErrorStateMatcher();
  isLinear = true;
  uploadForm: FormGroup;
  templateSelected: MuTemplate;
  templates: Array<MuTemplate>;
  currentUser: any;
  changeNumber = '';
  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  uploadFile = '';
  uploadSuccess = false;
  uploadResponse: any;
  fileSelected: any;
  changeReq: string;
  public refreshHistory: number;
  @ViewChild('stepper') stepper: MatStepper;
  @Output("notifyParentUploaded") notifyParentUploaded: EventEmitter<any> = new EventEmitter();

  constructor(
    private formBuilder: FormBuilder,
    private templateService: MuTemplateServiceService,
    private authService: AuthService) {

    this.currentUser = this.authService.getCurrentUser();

  }

  ngOnInit(): void {
    this.templateService.getTemplates().subscribe((data: any) => {
      this.templates = data.templates;
      this.changeReq = data.changeReq.trim();
      let uploadRequiredFields: any;
      console.log(this.changeReq);
      if (this.changeReq == 'Y') {
        uploadRequiredFields = {
          changeNumber: ['', Validators.required],
          uploadFile: ['', Validators.required],
        };
      } else {
        uploadRequiredFields = { uploadFile: ['', Validators.required] };
      }
      this.uploadForm = this.formBuilder.group({
        formArray: this.formBuilder.array([
          this.formBuilder.group({
            template: ['', Validators.required],}),
                    this.formBuilder.group(uploadRequiredFields),
        ]),
      });
    });
  }

  doDownloadTemplate(): any {
    this.templateService
      .downloadTemplate(this.templateSelected.id)
      .subscribe(response => {
        const name = this.getFilename(response);
        const blob: any = new Blob([response.body], { type: 'text/json; charset=utf-8' });
        const url = window.URL.createObjectURL(blob);
        fileSaver.saveAs(blob, name);
      }), error => console.log('Error downloading the file'),
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

  templateChanged(event): void {
    this.templateSelected = event.value;
  }

formatAs400Date(str: string): string{
  const [year, month, day, timeString] =str.split('-')
  const [hours, minutes, seconds] = timeString.split('.');

  const date = new Date(+year, +month-1 , +day, +hours, +minutes, +seconds);
  const newFormat = formatDate(date, 'MM/dd/yyyy hh:mm a', 'en_US')

  return newFormat;
}

  doSubmitTemplate(): void {
    console.log('submitting MassUpload file');
    const uploadData = this.uploadForm.get('formArray').value[1];

    this.templateService.uploadTemplate(this.currentFile
      , (typeof(uploadData.changeNumber) === 'undefined') ? '': uploadData.changeNumber
      , this.templateSelected.id).subscribe(
    (event: HttpEvent<any>) => {
      switch (event.type) {
        case HttpEventType.UploadProgress:
          this.progress = Math.round(100 * event.loaded / event.total);
          console.log(this.progress);
          break;
        case HttpEventType.Response:
          //console.log('Done!', event.body);
          this.uploadSuccess = true;
          this.templateService.uploadTemplateCompleted.next(true);
          this.notifyParentUploaded.emit();
          this.stepper.next();
      }
      console.log(event);
    },
    (err: any) => {
      console.log(err);
      this.uploadSuccess = false;
        // this.uploadResponse = err.error;
        if( err.error.errorMsg || err.error.errorMessage){
          this.uploadResponse = {
            errorMessage : err.error.errorMsg || err.error.errorMessage , 
            errors : null
          };
        }
      this.stepper.next();
    });
    //console.log(uploadData);
  }

  cleanSelect() {
    const fileSelected1 = <HTMLInputElement> document.querySelector('.selectedFileId input[type=file]');
    fileSelected1.value = null;
    this.selectedFiles=null;
    this.currentFile=null;
    this.uploadSuccess=false;
    this.uploadResponse=null;
    this.progress=0;
    this.templateSelected=null;
    this.changeNumber='';
    this.uploadFile='';
    }

  selectFile(event): void {
    this.selectedFiles = event.target.files;
    if (this.selectedFiles.length > 0) {
      let file = this.selectedFiles.item(0);
      const sanitizedFileName = file.name.replace(/[;,]/g, '');
      this.currentFile = new File([file], sanitizedFileName, { type: file.type });

      console.log('orginal file name ' , file.name);
      console.log('sanitized file name ' , this.currentFile.name);
    }
  }



  get formArray(): AbstractControl | null { return this.uploadForm.get('formArray'); }

}
