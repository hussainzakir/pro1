import { Component, Inject, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, Params } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormGroup } from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import { LoaderBtnDirective } from '@app/common/directives/loader-btn.directive';
import { AccountDetail, Container, QuoteMetadata, Site } from '@app/core/interfaces/aae-AccountDetail-interface';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';
import { FormatEnteredDatePipe } from '@app/common/pipes/datePipe/format-entered-date.pipe';
import { FormatNumDatePipe } from '@app/common/pipes/datePipe/format-num-date.pipe';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-route-edit',
  templateUrl: './route-edit.component.html',
  styleUrls: ['./route-edit.component.css'],
  providers: [FormatNumDatePipe]
})
export class RouteEditComponent implements OnInit {

  public accountDetail:  AccountDetail = {} as AccountDetail;
  public site: Site = {} as Site;
  sites: any;
  public container: Container = {} as Container;
  containers: any;
  public errorMsg: string;
  public loading : boolean;
  durationInSeconds: number = 5;
  private datePipe: DatePipe;
  private formatNumDate = new FormatNumDatePipe();
  formatEnteredDatePipe = new FormatEnteredDatePipe();
  public quoteMetadata: QuoteMetadata = {} as QuoteMetadata;
  errorsList: Error[];
  public dataForErrorTable: any[];

  updateForm = new FormGroup({
    stagingId: new FormControl(''),
    companyNumber: new FormControl(''),
    containerQtyOnSite: new FormControl(''),
    routeRequestedDelivery: new FormControl(''),
    containerQtyOrder: new FormControl(''),
    routePOnumber: new FormControl(''),
    routeUrPlanDate: new FormControl(''),
    routeNotes: new FormControl(''),
    containerOrder: new FormControl('')
  });

  constructor (private location:Location,
    private route: ActivatedRoute,
    private aaeEntryService: AaeEntryService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<RouteEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log(data);
      console.log('data above');
      this.accountDetail = data.accountDetail;
      this.containers = data.containers;
      this.container = data.container;
      this.sites = data.sites;
      this.site = data.site;
      this.datePipe = new DatePipe('en-US'); 
      this.errorsList = data.errorList;
      this.quoteMetadata = data.quoteMetadata;
      this.dataForErrorTable = this.data.dataForErrorTable;
    }

  ngOnInit() {

    this.updateForm
    .patchValue({
     stagingId: this.container.containerStagingId,
     companyNumber: this.container.companyNumber,
     containerQtyOnSite: this.container.containerQtyOnSite,
     routeRequestedDelivery: this.container.routeRequestedDelivery,
     containerQtyOrder: this.container.quantityOrder,
     routePOnumber: this.container.routePOnumber,
     routeUrPlanDate: this.formatNumDate.transform(this.container.routeUrPlanDate),
     routeNotes: this.container.routeNotes,
     containerOrder: this.container.quantityOrder
     });

     this.updateForm.get('routeUrPlanDate').valueChanges.subscribe(value => {
      if (typeof value === 'string') {
        const formattedValue = this.formatEnteredDatePipe.transform(value);
        this.updateForm.get('routeUrPlanDate').setValue(formattedValue, { emitEvent: false });
      }
    });

    this.updateForm.get('containerOrder').disable();
  }

  save() {

    this.errorMsg = null;
    this.loading = true;

    const formValue = { ...this.updateForm.value };
  
    const routeUrPlanDate = formValue.routeUrPlanDate.toString();
    if (routeUrPlanDate === '00/00/0000') {
      formValue.routeUrPlanDate = '00000000';
    } else if (!isNaN(new Date(routeUrPlanDate).getTime())) {
      formValue.routeUrPlanDate = this.datePipe.transform(new Date(routeUrPlanDate), 'yyyyMMdd');
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

  get isDisabled(): boolean {
    return !this.updateForm.valid || 
           this.quoteMetadata.allowUpdate !== 'Y';
  }
  
}
