import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/internal/Subject';

@Injectable({
  providedIn: 'root'
})
export class DialogCommunicationService {

  constructor() { }

  private dialogClosedSource = new Subject<void>();
  dialogClosed$ = this.dialogClosedSource.asObservable();
  
  notifyDialogClosed(): void {
    this.dialogClosedSource.next();
  }
}
