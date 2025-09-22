import { ChangeDetectorRef, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, Params } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import { LoaderBtnDirective } from '@app/common/directives/loader-btn.directive';
import { AccountDetail, Site, Container, QuoteMetadata } from '@app/core/interfaces/aae-AccountDetail-interface';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';
import { DropdownItem } from '@app/modules/auto-complete-dropdown/auto-complete-dropdown.component';
import { ReplaySubject } from 'rxjs';
import { MatSelectChange } from '@angular/material';
import { CdkVirtualScrollViewport } from '@angular/cdk/scrolling';


@Component({
  selector: 'app-sales-transactions-edit',
  templateUrl: './sales-transactions-edit.component.html',
  styleUrls: ['./sales-transactions-edit.component.css']
})
export class SalesTransactionsEditComponent implements OnInit {

  public accountDetail:  AccountDetail = {} as AccountDetail;
  public site: Site = {} as Site;
  public container: Container = {} as Container;
  containers: any;
  public errorMsg: string;
  public loading : boolean;
  durationInSeconds: number = 5;
  errorsList: Error[];
  public quoteMetadata: QuoteMetadata = {} as QuoteMetadata;
  public dataForErrorTable: any[];
  public leadSourceValues: any;
  public serviceRepValues: any;
  public signingRepValues: any;
  public comCodeValues: any;
  public transactionReasonCodeValues: any;
  public filteredLeadSourceValues: any;
  public filteredServiceRepValues: any;
  public filteredSigningRepValues: any;
  public filteredComCodeValues: any;
  public filteredTransactionReasonCodeValues: any;
  scrollIndexSigningRep: number = 0;
  scrollIndexServiceRep: number = 0;
  scrollIndexComCode: number = 0;
  public transactionReasonCode: any[] = [];
  public matchingTransReasonCode: any;
  selectedTransReasonCode: any;

  @ViewChild('signingRepViewport') signingRepViewport: CdkVirtualScrollViewport;
  @ViewChild('serviceRepViewport') serviceRepViewport: CdkVirtualScrollViewport;
  @ViewChild('comCodeViewport') comCodeViewport: CdkVirtualScrollViewport;

  updateForm = new FormGroup({
    stagingId: new FormControl(''),
    companyNumber: new FormControl(''),
    sdtransactionCode: new FormControl(''),
    sdReasonCode: new FormControl(''),
    sdServicingRep: new FormControl(''),
    sdSigningRep: new FormControl(''),
    sdCompetitorCode: new FormControl(''),
    sdCsaContractNumber: new FormControl(''),
    sdLeadSource: new FormControl(''),
  });

  constructor (private location:Location, private cdr: ChangeDetectorRef,
    private route: ActivatedRoute,
    private aaeEntryService: AaeEntryService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<SalesTransactionsEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log(data);
      console.log('data above');
      this.accountDetail = data.accountDetail;
      this.containers = data.containers;
      this.container = data.container;
      this.site = data.site;
      this.errorsList = data.errorList;
      this.quoteMetadata = data.quoteMetadata;
      this.dataForErrorTable = this.data.dataForErrorTable;
    }

  ngOnInit() {

    this.aaeEntryService.getDropdownInfo(this.accountDetail.company, 'SALES').subscribe(data => {
      this.transactionReasonCode = data.transactionReasonCode;

      this.matchingTransReasonCode = this.transactionReasonCode.find(transReasonCode =>
        transReasonCode.transactionCode === this.container.sdtransactionCode &&
        transReasonCode.reasonCode === this.container.sdReasonCode
      );

      this.selectedTransReasonCode = this.matchingTransReasonCode;

    this.updateForm
    .patchValue({
      stagingId: this.container.containerStagingId,
      companyNumber: this.container.companyNumber,
      sdtransactionCode: this.container.sdtransactionCode,
      sdReasonCode: this.container.sdReasonCode,
      sdServicingRep: this.container.sdServicingRep,
      sdSigningRep: this.container.sdSigningRep,
      sdCompetitorCode: this.container.sdCompetitorCode,
      sdCsaContractNumber: this.container.sdCsaContractNumber,
      sdLeadSource: this.container.sdLeadSource,
     });

        this.leadSourceValues = data.leadsourceCode;
        this.filteredLeadSourceValues = this.leadSourceValues.slice();
        this.serviceRepValues = data.servicingRep;
        this.filteredServiceRepValues = this.serviceRepValues.slice(0, 20);
        this.signingRepValues = data.signingRep;
        this.filteredSigningRepValues = this.signingRepValues.slice(0, 20);
        this.comCodeValues = data.competitorCode;
        this.filteredComCodeValues = this.comCodeValues.slice(0, 20);
        this.transactionReasonCodeValues = data.transactionReasonCode;
        this.filteredTransactionReasonCodeValues = this.transactionReasonCodeValues.slice();

        this.filteredServiceRepValues = this.serviceRepValues;
        this.filteredSigningRepValues = this.signingRepValues;
        this.filteredComCodeValues = this.comCodeValues;
        this.moveSigningRepSelectedToTop(this.updateForm.get('sdSigningRep').value);
        this.moveServiceRepSelectedToTop(this.updateForm.get('sdServicingRep').value);
        this.moveComCodeSelectedToTop(this.updateForm.get('sdCompetitorCode').value);
        this.cdr.detectChanges();
  });
  if (this.accountDetail.account != '9999999' && this.dataForErrorTable && this.dataForErrorTable.every(error => error.key && error.key !== 'SDSNRP')) {
    this.updateForm.get('sdSigningRep').disable();
  } else {
    this.updateForm.get('sdSigningRep').enable();
  }

  if (this.accountDetail.account != '9999999' && this.dataForErrorTable && this.dataForErrorTable.every(error => error.key && error.key !== 'SDSVRP')) {
    this.updateForm.get('sdServicingRep').disable();
  } else {
    this.updateForm.get('sdServicingRep').enable();
  }
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
      }
      )
  }

  onServiceRepSelectionChange(event: any) {
    const selectedServiceRep = event.value;
    this.moveServiceRepSelectedToTop(selectedServiceRep);
    this.updateForm.controls.sdServicingRep.setValue(selectedServiceRep);
    this.cdr.detectChanges(); 
  }

  private moveServiceRepSelectedToTop(selectedValue: any) {
    const selectedIndex = this.serviceRepValues.findIndex(item => item.value === selectedValue);
    if (selectedIndex > -1) {
      const selectedItem = this.serviceRepValues[selectedIndex];
      this.serviceRepValues.splice(selectedIndex, 1);
      this.serviceRepValues.unshift(selectedItem);
  
      const filteredIndex = this.filteredServiceRepValues.findIndex(item => item.value === selectedValue);
      if (filteredIndex > -1) {
        this.filteredServiceRepValues.splice(filteredIndex, 1);
      }
  
      this.filteredServiceRepValues.unshift(selectedItem);
    }
  }

  onDropdownOpenedServiceRep(opened: boolean) {
    if (opened && this.serviceRepViewport) {
      this.serviceRepViewport.scrollToIndex(this.scrollIndexServiceRep);
    }
  }

  onServiceRepBlur() {
    if (this.serviceRepViewport) {
      this.serviceRepViewport.checkViewportSize();
    }
  }

  onFilterServiceRep(filteredData: any[]) {
    this.filteredServiceRepValues = filteredData;
    this.cdr.detectChanges(); 
  }

  onScrollServiceRep(index: number) {
    this.scrollIndexServiceRep = index;
    const currentLength = this.filteredServiceRepValues.length;
    const more = this.serviceRepValues.slice(currentLength, currentLength + 20);
    this.filteredServiceRepValues = this.filteredServiceRepValues.concat(more);
    this.cdr.detectChanges(); 
  }

  onSigningRepSelectionChange(event: any) {
    const selectedSigningRep = event.value;
    this.moveSigningRepSelectedToTop(selectedSigningRep);
    this.updateForm.controls.sdSigningRep.setValue(selectedSigningRep);
    this.cdr.detectChanges(); 
  }

  private moveSigningRepSelectedToTop(selectedValue: any) {
    const selectedIndex = this.signingRepValues.findIndex(item => item.value === selectedValue);
    if (selectedIndex > -1) {
      const selectedItem = this.signingRepValues[selectedIndex];
      this.signingRepValues.splice(selectedIndex, 1);
      this.signingRepValues.unshift(selectedItem);
  
      const filteredIndex = this.filteredSigningRepValues.findIndex(item => item.value === selectedValue);
      if (filteredIndex > -1) {
        this.filteredSigningRepValues.splice(filteredIndex, 1);
      }
  
      this.filteredSigningRepValues.unshift(selectedItem);
    }
  }

  onDropdownOpenedSigningRep(opened: boolean) {
    if (opened && this.signingRepViewport) {
      this.signingRepViewport.scrollToIndex(this.scrollIndexSigningRep);
    }
  }

  onSigningRepBlur() {
    if (this.signingRepViewport) {
      this.signingRepViewport.checkViewportSize();
    }
  }

  onFilterSigningRep(filteredData: any[]) {
    this.filteredSigningRepValues = filteredData;
    this.cdr.detectChanges(); 
  }

  onScrollSigningRep(index: number) {
    this.scrollIndexSigningRep = index;
    const currentLength = this.filteredSigningRepValues.length;
    const more = this.signingRepValues.slice(currentLength, currentLength + 20);
    this.filteredSigningRepValues = this.filteredSigningRepValues.concat(more);
    this.cdr.detectChanges(); 
  }

  onComCodeSelectionChange(event: any) {
    const selectedComCode = event.value;
    this.moveComCodeSelectedToTop(selectedComCode);
    this.updateForm.controls.sdCompetitorCode.setValue(selectedComCode);
    this.cdr.detectChanges(); 
  }

  private moveComCodeSelectedToTop(selectedValue: any) {
    const selectedIndex = this.comCodeValues.findIndex(item => item.value === selectedValue);
    if (selectedIndex > -1) {
      const selectedItem = this.comCodeValues[selectedIndex];
      this.comCodeValues.splice(selectedIndex, 1);
      this.comCodeValues.unshift(selectedItem);
  
      const filteredIndex = this.filteredComCodeValues.findIndex(item => item.value === selectedValue);
      if (filteredIndex > -1) {
        this.filteredComCodeValues.splice(filteredIndex, 1);
      }
  
      this.filteredComCodeValues.unshift(selectedItem);
    }
  }

  onDropdownOpenedComCode(opened: boolean) {
    if (opened && this.comCodeViewport) {
      this.comCodeViewport.scrollToIndex(this.scrollIndexComCode);
    }
  }

  onComCodeBlur() {
    if (this.comCodeViewport) {
      this.comCodeViewport.checkViewportSize();
    }
  }

  onFilterComCode(filteredData: any[]) {
    this.filteredComCodeValues = filteredData;
    this.cdr.detectChanges(); 
  }

  onScrollComCode(index: number) {
    this.scrollIndexComCode = index;
    const currentLength = this.filteredComCodeValues.length;
    const more = this.comCodeValues.slice(currentLength, currentLength + 20);
    this.filteredComCodeValues = this.filteredComCodeValues.concat(more);
    this.cdr.detectChanges(); 
  }

  onSelectionChange(event: MatSelectChange) {
    this.updateForm.get('sdtransactionCode').setValue(event.value.transactionCode);
    this.updateForm.get('sdReasonCode').setValue(event.value.reasonCode);
  }

  get isDisabled(): boolean {
    return !this.updateForm.valid || 
           this.quoteMetadata.allowUpdate !== 'Y';
  }
}
