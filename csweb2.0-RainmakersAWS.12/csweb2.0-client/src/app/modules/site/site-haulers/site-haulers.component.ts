import { Component, OnInit, Inject, AfterViewInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-site-haulers',
  templateUrl: './site-haulers.component.html',
  styleUrls: ['./site-haulers.component.css']
})
export class SiteHaulersComponent implements OnInit, AfterViewInit {
  company: any;
  account: any;
  site: any;
  containers: any;
  dataSource: any;
  isMaximize: boolean;
  public displayedColumns: string[] = ['container', 'haulerName', 'haulerAddress', 'contactName', 'contactPhone', 'contactEmail'];


  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.containers = data.containers;
    this.company = data.company;
    this.account = data.account;
    this.site = data.site;
   }

  ngOnInit() {
    this.dataSource = new MatTableDataSource(this.tableModel());
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
      if (typeof container.haulers == 'undefined') {
        container.haulers = [];
      }
     return container.haulers.map((hauler, index) => {
      return {
      containerId: container.containerId,
      vendorNumber: hauler.vendorNumber,
      addressLine1: hauler.addressLine1,
      addressLine2: hauler.addressLine2,
      addressLine3: hauler.addressLine3,
      city: hauler.city,
      state: hauler.state,
      postalCode: hauler.postalCode,
      contactFirstName: hauler.contactFirstName,
      contactLastName: hauler.contactLastName,
      contactDescription: hauler.contactDescription,
      primaryTelephoneNumber: hauler.primaryTelephoneNumber,
      primaryFaxNumber: hauler.primaryFaxNumber,
      vendorContactEmail: hauler.vendorContactEmail,
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
