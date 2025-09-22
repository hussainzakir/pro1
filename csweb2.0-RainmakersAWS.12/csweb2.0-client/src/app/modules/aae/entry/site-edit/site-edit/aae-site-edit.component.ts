import { ChangeDetectorRef, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, Params } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import { LoaderBtnDirective } from '@app/common/directives/loader-btn.directive';
import { AccountDetail, QuoteMetadata, Site } from '@app/core/interfaces/aae-AccountDetail-interface';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';
import { DropdownItem } from '@app/modules/auto-complete-dropdown/auto-complete-dropdown.component';
import { ReplaySubject } from 'rxjs';
import { FormatPhonePipe } from '@app/common/pipes/phonePipe/format-phone.pipe';
import { FormatEnteredDatePipe } from '@app/common/pipes/datePipe/format-entered-date.pipe';
import { FormatNumDatePipe } from '@app/common/pipes/datePipe/format-num-date.pipe';
import { DatePipe } from '@angular/common';
import { CdkVirtualScrollViewport } from '@angular/cdk/scrolling';


@Component({
  selector: 'app-site-edit',
  templateUrl: './aae-site-edit.component.html',
  styleUrls: ['./aae-site-edit.component.css'],
  providers: [FormatPhonePipe, FormatNumDatePipe]
})
export class AAESiteEditComponent implements OnInit {

  public accountDetail:  AccountDetail = {} as AccountDetail;
  public site: Site = {} as Site;
  sites: any;
  public quoteId: string;
  public errorMsg: string;
  public loading : boolean;
  durationInSeconds: number = 5;
  errorsList: Error[];
  private formatPhone = new FormatPhonePipe();
  private datePipe: DatePipe;
  private formatNumDate = new FormatNumDatePipe();
  formatEnteredDatePipe = new FormatEnteredDatePipe();
  public quoteMetadata: QuoteMetadata = {} as QuoteMetadata;
  public dataForErrorTable: any[];
  public contractStatusValues: any;
  public naicsValues: any;
  public salesRepValues: any;
  public territoryValues: any;
  public filteredContractStatusValues: any;
  public filteredNaicsValues: any;
  public filteredSalesRepValues: any;
  public filteredTerritoryValues: any;
  scrollIndexTerritory: number = 0;
  scrollIndexNaics: number = 0;
  scrollIndexSalesRep: number = 0;

  @ViewChild('territoryViewport') territoryViewport: CdkVirtualScrollViewport;
  @ViewChild('naicsViewport') naicsViewport: CdkVirtualScrollViewport;
  @ViewChild('salesRepViewport') salesRepViewport: CdkVirtualScrollViewport;

  updateForm = new FormGroup({
    stagingId: new FormControl(''),
    companyNumber: new FormControl(''),
    siteName: new FormControl(''),
    addressNumber: new FormControl(''),
    addressName: new FormControl(''),
    streetDirection: new FormControl('', [Validators.maxLength(3)]),
    streetType: new FormControl('', [Validators.maxLength(4)]),
    addressSuite: new FormControl('', [Validators.maxLength(5)]),
    city: new FormControl(''),
    state: new FormControl('', [Validators.maxLength(3)]),
    zipCode: new FormControl('', [Validators.maxLength(12)]),
    phoneNumber: new FormControl(''),
    phoneExtension: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(4)]),
    contractNumber: new FormControl('', [Validators.maxLength(10)]),
    csaTerm: new FormControl(''),
    effectiveDate: new FormControl(''),
    expiryDate: new FormControl(''),
    reviewDate: new FormControl(''),
    contractStatus: new FormControl(''),
    purchaseOrder: new FormControl('', [Validators.maxLength(15)]),
    authorizedBy: new FormControl(''),
    authorizedByTitle: new FormControl(''),
    serviceContact: new FormControl(''),
    serviceContactTitle: new FormControl(''),
    salesRep: new FormControl(''),
    salesRepName: new FormControl(''),
    territoryCode: new FormControl(''),
    sharedContainers: new FormControl('', [Validators.maxLength(1)]),
    taxExempt: new FormControl(''),
    naics: new FormControl(''),
    faxNumber: new FormControl(''),
    closeDate: new FormControl(''),
    lastUpdatedDate: new FormControl(''),
    preDirectional: new FormControl(''),
  });

  constructor (private location:Location, private cdr: ChangeDetectorRef,
    private route: ActivatedRoute,
    private aaeEntryService: AaeEntryService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<AAESiteEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log(data);
      console.log('data above');
      this.accountDetail = data.accountDetail;
      this.sites = data.sites;
      this.site = data.site;
      this.errorsList = data.errorList;
      this.datePipe = new DatePipe('en-US');
      this.quoteMetadata = data.quoteMetadata;
      this.dataForErrorTable = this.data.dataForErrorTable;
    }

  ngOnInit() {

        //this is where we initialize the values
        this.updateForm
        .patchValue({
        stagingId: this.site.siteStagingId,
        companyNumber: this.site.companyNumber,
        siteName: this.site.siteName,
        addressNumber: this.site.addressNumber,
        addressName: this.site.addressName,
        streetDirection: this.site.streetDirection,
        streetType: this.site.streetType,
        addressSuite: this.site.addressSuite,
        city: this.site.city,
        state: this.site.stateProvince,
        zipCode: this.site.postalCode,
        phoneNumber: this.formatPhone.transform(this.site.phoneNumber),
        phoneExtension: this.site.phoneExtension,
        contractNumber: this.site.contractNumber,
        csaTerm: this.site.term,
        effectiveDate: this.formatNumDate.transform(this.site.effectiveDate),
        expiryDate: this.formatNumDate.transform(this.site.expiryDate),
        reviewDate: this.formatNumDate.transform(this.site.reviewDate),
        contractStatus: this.site.contractStatus,
        purchaseOrder: this.site.purchaseOrder,
        authorizedBy: this.site.authorizedBy,
        authorizedByTitle: this.site.authorizedByTitle,
        serviceContact: this.site.serviceContact,
        serviceContactTitle:  this.site.contactTitle,
        salesRep: this.site.salesRep,
        salesRepName: this.site.salesRepName,
        territoryCode: this.site.territoryCode,
        sharedContainers: this.site.sharedContainers,
        taxExempt: this.site.taxExempt,
        faxNumber: this.formatPhone.transform(this.site.faxNumber),
        naics: this.site.naics,
        closeDate: this.formatNumDate.transform(this.site.closeDate),
        lastUpdatedDate: this.formatNumDate.transform(this.site.lastUpdatedDate),
        preDirectional: this.site.preDirectional,
         });
         this.updateForm.get('phoneNumber').valueChanges.subscribe(value => {
          if (typeof value === 'string') {
            const formattedValue = this.formatPhone.transform(value);
            this.updateForm.get('phoneNumber').setValue(formattedValue, { emitEvent: false });
          }
        });

        this.aaeEntryService.getDropdownInfo(this.accountDetail.company, 'SITE').subscribe(data => {

          this.contractStatusValues = data.contractStatus;
          this.filteredContractStatusValues = this.contractStatusValues.slice();
          this.naicsValues = data.naics;
          this.filteredNaicsValues = this.naicsValues.slice(0, 20);
          this.salesRepValues = data.salesRep;
          this.filteredSalesRepValues = this.salesRepValues.slice(0, 20);
          this.territoryValues = data.territory;
          this.filteredTerritoryValues = this.territoryValues.slice(0, 20);

          this.filteredNaicsValues = this.naicsValues;
          this.filteredTerritoryValues = this.territoryValues;
          this.filteredSalesRepValues = this.salesRepValues;
          this.moveNaicsSelectedToTop(this.updateForm.get('naics').value);
          this.moveTerritorySelectedToTop(this.updateForm.get('territoryCode').value);
          this.moveSalesRepSelectedToTop(this.updateForm.get('salesRep').value);
          this.cdr.detectChanges();
         });

        this.updateForm.get('faxNumber').valueChanges.subscribe(value => {
          if (typeof value === 'string') {
            const formattedValue = this.formatPhone.transform(value);
            this.updateForm.get('faxNumber').setValue(formattedValue, { emitEvent: false });
          }
        });

        this.updateForm.get('effectiveDate').valueChanges.subscribe(value => {
          if (typeof value === 'string') {
            const formattedValue = this.formatEnteredDatePipe.transform(value);
            this.updateForm.get('effectiveDate').setValue(formattedValue, { emitEvent: false });
          }
        });

        this.updateForm.get('expiryDate').valueChanges.subscribe(value => {
          if (typeof value === 'string') {
            const formattedValue = this.formatEnteredDatePipe.transform(value);
            this.updateForm.get('expiryDate').setValue(formattedValue, { emitEvent: false });
          }
        });

        this.updateForm.get('reviewDate').valueChanges.subscribe(value => {
          if (typeof value === 'string') {
            const formattedValue = this.formatEnteredDatePipe.transform(value);
            this.updateForm.get('reviewDate').setValue(formattedValue, { emitEvent: false });
          }
        });

        this.updateForm.get('closeDate').disable();
        this.updateForm.get('lastUpdatedDate').disable();

        if (this.errorsList && this.errorsList.length == 0 || this.dataForErrorTable && this.dataForErrorTable.every(error => error.key && error.key !== 'ASSLNO')) {
          this.updateForm.get('salesRep').disable();
        } else {
          this.updateForm.get('salesRep').enable();
        }

      }

  save() {

    this.errorMsg = null;
    this.loading = true;

    let phoneNumber = this.updateForm.get('phoneNumber').value;
    phoneNumber = ('' + phoneNumber).replace(/\D/g, '');

    let faxNumber = this.updateForm.get('faxNumber').value;
    faxNumber = ('' + faxNumber).replace(/\D/g, '');

    const formValues = {
      ...this.updateForm.value,
      phoneNumber: phoneNumber,
      faxNumber: faxNumber,
    };

    const effectiveDate = formValues.effectiveDate.toString(); 
    if (effectiveDate === '00/00/0000') {
      formValues.effectiveDate = '00000000';
    } else if (!isNaN(new Date(effectiveDate).getTime())) {
      formValues.effectiveDate = this.datePipe.transform(new Date(effectiveDate), 'yyyyMMdd');
    }
    
    const expiryDate = formValues.expiryDate.toString(); 
    if (expiryDate === '00/00/0000') {
      formValues.expiryDate = '00000000';
    } else if (!isNaN(new Date(expiryDate).getTime())) {
      formValues.expiryDate = this.datePipe.transform(new Date(expiryDate), 'yyyyMMdd');
    }
    
    const reviewDate = formValues.reviewDate.toString(); 
    if (reviewDate === '00/00/0000') {
      formValues.reviewDate = '00000000';
    } else if (!isNaN(new Date(reviewDate).getTime())) {
      formValues.reviewDate = this.datePipe.transform(new Date(reviewDate), 'yyyyMMdd');
    }

    this.aaeEntryService.siteInfoUpdate(formValues)
    .subscribe(response =>{
      this.loading = false;
      if(response.status == 200){
        this.dialogRef.close({success:true});
      }
      },
      error => {
        this.loading = false;
        const reader = new FileReader();
        reader.onloadend = () => {
          const errorObj = JSON.parse(reader.result.toString());
          this.errorMsg = errorObj.errorMsg;
          console.log('errorMsg: ', this.errorMsg);
        };
        reader.readAsText(error.error);
        console.log('error: ', error);

        if (this.errorMsg === undefined){
          this.errorMsg = 'we have an error '
        }
        console.log('errorMsg: ' + this.errorMsg);
      }
      )
  }

  get isDisabled(): boolean {
    return !this.updateForm.valid || 
           this.quoteMetadata.allowUpdate !== 'Y' || 
           this.quoteMetadata.quoteType.toLowerCase() === 'service change-container sched/qty';
  }

  onTerritorySelectionChange(event: any) {
    const selectedTerritory = event.value;
    this.moveTerritorySelectedToTop(selectedTerritory);
    this.updateForm.controls.territoryCode.setValue(selectedTerritory);
    this.cdr.detectChanges(); 
  }

  private moveTerritorySelectedToTop(selectedValue: any) {
    const selectedIndex = this.territoryValues.findIndex(item => item.value === selectedValue);
    if (selectedIndex > -1) {
      const selectedItem = this.territoryValues[selectedIndex];
      this.territoryValues.splice(selectedIndex, 1);
      this.territoryValues.unshift(selectedItem);
  
      const filteredIndex = this.filteredTerritoryValues.findIndex(item => item.value === selectedValue);
      if (filteredIndex > -1) {
        this.filteredTerritoryValues.splice(filteredIndex, 1);
      }
  
      this.filteredTerritoryValues.unshift(selectedItem);
    }
  }

  onDropdownOpenedTerritory(opened: boolean) {
    if (opened && this.territoryViewport) {
      this.territoryViewport.scrollToIndex(this.scrollIndexTerritory);
    }
  }

  onTerritoryBlur() {
    if (this.territoryViewport) {
      this.territoryViewport.checkViewportSize();
    }
  }

  onFilterTerritory(filteredData: any[]) {
    this.filteredTerritoryValues = filteredData;
    this.cdr.detectChanges(); 
  }

  onScrollTerritory(index: number) {
    this.scrollIndexTerritory = index;
    const currentLength = this.filteredTerritoryValues.length;
    const more = this.territoryValues.slice(currentLength, currentLength + 20);
    this.filteredTerritoryValues = this.filteredTerritoryValues.concat(more);
    this.cdr.detectChanges(); 
  }

  onNaicsSelectionChange(event: any) {
    const selectedNaics = event.value;
    this.moveNaicsSelectedToTop(selectedNaics);
    this.updateForm.controls.naics.setValue(selectedNaics);
    this.cdr.detectChanges(); 
  }

  private moveNaicsSelectedToTop(selectedValue: any) {
    const selectedIndex = this.naicsValues.findIndex(item => item.value === selectedValue);
    if (selectedIndex > -1) {
      const selectedItem = this.naicsValues[selectedIndex];
      this.naicsValues.splice(selectedIndex, 1);
      this.naicsValues.unshift(selectedItem);
  
      const filteredIndex = this.filteredNaicsValues.findIndex(item => item.value === selectedValue);
      if (filteredIndex > -1) {
        this.filteredNaicsValues.splice(filteredIndex, 1);
      }
  
      this.filteredNaicsValues.unshift(selectedItem);
    }
  }

  onDropdownOpenedNaics(opened: boolean) {
    if (opened && this.naicsViewport) {
      this.naicsViewport.scrollToIndex(this.scrollIndexNaics);
    }
  }

  onNaicsBlur() {
    if (this.naicsViewport) {
      this.naicsViewport.checkViewportSize();
    }
  }

  onFilterNaics(filteredData: any[]) {
    this.filteredNaicsValues = filteredData;
    this.cdr.detectChanges(); 
  }

  onScrollNaics(index: number) {
    this.scrollIndexNaics = index;
    const currentLength = this.filteredNaicsValues.length;
    const more = this.naicsValues.slice(currentLength, currentLength + 20);
    this.filteredNaicsValues = this.filteredNaicsValues.concat(more);
    this.cdr.detectChanges(); 
  }

  onSalesRepSelectionChange(event: any) {
    const selectedSalesRep = event.value;
    this.moveSalesRepSelectedToTop(selectedSalesRep);
    this.updateForm.controls.salesRep.setValue(selectedSalesRep);
    this.cdr.detectChanges(); 
  }

  private moveSalesRepSelectedToTop(selectedValue: any) {
    const selectedIndex = this.salesRepValues.findIndex(item => item.value === selectedValue);
    if (selectedIndex > -1) {
      const selectedItem = this.salesRepValues[selectedIndex];
      this.salesRepValues.splice(selectedIndex, 1);
      this.salesRepValues.unshift(selectedItem);
  
      const filteredIndex = this.filteredSalesRepValues.findIndex(item => item.value === selectedValue);
      if (filteredIndex > -1) {
        this.filteredSalesRepValues.splice(filteredIndex, 1);
      }
  
      this.filteredSalesRepValues.unshift(selectedItem);
    }
  }

  onDropdownOpenedSalesRep(opened: boolean) {
    if (opened && this.salesRepViewport) {
      this.salesRepViewport.scrollToIndex(this.scrollIndexSalesRep);
    }
  }

  onSalesRepBlur() {
    if (this.salesRepViewport) {
      this.salesRepViewport.checkViewportSize();
    }
  }

  onFilterSalesRep(filteredData: any[]) {
    this.filteredSalesRepValues = filteredData;
    this.cdr.detectChanges(); 
  }

  onScrollSalesRep(index: number) {
    this.scrollIndexSalesRep = index;
    const currentLength = this.filteredSalesRepValues.length;
    const more = this.salesRepValues.slice(currentLength, currentLength + 20);
    this.filteredSalesRepValues = this.filteredSalesRepValues.concat(more);
    this.cdr.detectChanges(); 
  }
}
