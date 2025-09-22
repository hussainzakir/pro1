import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent, HttpParams, HttpResponse } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { Observable, Subject, EMPTY, empty, of } from 'rxjs';
import { FormGroup} from '@angular/forms';
import { HttpService } from '../HttpService';

@Injectable({
  providedIn: 'root'
})
export class ServiceRecordingsService extends HttpService{

  constructor(private http: HttpClient) {
    super();
  }

  getTableCodes(company: string, department: string): Observable<CswebModel.TableCode[]> {
    let types = super.formatUrl(`/ws/svc-recordings/table-codes/${department}?company=${company}`);
    return this.http.get<CswebModel.TableCode[]>(types);
  }


  saveRecording(addRecodingForm: FormGroup): Observable<any>{
    let createUrl = super.formatUrl(`/ws/svc-recordings`);
    return this.http.post<any>(createUrl, addRecodingForm.value, { observe: 'response'});
  }
}
