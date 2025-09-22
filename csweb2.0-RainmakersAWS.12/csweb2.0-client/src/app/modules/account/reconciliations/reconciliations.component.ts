import { Component, OnInit, ViewChild, Inject, AfterViewInit } from '@angular/core';
import { ObligationInvoiceDetailComponent } from '../obligations/obligation-invoice-detail/obligation-invoice-detail.component';
import { AccountService } from './../../../core/services/account/account.service';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatTableModule } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import {MatPaginatorModule} from '@angular/material/paginator';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-reconciliations',
  templateUrl: './reconciliations.component.html',
  styleUrls: ['./reconciliations.component.css']
})
export class ReconciliationsComponent implements OnInit, AfterViewInit {

  public openReconciliations: any;
  public company: any;
  public account: any;
  isMaximize: boolean;
  public dataSource: MatTableDataSource<any>;
  public displayedColumns: string[] = ['transactionType','id','date','appliedDate','checkNumber','totalAmount',
  'cashApplied','openAmount','bankAccount','anticipationAmount',
  'discountAmount','tolleranceAmount','writeOffAmount','chargeBackAmount','debtCreditMemoApplied'];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, public accountService: AccountService,
      public dialog: MatDialog) {
    this.openReconciliations = data.openReconciliations;
    this.company = data.company;
    this.account = data.account;
  }

  ngOnInit() {
    this.loadTableData(this.openReconciliations);
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
    tableRatesStyle.style.height = "93vh";
    tableRatesStyle.style.maxHeight = "75vh";
  }

  restore () {
    let tableRatesStyle1 = <HTMLScriptElement>document.querySelector("#my-modal-dialog .tableRates");
    let styleRestore = <HTMLScriptElement>document.querySelector(".cdk-overlay-container .cdk-global-overlay-wrapper .cdk-overlay-pane");
  styleRestore.style.margin = "";
  styleRestore.style.display = "block";
  styleRestore.style.maxHeight = "95vh";
  styleRestore.style.maxWidth = "80vw";
  styleRestore.style.width = "90%";
  tableRatesStyle1.style.height = "10rem";
  styleRestore.style.height = "35vh";
}

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  filterType(value) {
    console.log(value);
    if('ALL' == value)
      this.loadAllReconciliations();
    else
      this.loadTableData(this.openReconciliations);
}

loadTableData(data: any){
  data.forEach(element => {
    element.date = this.pareseDateStringtoDate(element.date);
    element.appliedDate = this.pareseDateStringtoDate(element.appliedDate);
  })
  this.dataSource = new MatTableDataSource(data);
  this.dataSource.sort = this.sort;
  this.dataSource.paginator = this.paginator;
}

loadAllReconciliations(): any {
  this.accountService.getAllReconciliations(this.company, this.account)
      .subscribe((dataList: any) => {
        this.loadTableData(dataList);
      }
      );
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

pareseDateStringtoDate(dateString: string): Date {
  const dateParts = dateString.split('/');
  const month = parseInt(dateParts[0],10)-1;
  const day = parseInt(dateParts[1],10)
  const year = parseInt(dateParts[2],10);
  return new Date(year,month,day);
}

}
