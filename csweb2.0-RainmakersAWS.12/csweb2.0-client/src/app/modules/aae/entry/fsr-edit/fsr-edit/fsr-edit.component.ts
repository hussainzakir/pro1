import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { AccountDetail, Container, QuoteMetadata, Site } from '@app/core/interfaces/aae-AccountDetail-interface';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';
import { CommonModule, DatePipe, Location } from '@angular/common';
import { FormatEnteredDatePipe } from '@app/common/pipes/datePipe/format-entered-date.pipe';
import { FormatNumDatePipe } from '@app/common/pipes/datePipe/format-num-date.pipe';
import { AbstractControl, ValidatorFn } from '@angular/forms';

@Component({
  selector: 'app-fsr-edit',
  templateUrl: './fsr-edit.component.html',
  styleUrls: ['./fsr-edit.component.css'],
})

export class FutureServiceRequestEditComponent implements OnInit {

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
  private datePipe: DatePipe;
  formatEnteredDatePipe = new FormatEnteredDatePipe();
  private formatNumDate = new FormatNumDatePipe();

  updateForm = new FormGroup({
    stagingId: new FormControl(''),
    fsrEffectiveDate: new FormControl('', [Validators.required, this.futureDateValidator()]),
    fsrQtyOnOrder: new FormControl('', [Validators.pattern('^[0-9]*$')]),
    fsrTotalLifts: new FormControl('', [Validators.pattern('^[0-9]*$')]),
    fsrTimeFrame: new FormControl('', [Validators.pattern('^[0-9]*$')]),
    fsrTimeFrameReference: new FormControl('', [Validators.pattern('^[a-zA-Z]*$'), Validators.maxLength(1)]),
    fsrOnCall: new FormControl(''),
    fsrMinLifts: new FormControl('', [Validators.pattern('^[0-9]*$')]),
  });

  constructor (
    private route: ActivatedRoute,
    private aaeEntryService: AaeEntryService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<FutureServiceRequestEditComponent>,
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
      this.datePipe = new DatePipe('en-US');
    }

  ngOnInit() {

    this.updateForm
    .patchValue({
      stagingId: this.container.containerStagingId,
      fsrEffectiveDate: this.formatNumDate.transform(this.container.fsrEffectiveDate),
      fsrQtyOnOrder: this.container.fsrQtyOnOrder,
      fsrTotalLifts: this.container.fsrTotalLifts,
      fsrTimeFrame: this.container.fsrTimeFrame,
      fsrTimeFrameReference: this.container.fsrTimeFrameRef,
      fsrOnCall: this.container.fsrOnCall,
      fsrMinLifts: this.container.fsrMinLifts,
     });

     if (this.quoteMetadata.formatType == "RESI") {
      this.updateForm.get('fsrOnCall').disable();
      this.updateForm.get('fsrMinLifts').disable();
    }

     this.updateForm.get('fsrEffectiveDate').valueChanges.subscribe(value => {
      if (typeof value === 'string') {
        const formattedValue = this.formatEnteredDatePipe.transform(value);
        this.updateForm.get('fsrEffectiveDate').setValue(formattedValue, { emitEvent: false });
      }
    });

    this.updateForm.get('fsrOnCall').valueChanges.subscribe(value => {
      const minLiftsControl = this.updateForm.get('fsrMinLifts');
      const fsrOnCallControl = this.updateForm.get('fsrOnCall');
    
      if (fsrOnCallControl.enabled) {
        if (value === 'Y') {
          minLiftsControl.setValidators([Validators.required, Validators.pattern('^[0-9]*$')]);
        } else {
          minLiftsControl.clearValidators();
        }
        minLiftsControl.updateValueAndValidity();
      }
    });
   }

    save() {

      this.errorMsg = null;
      this.loading = true;

      const formValue = {
        ...this.updateForm.value,
      };

      const fsrEffectiveDate = formValue.fsrEffectiveDate.toString();
      if (fsrEffectiveDate === '00/00/0000') {
        formValue.fsrEffectiveDate = '00000000';
      } else if (!isNaN(new Date(fsrEffectiveDate).getTime())) {
        formValue.fsrEffectiveDate = this.datePipe.transform(new Date(fsrEffectiveDate), 'yyyyMMdd');
      }

      this.aaeEntryService.containerInfoUpdate(formValue)
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

      futureDateValidator(): ValidatorFn {
        return (control: AbstractControl): { [key: string]: any } | null => {
          if (!control.value) {
            return null; 
          }
      
          const inputDate = new Date(control.value);
          const today = new Date();
          today.setHours(0, 0, 0, 0); 
      
          return inputDate >= today ? null : { 'invalidDate': { value: control.value } };
        };
  }
}