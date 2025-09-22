import { Injectable,Input } from '@angular/core';
import { AuthService } from '../services/authentication/auth.service';
import { CanActivate, ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { RouteAuthenticator } from './routeAuthenticator';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  lastActivityTime: Date;
  timer: any;
    constructor(private authService: AuthService,
                private router: Router, private routeAuthenticator: RouteAuthenticator) { }

  canActivate(
    route: ActivatedRouteSnapshot,
      state: RouterStateSnapshot): Observable<boolean>  {
            console.log('authGuard canActivate');
            console.log('ReturnUrl from state: ' + state.url);

            return this.authService.isAuthenticated().pipe(map(response => {
            if (response) {
            const hasPermission = this.routeAuthenticator.validateAuths(state.url);
            if (hasPermission) {
              return true;
            } else {
              this.router.navigate(['dashboard']);
              return true;
            }
            }

            this.router.navigate(['login'], { queryParams: { returnUrl: state.url }});
            return false;
        }), catchError((error) => {
            console.log('Error in canActivate');
            console.log(error);
            this.router.navigate(['login'], { queryParams: { returnUrl: state.url } });
            return of(false);
        }));


    }
    updateLastActivityTime(): void {
      this.lastActivityTime = new Date();
    }


}
