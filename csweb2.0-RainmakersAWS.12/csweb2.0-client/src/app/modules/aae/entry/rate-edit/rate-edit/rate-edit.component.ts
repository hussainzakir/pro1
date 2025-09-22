import { Component, Inject, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, Params } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import { LoaderBtnDirective } from '@app/common/directives/loader-btn.directive';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';
import { AccountDetail, Container, QuoteMetadata, Rate, Site } from '@app/core/interfaces/aae-AccountDetail-interface';
import { ReplaySubject } from 'rxjs';
import { DropdownItem } from '@app/modules/auto-complete-dropdown/auto-complete-dropdown.component';
import { FormatEnteredDatePipe } from '@app/common/pipes/datePipe/format-entered-date.pipe';
import { FormatNumDatePipe } from '@app/common/pipes/datePipe/format-num-date.pipe';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-rate-edit',
  templateUrl: './rate-edit.component.html',
  styleUrls: ['./rate-edit.component.css'],
  providers: [FormatNumDatePipe]
})
export class RateEditComponent implements OnInit {

  public accountDetail:  AccountDetail = {} as AccountDetail;
  public site: Site = {} as Site;
  public container: Container = {} as Container;
  public rate: Rate = {} as Rate;
  private datePipe: DatePipe;
  private formatNumDate = new FormatNumDatePipe();
  containers: any;
  rates: any;
  public quoteId: string;
  public errorMsg: string;
  public loading : boolean;
  durationInSeconds: number = 5;
  errorsList: Error[];
  formatEnteredDatePipe = new FormatEnteredDatePipe();
  public quoteMetadata: QuoteMetadata = {} as QuoteMetadata;
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
    companyNumber: new FormControl(''),
    rateEffectiveDate: new FormControl(''),
    closeDate: new FormControl(''),
    chargeCode: new FormControl(''),
    chargeType: new FormControl(''),
    chargeMethod: new FormControl(''),
    chargeRate: new FormControl('', [Validators.pattern(/^-?\d{1,9}(\.\d{1,2})?$/), Validators.maxLength(12)]),
    wasteMaterialType: new FormControl(''),
    unitOfMeasure: new FormControl(''),
  });

  constructor (private location:Location,
    private route: ActivatedRoute,
    private aaeEntryService: AaeEntryService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<RateEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log(data);
      console.log('data above');
      this.accountDetail = data.accountDetail;
      this.containers = data.containers;
      this.container = data.container;
      this.site = data.site;
      this.rate = data.rate;
      this.rates = data.rates;
      this.errorsList = data.errorList;
      this.datePipe = new DatePipe('en-US'); 
      this.quoteMetadata = data.quoteMetadata;
    }

  ngOnInit() {

    this.updateForm
    .patchValue({
      rateStagingId: this.rate.rateStagingId,
      companyNumber: this.rate.company,
      rateEffectiveDate: this.formatNumDate.transform(this.rate.effectiveDate),
      closeDate: this.formatNumDate.transform(this.rate.closeDate),
      chargeCode: this.rate.chargeCode,
      chargeType: this.rate.chargeType,
      chargeMethod: this.rate.chargeMethod,
      chargeRate: this.rate.chargeRate,
      wasteMaterialType: this.rate.wasteMaterialType,
      unitOfMeasure: this.rate.unitOfMeasure,
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
  
    const rateEffectiveDate = formValue.rateEffectiveDate.toString(); 
    if (rateEffectiveDate === '00/00/0000') {
      formValue.rateEffectiveDate = '00000000';
    } else if (!isNaN(new Date(rateEffectiveDate).getTime())) {
      formValue.rateEffectiveDate = this.datePipe.transform(new Date(rateEffectiveDate), 'yyyyMMdd');
    }

    const closeDate = formValue.closeDate.toString(); 
    if (closeDate === '00/00/0000') {
      formValue.closeDate = '00000000';
    } else if (!isNaN(new Date(closeDate).getTime())) {
      formValue.closeDate = this.datePipe.transform(new Date(closeDate), 'yyyyMMdd');
    }

    this.aaeEntryService.rateInfoUpdate(formValue)
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

