import { HttpService } from './../HttpService';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';
import { ServiceRecording } from 'src/app/modules/account/service-recording-model';

@Injectable({
  providedIn: 'root'
})
export class ServiceRecordingService extends HttpService{

getServiceRecordingsBaseUrl = (company, account, site: string) => this.formatUrl(`/ws/svc-recordings/${company}/${account}/${site}/`);


constructor(private http: HttpClient) {
  super();
}


  getServieRecordingsClosed(company, account, site: string): Observable<ServiceRecording>{
    return this.http.get<ServiceRecording>(this.getServiceRecordingsBaseUrl(company, account, site)+"closed");
  }


  getServieRecordingsOpened(company, account, site: string): Observable<ServiceRecording>{
    return this.http.get<ServiceRecording>(this.getServiceRecordingsBaseUrl(company, account, site)+"open");
  }

  getServieRecordingsAll(company, account, site: string): Observable<ServiceRecording>{
    return this.http.get<ServiceRecording>(this.getServiceRecordingsBaseUrl(company, account, site)+"all");
  }

  getMaintainServiceRecording(company: string, account: string, site: string, serviceRecording: string, date: string ): any{
    let recordingDetail = this.getServiceRecordingsBaseUrl(company, account, site)+`${serviceRecording}/${date}/maintain`;
    return this.http.get(recordingDetail);
  }

}
