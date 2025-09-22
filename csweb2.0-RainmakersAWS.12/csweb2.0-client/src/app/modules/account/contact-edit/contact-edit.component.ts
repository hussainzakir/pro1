import { Component, Inject, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, Params } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AccountService } from '../../../core/services/account/account.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormGroup } from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import { LoaderBtnDirective } from '@app/common/directives/loader-btn.directive';

@Component({
  selector: 'app-contact-edit',
  templateUrl: './contact-edit.component.html',
  styleUrls: ['./contact-edit.component.css']
})
export class ContactEditComponent implements OnInit {

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
    emailBus1: new FormControl(''),
    emailHome1: new FormControl(''),
    emailBus2: new FormControl(''),
    emailHome2: new FormControl(''),
  });

  constructor (private location:Location,
    private route: ActivatedRoute,
    public accountService: AccountService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<ContactEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log(data);
      console.log('data above');
      this.company = data.company;
      this.account = data.account;
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
      emailBus1: this.accountDetail.accountContact.businessEmail1,
      emailHome1: this.accountDetail.accountContact.homeEmail1,
      emailBus2: this.accountDetail.accountContact.businessEmail2,
      emailHome2: this.accountDetail.accountContact.homeEmail2
      });

  }

  save() {
    const body = this.updateForm.value;
    body.company = this.company;
    body.account = this.account;
    this.loading = true;
    this.errorMsg = null;

    this.accountService.accountContactsUpdate(this.updateForm, this.company, this.account)
    .subscribe(response =>{
      this.loading = false;
      if(response.status == 200){
        this.dialogRef.close({success:true});
        this._snackBar.open("Success! Contact Information was updated.",null,
        {duration: this.durationInSeconds *1000,panelClass: ['sucess-snackbar']});
      }
      },
      error => {
        this.loading = false;
        this.errorMsg = error.error.errorMsg;
      }
      )
  }


}
