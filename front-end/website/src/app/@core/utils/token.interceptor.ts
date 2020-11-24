import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NbAuthService, NbAuthToken} from '@nebular/auth';
import {switchMap} from 'rxjs/operators';
import {LoggerService} from './logger.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  strategy: string = '';

  constructor(private authService: NbAuthService,
              private loggerService: LoggerService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    this.loggerService.info('*************token interceptor***********************');
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
          return next.handle(request);
        }),
      );

}


  getClaims(rawToken: string): string {

    this.loggerService.debug(rawToken);
    const obj = JSON.parse(rawToken);
    return obj.access_token ;
  //  if (!rawToken) {
   //   return null;
   // }
   // return nbAuthCreateToken(NbAuthJWTToken, rawToken, this.strategy).getPayload();
  }


}
