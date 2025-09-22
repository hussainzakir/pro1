import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent, HttpParams } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { Observable, Subject } from 'rxjs';
import { HttpService } from '../HttpService';


@Injectable({
  providedIn: 'root'
})
export class MuTemplateServiceService extends HttpService{
  public uploadTemplateCompleted: Subject<boolean>;

  getTemplatesUrl = super.formatUrl(`/ws/massuploads/templates`);
  downloadTemplateUrl = super.formatUrl(`/ws/massuploads/template/download/`);
  errorFileUrl = super.formatUrl(`/ws/massuploads/error-file/download`);
  uploadTemplateUrl = (templateId: string) => super.formatUrl(`/ws/massuploads/template/${templateId}/upload`);


  constructor(private http: HttpClient) {
    super();
    this.uploadTemplateCompleted = new Subject<boolean>();
   }

  getTemplates(): any{
    return this.http.get(this.getTemplatesUrl);
  }

  downloadTemplate(templateId: string): any{
    console.log('downloading template id: ' + templateId);

    return this.http.get(this.downloadTemplateUrl + templateId, {responseType: 'blob' as 'json',
        observe: 'response' as 'body'});
  }

  downloadErrorFile(logId: string): any{
    const params = new HttpParams()
    .set('logId', logId);

    return this.http.get(this.errorFileUrl, {params, responseType: 'blob' as 'json',
        observe: 'response' as 'body'});
  }

  uploadTemplate(file: File, changeNumber: string, templateId: string ): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);
    formData.append('changeNumber', changeNumber);

    const req = new HttpRequest('POST', this.uploadTemplateUrl(templateId), formData, {
      reportProgress: true,
      responseType: 'json'
    });


    return this.http.request(req);
  }

}
