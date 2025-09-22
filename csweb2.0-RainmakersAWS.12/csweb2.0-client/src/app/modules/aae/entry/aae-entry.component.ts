import { Component, Inject, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {MatTableDataSource} from '@angular/material/table';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';
import { AccountDetail, Rate, Container,Site, QuoteMetadata } from '@app/core/interfaces/aae-AccountDetail-interface';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatTabsModule } from '@angular/material/tabs';
import { MatPaginator} from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { AAEContactEditComponent } from './contact-edit/contact-edit/aae-contact-edit.component';
import { SalesTransactionsEditComponent } from './sales-transactions-edit/sales-transactions-edit/sales-transactions-edit.component';
import { RateEditComponent } from './rate-edit/rate-edit/rate-edit.component';
import { ContainerEditComponent } from './container-edit/container-edit/container-edit.component';
import { AAESiteEditComponent } from './site-edit/site-edit/aae-site-edit.component';
import { AAEAccountEditComponent } from './account-edit/account-edit/aae-account-edit.component';
import { RouteEditComponent } from './route-edit/route-edit/route-edit.component';
import { EntityErrors, Error, AaeErrors } from '@app/core/interfaces/aae-ErrorResponse-interface';
import { AAEAccountContactEditComponent } from './account-contact-edit/aae-account-contact-edit/aae-account-contact-edit.component';
import { FinalValidationComponent } from './final-validation/final-validation/final-validation.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RateCreateComponent } from './rate-create/rate-create/rate-create.component';
import { QuoteValidateComponent } from './quote-validate/quote-validate/quote-validate.component';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { PhonePipe } from '@app/common/pipes/phone.pipe';
import { LocalStorageService } from '@app/core/services/LocalStorage.service';
import { RateDeleteComponent } from './rate-delete/rate-delete/rate-delete.component';
import { APP_BASE_HREF } from '@angular/common';
import { MatMenuTrigger } from '@angular/material/menu';
import { AaeSearchService } from '@app/core/services/aae-entry/aae-search.service';
import { UserAuths } from '@app/core/services/authentication/userAuths';
import { ContainerDeleteComponent } from './container-delete/container-delete.component';
import { FutureServiceRequestEditComponent } from './fsr-edit/fsr-edit/fsr-edit.component';
import { FutureServiceRequestDeleteComponent } from './fsr-delete/fsr-delete/fsr-delete.component';


@Component({
  selector: 'app-automated-account-entry',
  templateUrl: './aae-entry.component.html',
  styleUrls: ['./aae-entry.component.css'],
  providers: [PhonePipe]
})
export class AutomatedAccountEntryComponent implements OnInit {

  NONFINALIZE_STATUSES: string[] = ["HLD", "DEL", "CMP"];

  public accountDetailReady = false;
  public accountDetail: AccountDetail = {} as AccountDetail;
  public errorResponse: AaeErrors = {} as AaeErrors;
  public site: any;
  public containerId: any;
  public entity: any;
  private company: string;
  private projectId: string;
  public theSite: any;
  public theContainer: any;
  public container: any;
  public account:any
  public sites: any;
  public errors: any;
  public containers: any;
  public rateData: Rate = {} as Rate;
  public containerData: Container = {} as Container;
  public siteData: Site = {} as Site;
  public errorData: Error = {} as Error;
  public rate: any;
  public errorList: EntityErrors[];
  private accountStagingId: string;
  private snackbarTimeout = 4;
  panelOpenState = false;
  isLoadingErrorResults = false;
  isLoadingQuoteData = false;
  canFinalize: Boolean = false;

  displayedSiteColumns: string[] = ['site', 'siteName', 'address', 'effectiveDate'];
  displayedContainerColumns: string[] = ['container', 'quantity', 'contractNumber', 'type', 'size', 'accountTypeDesc', 'onCallFlag', 'idCode', 'revDistCode', 'shared', 'startDate', 'delete'];
  displayedSaleTransColumns: string[] = ['container', 'transCode', 'reasonCode', 'leadSource', 'signingRep'];
  displayedRouteColumns: string[] = ['container', 'poNumber', 'quantity', 'planDate', 'serviceCode'];
  displayedRateColumns: string[] = ['site', 'chargeCode', 'chargeType', 'chargeMethod', 'effectiveDate', 'amount'];
  displayedNonResiRateColumns: string[] = ['site', 'chargeCode', 'wmType', 'chargeType', 'chargeMethod', 'effectiveDate', 'amount'];
  displayedErrorColumns: string[] = ['entity', 'identifier', 'errorDescription','action'];
  displayedContactColumns: string[] = ['site', 'siteName', 'email'];
  displayedFsrColumns: string[] = ['container', 'effectiveDate', 'qtyOnOrder', 'liftCycle', 'onCall', 'estLiftField'];
  dataSource: MatTableDataSource<any>;
  dataSourceContainer: MatTableDataSource<AccountDetail>;
  dataSourceError: MatTableDataSource<EntityErrors>;


  public quoteId: string;
  private division: string;
  public dataForContainerRateTable = [];
  public dataForContainerTable = [];
  public dataForSiteTable = [];
  public dataForErrorTable = [];
  public quoteMetadata: QuoteMetadata;
  username: string;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;
  @ViewChild("finalizeButton") finalizeButton: HTMLButtonElement;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private aaeService: AaeEntryService,
    private searchService: AaeSearchService,
    public dialog: MatDialog, private snackBar: MatSnackBar,
    private userAuth: UserAuths,
    private storageService: LocalStorageService,
    @Inject(APP_BASE_HREF) private baseHref: string) {

   }

   fetchAccountDetail(){
    this.isLoadingQuoteData = true;
    this.aaeService.getAccountDetail(this.division, this.quoteId).subscribe( (data: any) => {
      this.accountDetail = data;
      this.dataSource = new MatTableDataSource(this.accountDetail.sites);
      this.isLoadingQuoteData = false;
      this.setRates();
      this.setContainers();
      this.setCanFinalize();
         });
   }

   fetchErrorDetails(){
    this.isLoadingErrorResults = true;
    this.aaeService.getErrorResponse(this.quoteId).subscribe( (data: EntityErrors[]) => {
      this.errorList = data;
      this.dataSourceError = new MatTableDataSource(this.errorList);
      this.setErrors();
      this.isLoadingErrorResults = false;
      this.setCanFinalize();
         });
   }

  ngOnInit(): void {

    this.route.paramMap.subscribe( paramMap => {
      this.quoteId = paramMap.get('quoteId');
      this.division = paramMap.get('division');
      console.log('Quote ID: ' + this.quoteId);
      console.log('Division: ' + this.division);

      this.fetchQuoteMetadata();
      this.fetchAccountDetail();
      this.fetchErrorDetails();

  });

}
fetchQuoteMetadata(): void{
  this.aaeService.getQuoteMetadata(this.quoteId).subscribe( (metadata: QuoteMetadata) => {
    this.quoteMetadata = metadata;
    this.username = this.storageService.getUsername();
    this.setCanFinalize();
    this.updateDisplayedColumns();
  });

  this.username = this.storageService.getUsername();
}

updateDisplayedColumns() {
  if (this.quoteMetadata && this.quoteMetadata.formatType !== 'RESI') {
    this.displayedFsrColumns = ['container', 'effectiveDate', 'qtyOnOrder', 'liftCycle', 'onCall', 'estLiftField'];
  } else {
    this.displayedFsrColumns = ['container', 'effectiveDate', 'qtyOnOrder', 'liftCycle'];
  }
}

private setCanFinalize(){
  this.canFinalize = (this.dataForErrorTable.length < 1) && (this.username == this.quoteMetadata?.assignedTo)
  && !this.NONFINALIZE_STATUSES.includes(this.quoteMetadata?.statusCode) ;
}

private setRates() {
  let temp = [];
  this.accountDetail.sites.forEach(site => {
      site.containers.forEach(container => {
      container.rates.forEach(rateData => {
        temp.push(rateData);
          });
      });
  });

  this.dataForContainerRateTable = temp;
}

private setContainers() {
  let temp2 = [];
  this.accountDetail.sites.forEach(site => {
      site.containers.forEach(containerData => {
       containerData['site'] = site.site
        temp2.push(containerData);
      });
  });

  this.dataForContainerTable = temp2;
}

private setErrors() {
  let temp3 = [];
  if(this.errorList){
      this.errorList.forEach(errorResponse => {
        errorResponse.errors.forEach(errorData => {
        errorData.entity = errorResponse.entity
        temp3.push(errorData);
      });
    });
  }
  this.dataForErrorTable = temp3;
}

openEditSalesTrans(containerStagingId: string): void {
  let selectedContainer;
  let selectedSite;
  for (let site of this.accountDetail.sites) {
    for(let container of site.containers){
      if(String(container.containerStagingId) == containerStagingId){
        selectedContainer = container;
        selectedSite = site;
        break;
      }
    }
  }

  if(selectedContainer) {
  const dialogRef = this.dialog.open(SalesTransactionsEditComponent, {
    width: '550px',
    data: {container: selectedContainer, accountDetail: this.accountDetail, site: selectedSite, quoteMetadata: this.quoteMetadata,dataForErrorTable: this.dataForErrorTable, errorList: this.getErrors('SALES DETAILS', containerStagingId)},
    position: {
      top: '10px'
    }
  });

    this.handleOnCloseEdit(dialogRef, 'Sales Transaction updated.');

} else {
  console.log(`No container found with key: ${containerStagingId}`);
}
}

openEditFutureServReq(containerStagingId: string): void {
  let selectedContainer;
  let selectedSite;
  for (let site of this.accountDetail.sites) {
    for(let container of site.containers){
      if(String(container.containerStagingId) == containerStagingId){
        selectedContainer = container;
        selectedSite = site;
        break;
      }
    }
  }

  if(selectedContainer) {
  const dialogRef = this.dialog.open(FutureServiceRequestEditComponent, {
    width: '680px',
    data: {container: selectedContainer, accountDetail: this.accountDetail, site: selectedSite, quoteMetadata: this.quoteMetadata,dataForErrorTable: this.dataForErrorTable, errorList: this.getErrors('FSR', containerStagingId)},
    position: {
      top: '10px'
    }
  });

    this.handleOnCloseEdit(dialogRef, 'Future Service Request updated.');

} else {
  console.log(`No container found with key: ${containerStagingId}`);
}
}

openEditRoute(containerStagingId: string): void {
  let selectedContainer;
  let selectedSite;
  for (let site of this.accountDetail.sites) {
    for(let container of site.containers){
      if(String(container.containerStagingId) == containerStagingId){
        selectedContainer = container;
        selectedSite = site;
        break;
      }
    }
  }

  if(selectedContainer) {
  const dialogRef = this.dialog.open(RouteEditComponent, {
    width: '550px',
    data: {container: selectedContainer, accountDetail: this.accountDetail, site:selectedSite,  quoteMetadata: this.quoteMetadata,dataForErrorTable: this.dataForErrorTable, errorList: this.getErrors('ROUTE', containerStagingId)},
    position: {
      top: '10px'
    }
  });

    this.handleOnCloseEdit(dialogRef, 'Route Information updated.');

} else {
  console.log(`No container found with key: ${containerStagingId}`);
}
}

openEditRate(rateStagingId: string): void {
  let selectedRate;
  for (let rate of this.dataForContainerRateTable) {
    if(String(rate.rateStagingId) == rateStagingId){
      selectedRate = rate;
      break;
    }
}

  //  console.log(selectedContainer);
  if(selectedRate) {
  const dialogRef = this.dialog.open(RateEditComponent, {
    width: '65%',
    data: {rate: selectedRate, accountDetail: this.accountDetail,  quoteMetadata: this.quoteMetadata, errorList: this.getErrors('RATE', rateStagingId)},
    position: {
      top: '10px'
    }
  });

  this.handleOnCloseEdit(dialogRef, 'Container Rate updated.');

} else {
  console.log(`No Rate found with key: ${rateStagingId}`);
}
}

openDeleteRate(rateStagingId: string): void {
  let selectedRate;
  for (let rate of this.dataForContainerRateTable) {
    if(String(rate.rateStagingId) == rateStagingId){
      selectedRate = rate;
      break;
    }
}

  if(selectedRate) {
  const dialogRef = this.dialog.open(RateDeleteComponent, {
    width:'550px',
    maxHeight:'100vh',
    data: {rate: selectedRate, accountDetail: this.accountDetail.sites, quoteMetadata: this.quoteMetadata},
    position: {
      top: '10px'
    }
  });
  this.handleOnCloseEdit(dialogRef, 'Rate has been deleted.');
} else {
  console.log(`No Rate found with key: ${rateStagingId}`);
}
}

openEditContainer(containerStagingId: string): void {
  let selectedContainer;
  let selectedSite;
  for (let site of this.accountDetail.sites) {
    for(let container of site.containers){
      if(String(container.containerStagingId) == containerStagingId){
        selectedContainer = container;
        selectedSite = site;
        break;
      }
    }
  }

  //  console.log(selectedContainer);
  if(selectedContainer) {
  const dialogRef = this.dialog.open(ContainerEditComponent, {
    width: '85%',
    height: '860px',
    maxHeight: '100vh',
    data: {container: selectedContainer, accountDetail: this.accountDetail, site: selectedSite, quoteMetadata: this.quoteMetadata, errorList: this.getErrors('CONTAINER', containerStagingId)},
    position: {
      top: '10px'
    }
  });

  this.handleOnCloseEdit(dialogRef, 'Container Information updated.');
} else {
  console.log(`No container found with key: ${containerStagingId}`);
}
}

openDeleteContainer(containerStagingId: string): void {
  let selectedContainer;
  let selectedSite;
  for (let site of this.accountDetail.sites) {
    for(let container of site.containers){
      if(String(container.containerStagingId) == containerStagingId){
        selectedContainer = container;
        selectedSite = site;
        break;
      }
    }
  }

  //  console.log(selectedContainer);
  if(selectedContainer) {
  const dialogRef = this.dialog.open(ContainerDeleteComponent, {
    width:'550px',
    maxHeight:'100vh',
    data: {container: selectedContainer, accountDetail: this.accountDetail, site: selectedSite, quoteMetadata: this.quoteMetadata},
    position: {
      top: '10px'
    }
  });

  this.handleOnCloseEdit(dialogRef, 'Container has been deleted.');
} else {
  console.log(`No container found with key: ${containerStagingId}`);
}
}

openDeleteFsr(containerStagingId: string): void {
  let selectedContainer;
  let selectedSite;
  for (let site of this.accountDetail.sites) {
    for(let container of site.containers){
      if(String(container.containerStagingId) == containerStagingId){
        selectedContainer = container;
        selectedSite = site;
        break;
      }
    }
  }

  //  console.log(selectedContainer);
  if(selectedContainer) {
  const dialogRef = this.dialog.open(FutureServiceRequestDeleteComponent, {
    width:'550px',
    maxHeight:'100vh',
    data: {container: selectedContainer, accountDetail: this.accountDetail, site: selectedSite, quoteMetadata: this.quoteMetadata},
    position: {
      top: '10px'
    }
  });

  this.handleOnCloseEdit(dialogRef, 'Future Service Request has been deleted.');
} else {
  console.log(`No container found with key: ${containerStagingId}`);
}
}

  openEditSite(siteStagingId: string): void {
      let selectedSite = this.accountDetail.sites.find(site => site.siteStagingId === siteStagingId);

      if (selectedSite) {
        const dialogRef = this.dialog.open(AAESiteEditComponent, {
          width: '85%',
          height: '860px',
          maxHeight: '100vh',
          data: {site: selectedSite, accountDetail: this.accountDetail,  quoteMetadata: this.quoteMetadata, dataForErrorTable: this.dataForErrorTable, errorList: this.getErrors('SITE', siteStagingId)},
          position: {
            top: '10px'
          }
        });

        this.handleOnCloseEdit(dialogRef, 'Site Information updated.');

      } else {
        console.log(`No site found with siteStagingId: ${siteStagingId}`);
      }
    }

openEditAccount(): void {
  const dialogRef = this.dialog.open(AAEAccountEditComponent, {
    width: '85%',
    height: '860px',
    maxHeight: '100vh',
    data: {accountDetail: this.accountDetail, accountStagingId: this.accountStagingId, quoteMetadata: this.quoteMetadata,
      dataForErrorTable: this.dataForErrorTable, errorList: this.getErrors('ACCOUNT', null)},
    position: {
      top: '10px'
    }
  });

  this.handleOnCloseEdit(dialogRef, 'Account Information updated.');

}

openFinalValidation(): void {
  this.aaeService.getFinalAccountNumber(this.quoteMetadata.projectId).subscribe(
    response => {
      this.accountDetail.account = response.finalAccountNumber;
      console.log(response.finalAccountNumber);
      const dialogRef = this.dialog.open(FinalValidationComponent, {
        width:'550px',
        maxHeight:'100vh',
        data: {accountDetail: this.accountDetail, quoteId: this.quoteId, quoteMetadata: this.quoteMetadata},
        position: {
          top: '10px'
        }
      });
      this.handleOnCloseFinal(dialogRef, 'The quote has been submitted for finalization.');
      this.finalizeButton.disabled = false;
    });   
}

openQuoteValidate(): void {
  const dialogRef = this.dialog.open(QuoteValidateComponent, {
    width: '550px',
    maxHeight: '100vh',
    data: {accountDetail: this.accountDetail, quoteId: this.quoteId, quoteMetadata: this.quoteMetadata},
    position: {
      top: '10px'
    },
    id:'QuoteValidation'
  });
    dialogRef.afterClosed().subscribe(result => {
      if(result && result.refresh) {
        this.fetchAccountDetail();
        this.fetchErrorDetails();
        this.fetchQuoteMetadata();
      }
    });
}

doFixPopup(errorRecord: Error){
  console.log('Going to find '+errorRecord.entity+' '+errorRecord.stagingId +' for modal popup');
  console.log(errorRecord);

  switch (errorRecord.entity){
    case 'ACCOUNT':
      this.openEditAccount();
      break;
    case 'SITE':
      this.openEditSite(errorRecord.stagingId);
      break;
    case 'CONTAINER':
      this.openEditContainer(errorRecord.stagingId);
      break;
    case 'RATE':
      this.openEditRate(errorRecord.stagingId);
      break;
    case 'SALES DETAILS':
      this.openEditSalesTrans(errorRecord.stagingId);
      break;
    case 'ROUTE':
      this.openEditRoute(errorRecord.stagingId);
      break;
    case 'FSR':
      this.openEditFutureServReq(errorRecord.stagingId);
      break;
    default:{
      console.log('Entity type not found: ' + errorRecord.entity);
      break;
    }
  }


}

getErrors(entityType: string, stagingId: string): Error[] {
  console.log(this.errorList);
  if(!this.errorList)
    return [];

   for (let eRes of this.errorList){
     if(eRes.entity == entityType){
       if(stagingId){
        let errs = [];
        for(let err of eRes.errors){
          if(err.stagingId == stagingId)
            errs.push(err);
        }
        return errs;
       } else{
        return eRes.errors;
       }


     }
   }
 }

 /**
 * This method handles the dialog onclose and success message
 * @param dialogRef - dialog reference
 * @param successMsg - success message appended to 'Success!' message
 */
handleOnCloseEdit(dialogRef:  MatDialogRef<any, any>, successMsg: string): void{
  dialogRef.afterClosed().subscribe(result => {
    if(result && result.success) {
      this.snackBar.open("Success!  "+successMsg,null,
      {duration: this.snackbarTimeout * 1000, panelClass: ['success-snackbar']});

      this.fetchAccountDetail();
      this.fetchErrorDetails();
      this.fetchQuoteMetadata();
      }
  });
}

handleOnCloseFinal(dialogRef:  MatDialogRef<any, any>, successMsg: string): void{
  dialogRef.afterClosed().subscribe(result => {
    if(result && result.success) {
      this.snackBar.open("Success!  "+successMsg,null,
      {duration: this.snackbarTimeout * 2000, panelClass: ['success-snackbar']});

      this.fetchAccountDetail();
      this.fetchErrorDetails();
      this.fetchQuoteMetadata();
      }
      this.fetchAccountDetail();
  });
}

openCreateRate(containerId: string): void {
  this.trigger.closeMenu();
  let selectedContainer;
  let selectedSite;
  for (let site of this.accountDetail.sites) {
    for(let container of site.containers){
      if(String(container.containerId) == containerId){
        selectedContainer = container;
        selectedSite = site;
        break;
      }
    }
  }

  if(selectedContainer) {
  const dialogRef = this.dialog.open(RateCreateComponent, {
    width: '65%',
    maxHeight: '100vh',
    data: {container: selectedContainer, accountDetail: this.accountDetail, site: selectedSite, quoteMetadata: this.quoteMetadata},
    position: {
      top: '10px'
    }
  });

  this.handleOnCloseEdit(dialogRef, 'New Container Rate created.');
} else {
  console.log(`No container found with key: ${containerId}`);
}
}

formatTelephone(value: string): string {
  const cleaned = ('' + value).replace(/\D/g, '');
  const match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
  if (match) {
    return '(' + match[1] + ')' + match[2] + '-' + match[3];
  }
  return value;
}

shouldDisplayButton(): boolean {
  return this.dataForContainerTable && this.dataForContainerTable.some(container => container.contractNumber && container.contractNumber.trim() !== '');
}

openNewWindow($event: Event, element) {
  $event.stopPropagation();
  const url = this.baseHref+`/aae/quote/${this.quoteMetadata.quoteNumber}/contractinfo`;
  window.open(url, '_blank', 'width=1800,height=800');
}

assignQuote(quoteId: string){
  console.log('assigning quote '+ quoteId);
    this.searchService.updateAssignee('ASSIGN', quoteId)
      .subscribe( (dataResp: any) => {
        if(dataResp.errorMsg === null || dataResp.errorMsg === undefined || dataResp.errorMsg.trim() === ''){
          this.showSnack("Quote has been assigned to you, refreshing the page");
        } else {
          this.showSnackWarning(dataResp.errorMsg);
        }
        this.quoteMetadata.assignedTo = dataResp.username;
      });
      setTimeout(()=>{
        window.location.reload();
      }, 3000);
}

unassignQuote(quoteId: string){
  console.log('assigning quote '+ quoteId);
    this.searchService.updateAssignee('UNASSIGN', quoteId)
      .subscribe( (data: any) => {
        this.showSnack("Quote has been unassigned, refreshing the page");
        this.quoteMetadata.assignedTo = "UNASSIGNED";
      });
     setTimeout(()=>{
        window.location.reload();
      }, 3000);
}

showSnack(msg: string){
  this.snackBar.open("Success!  "+msg,null,
  {duration: 3.5 * 1000, panelClass: ['success-snackbar']});
  }

showSnackWarning(msg: string){
  this.snackBar.open(msg,null,
  {duration: 5.5 * 1000, panelClass: ['blue-snackbar']});
  }

canUnassign(username: any){
  return this.userAuth.isSuperUser();
  }

getEncodedAddress(siteStagingId: any): string {
  let selectedSite = this.accountDetail.sites.find(site => site.siteStagingId === siteStagingId);
  const address = `${selectedSite.addressNumber} ${selectedSite.preDirectional} ${selectedSite.addressName} ${selectedSite.streetType} ${selectedSite.streetDirection} ${selectedSite.addressSuite} ${selectedSite.city} ${selectedSite.stateProvince} ${selectedSite.postalCode}`.trim();
  return encodeURIComponent(address);
  }

  isQuoteTypeAccountEditable(): boolean {
    const lowerCaseQuoteType = this.quoteMetadata.quoteType.toLowerCase();
    return ['new', 'new/new', 'new from competitor', 'reinstatement', 'transfer of service'].includes(lowerCaseQuoteType);
  }

  getUniqueSites(): any[] {
    const uniqueAddresses = new Set();
    return this.accountDetail.sites.filter(site => {
      const address = this.getEncodedAddress(site.siteStagingId);
      if (uniqueAddresses.has(address)) {
        return false;
      } else {
        uniqueAddresses.add(address);
        return true;
      }
    });
  }

}

