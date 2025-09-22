import { Component, Input, OnInit } from '@angular/core';
import { RowLimitService } from '@app/row-limit.service';

@Component({
  selector: 'app-preview-sheets',
  templateUrl: './preview-sheets.component.html',
  styleUrls: ['./preview-sheets.component.css']
})
export class PreviewSheetsComponent implements OnInit {

  prevIndex = 0;

  @Input()
  book: any;

  @Input()
  uploadType: string;

  objectKeys = Object.keys;

  constructor(public rowlimitService: RowLimitService) { }

  ngOnInit(): void {
  }

  //Filter out certain fields
  getColumns(obj: any){
    let ignoreColumns = ['sites','aobSites','aobContainers','aobRates','openMarketRoutes','openMarketRateInformation', 'openMarketSiteInformation', 'openMarketContainerInformation', 'aobRoutes','rowErrors','containers', 'rates', 'routes','uploadError', 'errors', 'Row Index']
    
    if (this.uploadType === 'AOM') {
      ignoreColumns.push('Contract Group #');
    }
    
    let okeys = this.objectKeys(obj);

    okeys = okeys.filter( function( el ) {
      return !ignoreColumns.includes( el );
    } );

    return okeys;
  }

  asIsOrder(a, b) {
    return 1;
 }

}
