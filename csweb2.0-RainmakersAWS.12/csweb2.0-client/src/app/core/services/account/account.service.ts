import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent, HttpParams } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { Observable, Subject, EMPTY, empty, of } from 'rxjs';
import { FormGroup } from '@angular/forms';
import { HttpService } from '../HttpService';

@Injectable({
  providedIn: 'root'
})
export class AccountService extends HttpService{

  constructor(private http: HttpClient) {
    super();
  }

  getAccountDetail(company: string, account: string, site: string): any{
    let accountDetailUrl = this.formatUrl(`/ws/account/${company}/${account}/${site}`);
    return this.http.get(accountDetailUrl);
  }

  getAccountBalance(company: string, account: string): any{
    let accountBalance = this.formatUrl(`/ws/account/${company}/${account}/balance`);
    return this.http.get(accountBalance);
  }

  getOpenObligations(company: string, account: string): any{
    let openObligationsUrl = this.formatUrl(`/ws/account/${company}/${account}/obligations/open`);
    return this.http.get(openObligationsUrl);
  }

  getPayments(company: string, account: string): any{
    let paymentsUrl = this.formatUrl(`/ws/account/${company}/${account}/payments`);
    return this.http.get(paymentsUrl);
  }

  getClosedObligations(company: string, account: string): any{
    let closedObligationsUrl = this.formatUrl(`/ws/account/${company}/${account}/obligations/closed`);
    return this.http.get(closedObligationsUrl);
  }

  getAllReconciliations(company: string, account: string): any{
    let allRecsUrl = this.formatUrl(`/ws/account/${company}/${account}/reconciliations/all`);
    return this.http.get(allRecsUrl);
  }

  getOpenReconciliations(company: string, account: string): any{
    let openRecsUrl = this.formatUrl(`/ws/account/${company}/${account}/reconciliations/open`);
    return this.http.get(openRecsUrl);
  }

  getAccountCharges(company: string, account: string) {
    let openRecsUrl = this.formatUrl(`/ws/account/${company}/${account}/charges`);
    return this.http.get(openRecsUrl);
  }

  getObligationInvoiceDetail(company: any, account: any, obligationId: string): Observable<any> {
    let details = this.formatUrl(`/ws/account/${company}/${account}/obligation/${obligationId}/invoiceDetails`);
    return this.http.get(details);
///{company}/{account}/obligation/{obligationId}/invoiceDetails
    // return new Observable<any>((observer) => {
    //   return observer.next(null); //when you want to return NULL/Empty.
    // });
  }

  getAccountSiteContainerDetail(company: string, account: string, site: string, container: number) {
    let containerDetail = this.formatUrl(`/ws/account/${company}/${account}/${site}/container/${container}/detail`);
    return this.http.get(containerDetail);
  }

  getIndustrialstatisticsServiceHistory(company: string, account: string, site: string, container: number) {
    let  IndustrialServiceHistory = this.formatUrl(`/ws/account/${company}/${account}/${site}/container/${container}/industrialstatisticsservhistory`);
    return this.http.get(IndustrialServiceHistory);
  } 

  getContainerServiceHistory(company: string, account: string, site: string, container: number) {
    let  ContainerServiceHistory = this.formatUrl(`/ws/account/${company}/${account}/${site}/container/${container}/containerservhistory`);
    return this.http.get(ContainerServiceHistory);
  } 

  getAccountSiteContainerRates(company: string, account: string, site: string) {
    let rates = this.formatUrl(`/ws/account/${company}/${account}/${site}/rates`);
    return this.http.get(rates);
  }

  getAccountSiteHaulerDetail(company: string, account: string, site: string) {
    let haulers = this.formatUrl(`/ws/account/${company}/${account}/${site}/haulers`);
    return this.http.get(haulers);
  }

  getAccountSiteHcmtRates(company: string, account: string, site: string, vendorNumber: string, vendorSubtype: string) {
    let hcmtRates = this.formatUrl(`/ws/account/${company}/${account}/${site}/hcmt/${vendorNumber}/${vendorSubtype}`);
    return this.http.get(hcmtRates);
  }

  getAccountReage(company: string, account: string): any{
    let accountReage = this.formatUrl(`/ws/account/${company}/${account}/reage`);
    return this.http.get(accountReage);
  }

  accountDetailsUpdate(addUpdateForm: FormGroup, company: string, account: string): Observable<any>{
    let createUrl = this.formatUrl(`/ws/account/${company}/${account}/details`);
    return this.http.post<any>(createUrl, addUpdateForm.value, { observe: 'response'});
  }

  accountContactsUpdate(addUpdateForm: FormGroup, company: string, account: string): Observable<any>{
    let createUrl = this.formatUrl(`/ws/account/${company}/${account}/contacts`);
    return this.http.post<any>(createUrl, addUpdateForm.value, { observe: 'response'});
  }

  accountSitesUpdate(addUpdateForm: FormGroup, company: string, account: string, site: string): Observable<any>{
    let createUrl = this.formatUrl(`/ws/account/${company}/${account}/${site}/sites`);
    return this.http.post<any>(createUrl, addUpdateForm.value, { observe: 'response'});
  }

  accountSiteContactsUpdate(addUpdateForm: FormGroup, company: string, account: string, site: string): Observable<any>{
    let createUrl = this.formatUrl(`/ws/account/${company}/${account}/${site}/sitecontacts`);
    return this.http.post<any>(createUrl, addUpdateForm.value, { observe: 'response'});
  }

}
