import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { HttpService } from '../HttpService';
import { AuditRequest } from '@app/modules/audit/audit.component';
@Injectable({
  providedIn: 'root'
})
export class AuditserviceService extends HttpService {
  private auditUrl = this.formatUrl('/ws/audit-search/search?');
  private exportUrl = this.formatUrl('/ws/audit-search/export/audit?');
  constructor(private http: HttpClient) {
    super();
  }
  
  getAuditRecords(auditRequest: AuditRequest
    ): any {
    let aUrl = this.auditUrl;
    console.log(aUrl);
    const httpOptions ={
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
    return this.http.post(aUrl, auditRequest, httpOptions);
  }
  exportAuditRecords(auditRequest: AuditRequest
    ): any {
    return this.http.post(this.exportUrl, auditRequest, {responseType: 'blob'});
  }
}

