import { Component, OnInit, Inject, AfterViewInit, ViewChildren} from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { WindowbuttonsDirective } from '@app/windowbuttons.directive';
import { AccountService } from 'src/app/core/services/account/account.service';
import { ContainerServiceHistoryComponent } from '../../container-service-history/container-service-history/container-service-history.component';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { IndStatSrvHistoryComponent } from '../../industrial-statistics-service-history/ind-stat-srv-history/ind-stat-srv-history.component';

@Component({
  selector: 'app-container-detail',
  templateUrl: './container-detail.component.html',
  styleUrls: ['./container-detail.component.scss']
})
export class ContainerDetailComponent implements OnInit, AfterViewInit  {
  public company: any;
  public account: any;
  public site: any;
  public container: any;
  containerDetail: any;
  serviceInfo: any;
  rateInfo: any;

 constructor(private route: ActivatedRoute, public accountService: AccountService, public dialog: MatDialog, private location: Location) {
  this.route.params.subscribe(params => {
    this.company = params['company'];
    this.account = params['account'];
    this.site = params['site'];
    this.container = params['containerId'];
  });
}
  ngAfterViewInit(): void {
  }

  ngOnInit() {  
    this.route.params.subscribe(params => {
      this.company = params['company'];
      this.account = params['account'];
      this.site = params['site'];
      this.container = params['containerId'];
      this.loadDetail();
    });
  }

  loadDetail(){
    //getAccountSiteContainerDetail
    this.accountService.getAccountSiteContainerDetail(this.company, this.account, this.site, this.container)
    .subscribe((data: any) => {
      this.containerDetail = data;
      this.serviceInfo = data.container.serviceInfo;
      this.rateInfo = data.container.rateInfo;
    });
  }

  openIndustrialstsservicehistory(): void{
    console.log("Industrial statistics service history");
    
    this.accountService.getIndustrialstatisticsServiceHistory(this.company, this.account, this.site, this.container)
    .subscribe((response) => {
      this.dialog.open(IndStatSrvHistoryComponent, {width: '80%',
        data: { company: this.company, account: this.account, site: this.site, container: this.container, historyList: response, containerDetail: this.containerDetail }
        });
    })
  }

  openContainerservicehistory(): void{
    console.log("Container service history");
  this.accountService.getContainerServiceHistory(this.company, this.account, this.site, this.container)
  .subscribe((data) => {
    this.dialog.open(ContainerServiceHistoryComponent, {width: '80%',
      data: { company: this.company, account: this.account, site: this.site, container: this.container, containerList: data, containerDetail: this.containerDetail }
      });
  })
}

goBack(): void {
  this.location.back();
}
}
