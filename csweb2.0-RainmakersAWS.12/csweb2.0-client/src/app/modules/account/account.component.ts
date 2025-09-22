import { SiteRatesComponent } from './../site/site-rates/site-rates.component';
import { AccountService } from './../../core/services/account/account.service';
import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { MatTabsModule } from '@angular/material/tabs';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { AddServiceRecordingComponent } from './add-service-recording/add-service-recordings';
import { ObligationsComponent } from './obligations/obligations.component';
import { ReconciliationsComponent } from './reconciliations/reconciliations.component';
import { PaymentsComponent } from './payments/payments.component';
import { ServiceRecordingService } from 'src/app/core/services/cs-search/service-recording.service';
import { ServiceRecording } from './service-recording-model';
import { MatTableDataSource } from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import { ChargesComponent } from './charges/charges.component';
import { SiteDetailComponent } from '../site/site-detail/site-detail.component';
import { ContainerDetailComponent } from '../site/container/container-detail/container-detail.component';
import { SiteHaulersComponent } from '../site/site-haulers/site-haulers.component';
import { ViewAllServiceRecordingsComponent } from './view-all-service-recordings/view-all-service-recordings.component';
import { MaintainServiceRecordingsComponent } from './maintain-service-recordings/maintain-service-recordings.component';
import { SiteHcmtComponent } from '../site/site-hcmt/site-hcmt.component';
import { catchError } from 'rxjs/operators';
import { of, throwError } from 'rxjs';
import { emptyAccountDetail } from './emptyAccountDetails';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  public accountDetailReady = false;
  public balanceReady = false;
  public closedRecordingsReady = false;
  public openRecordingsReady = false;
  public reageReady = false;
  public reageShow = false;
  private company: string;
  private account: string;
  private site: string;
  public theSite: any;
  public  accountDetail: any;
  public  balance: any;
  public closedRecordings: ServiceRecording[];
  public openRecordings: ServiceRecording[];
  public container: string;
  public reage: any;
  public serviceRecording: string;
  public date: string;
  public recordingDetail: any;
  public isNationalAccount: boolean = false;

  displayedColumns: string[] = ['serviceRecordingNumber', 'transactionDescription', 'scheduleCompletionDate'];
  dataSourceOpen: MatTableDataSource<ServiceRecording>;
  dataSourceClosed: MatTableDataSource<ServiceRecording>;

  @ViewChild(MatSort) sortOpen: MatSort;
  @ViewChild(MatSort) sortClosed: MatSort;

  constructor(private route: ActivatedRoute,
          public dialog: MatDialog,
          public accountService: AccountService,
          private recordingService: ServiceRecordingService,
          private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe( paramMap => {
      this.company = paramMap.get('companyId');
      this.account = paramMap.get('account');
      this.site = paramMap.get('site');
      this.serviceRecording = paramMap.get('serviceRecordNumber');
      this.date = paramMap.get('serivceRecordDate');
      this.accountService.getAccountDetail(this.company, this.account, this.site).pipe(catchError(error => {
      return of(emptyAccountDetail);
      })).subscribe(
        (data: any) => { this.accountDetail = data;
          this.accountDetailReady = true;
          this.setSite();
        this.accountService.getAccountBalance(this.company, this.account).subscribe(
          (data: any) => {
              this.balance = data;
              this.balanceReady = true;
        this.accountService.getAccountReage(this.company, this.account).subscribe(
          (data: any) => {
              this.reage = data;
              this.reageReady = true;
           });
          });
      });
        });
		this.loadServiceData();
    this.reageShow = false;
  }

  private setSite(){
    if(this.accountDetail && this.accountDetail.sites) {
      this.theSite = this.accountDetail.sites[0];
      this.accountDetail.sites.forEach(asite => {
        if(this.site === asite.site){
          this.theSite = asite;
          return;
        }
    })};
  this.isNationalAccount = this.accountDetail.naFlag==="Y";
  }

 isSiteInactive(site: any): boolean {
 const isSiteOpen = site.closeDate == 0;
  if (!isSiteOpen) {
    return false;
  }
  if (!site.containers || site.containers.length === 0) {
    return true;
  }
  const allContainersClosed = site.containers.every(
    container => container.containerStatus?.toUpperCase() === 'CLOSED'
  );
  return allContainersClosed;
  }
  openAddDialog(): void{
    const dialogRef = this.dialog.open(AddServiceRecordingComponent, {
      width: '550px',
      data: {company: this.company, isNationalAccount: this.isNationalAccount,
        account: this.account, site: this.site},
      position: {
        top: '10px'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed',result);
      //this.animal = result;
      //refresh the recordings
      if(result){
        this.loadServiceData();
      }
    });
  }

  openMaintainDialog(serviceRecording: any, completionDate: any): void{
    console.log('Recordings to be shown');
    console.log("completion date to popup: "+ completionDate);
    let dateParts = completionDate.split("/");
    let formatedDate = "20"+dateParts[2]+dateParts[0]+dateParts[1];
    const dialogRef = this.dialog.open(MaintainServiceRecordingsComponent, {
      width: '550px',
      data: {company: this.company, account: this.account, site: this.site, serviceRecording, date: formatedDate},
      position: {
        top: '10px'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed',result);
    });
  }

  openPaymentsDialog(): void{
    this.accountService.getPayments(this.company, this.account)
      .subscribe((dataList: any) => {
        const dialogRef = this.dialog.open(PaymentsComponent, { width: '850px',
          data: {payments: dataList, company: this.company, account: this.account}
        });

        dialogRef.afterClosed().subscribe(result => {
          console.log('The dialog was closed');
        });
      }
      );
  }

  openObligationsDialog(): void{
    this.accountService.getOpenObligations(this.company, this.account)
      .subscribe((dataList: any) => {
        const dialogRef = this.dialog.open(ObligationsComponent, {
          width: '850px',
          data: {openObligations: dataList, company: this.company, account: this.account}
        });

        dialogRef.afterClosed().subscribe(result => {
          console.log('The dialog was closed');
          //this.animal = result;
        });
      }
      );
  }

  openReconsilesDialog(): void{
    this.accountService.getOpenReconciliations(this.company, this.account)
    .subscribe((dataList: any) => {
      const dialogRef = this.dialog.open(ReconciliationsComponent, {
        width: '90%',
        data: {openReconciliations: dataList, company: this.company, account: this.account}
      });

      dialogRef.afterClosed().subscribe(result => {
      });
    }
    );
  }

  openChargesDialog(): void{
    this.accountService.getAccountCharges(this.company, this.account)
    .subscribe((dataList: any) => {
      const dialogRef = this.dialog.open(ChargesComponent, { width: '90%', maxHeight: '90vh',
        data: {charges: dataList, company: this.company, account: this.account}
      });

      dialogRef.afterClosed().subscribe(result => {
      });
    }
    );
  }

  openSiteDetail(): void{
    const dialogRef = this.dialog.open(SiteDetailComponent, {
      width: '75%',
      maxHeight: '80vh',
      panelClass: 'repsrv-dialog',
      data: {accountDetail: this.accountDetail, site: this.site, company: this.company, account: this.account},
      position: {
        top: '100px'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  loadServiceData(): void{

	  this.recordingService.getServieRecordingsClosed(this.company, this.account, this.site)
        .subscribe((data: any) =>{
          this.closedRecordings = data;
          this.dataSourceClosed = new MatTableDataSource(this.closedRecordings);
          this.dataSourceOpen.sort = this.sortClosed;
          this.closedRecordingsReady = true;
        });
      this.recordingService.getServieRecordingsOpened(this.company, this.account, this.site)
        .subscribe((data: any) => {
          this.openRecordings = data;
          this.dataSourceOpen = new MatTableDataSource(this.openRecordings);
          this.dataSourceOpen.sort = this.sortOpen;
          this.openRecordingsReady = true;
        });
}

//openAllRecordings
openAllRecordingsDialog(theSite: any): void{
  console.log('Recordings to be shown');
  console.log(theSite);
  const dialogRef = this.dialog.open(ViewAllServiceRecordingsComponent, {
        width: '850px',
        data: { company: this.company, account: this.account, site: this.site }
      });

  dialogRef.afterClosed().subscribe(result => {
      });
}

openRates(theSite: any): void{
  console.log('rates to be shown');
  console.log(theSite);

  //getAccountSiteContainerRates
  this.accountService.getAccountSiteContainerRates(this.company, this.account, this.site)
    .subscribe((dataList: any) => {
      let siteRates = this.setRates(theSite, dataList);
      console.log(theSite);
      const dialogRef = this.dialog.open(SiteRatesComponent, {width: '70%',
      data: {containers: theSite.containers, company: this.company, account: this.account, site: this.site }
      });

      dialogRef.afterClosed().subscribe(result => {
      });
    }
    );
}

openHaulers(theSite: any): void{
  console.log('haulers to be shown');
  console.log(theSite);

  //getAccountSiteHaulers
  this.accountService.getAccountSiteHaulerDetail(this.company, this.account, this.site)
    .subscribe((dataList: any) => {
      let siteHaulers = this.setHaulers(theSite, dataList);
      const dialogRef = this.dialog.open(SiteHaulersComponent, {width: '70%',
        data: {containers: theSite.containers, company: this.company, account: this.account, site: this.site }
      });

      dialogRef.afterClosed().subscribe(result => {
      });
    }
    );
}

openHcmt(theSite: any): void{
  console.log('HCMT rates to be shown');
  console.log(theSite);
  let vendorNumber = theSite.vendorNumber;
  let vendorSubtype = theSite.vendorSubtype;
  console.log(vendorNumber);
  console.log(vendorSubtype);
  //getAccountHcmtRates
  this.accountService.getAccountSiteHcmtRates(this.company, this.account, this.site, vendorNumber, vendorSubtype)
    .subscribe((dataList: any) => {
      const dialogRef = this.dialog.open(SiteHcmtComponent, {width: '70%',
        data: {hcmtRates: dataList, company: this.company, account: this.account, site: this.site }
      });
      dialogRef.afterClosed().subscribe(result => {
      });
    }
    );
}

private setRates(theSite: any, ratesList: any){
  if(theSite && theSite.containers){
    theSite.containers.forEach(container => {
      let containerId = Number(container.containerId).toString();
      container['rates'] = ratesList[containerId];
    });
  }

}

private setHaulers(theSite: any, haulersList: any){
  if(theSite && theSite.containers){
    theSite.containers.forEach(container => {
      let containerId = Number(container.containerId).toString();
      container['haulers'] = haulersList[containerId];
    });
  }

}

showReage() {
 this.reageShow = !this.reageShow;
}

reloadLink() {
      this.router.routeReuseStrategy.shouldReuseRoute = () => false;
      }
}
