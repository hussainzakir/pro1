import { Component, OnInit, ViewChild } from '@angular/core';
import { MassUploadHistoryComponent } from './mass-upload-history/mass-upload-history.component';

@Component({
  selector: 'app-mass-uploads',
  templateUrl: './mass-uploads.component.html',
  styleUrls: ['./mass-uploads.component.css']
})
export class MassUploadsComponent implements OnInit {

  @ViewChild(MassUploadHistoryComponent) muHistoryComp: MassUploadHistoryComponent;

  constructor() { }

  ngOnInit(): void {}

  uploadSuccess() {
    setTimeout(() => {
      this.muHistoryComp.loadHistory(true);
    }, 6000);
  }
}
