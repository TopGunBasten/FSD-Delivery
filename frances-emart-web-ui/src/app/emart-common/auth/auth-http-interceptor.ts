import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable, throwError as observableThrowError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { EmartAuthService } from './emart-auth.service';

@Injectable()
export class AuthHttpInterceptor implements HttpInterceptor {


  constructor(private authService: EmartAuthService, private router: Router) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getToken();
    if (token == null) { return next.handle(request.clone()); }
    const authRequest = request.clone({ setHeaders: { Authorization: `Bearer ${token}` } });

    return next.handle(authRequest).pipe(
      catchError((err, caught) => {
        if (err.status === 403) {
          this.router.navigate(['error', { errCode: err.status, errMessage: err.statusText}]);
        }
        if (err.status === 401) {
          this.authService.clearToken();
          this.router.navigate(['login']);
        }
        return observableThrowError(err);
      })
    );

  }
}
