import { Component, Inject, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AccountDetail, Container, QuoteMetadata, Site } from '@app/core/interfaces/aae-AccountDetail-interface';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';
import { ReplaySubject } from 'rxjs';
import { DropdownItem } from '@app/modules/auto-complete-dropdown/auto-complete-dropdown.component';
import { FormatNumDatePipe } from '@app/common/pipes/datePipe/format-num-date.pipe';
import { MatSelectChange } from '@angular/material';

@Component({
  selector: 'app-container-edit',
  templateUrl: './container-edit.component.html',
  styleUrls: ['./container-edit.component.css'],
  providers: [FormatNumDatePipe]
})
export class ContainerEditComponent implements OnInit {

  public accountDetail:  AccountDetail = {} as AccountDetail;
  public site: Site = {} as Site;
  sites: any;
  public container: Container = {} as Container;
  containers: any;
  public errorMsg: string;
  public loading : boolean;
  public quoteId: string;
  durationInSeconds: number = 5;
  errorsList: Error[];
  private formatNumDate = new FormatNumDatePipe();
  public quoteMetadata: QuoteMetadata = {} as QuoteMetadata;
  public containerSizeTypeValues: any;
  public filteredContainerSizeTypeValues: any;
  public containerIdCodeValues: any;
  public filteredContainerIdCodeValues: any;
  public stopCodeValues: any;
  public filteredStopCodeValues: any;
  public rateTypeValues: any;
  public filteredRateTypeValues: any;
  public accountTypeValues: any;
  public filteredAccountTypeValues: any;
  public specialHandlingValues: any;
  public filteredSpecialHandlingValues: any;
  public receiptReqdValues: any;
  public filteredReceiptReqdValues: any;
  public disposalCodeValues: any;
  public filteredDisposalCodeValues: any;
  public uomValues: any;
  public filteredUOMValues: any;
  public revenueDistributionCodeValues: any;
  public filteredRevenueDistributionCodeValues: any;
  public recurringChargeFrequencyValues: any;
  public filteredRecurringChargeFrequencyValues: any;
  public districtCodeValues: any;
  public filteredDistrictCodeValues: any;
  public matchingTypeSize: any;
  public matchingPriceCode: any;
  selectedTypeSize: any;
  selectedPriceCde: any;
  public description: any[] = []; 
  public cntrTypeSize: any[] = [];
  public disCdePriceCde: any[] = [];
  panelOpenState = false; 
  isDisposalCodeDisabled: boolean = true;

  updateForm = new FormGroup({
    stagingId: new FormControl(''),
    companyNumber: new FormControl(''),
    containerType: new FormControl(''),
    containerSize: new FormControl(''),
    compactorFlag: new FormControl(''),
    containerQtyOrder: new FormControl(''),
    accountType: new FormControl(''),
    containerOwned: new FormControl(''),
    oncallFlag: new FormControl('', [Validators.maxLength(1)]),
    gridNumber: new FormControl(''),
    periodLength: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(2)]),
    minNumberOfLifts: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(3)]),
    specialHandling: new FormControl(''),
    purchaseOrderRequired: new FormControl('', [Validators.maxLength(1)]),
    disposalCode: new FormControl(''),
    disposalPriceCode: new FormControl(''),
    revenueDistributionCode: new FormControl(''),
    recurringChargeFrequency: new FormControl(''),
    contractNumber: new FormControl('', [Validators.maxLength(10)]),
    contractGroupNumber: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(2)]),
    idCode: new FormControl(''),
    totalLifts: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(3)]),
    receiptRequired: new FormControl(''),
    unitOfMeasure: new FormControl(''),
    weightLimit: new FormControl(''),
    recurMnthsAdvBill: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(1)]),
    remoteMonitorFlag: new FormControl('', [Validators.maxLength(1)]),
    disposalRateRestriction: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(8)]),
    rateRestrOperDate: new FormControl('',),
    cityAccountNumber: new FormControl('', Validators.maxLength(20)),
    stopCode: new FormControl(''),
    rateType: new FormControl(''),
    districtCode: new FormControl(''),
    contractDescription: new FormControl(''),
    contractName: new FormControl(''),
    containerQtyOnSite: new FormControl(''),
    closeDate: new FormControl(''),
    billedToDate: new FormControl(''),
    sharedContainerID: new FormControl(''),
  });

  constructor (private location:Location,
    private route: ActivatedRoute,
    private aaeEntryService: AaeEntryService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<ContainerEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log(data);
      console.log('data above');
      this.accountDetail = data.accountDetail;
      this.containers = data.containers;
      this.container = data.container;
      this.sites = data.sites;
      this.site = data.site;
      this.errorsList = data.errorList;
      this.quoteMetadata = data.quoteMetadata;
    }

  ngOnInit() {

         this.aaeEntryService.getDropdownInfo(this.accountDetail.company, 'CONTAINER').subscribe(data => {
          this.cntrTypeSize = data.cntrTypeSize;
          this.disCdePriceCde = data.disCdePriceCde;

          this.matchingTypeSize = this.cntrTypeSize.find(typeSize =>
            typeSize.containerType === this.container.containerType &&
            typeSize.containerSize === this.container.containerSize &&
            typeSize.compactorFlag === this.container.compactor,

          this.matchingPriceCode = this.disCdePriceCde.find(priceCode =>
            priceCode.disposalCode === this.container.disposalCode &&
            priceCode.disposalPriceCode === this.container.disposalPriceCode  
          ));

          this.selectedTypeSize = this.matchingTypeSize;
          this.selectedPriceCde = this.matchingPriceCode;

          this.updateForm
          .patchValue({
          stagingId: this.container.containerStagingId,
            companyNumber: this.container.companyNumber,
            containerType: this.container.containerType,
            containerSize: this.container.containerSize,
            compactorFlag: this.container.compactor,
            containerQtyOrder: this.container.quantityOrder,
            accountType: this.container.accountType,
            containerOwned: this.container.customerOwned,
            oncallFlag: this.container.onCall,
            gridNumber: this.container.gridNumber,
            periodLength: this.container.periodLength,
            minNumberOfLifts: this.container.estimatedLifts,
            specialHandling: this.container.specialHandling,
            purchaseOrderRequired: this.container.poRequired,
            disposalCode: this.container.disposalCode,
            disposalPriceCode: this.container.disposalPriceCode,
            closeDate: this.formatNumDate.transform(this.container.closeDate),
            revenueDistributionCode: this.container.revenueDistribution,
            recurringChargeFrequency: this.container.billingFrequency,
            contractNumber: this.container.contractNumber,
            contractGroupNumber: this.container.contractGroup,
            idCode: this.container.idCode,
            containerQtyOnSite: this.container.containerQtyOnSite,
            totalLifts: this.container.totalLifts,
            receiptRequired: this.container.receiptRequired,
            unitOfMeasure: this.container.unitOfMeasure,
            weightLimit: this.container.weightLimit,
            billedToDate: this.formatNumDate.transform(this.container.billedToDate),
            recurMnthsAdvBill: this.container.recurMnthsAdvBill,
            remoteMonitorFlag: this.container.remoteMonitorFlag,
            disposalRateRestriction: this.container.disposalRateRestriction,
            rateRestrOperDate: this.container.rateRestrOperDate,
            cityAccountNumber: this.container.cityAccountNumber,
            stopCode: this.container.stopCode,
            rateType: this.container.rateType,
            districtCode: this.container.districtCode,
            contractDescription: this.container.contractDescription,
            contractName: this.container.contractName,
            sharedContainerID: this.container.sharedContainerId,
           });

          this.containerSizeTypeValues = data.cntrTypeSize;
          this.filteredContainerSizeTypeValues = this.containerSizeTypeValues.slice();
          this.containerIdCodeValues = data.containerIdcode;
          this.filteredContainerIdCodeValues = this.containerIdCodeValues.slice();
          this.stopCodeValues = data.stopCode;
          this.filteredStopCodeValues = this.stopCodeValues.slice();
          this.rateTypeValues = data.rateType;
          this.filteredRateTypeValues = this.rateTypeValues.slice();
          this.accountTypeValues = data.accountType;
          this.filteredAccountTypeValues = this.accountTypeValues.slice();
          this.specialHandlingValues = data.specialHandling;
          this.filteredSpecialHandlingValues = this.specialHandlingValues.slice();
          this.receiptReqdValues = data.receiptRequired;
          this.filteredReceiptReqdValues = this.receiptReqdValues.slice();
          this.disposalCodeValues = data.disCdePriceCde;
          this.filteredDisposalCodeValues = this.disposalCodeValues.slice();
          this.uomValues = data.uom;
          this.filteredUOMValues = this.uomValues.slice();
          this.revenueDistributionCodeValues = data.revenueDisCode;
          this.filteredRevenueDistributionCodeValues = this.revenueDistributionCodeValues.slice();
          this.recurringChargeFrequencyValues = data.recurringChargeFreq;
          this.filteredRecurringChargeFrequencyValues = this.recurringChargeFrequencyValues.slice();
          this.districtCodeValues = data.residentialDistrict;
          this.filteredDistrictCodeValues = this.districtCodeValues.slice();
         });

        if (this.quoteMetadata.formatType == "RESI") {
          this.updateForm.get('oncallFlag').disable();
          this.updateForm.get('minNumberOfLifts').disable();
          this.updateForm.get('specialHandling').disable();
          this.updateForm.get('purchaseOrderRequired').disable();
          this.updateForm.get('receiptRequired').disable();
          this.updateForm.get('unitOfMeasure').disable();
          this.updateForm.get('weightLimit').disable();
          this.updateForm.get('disposalRateRestriction').disable();
          this.updateForm.get('rateRestrOperDate').disable();
          this.updateForm.get('cityAccountNumber').disable();
          this.updateForm.get('sharedContainerID').disable();
        }

        if (this.quoteMetadata.formatType == "NON RESI") {
          this.updateForm.get('stopCode').disable();
          this.updateForm.get('rateType').disable();
          this.updateForm.get('districtCode').disable();
          this.enableDisposalCode();
        }

        this.updateForm.get('contractDescription').disable();
        this.updateForm.get('contractName').disable();
        this.updateForm.get('containerQtyOnSite').disable();
        this.updateForm.get('closeDate').disable();
        this.updateForm.get('billedToDate').disable();
  }

  save() {

    this.errorMsg = null;
    this.loading = true;

    this.aaeEntryService.containerInfoUpdate(this.updateForm.value)
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

        console.log('errorMsg: ' + this.errorMsg);
      })
  }

  onSelectionChange(event: MatSelectChange) {
    this.updateForm.get('containerType').setValue(event.value.containerType);
    this.updateForm.get('containerSize').setValue(event.value.containerSize);
    this.updateForm.get('compactorFlag').setValue(event.value.compactorFlag);
  }

  onDisposalChange(event: MatSelectChange) {
    this.updateForm.get('disposalCode').setValue(event.value.disposalCode);
    this.updateForm.get('disposalPriceCode').setValue(event.value.disposalPriceCode);
  }

  get isDisabled(): boolean {
    return !this.updateForm.valid || 
           this.quoteMetadata.allowUpdate !== 'Y';
  }

  enableDisposalCode() {
    if (this.quoteMetadata.formatType === "NON RESI") {
    this.isDisposalCodeDisabled = false;
    }
  }
  
}
