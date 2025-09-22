import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-submit-confirm',
  templateUrl: './submit-confirm.component.html',
  styleUrls: ['./submit-confirm.component.css']
})
export class SubmitConfirmComponent implements OnInit {

  @Input()
  uploadResult: any;

  @Output()
  filterCheckBoxValueChanged = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit(): void {
  }

  emitState(chk: boolean){
    this.filterCheckBoxValueChanged.emit(chk)
  }

}
