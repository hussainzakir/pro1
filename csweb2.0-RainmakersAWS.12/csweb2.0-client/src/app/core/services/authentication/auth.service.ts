import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

import { environment } from '../../../../environments/environment';
import { HttpService } from '../HttpService';
import { catchError, map } from 'rxjs/operators';
import { UserAuths } from './userAuths';
import { throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService extends HttpService{

  private loggedIn = new BehaviorSubject<boolean>(false);
  public currentUser: Observable<CswebModel.User>;

  constructor(private http: HttpClient, private userAuths: UserAuths) {
    super();
  }

  isAuthenticated(): Observable<HttpResponse<any>> {
      let url = this.formatUrl('/ws/user/authenticated');
      return this.http.get(url,
        {observe: 'response'});
    }


  login(username: any, password: any): Observable<any>{
    console.log(`logging in ` + username + ` to ` + `${environment.ws_url}/auth/login`);
    const payload = new HttpParams()
      .set('username', username)
      .set('password', password);
      let url = this.formatUrl('/auth/login');

    return this.http.post<any>(url, payload)
            .pipe(map(response => {
                // store user details and basic auth credentials in local storage to keep user logged in between page refreshes
                // user.authdata = window.btoa(username + ':' + password);
              //  localStorage.setItem('currentUser', JSON.stringify(user));
              console.log('auth service user is:');
              console.log(response);
              this.userAuths.setAuths(response.authorities.map(authority => authority.menuOption));
              this.userAuths.setSuperPerms(response.details);
              //  this.currentUserSubject.next(user);
               return response;
            }))
            .pipe(catchError(error => {
              console.log(error);
              if(error.status === 401){
                return throwError(error.error)
              }
              return new Observable<any>();
            }));
  }

  logout(): Observable<any> {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        localStorage.removeItem("auditFormValues");
        localStorage.removeItem("auditRequest");
        localStorage.removeItem("aaeSearch");
        let url = this.formatUrl('/auth/logout');
        this.userAuths.removeAuths();
        return this.http.get(url);
    }

  getCurrentUser(): any{
    let retrievedObject = localStorage.getItem('currentUser');
    let userJson = JSON.parse(retrievedObject);

    console.log('retrievedObject: ', userJson);

    return userJson;
  }
}
