import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

export interface TemplateItem {
  name: string;
  updatedDate: string;
}

const MOCK_TEMPLATES: TemplateItem[] = [
  {name: '31 Adjustments Mass Upload', updatedDate: '05/12/2009 2:44pm'},
  {name: 'Write Off Upload', updatedDate: '08/06/2008 12:34pm'},
  {name: 'Update Invoice Delivuery method', updatedDate: '05/12/2009'},
  {name: 'TPH Passthrough Table Maintenance ', updatedDate: '03/15/2016'},
  {name: '31 Adjustments Mass Upload', updatedDate: '05.12.2009'},
  {name: '31 Adjustments Mass Upload', updatedDate: '05.12.2009'},
  {name: '31 Adjustments Mass Upload', updatedDate: '05.12.2009'},
  {name: '31 Adjustments Mass Upload', updatedDate: '05.12.2009'},
  {name: '31 Adjustments Mass Upload', updatedDate: '05.12.2009'},
  {name: '31 Adjustments Mass Upload', updatedDate: '05.12.2009'},
  {name: '31 Adjustments Mass Upload', updatedDate: '05.12.2009'},
  {name: '31 Adjustments Mass Upload', updatedDate: '05.12.2009'},
  {name: '31 Adjustments Mass Upload', updatedDate: '05.12.2009'}
];


@Component({
  selector: 'app-mass-upload-templates',
  templateUrl: './mass-upload-templates.component.html',
  styleUrls: ['./mass-upload-templates.component.css']
})
export class MassUploadTemplatesComponent implements OnInit {

  templates = MOCK_TEMPLATES;

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  downloadTemplate(): void{
    this.dialog.open(ComingSoonComponent, {
      data: {
        name: 'Template downloads'
      }
    });


  }
}

@Component({
  selector: 'dialog-content-example-dialog',
template: '{{data.name}} is coming soon!',
})
export class ComingSoonComponent {
  constructor(
    public dialogRef: MatDialogRef<ComingSoonComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
