import { Component, OnInit, ViewChild, Inject, AfterViewInit } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatTableModule } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import {MatPaginatorModule} from '@angular/material/paginator';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-charges',
  templateUrl: './charges.component.html',
  styleUrls: ['./charges.component.css']
})
export class ChargesComponent implements OnInit, AfterViewInit {

  public charges: any;
  public company: any;
  public account: any;
  isMaximize: boolean;
  public dataSource: MatTableDataSource<any>;
  public displayedColumns: string[] = ['site','containerGroup','chargeDate','containerType'
  ,'containerSize','compactor','chargeCode','chargeAmount'
  ,'fromDate','toDate','serviceCode','quantity','receipt','lineOfBusiness'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.charges = data.charges;
    this.company = data.company;
    this.account = data.account;
  }

  ngOnInit() {
    this.loadTableData(this.charges);
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
    tableRatesStyle.style.height = "75vh";
  }

  restore () {
    let tableRatesStyle1 = <HTMLScriptElement>document.querySelector("#my-modal-dialog .tableRates");
    let styleRestore = <HTMLScriptElement>document.querySelector(".cdk-overlay-container .cdk-global-overlay-wrapper .cdk-overlay-pane");
  styleRestore.style.margin = "";
  styleRestore.style.display = "block";
  styleRestore.style.maxHeight = "calc(100vh - 190px)";
  styleRestore.style.width = "90%";
  tableRatesStyle1.style.height = "10rem";
  styleRestore.style.height = "35vh";
}

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  loadTableData(data: any){
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }
}
