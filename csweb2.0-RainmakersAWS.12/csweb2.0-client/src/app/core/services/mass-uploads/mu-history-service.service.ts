import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { HttpService } from '../HttpService';

@Injectable({
  providedIn: 'root'
})
export class MuHistoryServiceService extends HttpService{

  constructor(private http: HttpClient) {
    super();
  }

  getHistoryItems(): any{
    let url = super.formatUrl(`/ws/massuploads/history`);
    return this.http.get(url);
  }
}
