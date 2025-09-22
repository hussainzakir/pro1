import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AccountDetail } from '@app/core/interfaces/aae-AccountDetail-interface';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';
import { AAEAccountEditComponent } from '../../account-edit/account-edit/aae-account-edit.component';


@Component({
  selector: 'app-aae-account-contact-edit',
  templateUrl: './aae-account-contact-edit.component.html',
  styleUrls: ['./aae-account-contact-edit.component.css']
})
export class AAEAccountContactEditComponent implements OnInit {

  public accountDetail:  AccountDetail = {} as AccountDetail;
  public errorMsg: string;
  public loading : boolean;
  durationInSeconds: number = 5;

  updateForm = new FormGroup({
    accountStagingId: new FormControl(''),
    companyNumber: new FormControl(''),
    accountContactName: new FormControl(''),
    accountContactTitle: new FormControl(''),
    accountContactEmail: new FormControl(''),
    accountContactAreaCode1: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(3)]),
    accountContactTelephone1: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(7)]),
    accountContactTelephoneExtn1: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(6)]),
    accountContactType1: new FormControl('', [Validators.maxLength(4)]),
    accountContactAreaCode2: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(3)]),
    accountContactTelephone2: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(7)]),
    accountContactTelephoneExtn2: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(6)]),
    accountContactType2: new FormControl('', [Validators.maxLength(4)]),
  });

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialog: MatDialog,
  private aaeEntryService: AaeEntryService,
  private _snackBar: MatSnackBar,
  private dialogRef: MatDialogRef<AAEAccountEditComponent>) {
     this.accountDetail = data.accountDetail;
  }

  ngOnInit() {

        //this is where we initialize the values
        this.updateForm
        .patchValue({
        accountStagingId: this.accountDetail.accountStagingId,
        companyNumber: this.accountDetail.company,
        accountContactName: this.accountDetail.accountContactName,
        accountContactTitle: this.accountDetail.accountContactTitle,
        accountContactEmail: this.accountDetail.accountContactEmail,
        accountContactAreaCode1: this.accountDetail.accountContactAreaCode1,
        accountContactTelephone1: this.accountDetail.accountContactTelephone1,
        accountContactTelephoneExtn1: this.accountDetail.accountContactTelephoneExtn1,
        accountContactType1: this.accountDetail.accountContactType1,
        accountContactAreaCode2: this.accountDetail.accountContactAreaCode2,
        accountContactTelephone2: this.accountDetail.accountContactTelephone2,
        accountContactTelephoneExtn2: this.accountDetail.accountContactTelephoneExtn2,
        accountContactType2: this.accountDetail.accountContactType2,
         });
      }

  save() {

    this.errorMsg = null;
    this.loading = true;

    this.aaeEntryService.accountInfoUpdate(this.updateForm.value)
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