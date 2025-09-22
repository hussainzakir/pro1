import { Component, Inject, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, Params } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormGroup } from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import { LoaderBtnDirective } from '@app/common/directives/loader-btn.directive';
import { AccountService } from '@app/core/services/account/account.service';

@Component({
  selector: 'app-site-contact-edit',
  templateUrl: './site-contact-edit.component.html',
  styleUrls: ['./site-contact-edit.component.scss']
})
export class SiteContactEditComponent implements OnInit {

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
    site: new FormControl(''),
    emailBus1: new FormControl(''),
    emailHome1: new FormControl(''),
    emailBus2: new FormControl(''),
    emailHome2: new FormControl(''),
  });

    constructor (private location:Location,
    private route: ActivatedRoute,
    public accountService: AccountService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<SiteContactEditComponent>,
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
      site: this.accountDetail.site,
      emailBus1: this.accountDetail.accountSiteContact.businessEmail1,
      emailHome1: this.accountDetail.accountSiteContact.homeEmail1,
      emailBus2: this.accountDetail.accountSiteContact.businessEmail2,
      emailHome2: this.accountDetail.accountSiteContact.homeEmail2
      });

  }

  save() {
    const body = this.updateForm.value;
    body.company = this.company;
    body.account = this.account;
    body.site = this.site;
    this.loading = true;
    this.errorMsg = null;

    this.accountService.accountSiteContactsUpdate(this.updateForm, this.company, this.account, this.site)
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
