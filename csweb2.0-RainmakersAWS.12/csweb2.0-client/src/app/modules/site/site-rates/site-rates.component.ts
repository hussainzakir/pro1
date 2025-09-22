import { Component, OnInit, Inject, AfterViewInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-site-rates',
  templateUrl: './site-rates.component.html',
  styleUrls: ['./site-rates.component.scss']
})
export class SiteRatesComponent implements OnInit, AfterViewInit {
  company: any;
  account: any;
  site: any;
  containers : any;
  dataSource: any;
  isMaximize: boolean;

  public displayedColumns: string[] = ['container', 'description', 'effectiveDate', 'ChargeCode', 'ChargeType', 'ChargeMethod', 'TaxApp', 'UOM', 'WasteMaterialType', 'Rate'];
  showError: boolean;


  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.containers = data.containers;
    this.company = data.company;
    this.account = data.account;
    this.site = data.site;
 }

ngOnInit() {
  const tableData = this.tableModel();
  this.dataSource = new MatTableDataSource(tableData);
  if (tableData) {
   this.showError = false;
  }
  else{
   this.showError = true;
  }
  console.log(this.tableModel());
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

ngAfterViewInit() {  
}

tableModel () {
  return this.containers.map(container => {
   return container.rates.map((rate, index) => {
    return {
    containerId: container.containerId,
    Description: rate.chargeDescription,
    EffectiveDate: rate.effectiveDate,
    ChargeCode: rate.chargeCode,
    ChargeType: rate.chargeType,
    ChargeMethod: rate.chargeMethod,
    TaxApp: rate.taxApplicationCode,
    UOM: rate.unitOfMeasure,
    WasteMaterialType: rate.wasteMaterialType,
    Rate: rate.chargeRate,
    containerSize: container.containerSize,
    containerType: container.containerType,
    compactor: container.compactor,
    customerOwned: container.customerOwned,
    onCall: container.onCall,
   accountTypelabel: container.accountType.label,
   containerStatus: container.containerStatus,
  specialHandlingDescription: container.specialHandlingDescription,
  poRequired: container.poRequired,
  isVisible: (index==0)
    }
   })
  }).flat();
}
}
