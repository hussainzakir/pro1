import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, Params } from '@angular/router';
import { AccountService } from './../../../core/services/account/account.service';
import { MatDialog } from '@angular/material';
import { ContactEditComponent } from '../contact-edit/contact-edit.component';
import { AccountEditComponent } from '../account-edit/account-edit.component';


@Component({
  templateUrl: './account-detail.component.html',
  styleUrls: ['./account-detail.component.css']
})
export class AccountDetailComponent implements OnInit {

  public accountDetail: any;
  private company: string;
  private account: string;
  private site: string;
  public accountDetailReady = false;
  public theSite: any;

  constructor (private location:Location,
    private route: ActivatedRoute,
    public dialog: MatDialog,
    public accountService: AccountService) { }

  ngOnInit(): void {
    this.accountDetail = this.location.getState();


 //   if(!this.accountDetail.account){
      //call to get data here
 //     this.route.paramMap.subscribe( paramMap => {
 //       this.company = paramMap.get('companyId');
 //       this.account = paramMap.get('account');
 //       this.site = paramMap.get('site');
//
 //       this.accountService.getAccountDetail(this.company, this.account, this.site).subscribe((data: any) => this.accountDetail = data);
 //     })
 //   }

    this.route.paramMap.subscribe( paramMap => {
      this.company = paramMap.get('companyId');
      this.account = paramMap.get('account');
      this.site = paramMap.get('site');

      this.loadDetail();
        });
  }

  openEditContact(): void {
    // This is 'commented' out per Shankar March31, 2022
    if(false) {
    const dialogRef = this.dialog.open(ContactEditComponent, {
      width: '550px',
      data: {accountDetail: this.accountDetail, site: this.site, account: this.account, company: this.company},
      position: {
        top: '10px'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result && result.success)
        this.loadDetail();
    });
  }
  }

  loadDetail(): void{
    this.accountService.getAccountDetail(this.company, this.account, this.site).subscribe(
      (data: any) => { this.accountDetail = data;
        this.accountDetailReady = true;
        this.setSite();
   });
  }

  openEditAccount(): void {
    const dialogRef = this.dialog.open(AccountEditComponent, {
      width: '550px',
      data: {accountDetail: this.accountDetail, site: this.site, account: this.account, company: this.company},
      position: {
        top: '10px'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result && result.success)
        this.loadDetail();
    });
  }

  private setSite(){
    if(this.accountDetail && this.accountDetail.sites) {
      this.theSite = this.accountDetail.sites[0];
      this.accountDetail.sites.forEach(asite => {
        if(this.site === asite.site){
          this.theSite = asite;
          return;
        }
    });

  }

}
}
