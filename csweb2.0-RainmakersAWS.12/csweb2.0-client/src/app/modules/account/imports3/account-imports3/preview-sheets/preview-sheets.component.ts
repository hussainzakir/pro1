import { Component, Input, OnInit } from '@angular/core';
import { RowLimitService } from '@app/row-limit.service';

@Component({
  selector: 'app-preview-sheets3',
  templateUrl: './preview-sheets.component3.html',
  styleUrls: ['./preview-sheets.component3.css']
})
export class PreviewSheetsComponent3 implements OnInit {

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
    let ignoreColumns = ['sites','aob3Sites','aob3Containers','aob3Rates','openMarketRoutes','openMarketRateInformation', 'openMarketSiteInformation', 'openMarketContainerInformation', 'aobRoutes','rowErrors','containers', 'rates', 'routes','uploadError', 'errors', 'Row Index']
    
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
