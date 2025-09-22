import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { HttpService } from '../HttpService';

@Injectable({
  providedIn: 'root'
})

export class RegionsServiceService extends HttpService {

  private getRegionsUrl = this.formatUrl('/ws/obligations/regions');

  constructor(private http: HttpClient) {
    super();
  }

  getRegionsAll(): any {
    return this.http.get(this.getRegionsUrl);
  }


}
