import { OnChanges, SimpleChanges } from '@angular/core';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-resi-contract',
  templateUrl: './resi-contract.component.html',
  styleUrls: ['./resi-contract.component.css']
})
export class ResiContractComponent implements OnInit, OnChanges {

  @Input()
  account: any;

  @Input()
  site: any = null;

  container: any = null;
  sitesCount = 0;
  siteIndex = 0;
  containerCount = 0;
  containerIndex = 0;


  constructor() { }

  ngOnInit(): void {
    //console.log('ngOninit called');
    //this.initAccount();
  }



  incrementSite(){
    this.site = this.account.sites[++this.siteIndex];
    this.initContainer();
  }

  decrementSite(){
    this.site = this.account.sites[--this.siteIndex];
    this.initContainer();
  }

  incrementContainer(){
    this.container = this.site.containers[++this.containerIndex];
  }

  decrementContainer(){
    this.container = this.site.containers[--this.containerIndex];
  }


  // if sites, set site count and site Index and call initConainers
  initSite(){
    this.site = this.account.sites ? this.account.sites[0] : null;
    this.siteIndex = 0;
    this.sitesCount = this.site ?  this.account.sites.length : 0;

    this.initContainer();
  }

  // if container, init container count, container index
  initContainer(){
    this.container = (this.site && this.site.containers) ? this.site.containers[0] : null;
    this.containerCount = this.container ? this.site.containers.length : 0;
    this.containerIndex = 0;
  }


  ngOnChanges(changes: SimpleChanges) {
    console.log('ngOnChanges called', changes);
    if(changes.account ){
      this.initSite();
    }
  }

}
