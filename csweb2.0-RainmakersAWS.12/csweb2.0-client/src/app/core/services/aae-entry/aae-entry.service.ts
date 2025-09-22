import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpService } from '../HttpService';
import { FormGroup } from '@angular/forms';
import { Observable, Subject, EMPTY, empty, of } from 'rxjs';
import { EntityErrors } from '@app/core/interfaces/aae-ErrorResponse-interface';
import { QuoteMetadata } from '@app/core/interfaces/aae-AccountDetail-interface';

@Injectable({
  providedIn: 'root'
})
export class AaeEntryService  extends HttpService{

constructor(private http: HttpClient) {
  super();
 }

 getAccountDetail(division: string, quoteId: string): any{
  let aaeDetailUrl = this.formatUrl(`/ws/aae/quote/${quoteId}/div/${division}?`);
  return this.http.get(aaeDetailUrl);
 }

 getQuoteMetadata(quoteId: string): Observable<QuoteMetadata>{
  let aaeMetadataUrl = this.formatUrl(`/ws/aae/quote/${quoteId}/metadata`);
  return this.http.get<QuoteMetadata>(aaeMetadataUrl);
 }

 getErrorResponse(quoteId: string): Observable<EntityErrors[]>{
  let aaeErrorUrl = this.formatUrl(`/ws/aae/quote/${quoteId}/errors`);
  return this.http.get<EntityErrors[]>(aaeErrorUrl);
 }

//  accountInfoUpdate(addUpdateForm: FormGroup, division: string, quoteId: string): Observable<any>{
//   let createUrl = this.formatUrl(`/ws/aae/quote/${quoteId}/div/${division}/accountdetails`);
//   return this.http.post<any>(createUrl, addUpdateForm.value, { observe: 'response'});
//  }

accountInfoUpdate(addUpdateForm: any): Observable<any>{
  let createUrl = this.formatUrl(`/ws/aae/quote/accountdetails`);
  return this.http.post(createUrl, addUpdateForm,
    {'headers':this.json_headers, responseType: 'blob' as 'json', observe: 'response' as 'body'});
 }

 siteInfoUpdate(addUpdateForm: any): Observable<any>{
  let createUrl = this.formatUrl(`/ws/aae/quote/sitedetails`);

  return this.http.post(createUrl, addUpdateForm,
    {'headers':this.json_headers, responseType: 'blob' as 'json', observe: 'response' as 'body'});

 }

 containerInfoUpdate(addUpdateForm: any): Observable<any>{
  let createUrl = this.formatUrl(`/ws/aae/quote/containerdetails`);
  return this.http.post(createUrl, addUpdateForm,
    {'headers':this.json_headers, responseType: 'blob' as 'json', observe: 'response' as 'body'});
 }

 rateInfoUpdate(addUpdateForm: any): Observable<any>{
  let createUrl = this.formatUrl(`/ws/aae/quote/ratedetails`);
  return this.http.post(createUrl, addUpdateForm,
    {'headers':this.json_headers, responseType: 'blob' as 'json', observe: 'response' as 'body'});
 }

 finalizationInfoUpdate(addUpdateForm: any): Observable<any>{
  let createUrl = this.formatUrl(`/ws/aae/quote/finalization`);
  return this.http.post(createUrl, addUpdateForm,
    {'headers':this.json_headers, responseType: 'blob' as 'json', observe: 'response' as 'body'});
 }

 getDropdownInfo(division: string, selected: string): Observable<any>{
  let createUrl = this.formatUrl(`/ws/aae/quote/listdata/${division}/${selected}`);
  return this.http.get(createUrl);
 }
 
 rateInfoDelete(addUpdateForm: any): Observable<any>{
  let createUrl = this.formatUrl(`/ws/aae/quote/ratedelete`);
  return this.http.post(createUrl, addUpdateForm,
    {'headers':this.json_headers, responseType: 'blob' as 'json', observe: 'response' as 'body'});
 }

 rateInfoCreate(addUpdateForm: any): Observable<any>{
  let createUrl = this.formatUrl(`/ws/aae/quote/ratecreate`);
  return this.http.post(createUrl, addUpdateForm,
    {'headers':this.json_headers, responseType: 'blob' as 'json', observe: 'response' as 'body'});
 }

 getContractResponse(quoteId: string): Observable<any>{
  let aaeContractUrl = this.formatUrl(`/ws/aae/quote/${quoteId}/contractinfo`);
  return this.http.get(aaeContractUrl);
 }

getFinalAccountNumber(projectId: string): Observable<any>{
  let aaeContractUrl = this.formatUrl(`/ws/aae/quote/finalization/getaccount/${projectId}`);
  return this.http.get(aaeContractUrl);
}

resetAccountNumber(quoteId: string): Observable<any>{
  let aaeContractUrl = this.formatUrl(`/ws/aae/quote/finalization/resetaccount/${quoteId}`);
  return this.http.get(aaeContractUrl);
}
containerInfoDelete(addUpdateForm: any): Observable<any>{
  let createUrl = this.formatUrl(`/ws/aae/quote/containerdelete`);
  return this.http.post(createUrl, addUpdateForm,
    {'headers':this.json_headers, responseType: 'blob' as 'json', observe: 'response' as 'body'});
 }

 fsrInfoDelete(addUpdateForm: any): Observable<any>{
  let createUrl = this.formatUrl(`/ws/aae/quote/fsrdelete`);
  return this.http.post(createUrl, addUpdateForm,
    {'headers':this.json_headers, responseType: 'blob' as 'json', observe: 'response' as 'body'});
 }

 getReport(addUpdateForm: any): Observable<any>{
  let createUrl = this.formatUrl(`/ws/aae/quote/errorReport`);
  return this.http.post(createUrl, addUpdateForm,
    {'headers':this.json_headers, responseType: 'blob' as 'json', observe: 'response' as 'body'});
 }

 getExceptionReport(addExceptionForm: any): Observable<any>{
  let createUrl = this.formatUrl(`/ws/aae/quote/exceptionreport`);
  return this.http.post(createUrl, addExceptionForm,
    {'headers':this.json_headers, responseType: 'blob' as 'json', observe: 'response' as 'body'});
 }

}
