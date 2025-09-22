import { Component, OnInit } from '@angular/core';
import { FormControl,FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AccountDetail, QuoteMetadata } from '@app/core/interfaces/aae-AccountDetail-interface';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';
import { AaeSearchRecord } from '@app/core/interfaces/aae-search-interfaces';
import {MatSnackBar} from '@angular/material/snack-bar';
import { Inject } from '@angular/core';

@Component({
  selector: 'app-quote-validate',
  templateUrl: './quote-validate.component.html',
  styleUrls: ['./quote-validate.component.css']
})
export class QuoteValidateComponent implements OnInit {

  public accountDetail:  AccountDetail = {} as AccountDetail;
  public aaeSearchRecord:  AaeSearchRecord = {} as AaeSearchRecord;
  public quoteMetadata: QuoteMetadata = {} as QuoteMetadata;
  public quoteId: any;
  public errorMsg: string;
  public loading : boolean;
  private validateOnly : any;
  durationInSeconds: number = 5;
  public validateAttemptFailed : boolean;

  updateForm = new FormGroup({
    projectId: new FormControl(''),
    validateOnly: new FormControl('')
  });

  constructor( private aaeEntryService: AaeEntryService,
    @Inject(MAT_DIALOG_DATA) public data: any, public dialog: MatDialog,
    private _snackBar: MatSnackBar,
  private dialogRef: MatDialogRef<QuoteValidateComponent>) {
    this.accountDetail = data.accountDetail;
    this.quoteMetadata = data.quoteMetadata;
    this.quoteId = data.quoteId;
    this.validateOnly='1';
    this.validateAttemptFailed=false;
    this.dialogRef.disableClose = true;
 }

  ngOnInit() {
    this.updateForm.patchValue({
      projectId : this.quoteMetadata.projectId,
      validateOnly : this.validateOnly,
    })
  }

  save(){

    this.errorMsg = null;
    this.loading = true;

    // note: we are using the same service with different params.
   this.aaeEntryService.finalizationInfoUpdate(this.updateForm.value)
   .subscribe(response =>{
     this.loading = false;
     if(response.status == 200){
       this.dialogRef.close({refresh: true});
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
       this.validateAttemptFailed=true;
     }
     )
  }

  closeIt(){
    console.log('final validation close button clicked');
    if (this.validateAttemptFailed){
      this.dialogRef.close({refresh: true});
    }
    else {
      this.dialogRef.close({refresh: false});
    }
  }

}
