import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RowLimitService {
private row_limit: boolean = false;

  constructor() { }

  set rowlimit(value :boolean){
    this.row_limit = value;
  }

  get rowlimit(): boolean{
    return this.row_limit;
  }
}
