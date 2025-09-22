import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { HttpService } from '../HttpService';

@Injectable({
  providedIn: 'root'
})
export class BuildServiceService extends HttpService{

  constructor(private http: HttpClient) {
    super();
  }

  getBuildId(): Observable<string> {
    const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    let url = this.formatUrl('/ws/build-version');
    return this.http.get(url,
    { headers, responseType: 'text'});
}
}
