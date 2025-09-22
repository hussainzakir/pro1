import { HttpClient, HttpEvent, HttpRequest, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Subject } from 'rxjs/internal/Subject';
import { CustomQueryEncoderHelper, HttpService } from '../HttpService';
import { Aob3ProjectDetails } from '../../interfaces/aob3-project-details';

@Injectable({
  providedIn: 'root'
})
export class ImportService extends HttpService{

  public uploadTemplateCompleted: Subject<boolean>;

    uploadFileUrl = super.formatUrl(`/ws/account/imports`);
    uploadFileUrl3 = super.formatUrl(`/ws/imports`);
    historyUrl = super.formatUrl(`/ws/account/imports/history`);
    copyToYUrl = super.formatUrl(`/ws/account/imports/process`);
    copyToYUrl3 = super.formatUrl(`/ws/imports/process`);
    errorFileUrl = super.formatUrl(`/ws/account/imports/history/errorFile`);
    aobErrorFileUrl = super.formatUrl(`/ws/account/imports/history/aoberrorfile`);
    openMarketErrorFileUrl = super.formatUrl(`/ws/account/imports/history/openmarketerrorfile`);
    scheduleUploadUrl = super.formatUrl(`/ws/account/imports/history/scheduleupload`);
    changeScheduleUploadAomUrl = super.formatUrl(`/ws/account/imports/history/aomchangescheduleupload`);
    allHistoryUrl = super.formatUrl(`/ws/account/imports/allhistory`);
    projectsUrl = super.formatUrl(`/ws/imports/history`);
    allProjectsUrl = super.formatUrl(`/ws/imports/allhistory`);
    projectDetailsUrl = super.formatUrl(`/ws/imports/projectdetails`);
    
    constructor(private http: HttpClient) {
      super();
      this.uploadTemplateCompleted = new Subject<boolean>();
     }

    public uploadTemplate(file: File, aquiName: string, uploadType: string ): Observable<HttpEvent<any>> {
      const formData: FormData = new FormData();
      formData.append('file', file);
      formData.append('aquiName', aquiName);
      formData.append('uploadType', uploadType);

      const req = new HttpRequest('POST', this.uploadFileUrl, formData, {
        reportProgress: true,
        responseType: 'json'
      });


      return this.http.request(req);
    }

      public uploadTemplate3(file: File, aquiName: string, uploadType: string ): Observable<HttpEvent<any>> {
      const formData: FormData = new FormData();
      formData.append('file', file);
      formData.append('aquiName', aquiName);
      formData.append('uploadType', uploadType);

      const req = new HttpRequest('POST', this.uploadFileUrl3, formData, {
        reportProgress: true,
        responseType: 'json'
      });


      return this.http.request(req);
    }

    copyToY(uType: string, aqName: string, fileName: any, acctsString: string, chgNumber: string, projectId: string) {


      const payload = new HttpParams({ encoder: new CustomQueryEncoderHelper() })
      .set('accts', acctsString)
      .set('fileName', fileName)
      .set('aquiName', aqName)
      .set('uploadType', uType)
      .set('changeNumber',chgNumber)
      .set('projectId', projectId); 

      return this.http.post(this.copyToYUrl, payload);
    }

     copyToY3(uType: string, aqName: string, fileName: any, acctsString: string, chgNumber: string, projectId: string) {


      const payload = new HttpParams({ encoder: new CustomQueryEncoderHelper() })
      .set('accts', acctsString)
      .set('fileName', fileName)
      .set('aquiName', aqName)
      .set('uploadType', uType)
      .set('changeNumber',chgNumber)
      .set('projectId', projectId); 

      return this.http.post(this.copyToYUrl3, payload);
    }
    getHistory(uploadType: string): any{
      return this.http.get(this.historyUrl+"/"+uploadType);
      }

      getAllHistory(uString: string): any {
        return this.http.get(this.allHistoryUrl + "/" + uString);
      }

    getProjectHistory(uploadType: string): any{
      return this.http.get(this.projectsUrl+"/"+uploadType);
      }

      getAllProjectHistory(uString: string): any {
        return this.http.get(this.allProjectsUrl + "/" + uString);
      }

    getProjectDetails(projectId: string): Observable<Aob3ProjectDetails> {
      return this.http.get<Aob3ProjectDetails>(
      `${this.projectDetailsUrl}/${projectId}`
        );
      }

    getErrorFile(elem: any): any {
      const headers = { 'content-type': 'application/json'}
      return this.http.post(this.errorFileUrl, elem,{'headers':headers, responseType: 'blob' as 'json',
      observe: 'response' as 'body'});

    }

    getAobErrorFile(elem: any): any {
      const headers = { 'content-type': 'application/json'}
      return this.http.post(this.aobErrorFileUrl, {projectid: elem.projectid, excelname: elem.excelname},{'headers':headers, responseType: 'blob' as 'json',
      observe: 'response' as 'body'});
    }

    getOpenMarketErrorFile(elem: any): any {
      const headers = { 'content-type': 'application/json'}
      return this.http.post(this.openMarketErrorFileUrl, {projectid: elem.projectid, excelname: elem.excelname},{'headers':headers, responseType: 'blob' as 'json',
      observe: 'response' as 'body'});
    }

    scheduleUpload(addUpdateForm: any): Observable<any>{
      return this.http.post(this.scheduleUploadUrl,addUpdateForm,
        {'headers':this.json_headers, responseType: 'blob' as 'json', observe: 'response' as 'body'});
    }

    changeScheduleUpload(elem: any): Observable<any> {
      return this.http.post(this.changeScheduleUploadAomUrl, elem, {
        headers: this.json_headers,
        responseType: 'blob' as 'json' 
      });
    }
}
