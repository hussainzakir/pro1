import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatSnackBar } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { AccountDetail, Container, QuoteMetadata, Site } from '@app/core/interfaces/aae-AccountDetail-interface';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';

@Component({
  selector: 'app-fsr-delete',
  templateUrl: './fsr-delete.component.html',
  styleUrls: ['./fsr-delete.component.css']
})
export class FutureServiceRequestDeleteComponent implements OnInit {

  public accountDetail:  AccountDetail = {} as AccountDetail;
  public site: Site = {} as Site;
  sites: any;
  public container: Container = {} as Container;
  containers: any;
  public quoteMetadata: QuoteMetadata = {} as QuoteMetadata;
  public loading : boolean;
  public errorMsg: string;

  updateForm = new FormGroup({
    stagingId: new FormControl(''),
  });

  constructor (
    private route: ActivatedRoute,
    private aaeEntryService: AaeEntryService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<FutureServiceRequestDeleteComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log(data);
      console.log('data above');
      this.accountDetail = data.accountDetail;
      this.containers = data.containers;
      this.container = data.container;
      this.sites = data.sites;
      this.site = data.site;
      this.quoteMetadata = data.quoteMetadata;
    }

  ngOnInit() {
    this.updateForm
      .patchValue({
        stagingId: this.container.containerStagingId,
       });
    }

    save() {

      this.errorMsg = null;
      this.loading = true;
  
      this.aaeEntryService.fsrInfoDelete(this.updateForm.value)
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
