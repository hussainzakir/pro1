import { LoaderBtnDirective } from '@app/common/directives/loader-btn.directive';
import { HttpEvent, HttpEventType } from '@angular/common/http';
import { Component, OnInit, Inject } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PriceIncreaseService } from 'src/app/core/services/price-increase/price-increase.service';

@Component({
  selector: 'app-price-increase-upload',
  templateUrl: './price-increase-upload.component.html',
  styleUrls: ['./price-increase-upload.component.css']
})
export class PriceIncreaseUploadComponent implements OnInit {
  options: FormGroup;
  uploadType = new FormControl('auto',[
    Validators.required
  ]);
  selectedFiles: FileList;
  loading = false;

  uploadFile = new FormControl('', [
    Validators.required
  ]);
  progress: any;
  uploadSuccess: boolean;
  templateUrl: string;
  
  constructor(fb: FormBuilder, public dialog: MatDialog,
    private priceIncreaseService: PriceIncreaseService,
    private _snackBar: MatSnackBar) {
    this.options = fb.group({
      uploadType: this.uploadType,
      uploadFile: this.uploadFile
    });
    this.templateUrl = this.priceIncreaseService.formatUrl('/ws/pricing/template');    

  }

  ngOnInit(): void {
  }

  doSubmit(): void{
    this.options.markAllAsTouched();

    if(!this.options.valid)
      return;

    this.loading = true;
    this.priceIncreaseService.uploadTemplate(this.selectedFiles.item(0), this.options.get('uploadType').value)
    .subscribe(
      (event: HttpEvent<any>) => {
        switch (event.type) {
          case HttpEventType.UploadProgress:

            //console.log(this.progress);
            break;
          case HttpEventType.Response:
            console.log('Done!', event.body);
            this.uploadSuccess = true;
            this._snackBar.open("Success! Upload processed successfully.",null,
            {duration: 5000,panelClass: ['green-snackbar']});
            this.resetForm();
        }
        //console.log(event);
      },
      (err: any) => {
        console.log(err);
          this.uploadSuccess = false;
          let errorMessage = "Error: Failed to process upload";
          if (err.error && err.error.msg) {
              errorMessage = err.error.msg;
          }
          this._snackBar.open("Error: " + errorMessage, null, {
              duration: 5000,
              panelClass: ['blue-snackbar']
          });
            this.loading = false;
            });
  }


  selectFile(event): void {
    this.selectedFiles = event.target.files;
  }

  resetForm(): void {
    this.loading = false;
    this.options.reset();
  }

}


@Component({
  selector: 'app-dialog-content-example-dialog',
template: '{{data.name}} is coming soon!',
})
export class ComingSoonComponent {
  constructor(
    public dialogRef: MatDialogRef<ComingSoonComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}


