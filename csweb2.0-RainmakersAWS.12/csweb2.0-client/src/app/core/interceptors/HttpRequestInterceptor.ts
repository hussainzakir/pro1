import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpErrorResponse
} from '@angular/common/http';

import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

/** Inject With Credentials into the request */
@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    // console.log("interceptor: " + req.url);
      req = req.clone({
        withCredentials: true
      });

  if( req.url.toLowerCase().includes('user/authenticated') )
        return next.handle(req);

  return next.handle(req).pipe( tap(() => {},
    (err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status !== 401) {
          return;
        }
        console.log('WS called failed - user not logged in');
        this.router.navigate(['login']);
      }
}));
  }
}
