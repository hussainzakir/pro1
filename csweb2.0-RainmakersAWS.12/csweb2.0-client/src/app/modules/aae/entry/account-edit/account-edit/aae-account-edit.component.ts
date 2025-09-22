import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AccountDetail, Container, QuoteMetadata, Site } from '@app/core/interfaces/aae-AccountDetail-interface';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { ReplaySubject } from 'rxjs';
import { DropdownItem } from '@app/modules/auto-complete-dropdown/auto-complete-dropdown.component';
import { FormatPhonePipe } from '@app/common/pipes/phonePipe/format-phone.pipe';
import { FormatNumDatePipe } from '@app/common/pipes/datePipe/format-num-date.pipe';
import { FormatEnteredDatePipe } from '@app/common/pipes/datePipe/format-entered-date.pipe';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-account-edit',
  templateUrl: './aae-account-edit.component.html',
  styleUrls: ['./aae-account-edit.component.css'],
  providers: [FormatPhonePipe, FormatNumDatePipe]
})

export class AAEAccountEditComponent implements OnInit {

site:Site;
container:Container;
public accountDetail:  AccountDetail = {} as AccountDetail;
public quoteMetadata: QuoteMetadata = {} as QuoteMetadata;
public dataForErrorTable: any[];
public division: any;
public quoteId: any;
public errorMsg: string;
public loading : boolean;
private formatPhone = new FormatPhonePipe();
private formatNumDate = new FormatNumDatePipe();
private datePipe: DatePipe;
formatEnteredDatePipe = new FormatEnteredDatePipe();
durationInSeconds: number = 5;
errorsList: Error[];
public aquisitionCodeValues: any;
public invoiceGroupValues: any;
public accountClassValues: any;
public customerCategoryValues: any;
public lateFeePolicyValues: any;
public filteredAquisitionCodeValues: any;
public filteredInvoiceGroupValues: any;
public filteredAccountClassValues: any;
public filteredCustomerCategoryValues: any;
public filteredLateFeePolicyValues: any;

updateForm = new FormGroup({
  accountStagingId: new FormControl(''),
  companyNumber: new FormControl(''),
  customerName: new FormControl(''),
  attention: new FormControl(''),
  streetType: new FormControl(''),
  addressLine1: new FormControl(''),
  addressLine2: new FormControl(''),
  addressLine3: new FormControl(''),
  city: new FormControl(''),
  state: new FormControl('', [Validators.maxLength(3)]),
  postalCode: new FormControl('', [Validators.maxLength(10)]),
  telephone: new FormControl('',),
  phoneExtension: new FormControl(''),
  accountAlias: new FormControl(''),
  fax: new FormControl(''),
  accountContactEmail: new FormControl(''),
  chainCode: new FormControl(''),
  acquisitionCode: new FormControl(''),
  invoiceGroupCode: new FormControl(''),
  accountClassTable: new FormControl(''),
  customerCategory: new FormControl(''),
  siEligible: new FormControl('', [Validators.maxLength(1)]),
  lateFeePolicy: new FormControl(''),
  chargeAdministrativeFee: new FormControl(''),
  chargeServiceIntFee: new FormControl(''),
  chargeLateFee: new FormControl('', [Validators.maxLength(1)]),
  chargeFuelFee: new FormControl(''),
  chargeEnvironmentalFee: new FormControl('', [Validators.maxLength(1)]),
  printMethodDescription: new FormControl(''),
  erfFrfFlag: new FormControl('', [Validators.maxLength(1)]),
  nextReviewDate: new FormControl(''),
  acquisitionCodeDesc: new FormControl(''),
  lastInvoiceDate: new FormControl(''),
  accountClassDesc: new FormControl(''),
  onlinePaymentProfile: new FormControl(''),
  creditAnalyst: new FormControl(''),
});

    
    constructor(@Inject(MAT_DIALOG_DATA) public data: any, public dialog: MatDialog,
    private aaeEntryService: AaeEntryService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<AAEAccountEditComponent>) {
      this.accountDetail = data.accountDetail;
      this.errorsList = data.errorList;
      this.datePipe = new DatePipe('en-US');
      this.quoteMetadata = data.quoteMetadata;
      console.log(data); 
      this.dataForErrorTable = this.data.dataForErrorTable;
    }

  ngOnInit():void {
    
    //this is where we initialize the values
    this.updateForm
    .patchValue({
    accountStagingId: this.accountDetail.accountStagingId,
    companyNumber: this.accountDetail.company,
    customerName: this.accountDetail.customerName,
    attention: this.accountDetail.attention,
    streetType: this.accountDetail.streetType,
    addressLine1: this.accountDetail.addressLine1,
    addressLine2: this.accountDetail.addressLine2,
    addressLine3: this.accountDetail.addressLine3,
    city: this.accountDetail.city,
    state: this.accountDetail.state,
    postalCode: this.accountDetail.postalCode,
    telephone: this.formatPhone.transform(this.accountDetail.telephone),
    phoneExtension: this.accountDetail.phoneExtension,
    accountAlias: this.accountDetail.accountAlias,
    fax: this.formatPhone.transform(this.accountDetail.fax),
    accountContactEmail: this.accountDetail.accountContactEmail,
    chainCode: this.accountDetail.chainCode,
    acquisitionCode: this.accountDetail.acquisitionCode,
    invoiceGroupCode: this.accountDetail.invoiceGroupCode,
    accountClassTable: this.accountDetail.accountClassTable,
    customerCategory: this.accountDetail.customerCategory,
    siEligible: this.accountDetail.siEligible,
    creditAnalyst: this.accountDetail.creditAnalyst,
    lateFeePolicy: this.accountDetail.lateFeePolicy,
    chargeAdministrativeFee: this.accountDetail.chargeAdministrativeFee,
    chargeServiceIntFee: this.accountDetail.chargeServiceIntFee,
    chargeLateFee: this.accountDetail.chargeLateFee,
    chargeFuelFee: this.accountDetail.chargeFuelFee,
    chargeEnvironmentalFee: this.accountDetail.chargeEnvironmentalFee,
    printMethodDescription: this.accountDetail.printMethodDescription,
    erfFrfFlag: this.accountDetail.erfFrfFlag,
    nextReviewDate: this.formatNumDate.transform(this.accountDetail.nextReviewDate),
    lastInvoiceDate: this.formatNumDate.transform(this.accountDetail.lastInvoiceDate),
    acquisitionCodeDesc: this.accountDetail.acquisitionCodeDesc,
     });

     this.updateForm.get('telephone').valueChanges.subscribe(value => {
      if (typeof value === 'string') {
        const formattedValue = this.formatPhone.transform(value);
        this.updateForm.get('telephone').setValue(formattedValue, { emitEvent: false });
      }
    });
    
    this.aaeEntryService.getDropdownInfo(this.accountDetail.company, 'ACCOUNT').subscribe(data => {

      this.aquisitionCodeValues = data.acquisitionCode;
      this.filteredAquisitionCodeValues = this.aquisitionCodeValues.slice();
      this.invoiceGroupValues = data.invoiceGroup;
      this.filteredInvoiceGroupValues = this.invoiceGroupValues.slice();
      this.accountClassValues = data.accountClass;
      this.filteredAccountClassValues = this.accountClassValues.slice();
      this.customerCategoryValues = data.customerCategory;
      this.filteredCustomerCategoryValues = this.customerCategoryValues.slice();
      this.lateFeePolicyValues = data.latePayFeePolicy;
      this.filteredLateFeePolicyValues = this.lateFeePolicyValues.slice();
     });


    this.updateForm.get('fax').valueChanges.subscribe(value => {
      if (typeof value === 'string') {
        const formattedValue = this.formatPhone.transform(value);
        this.updateForm.get('fax').setValue(formattedValue, { emitEvent: false });
      }
    });
    this.updateForm.get('nextReviewDate').valueChanges.subscribe(value => {
      if (typeof value === 'string') {
        const formattedValue = this.formatEnteredDatePipe.transform(value);
        this.updateForm.get('nextReviewDate').setValue(formattedValue, { emitEvent: false });
      }
    });

    this.updateForm.get('acquisitionCodeDesc').disable();
    this.updateForm.get('lastInvoiceDate').disable();
    this.updateForm.get('accountClassDesc').disable();
    this.updateForm.get('onlinePaymentProfile').disable();
    this.updateForm.get('creditAnalyst').disable();

    if (this.errorsList && this.errorsList.length == 0 || this.dataForErrorTable && this.dataForErrorTable.every(error => error.key && error.key !== 'CUINGP')) {
      this.updateForm.get('invoiceGroupCode').disable();
    } else {
      this.updateForm.get('invoiceGroupCode').enable();
    }
  }

  save() {

    this.errorMsg = null;
    this.loading = true;

    let telephone = this.updateForm.get('telephone').value;
    telephone = ('' + telephone).replace(/\D/g, '');

    let fax = this.updateForm.get('fax').value;
    fax = ('' + fax).replace(/\D/g, '');

    const formValues = {
      ...this.updateForm.value,
      telephone: telephone,
      fax: fax,
    };

    const nextReviewDate = formValues.nextReviewDate.toString(); 
    if (nextReviewDate === '00/00/0000') {
      formValues.nextReviewDate = '00000000';
    } else if (!isNaN(new Date(nextReviewDate).getTime())) {
      formValues.nextReviewDate = this.datePipe.transform(new Date(nextReviewDate), 'yyyyMMdd');
    }

    console.log("telephone:" + telephone.value);
    this.aaeEntryService.accountInfoUpdate(formValues)
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
      }
      )
  }

  get isDisabled(): boolean {
    return !this.updateForm.valid || 
           this.quoteMetadata.allowUpdate !== 'Y';
  }

}