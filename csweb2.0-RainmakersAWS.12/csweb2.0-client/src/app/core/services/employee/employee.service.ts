import { HttpService } from './../HttpService';
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent, HttpParams } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class EmployeeService extends HttpService{

  constructor(private http: HttpClient) {
    super();
  }

  getActiveEmployees(company: string): Observable<CswebModel.Employee[]> {
    let url = this.formatUrl(`/ws/employee/${company}`);
    return this.http.get<CswebModel.Employee[]>(url);
  }

}
