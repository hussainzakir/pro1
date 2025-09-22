import { Component, Inject, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, Params } from '@angular/router';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoaderBtnDirective } from '@app/common/directives/loader-btn.directive';
import { AccountService } from '@app/core/services/account/account.service';

@Component({
  selector: 'app-site-edit',
  templateUrl: './site-edit.component.html',
  styleUrls: ['./site-edit.component.scss']
})
export class SiteEditComponent implements OnInit {

  public accountDetail: any;
  private company: string;
  private account: string;
  public site: string;
  public errorMsg : string;
  public loading : boolean;
  siteId: any;
  theSite: any;
  public accountDetailReady = false;

  durationInSeconds: number = 3;

  updateForm = new FormGroup({
    company: new FormControl(''),
    account: new FormControl(''),
    site: new FormControl(''),
    siteName: new FormControl(''),
    siteAddressNumber: new FormControl(''),
    siteAddressName: new FormControl(''),
    streetType: new FormControl(''),
    streetDirection: new FormControl(''),
    siteAddress2: new FormControl(''),
    siteSuiteNumber: new FormControl(''),
    siteCity: new FormControl(''),
    siteState: new FormControl(''),
    sitePostalCode: new FormControl('', [Validators.pattern('^[0-9-]*$'), Validators.maxLength(10)]),
  });

  constructor (private location:Location,
    private route: ActivatedRoute,
    public accountService: AccountService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<SiteEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, public dialog: MatDialog) {
      console.log(data);
      console.log('data above');
      this.company = data.company;
      this.account = data.account;
      this.site = data.site;
      this.accountDetail = data.accountDetail;
      this.siteId = data.site;
      this.extractSite();
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
   siteName: this.theSite.siteName,
   siteAddressNumber: this.theSite.streetNumber,
   siteAddressName: this.theSite.addressName,
   streetType: this.theSite.streetType,
   streetDirection: this.theSite.streetDirection,
   siteAddress2: this.theSite.siteAddress2,
   siteSuiteNumber: this.theSite.addressSuite,
   siteCity: this.theSite.city,
   siteState: this.theSite.stateProvince,
   sitePostalCode: this.theSite.postalCode
   });
  }

save() {
 const body = this.updateForm.value;
 body.company = this.company;
 body.account = this.account;
 body.site = this.site;
 this.errorMsg = null;
 this.loading = true;


 this.accountService.accountSitesUpdate(this.updateForm, this.company, this.account, this.site)
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
    console.error(this.errorMsg);

   }
   )

   //only if the save succeeds
 //  this.dialogRef.close(this.updateForm.value);
 //  setTimeout(
 //    function(){
 //      location.reload();
 //    }, 250000);

}

private extractSite(){
  if(this.accountDetail.sites) {
    this.accountDetail.sites.forEach(asite => {
      if(this.siteId === asite.site){
        this.theSite = asite;
        return;
      }
  });
  }
}


}
