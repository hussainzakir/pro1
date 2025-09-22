import { Component, Input, OnInit } from '@angular/core';
//This component renders the live-ish preview of the
@Component({
  selector: 'app-preview-live',
  templateUrl: './preview-live.component.html',
  styleUrls: ['./preview-live.component.css']
})
export class PreviewLiveComponent implements OnInit {

  viewIndex = 0;
  maxIndex = 0;
  foundAcct = true;
  indexError: string = null;

  @Input()
  data: any;

  @Input()
  uploadType: string;

  constructor() { }

  doSearch(searchVal: string){
    this.foundAcct = false;
    this.indexError = null;
    let fnd = false;
    if(searchVal == ''){
      this.viewIndex = 0;
    } else {
      for (let i = 0; i < this.data.accounts.length ; i++) {
        let acct =  this.data.accounts[i];
        if(acct['Account #'] == searchVal){
          this.viewIndex = i;
          fnd = true;
          break;
        }
      }

    this.foundAcct = fnd;
  }
  }

  doJumpTo(jumpTo: string){
    this.indexError = null;
    let ind: number = +jumpTo;
    let maxRowNum = this.data.accounts[this.data.accounts.length - 1]['Row Index']+1;
    let minRowNum = this.data.accounts[0]['Row Index'] + 1;
    if((jumpTo > maxRowNum) || (jumpTo < minRowNum) ){
      this.indexError = 'Row number must be between ' + minRowNum + ' and ' + maxRowNum;
    } else {

      this.data.accounts.forEach((acct, index) => {
        if(acct['Row Index'] == jumpTo){
          this.viewIndex = index-1;
          return;
        }
      });
    }
  }

  ngOnInit(): void {
    this.maxIndex = this.data.accounts.length;
  }

  increment(){
    this.viewIndex++;
  }

  decrement(){
    this.viewIndex--;
  }

}
