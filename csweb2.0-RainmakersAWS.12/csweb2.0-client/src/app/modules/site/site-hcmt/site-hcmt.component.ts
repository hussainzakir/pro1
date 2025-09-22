import { Component, OnInit, Inject, AfterViewInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-site-hcmt',
  templateUrl: './site-hcmt.component.html',
  styleUrls: ['./site-hcmt.component.scss']
})
export class SiteHcmtComponent implements OnInit {
  company: any;
  account: any;
  site: any;
  hcmtRates : any;
  dataSource: any;
  isMaximize: boolean;

  public displayedColumns: string[] = ['ChargeCode', 'Cost', 'UOM', 'FRF/EVR/3PF', 'EffectiveDate'];


  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.hcmtRates = data.hcmtRates;
    this.company = data.company;
    this.account = data.account;
    this.site = data.site;
 }

ngOnInit() {
  this.dataSource = new MatTableDataSource(this.hcmtRates);
  console.log(this.hcmtRates);
  this.isMaximize = false;
}

 maximize () {
  this.isMaximize = true;
  let styleMaximize = <HTMLScriptElement>document.querySelector(".cdk-overlay-container .cdk-global-overlay-wrapper .cdk-overlay-pane");
  let tableRatesStyle = <HTMLScriptElement>document.querySelector("#my-modal-dialog .tableRates");
  styleMaximize.style.maxWidth = "95vw";
  styleMaximize.style.width = "95vw";
  styleMaximize.style.height = "95vh";
  styleMaximize.style.maxHeight = "95vh"
  tableRatesStyle.style.height = "41.5rem";
}

  restore () {
    let tableRatesStyle1 = <HTMLScriptElement>document.querySelector("#my-modal-dialog .tableRates");
    let styleRestore = <HTMLScriptElement>document.querySelector(".cdk-overlay-container .cdk-global-overlay-wrapper .cdk-overlay-pane");
  styleRestore.style.margin = "";
  styleRestore.style.display = "block";
  styleRestore.style.maxHeight = "510px";
  styleRestore.style.width = "70%";
  tableRatesStyle1.style.height = "13rem";
  styleRestore.style.height = "47%";
 }
}
