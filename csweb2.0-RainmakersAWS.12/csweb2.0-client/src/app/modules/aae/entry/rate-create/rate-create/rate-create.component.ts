import { Component, Inject, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { AccountDetail, Container, QuoteMetadata, Rate, Site } from '@app/core/interfaces/aae-AccountDetail-interface';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';
import { DatePipe } from '@angular/common';
import { FormatEnteredDatePipe } from '@app/common/pipes/datePipe/format-entered-date.pipe';
import { ReplaySubject } from 'rxjs';
import { DropdownItem } from '@app/modules/auto-complete-dropdown/auto-complete-dropdown.component';

@Component({
  selector: 'app-rate-create',
  templateUrl: './rate-create.component.html',
  styleUrls: ['./rate-create.component.css']
})
export class RateCreateComponent implements OnInit {

  public accountDetail:  AccountDetail = {} as AccountDetail;
  public site: Site = {} as Site;
  public container: Container = {} as Container;
  public quoteMetadata: QuoteMetadata = {} as QuoteMetadata;
  public rate: Rate = {} as Rate;
  private datePipe: DatePipe;
  containers: any;
  rates: any;
  sites: any;
  public isOpen = true;
  public errorMsg: string;
  public loading : boolean;
  public rateStagingId: any;
  durationInSeconds: number = 5;
  formatEnteredDate: FormatEnteredDatePipe;
  formatEnteredDatePipe = new FormatEnteredDatePipe();
  public quoteId: string;
  public chargeCodeValues: any;
  public chargeTypeValues: any;
  public wasteMaterialTypeValues: any;
  public chargeMethodValues: any;
  public filteredChargeCodeValues: any;
  public filteredChargeTypeValues: any;
  public filteredChargeMethodValues: any;
  public filteredWasteMaterialTypeValues: any;

  updateForm = new FormGroup({
    rateStagingId: new FormControl(''),
    projectId: new FormControl(''),
    companyNumber: new FormControl(''),
    customerAccount: new FormControl(''),
    site: new FormControl(''),
    containerGroup: new FormControl(''),
    rateEffectiveDate: new FormControl('', [Validators.pattern('^[0-9/]*$'), Validators.maxLength(10)]),
    closeDate: new FormControl('', [Validators.pattern('^[0-9/]*$'), Validators.maxLength(10)]),
    chargeCode: new FormControl(''),
    chargeType: new FormControl(''),
    chargeMethod: new FormControl(''),
    chargeRate: new FormControl('', [Validators.pattern(/^-?\d{1,9}(\.\d{1,2})?$/), Validators.maxLength(12)]),
    wasteMaterialType: new FormControl(''),
    unitOfMeasure: new FormControl(''),
  });

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private aaeEntryService: AaeEntryService,
    private dialogRef: MatDialogRef<RateCreateComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { 
      console.log(data);
      console.log('data above');
      this.accountDetail = data.accountDetail;
      this.containers = data.containers;
      this.container = data.container;
      this.sites = data.sites;
      this.site = data.site;
      this.rates = data.rates;
      this.rate = data.rate;
      this.quoteMetadata = data.quoteMetadata;
      this.rateStagingId = '0';
      this.datePipe = new DatePipe('en-US'); 
    }

  ngOnInit() {
    this.updateForm
    .patchValue({
      rateStagingId: this.rateStagingId,
      projectId: this.quoteMetadata.projectId,
      companyNumber: this.accountDetail.company,
      customerAccount: this.accountDetail.account,
      site: this.site.site,
      containerGroup: this.container.containerId,
    });

   this.aaeEntryService.getDropdownInfo(this.accountDetail.company, 'RATE').subscribe(data => {

    this.chargeCodeValues = data.chargeCode;
    this.filteredChargeCodeValues = this.chargeCodeValues.slice();
    this.chargeTypeValues = data.chargeType;
    this.filteredChargeTypeValues = this.chargeTypeValues.slice();
    this.chargeMethodValues = data.chargeMethod;
    this.filteredChargeMethodValues = this.chargeMethodValues.slice();
    this.wasteMaterialTypeValues = data.wasteMaterialType;  
    this.filteredWasteMaterialTypeValues = this.wasteMaterialTypeValues.slice();
  });

    this.updateForm.get('closeDate').valueChanges.subscribe(value => {
      if (typeof value === 'string') {
        const formattedValue = this.formatEnteredDatePipe.transform(value);
        this.updateForm.get('closeDate').setValue(formattedValue, { emitEvent: false });
      }
    });

    this.updateForm.get('rateEffectiveDate').valueChanges.subscribe(value => {
      if (typeof value === 'string') {
        const formattedValue = this.formatEnteredDatePipe.transform(value);
        this.updateForm.get('rateEffectiveDate').setValue(formattedValue, { emitEvent: false });
      }
    });

      if (this.quoteMetadata.formatType == "RESI") {
        this.updateForm.get('wasteMaterialType').disable();
        this.updateForm.get('unitOfMeasure').disable();
      }
  }

  save() {
    this.errorMsg = null;
    this.loading = true;
  
    const formValue = { ...this.updateForm.value };
  
    const rateEffectiveDate = new Date(formValue.rateEffectiveDate);
    const closeDate = new Date(formValue.closeDate);
    if (!isNaN(rateEffectiveDate.getTime())) {
      formValue.rateEffectiveDate = this.datePipe.transform(rateEffectiveDate, 'yyyyMMdd');
    }
    if (!isNaN(closeDate.getTime())) {
      formValue.closeDate = this.datePipe.transform(closeDate, 'yyyyMMdd');
    }
  
    this.aaeEntryService.rateInfoCreate(formValue)  
    .subscribe(response => {
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
    });
  }

  get isDisabled(): boolean {
    return !this.updateForm.valid || 
           this.quoteMetadata.allowUpdate !== 'Y';
  }
  
}
