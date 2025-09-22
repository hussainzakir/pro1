import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material';
import { MatTableDataSource } from '@angular/material/table';
import { ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-ind-stat-srv-history',
  templateUrl: './ind-stat-srv-history.component.html',
  styleUrls: ['./ind-stat-srv-history.component.css']
})
export class IndStatSrvHistoryComponent implements OnInit {

  historyList: any[] = [];
  public dataSource: MatTableDataSource<any>; 
   public displayedColumns: string[] = [ 
      'dateServiced',
      'fromAcctNumber',
      'travelDistance',
      'serviceDistance',
      'serviceCode',
      'disposalCode',
      'weight',
      'weightUm',
      'routeNumber',
      'travelTime',
      'startTime',
      'endTime',
      'serviceTime',
      'landfillTime',
    ];
  public company: any;
  public account: any;
  public site: any;
  public container: any;
  public containerDetail: any;
  isMaximize: boolean;
  containerGroup: any;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public dialogRef: MatDialogRef<IndStatSrvHistoryComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, public dialog: MatDialog) { }

  ngOnInit(): void {  
    console.log("recived data :", this.data)
    this.historyList = this.data.historyList;
    this.account = this.data.account;
    this.site = this.data.site;
    this.containerDetail = this.data.containerDetail;
    this.containerGroup = this.data.container;
    this.dataSource = new MatTableDataSource(this.historyList);
    this.dataSource.sort = this.sort;
  }

  formateDate(datestr  : string): string{
    const year = datestr.substring(0, 4);
    const month = datestr.substring(4, 6);
    const day = datestr.substring(6, 8);
    return `${month}/${day}/${year}`;
  }

   ngAfterViewInit() {
  this.dataSource.sort = this.sort;
  }

  maximize() {
    this.isMaximize = true;
    const modalContainer = this.dialogRef['_overlayRef'].overlayElement;
    modalContainer.style.maxWidth = "95vw";
    modalContainer.style.width = "95vw";
    modalContainer.style.height = "95vh";
    modalContainer.style.maxHeight = "95vh";
    modalContainer.style.marginTop = "20px";
  
    const tableRatesStyle = document.querySelector("#my-modal-dialogInd .tableRates") as HTMLElement;
    if (tableRatesStyle) {
      tableRatesStyle.style.height = "41.5rem";
    }
  }
  
  restore() {
    const modalContainer = this.dialogRef['_overlayRef'].overlayElement;
    modalContainer.style.marginTop = "90px";
    modalContainer.style.maxHeight = "500px";
    modalContainer.style.width = "73%";
    modalContainer.style.height = "47%";
  
    const tableRatesStyle1 = document.querySelector("#my-modal-dialogInd .tableRates") as HTMLElement;
    if (tableRatesStyle1) {
      tableRatesStyle1.style.height = "13rem";
    }
  }
}
