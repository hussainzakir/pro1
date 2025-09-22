import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { AccountDetail, Container, QuoteMetadata, Rate, Site } from '@app/core/interfaces/aae-AccountDetail-interface';
import { AaeSearchRecord } from '@app/core/interfaces/aae-search-interfaces';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-rate-delete',
  templateUrl: './rate-delete.component.html',
  styleUrls: ['./rate-delete.component.css']
})
export class RateDeleteComponent implements OnInit {

  public accountDetail:  AccountDetail = {} as AccountDetail;
  public site: Site = {} as Site;
  public container: Container = {} as Container;
  public rate: Rate = {} as Rate;
  public loading : boolean;
  public errorMsg: string;
  containers: any;
  rates: any;
  durationInSeconds: number = 5;
  public quoteMetadata: QuoteMetadata = {} as QuoteMetadata;

  updateForm = new FormGroup({
    rateStagingId: new FormControl(''),
  });

  constructor (
    private route: ActivatedRoute,
    private aaeEntryService: AaeEntryService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<RateDeleteComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log(data);
      console.log('data above');
      this.accountDetail = data.accountDetail;
      this.containers = data.containers;
      this.container = data.container;
      this.rate = data.rate;
      this.rates = data.rates;
      this.quoteMetadata = data.quoteMetadata;
     
    }

    ngOnInit() {

      this.updateForm
      .patchValue({
        rateStagingId: this.rate.rateStagingId,
       });
    }

  save() {

    this.errorMsg = null;
    this.loading = true;

    this.aaeEntryService.rateInfoDelete(this.updateForm.value)
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
}

