import { Component, OnInit, ViewChild, Inject, AfterViewInit } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { AccountService } from './../../../core/services/account/account.service';
import { ObligationInvoiceDetailComponent } from '../obligations/obligation-invoice-detail/obligation-invoice-detail.component';
import {DragDropModule} from '@angular/cdk/drag-drop'

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit, AfterViewInit {

  isMaximize: boolean;
  public payments: any;
  public company: any;
  public account: any;
  public dataSource: MatTableDataSource<any>;
  public displayedColumns: string[] = ['cashReceivedDate','checkNumber','receiptAmount',
  'obligationId','unappliedAmount','lockboxNumber','bankAccountNumber','batchNumber'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public accountService: AccountService,
  public dialog: MatDialog) {
    this.payments = data.payments;
    this.company = data.company;
    this.account = data.account;
  }

  ngOnInit() {
    this.loadTableData(this.payments);
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
  styleRestore.style.maxHeight = "95vh";
  styleRestore.style.width = "850px";
  tableRatesStyle1.style.height = "10rem";
  styleRestore.style.height = "35vh";
}


  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sortingDataAccessor = (item, property) => {
      switch (property) {
         case 'cashReceivedDate': return new Date(item.cashReceivedDate);
         default: return item[property];
      }
    };
    this.dataSource.sort = this.sort;
  }

  loadTableData(data: any){
    this.dataSource = new MatTableDataSource(data);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  openObligationInvoiceDetailsDialog(obligationId: string): void{
    this.accountService.getObligationInvoiceDetail(this.company, this.account, obligationId)
    .subscribe((dataList: any) => {
      const dialogRef = this.dialog.open(ObligationInvoiceDetailComponent, {
        width: '90%',
        data: {company: this.company, account: this.account, details: dataList},
        position: {
          top: '100px'
        }
      });

      dialogRef.afterClosed().subscribe(result => {
      });
    }
    );
  }
}
