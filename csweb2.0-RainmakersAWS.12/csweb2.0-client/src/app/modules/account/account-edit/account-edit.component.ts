import { AccountComponent } from './../account.component';
import { Component, Inject, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, Params } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AccountService } from '../../../core/services/account/account.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoaderBtnDirective } from '@app/common/directives/loader-btn.directive';

@Component({
  selector: 'app-account-edit',
  templateUrl: './account-edit.component.html',
  styleUrls: ['./account-edit.component.css']
})
export class AccountEditComponent implements OnInit {

  public accountDetail: any;
  private company: string;
  private account: string;
  private site: string;
  public errorMsg : string;
  public loading : boolean;

  durationInSeconds: number = 3;

  updateForm = new FormGroup({
    company: new FormControl(''),
    account: new FormControl(''),
    telephone: new FormControl(''),
    faxNumber: new FormControl(''),
    addressLine1: new FormControl(''),
    addressLine2: new FormControl(''),
    addressLine3: new FormControl(''),
    city: new FormControl(''),
    state: new FormControl(''),
    postalCode: new FormControl('', [Validators.pattern('^[0-9-]*$'), Validators.maxLength(10)]),
    billingAddressName: new FormControl(''),
  });

  constructor (private location:Location,
    private route: ActivatedRoute,
    public accountService: AccountService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<AccountEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log(data);
      console.log('data above');
      this.company = data.company;
      this.account = data.account;
      this.site = data.site;
      this.accountDetail = data.accountDetail;
    }


  ngOnInit(): void {
    console.log('State????');
    console.log(this.location.getState());


 //this is where we initialize the values
 this.updateForm
 .patchValue({
   company: this.accountDetail.company,
   account: this.accountDetail.account,
   billingAddressName: this.accountDetail.customerName,
   telephone: this.accountDetail.telephone,
   faxNumber: this.accountDetail.fax,
   city: this.accountDetail.city,
   addressLine1: this.accountDetail.addressLine1,
   addressLine2: this.accountDetail.addressLine2,
   addressLine3: this.accountDetail.addressLine3,
   state: this.accountDetail.state,
   postalCode: this.accountDetail.postalCode
   });

}

save() {
 const body = this.updateForm.value;
 body.company = this.company;
 body.account = this.account;
 this.errorMsg = null;
 this.loading = true;


 this.accountService.accountDetailsUpdate(this.updateForm, this.company, this.account)
 .subscribe(response =>{
  this.loading = false;
   if(response.status == 200){
    this.dialogRef.close({success:true});
     this._snackBar.open("Success! Account Information updated.",null,
     {duration: this.durationInSeconds *1000,panelClass: ['success-snackbar']});
   }
   },
   error => {
    this.loading = false;
    this.errorMsg = error.error.errorMsg;

   }
   )

   //only if the save succeeds
 //  this.dialogRef.close(this.updateForm.value);
 //  setTimeout(
 //    function(){
 //      location.reload();
 //    }, 250000);

}


}
