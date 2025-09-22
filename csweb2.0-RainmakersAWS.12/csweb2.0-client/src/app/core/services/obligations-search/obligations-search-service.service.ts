import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { HttpService } from '../HttpService';

@Injectable({
  providedIn: 'root'
})

export class ObligationsSearchServiceService extends HttpService{

  private obligationsSearchUrl = this.formatUrl('/ws/obligations?');
  constructor(private http: HttpClient) {
    super();
  }

  getSearchObligationsProps(regionCode, obligationId, accountingPeriodFrom,
    accountingPeriodTo, amountRangeFrom, amountRangeTo): any {

    let queryParams = new HttpParams();

    var cUrl = this.obligationsSearchUrl;
    if (regionCode) {
      queryParams = queryParams.append('regionCode', regionCode);
    }
    if (obligationId) {
      queryParams = queryParams.append('obligationId', obligationId);
    }
    if (accountingPeriodFrom) {
      queryParams = queryParams.append('accountingPeriodFrom', accountingPeriodFrom);
    }
    if (accountingPeriodTo) {
      queryParams = queryParams.append('accountingPeriodTo', accountingPeriodTo);
    }
    if (amountRangeFrom) {
      queryParams = queryParams.append('amountRangeFrom', amountRangeFrom);
    }
    if (amountRangeTo) {
      queryParams = queryParams.append('amountRangeTo', amountRangeTo);
    }

    //return this.http.get(cUrl, {params:queryParams, observe:'response'}); //,{observe:'response'}
    return this.http.get(cUrl, {params:queryParams}); //,{observe:'response'}
  }
}






