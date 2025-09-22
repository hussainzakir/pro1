import { Injectable } from '@angular/core';
import { HttpService } from '../HttpService';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AaeSearchRecord, AaeSearchResult } from '@app/core/interfaces/aae-search-interfaces';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AaeSearchService extends HttpService{

  aaeDetailUrl = this.formatUrl(`/ws/aae/search`);
  assigneeUrl = this.formatUrl(`/ws/aae/quote/assignee`);
  updateStatusCodeUrl = this.formatUrl(`/ws/aae/quote/updatestatus`);
  viewQuoteNotesUrl = this.formatUrl(`/ws/aae/quote/viewquotenotes`);

  constructor(private http: HttpClient) {
    super();
   }

   doSearch(searchForm: any): Observable<AaeSearchResult>{

  return this.http.get<AaeSearchResult>(this.aaeDetailUrl, {
    params: searchForm
  });
   }

   updateAssignee(action: string, quoteId: string){
    let body = new FormData();
    body.append('action', action);
    body.append('quoteId', quoteId);

    return this.http.post(this.assigneeUrl, body);
   }

   updateStatusCode(statusCode: string, quoteId: string, note: string){
    let body = new FormData();
    body.append('statusCode', statusCode);
    body.append('quoteId', quoteId);
    body.append('note', note);
    return this.http.post(this.updateStatusCodeUrl, body);
  }
    viewQuoteNotes(quoteId: string){
    return this.http.get(this.viewQuoteNotesUrl+"/"+quoteId);
    }

}
