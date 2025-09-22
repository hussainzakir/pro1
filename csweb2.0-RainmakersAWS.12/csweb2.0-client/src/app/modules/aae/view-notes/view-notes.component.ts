import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatTableDataSource } from '@angular/material';
import { AaeSearchService } from '@app/core/services/aae-entry/aae-search.service';

@Component({
  selector: 'app-view-notes',
  templateUrl: './view-notes.component.html',
  styleUrls: ['./view-notes.component.css']
})
export class ViewNotesComponent implements OnInit {

  public displayedColumns: string[] = ['note', 'previousStatus', 'newStatus', 'date', 'username'];
  showError: boolean;
  dataSource: any;
  isMaximize: boolean;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
  private searchService: AaeSearchService) { 

  }

  ngOnInit(): void {
    this.searchService.viewQuoteNotes(this.data.quoteId).subscribe((response: any[]) => {
      this.dataSource = new MatTableDataSource(response);
    })
    this.restore();
  }
    restore () {
      let tableRatesStyle1 = <HTMLScriptElement>document.querySelector("#my-modal-dialog .tableRates");
      let styleRestore = <HTMLScriptElement>document.querySelector(".cdk-overlay-container .cdk-global-overlay-wrapper .cdk-overlay-pane");
    styleRestore.style.margin = "";
    styleRestore.style.display = "block";
    styleRestore.style.maxHeight = "510px";
    styleRestore.style.width = "70%";
    tableRatesStyle1.style.height = "13rem";
    styleRestore.style.height = "50%";
  }

  printStatus(status: string){
    switch(status){
      case 'RLS':
      case 'PRI':
      case 'UND' : return 'Priority';
      case 'DEL' : return 'Delete';
      case 'HLD' : return 'Hold';
      case 'RVW' : return 'Review';
    }
  }
}
