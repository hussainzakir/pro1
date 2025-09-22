 
import { ImportService } from '@app/core/services/account/import.service';
import { Component, ElementRef, HostListener, Inject, OnDestroy, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { Location } from '@angular/common';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpEvent, HttpEventType } from '@angular/common/http';
import { DialogPosition, MatDialog, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ThisReceiver } from '@angular/compiler';
import { environment } from 'src/environments/environment';
import { HttpService } from '@app/core/services/HttpService';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Subscription } from 'rxjs';
import { MatTableDataSource, throwMatDuplicatedDrawerError } from '@angular/material';
import { RowLimitService } from '@app/row-limit.service';
import { WindowbuttonsDirective } from '@app/windowbuttons.directive';
import { DialogCommunicationService } from '@app/dialog-communication.service';
import { ActivatedRoute } from '@angular/router';
import { LocalStorageService } from '@app/core/services/LocalStorage.service';


export interface UploadData {
  uploadType: string,
  aqName: string,
  uploadFile: any,
  chgNumber: string,
  showChangeNumber: boolean,
  showHistory: boolean,
  projectId?: string 
}

@Component({
  selector: 'app-account-imports3',
  templateUrl: './account-imports3.component.html',
  styleUrls: ['./account-imports3.component.css']
})
export class AccountImports3Component implements OnInit {

  @ViewChild('uploadFile', {read: ElementRef, static: false}) uploadFile: ElementRef;
 
   uploadForm: FormGroup;
   uploadTypes: Array<any>;
   importService: ImportService;
   selectedFiles: FileList;
   selectedType : string;
 
   templateUrl: string;
   showChangeNumber: boolean = true;
   showHistory: boolean = false;
   fileName: string;
   private readonly STORAGE_KEY = 'aob3-upload-type';
   private readonly AQNAME_KEY = 'aob3-last-aqName';
   private readonly CHGNUMBER_KEY = 'aob3-last-chgNumber';
   private readonly TOGGLE_STORAGE_KEY = 'aob3-active-toggle';

   constructor(
    private formBuilder: FormBuilder,
    private location: Location,
    private route: ActivatedRoute,                  
    private storageService: LocalStorageService,    
    importService: ImportService, 
    public dialog: MatDialog, 
    private dialogCommService:DialogCommunicationService) {

     this.uploadTypes = [
      //  {id: "ARC", description: "Resi Contract"},
       {id: "AOB", description: "Account OnBoarding"},
      //  {id: "AOM", description: "Resi Open Market"}
     ];
 
     this.importService = importService;
     this.templateUrl = this.importService.formatUrl('/ws/imports/template');
    }
 
    resetTheForm(){
     this.selectedFiles = null;
     this.uploadForm.reset();
     this.uploadFile.nativeElement.value = '';
     for (let control in this.uploadForm.controls) {
       this.uploadForm.controls[control].setErrors(null);
       this.uploadForm.controls[control].updateValueAndValidity();
     }
     this.selectedType = this.selectedType;
     this.storageService.remove(this.STORAGE_KEY);
     this.storageService.remove(this.TOGGLE_STORAGE_KEY);
     this.storageService.remove(this.AQNAME_KEY);
     this.storageService.remove(this.CHGNUMBER_KEY);
     this.showHistory = false;
    }
 
    resetTheFormClose(){
     this.uploadFile.nativeElement.value = '';
     this.uploadForm.controls['uploadFile'].setValue(null, { emitEvent: false });
     const fileInput = <HTMLInputElement>document.querySelector('ngx-mat-file-input[formcontrolname="uploadFile"] input[type="file"]');
     fileInput.value = null;
     this.selectedFiles = null;
     this.selectedType = this.selectedType;
     this.uploadForm.controls["aqName"].setValue('');
     this.uploadForm.controls['chgNumber'].setValue('');
 
     if (this.uploadForm.get('uploadType')?.value === 'AOM') {
       this.uploadForm.controls['projectId'].setValue('');
     }
   
     for (let control in this.uploadForm.controls) {
       this.uploadForm.controls[control].setErrors(null);
       this.uploadForm.controls[control].updateValueAndValidity();
     }
     this.showHistory = true;
    }
 
     doUploadPhase1(){
 
     let dataU: UploadData = {
       aqName : this.uploadForm.get('aqName').value,
       chgNumber : this.uploadForm.get('chgNumber').value,
       uploadType : this.uploadForm.get('uploadType').value,
       projectId: this.uploadForm.get('projectId')?.value || '' ,
       uploadFile: this.selectedFiles.item(0),
       showChangeNumber: this.showChangeNumber,
       showHistory: this.showHistory,
     };
 
     let position: DialogPosition = {
       top: "15px"
     };
     const dialogRef = this.dialog.open(DialogAccountImportPreview3,
       {data: dataU, disableClose: true, position: position, maxHeight: '90vw'});
 
     dialogRef.afterClosed().subscribe(result => {
       switch (result) {
         case 'cancel':
         case 'close':
           this.resetTheFormClose();
           this.dialogCommService.notifyDialogClosed(); 
           break;
         case 'resubmit':
           this.uploadForm.controls['uploadFile'].setValue(null, { emitEvent: false });
           const fileInput = <HTMLInputElement>document.querySelector('ngx-mat-file-input[formcontrolname="uploadFile"] input[type="file"]');
           fileInput.value = null;
           this.selectedFiles = null;
           break;
         default:
           break;
       }
     });
 
 
     }
 
    ngOnInit(): void {
    const searchParam = this.route.snapshot.queryParamMap.get('search');
      if (searchParam !== '1') {
        this.storageService.remove(this.TOGGLE_STORAGE_KEY);
      }

    let uploadRequiredFields: any = {
      uploadType: ['', Validators.required],
      aqName:     ['', Validators.required],
      chgNumber:  [''],
      uploadFile: ['', Validators.required],
      projectId:  ['']
    };
    this.uploadForm = this.formBuilder.group(uploadRequiredFields);

    this.route.queryParams.subscribe(params => {
      if (params['search'] === '1') {
        const last = this.storageService.get(this.STORAGE_KEY);
        
        if (last) {
          this.uploadForm.get('uploadType')!.setValue(last);
          this.showChangeNumber = last !== 'AOB';
          this.fileName = last
          this.showHistory = true;
        }
      }
    });
   
    this.uploadForm.get('uploadType').valueChanges.subscribe((value: string) => {
    this.showHistory = true;
    this.showChangeNumber = value !== 'AOB';
    this.storageService.set(this.STORAGE_KEY, value);
    console.log("uploadType: " + this.uploadForm.get('uploadType').value);
    this.fileName = this.uploadForm.get('uploadType').value;

     this.uploadForm.get('aqName')!.valueChanges
      .subscribe(v => this.storageService.set(this.AQNAME_KEY, v));
    this.uploadForm.get('chgNumber')!.valueChanges
      .subscribe(v => this.storageService.set(this.CHGNUMBER_KEY, v));
 
    //    if (value === 'AOB') {
    //      if (!this.uploadForm.contains('chgNumber')) {
    //          this.uploadForm.addControl('chgNumber', new FormControl(''));
    //      }
    //      this.uploadForm.get('chgNumber').setValue('');
    //  }
 
     if (value === 'AOB' || value === 'ARC') {
         if (!this.uploadForm.contains('projectId')) {
             this.uploadForm.addControl('projectId', new FormControl(''));
         }
         this.uploadForm.get('projectId').setValue('');
     }
    });
  }

   selectFile(event): void {
     this.selectedFiles = event.target.files;
   }
  }
//    reuploadEvent(elem: any): void {
//      this.uploadForm.controls["chgNumber"].setValue(elem.changeticket);
//      this.uploadForm.controls["uploadType"].setValue(elem.type);
 
//      let aqName = elem.excelname.split('_',4)[3];
//      console.log("aqNmae: "+aqName);
//      this.uploadForm.controls["aqName"].setValue(aqName);
//      console.log("element:");
//      console.log(elem);
//      this.uploadFile.nativeElement.click();
//    }
 
//    openMarketReUpload(elem: any): void {
//      this.uploadForm.controls["chgNumber"].setValue(elem.changeTicket);
//      this.uploadForm.controls["uploadType"].setValue(elem.type);
//      this.uploadForm.controls["aqName"].setValue(elem.projectname);
   
//      if (elem.projectid) {
//        this.uploadForm.controls["projectId"].setValue(elem.projectid); 
//      } else {
//        console.error('ProjectId is missing in the reupload element:', elem);
//      }
 
//      this.uploadFile.nativeElement.click();
//    }

@Component({
  selector: 'dialog-account-import-preview',
  templateUrl: 'dialog-account-import-preview3.html',
  styleUrls: ['dialog-account-import-preview.css'],
})

export class DialogAccountImportPreview3 implements OnInit {

  subscription: Subscription;


  importService: ImportService;
  progress: number;
  file_action: string;
  uploadResult: any;
  confirmed = false;
  step = 0;
  displayedColumns: string[] = ['row', 'errors'];
  previewView = 'tabs';
  confirmSubmit = false;
  yError = null;
  ySuccess = false;
  yWorking = false;
  isWorking = false;
  initialSetup = true;
  showMaximumButtons = true;
  
  @ViewChildren(WindowbuttonsDirective) windowsbuttondirective;
 constructor(@Inject(MAT_DIALOG_DATA) public data: UploadData,
          importService: ImportService, public dialogRef: MatDialogRef<DialogAccountImportPreview3>, 
        private rowlimitService: RowLimitService) {
            this.importService = importService;
          }
  ngOnInit(): void {
    this.doUpload();
  }


  confirmSubmitToggle(){
    this.confirmSubmit = !this.confirmSubmit;
    this.showMaximumButtons = !this.showMaximumButtons;
  }

  doUpload(){
    this.file_action = 'Uploading'
    this.subscription = this.importService.uploadTemplate3(this.data.uploadFile ,this.data.aqName, this.data.uploadType)
    .subscribe(
      (event: HttpEvent<any>) => {
        switch (event.type) {
          case HttpEventType.UploadProgress:
            this.progress = Math.round(100 * (event.loaded / event.total)*0.5);
            if(this.progress == 50)
              this.counterGo();
            break;
          case HttpEventType.Response:
            this.progress = 100;
            console.log('Done!', event.body);
            this.uploadResult = event.body;
            console.log('NUmber of rows upload : ', this.uploadResult.accounts.length);
              if(this.uploadResult.accounts.length>200){
                this.rowlimitService.rowlimit = true;               
              }else{
                this.rowlimitService.rowlimit= false;
              } 
           // this.linkPaginatorsAndSorts();

        }
        console.log(event);
      },
      (err: any) => {
        if (err.status === 406 || err.status === 500) {
          this.progress = 100;
          this.file_action = 'Error';
          this.uploadResult = {
            validationErrorsCount: 1,
            mappingErrorsCount: 0,
            validationErrors: {
              "Template Error": [
                {
                  row: null,
                  errors: [err.error || 'Template is not readable. Please check the file format.']
                }
              ]
            }
          };
        } else {  
          console.log('An error occurred:', err);
        }
      });
  }

  counterGo(){
    this.file_action = 'Processing'
    var counter = 10;
    var countdown = setInterval(() =>  {

      counter--
      if(counter == 0 || this.progress==100) {
        clearInterval(countdown)
      }
      this.progress = this.progress + 4;
    }, 2500);
  }

  setStep(index: number) { this.step = index;}

  nextStep() { this.step++; }

  prevStep() { this.step--; }

  togglePreviewView(){
   this.previewView = this.previewView == 'tabs' ? 'livePreview' : 'tabs';
  }


  setConfirmedToY(isConfirmed: any){
    this.confirmed = isConfirmed;
  }

  initCopyToY3(){
    let accts = JSON.stringify(this.uploadResult.accounts);
    this.yWorking = true;
    this.importService.copyToY3(this.data.uploadType,
      this.data.aqName, this.data.uploadFile.name, accts,
      this.data.chgNumber, this.data.projectId
      )
    .subscribe(
      response  => {
        this.showSuccess();
        this.yWorking = false;
      },
      (err: any) => {
        console.log(err);
        this.showError(err.error)
        this.yWorking = false;
      });
  }

  showError(err : string) {
    this.yError = err;
  }

  showSuccess(){
    this.ySuccess = true;
  }

  asIsOrder(a, b) {
    return 1;
 }


ngOnDestroy() {
  if (this.subscription) {
    this.subscription.unsubscribe();
  }
}

ngAfterViewInit() {

}

maximize(){
  this.windowsbuttondirective.forEach(directive => directive.maximize());
}
restore(){
  this.windowsbuttondirective.forEach(directive => directive.restore());
}
}