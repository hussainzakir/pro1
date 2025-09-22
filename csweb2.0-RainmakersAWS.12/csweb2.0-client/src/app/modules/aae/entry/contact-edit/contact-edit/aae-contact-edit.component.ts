import { Component, Inject, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, Params } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import { LoaderBtnDirective } from '@app/common/directives/loader-btn.directive';
import { AccountDetail, Site, Container } from '@app/core/interfaces/aae-AccountDetail-interface';
import { AaeEntryService } from '@app/core/services/aae-entry/aae-entry.service';


@Component({
  selector: 'app-contact-edit',
  templateUrl: './aae-contact-edit.component.html',
  styleUrls: ['./aae-contact-edit.component.css']
})
export class AAEContactEditComponent implements OnInit {

  public accountDetail:  AccountDetail = {} as AccountDetail;
  public site: Site = {} as Site;
// public site:any;
container:Container;
sites: any;
dataSource: any;
public theSite: any;
public errorMsg: string;
public loading : boolean;
durationInSeconds: number = 5;

  updateForm = new FormGroup({
    stagingId: new FormControl(''),
    companyNumber: new FormControl(''),
    siteContactName: new FormControl(''),
    siteContactTitle: new FormControl(''),
    siteContactAreaCode1: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(3)]),
    siteContactTelephone1: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(7)]),
    siteContactTelephoneExtn1: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(6)]),
    siteContactType1: new FormControl('', [Validators.maxLength(4)]),
    siteContactAreaCode2: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(3)]),
    siteContactTelephone2: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(7)]),
    siteContactTelephoneExtn2: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(6)]),
    siteContactType2: new FormControl('', [Validators.maxLength(4)]),
    siteContactFaxAreaCode: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(3)]),
    siteContactFaxNumber: new FormControl('', [Validators.pattern('^[0-9]*$'), Validators.maxLength(7)]),
    siteContactEmail: new FormControl(''),
  });

  constructor(private location: Location,
    private route: ActivatedRoute,
    private aaeEntryService: AaeEntryService,
    private _snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<AAEContactEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log(data);
      console.log('data above');
      this.accountDetail = data.accountDetail;
      this.sites = data.sites;
      this.site = data.site;
    }


 // ngOnInit() {
   // this.dataSource = new MatTableDataSource(this.tableModel());

  //  setSite();
  //}

  ngOnInit(): void {
    console.log('State????');
    console.log(this.location.getState());

 //this is where we initialize the values
 this.updateForm
 .patchValue({
  stagingId: this.site.siteStagingId,
  companyNumber: this.site.companyNumber,
  siteContactName: this.site.siteContactName,
  siteContactTitle: this.site.siteContactTitle,
  siteContactAreaCode1: this.site.siteContactAreaCode1,
  siteContactTelephone1: this.site.siteContactTelephone1,
  siteContactTelephoneExtn1: this.site.siteContactTelephoneExtn1,
  siteContactType1: this.site.siteContactType1,
  siteContactAreaCode2: this.site.siteContactAreaCode2,
  siteContactTelephone2: this.site.siteContactTelephone2,
  siteContactTelephoneExtn2: this.site.siteContactTelephoneExtn2,
  siteContactType2: this.site.siteContactType2,
  siteContactFaxAreaCode: this.site.siteContactFaxAreaCode,
  contactFaxNumber: this.site.siteContactFaxNumber,
  siteContactFaxNumber: this.site.siteContactFaxNumber,
  });
}

save() {

  this.errorMsg = null;
  this.loading = true;

  this.aaeEntryService.siteInfoUpdate(this.updateForm.value)
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

  }}
