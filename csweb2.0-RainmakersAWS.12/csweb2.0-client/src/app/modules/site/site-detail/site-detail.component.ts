
import { Component, OnInit, Inject, AfterViewInit} from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SiteContactEditComponent } from '../site-contact-edit/site-contact-edit/site-contact-edit.component';
import { MatDialog } from '@angular/material';
import { SiteEditComponent } from '../site-edit/site-edit/site-edit.component';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { AccountService } from './../../../core/services/account/account.service';

@Component({
  selector: 'app-site-detail',
  templateUrl: './site-detail.component.html',
  styleUrls: ['./site-detail.component.css']
})
export class SiteDetailComponent implements OnInit, AfterViewInit {

  public accountDetail : any;
  private company: string;
  private account: string;
  private site: string;
  public accountDetailReady = false;
  public siteId: any;
  public theSite: any;
  isMaximize: boolean;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialog: MatDialog, public accountService: AccountService) {
                this.accountDetail = data.accountDetail;
                this.company = data.company;
                this.account = data.account;
                this.site = data.site;
                this.siteId = data.site;
                this.theSite = data.site;
                this.extractSite();
   }

  ngOnInit() {
    this.isMaximize = false;
  }

  ngAfterViewInit() {
  }


  formatFax(fax: string):string{
    if(!fax || fax.trim() === '' ){
      return '(000) 000-0000';
    }
    else if(fax==='(   )    -'){
      return '(000) 000-0000';
    }
    else {
      return fax;
    }
  }
  openSiteEdit(): void {
    const dialogRef = this.dialog.open(SiteEditComponent, {
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
  
  openSiteEditContact(): void {
    //This is 'commented' out per Shankar March31, 2022
    if(false){
    const dialogRef = this.dialog.open(SiteContactEditComponent, {
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
  
  maximize () {
    this.isMaximize = true;
    let styleMaximize = <HTMLScriptElement>document.querySelector(".cdk-overlay-container .cdk-global-overlay-wrapper .cdk-overlay-pane");
    let tableRatesStyle = <HTMLScriptElement>document.querySelector("#my-modal-dialog .tableRates");
    styleMaximize.style.maxWidth = "95vw";
    styleMaximize.style.width = "95vw";
    styleMaximize.style.height = "95vh";
    styleMaximize.style.maxHeight = "95vh"
    styleMaximize.style.marginTop = "20px";
    tableRatesStyle.style.height = "41.5rem";

  }
  
    restore () {
      let tableRatesStyle1 = <HTMLScriptElement>document.querySelector("#my-modal-dialog .tableRates");
      let styleRestore = <HTMLScriptElement>document.querySelector(".cdk-overlay-container .cdk-global-overlay-wrapper .cdk-overlay-pane");
    styleRestore.style.marginTop = "90px";
    styleRestore.style.display = "block";
    styleRestore.style.maxHeight = "500px";
    styleRestore.style.width = "73%";
    tableRatesStyle1.style.height = "13rem";
    styleRestore.style.height = "47%";
  }
  
}

