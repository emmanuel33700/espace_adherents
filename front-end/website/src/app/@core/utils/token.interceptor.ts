import {Injectable, Injector} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse,
} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {NbAuthService, NbAuthToken} from '@nebular/auth';
import {switchMap} from 'rxjs/operators';
import {LoggerService} from './logger.service';
import {Router} from '@angular/router';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { throwError } from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  strategy: string = '';

  constructor(private authService: NbAuthService,
              private loggerService: LoggerService,
              private injector: Injector) {
  }

  /**
   * Intercepte les requettes http
   * @param request
   * @param next
   */
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return this.authService.getToken()
      .pipe(
        switchMap((token: NbAuthToken) => {
          if (token && token.getValue()) {
            request = request.clone({
              setHeaders: {
                Authorization: `Bearer ${this.getClaims(token.getValue())}`,
              },
            });
          }
          return next.handle(request).catch(err => this.handleError(err));
        }),
      );

  }


  /**
   *
   * @param err
   */
  private handleError(err: HttpErrorResponse): Observable<any> {
    let errorMsg;
    if (err.error instanceof Error) {
      // A client-side or network error occurred. Handle it accordingly.
      errorMsg = `Erreru retour API: ${err.error.message}`;
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      errorMsg = `Backend retourn le code http ${err.status}, avec body: ${err.error}`;
    }
    if (err.status === 401) {
      this.loggerService.debug(' Deconnection de l\'utilisateur suite a token obsolete');
      this.authService.logout('password')
        .subscribe(() => {
          this.loggerService.debug(' Deconnection');
        });

      this.injector.get(Router).navigateByUrl('/auth/login');
    }
    this.loggerService.error(errorMsg);
    return throwError( errorMsg );
// https://stackoverflow.com/questions/45464852/rxjs-observable-throw-is-not-a-function-angular4

  }

  /**
   *
   * @param rawToken
   */
  getClaims(rawToken: string): string {

    this.loggerService.debug(rawToken);
    const obj = JSON.parse(rawToken);
    return obj.access_token;
  }


}
