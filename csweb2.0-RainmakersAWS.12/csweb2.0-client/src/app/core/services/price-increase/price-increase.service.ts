import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent, HttpParams } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { Observable, Subject } from 'rxjs';
import { HttpService } from '../HttpService';

@Injectable({
  providedIn: 'root'
})
export class PriceIncreaseService extends HttpService{
  public uploadTemplateCompleted: Subject<boolean>;

  getTemplatesUrl = super.formatUrl(`/ws/massuploads/templates`);
  uploadTemplateUrl = super.formatUrl(`/ws/pricing/upload`);


  constructor(private http: HttpClient) {
    super();
    this.uploadTemplateCompleted = new Subject<boolean>();
   }

  uploadTemplate(file: File, uploadType: string): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);
    formData.append('uploadType', uploadType);

    const req = new HttpRequest('POST', this.uploadTemplateUrl, formData, {
      reportProgress: true,
      responseType: 'json'
    });


    return this.http.request(req);
  }
}
