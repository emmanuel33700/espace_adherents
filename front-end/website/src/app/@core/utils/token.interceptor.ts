import {Injectable, Injector} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import {nbAuthCreateToken, NbAuthJWTToken, NbAuthService, NbAuthToken} from '@nebular/auth';
import {switchMap} from 'rxjs/operators';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  strategy: string = '';

  constructor(private authService: NbAuthService) {}

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
          return next.handle(request);
        }),
      );

}


  getClaims(rawToken: string): string {

    console.info(rawToken);
    const obj = JSON.parse(rawToken);
    return obj.access_token ;
  //  if (!rawToken) {
   //   return null;
   // }
   // return nbAuthCreateToken(NbAuthJWTToken, rawToken, this.strategy).getPayload();
  }


}
