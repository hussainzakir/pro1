import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { HttpService } from '../HttpService';

@Injectable({
  providedIn: 'root'
})
export class CsSearchServiceService extends HttpService{

  private csSearchUrl = this.formatUrl('/ws/cs-search/search?');
  constructor(private http: HttpClient) {
    super();
  }

  getSearchAccounts(): any {
    return this.http.get(this.csSearchUrl);
  }

  getSearchAccountsProps(locationId, siteNumber, accountNo,
    streetAddress, zipCode, siteName, city, phone,
    // accountEmail,
    // purchaseOrderNum,
    // openObligationBalance,
    division, state, includeClosedSites, nationalAccountsOnly): any {
    let params = new HttpParams();

    var cUrl = this.csSearchUrl;
    if (locationId) {
      params = params.append('locationId', locationId);
    }
    if (siteNumber) {
      params = params.append('siteNumber', siteNumber);
    }
    if (accountNo) {
      params = params.append('acctNum', accountNo);
    }
    if (streetAddress) {
      params = params.append('streetAddress', streetAddress);
    }
    if (zipCode) {
      params = params.append('zipCode', zipCode);
    }
    if (siteName) {
      params = params.append('acctSiteName', siteName);
    }
    if (city) {
      cUrl += "city=" + city + "&";
      params = params.append('city', city);
    }
    if (phone) {
      params = params.append('phone', phone);
    }
    // if (accountEmail) {
    //   params = params.append('accountEmail', accountEmail);
    // }
    // if (openObligationBalance) {
    //   params = params.append('openObligationBalance', openObligationBalance);
    // }
    // if (purchaseOrderNum) {
    //   params = params.append('purchaseOrderNum', purchaseOrderNum);
    // }
    if (division) {
      params = params.append('division', division);
    }
    if (state) {
      params = params.append('state', state);
    }
    if (includeClosedSites) {
      params = params.append('includeClosedSites', includeClosedSites);
    }
    if (nationalAccountsOnly) {
      params = params.append('nationalAccountsOnly', nationalAccountsOnly);
    }
    return this.http.get(cUrl, {params});
  }
}
