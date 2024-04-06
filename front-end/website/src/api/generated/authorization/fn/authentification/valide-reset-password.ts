/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ReinitAuthentification } from '../../models/reinit-authentification';

export interface ValideResetPassword$Params {

/**
 * id de la personne à modifier
 */
  idadh: number;
  
    /**
     * demander la validation de la réinitialisation du mot de passe
     */
    body?: ReinitAuthentification
}

export function valideResetPassword(http: HttpClient, rootUrl: string, params: ValideResetPassword$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, valideResetPassword.PATH, 'post');
  if (params) {
    rb.path('idadh', params.idadh, {});
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'text', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return (r as HttpResponse<any>).clone({ body: undefined }) as StrictHttpResponse<void>;
    })
  );
}

valideResetPassword.PATH = '/authentification/{idadh}/validerResetPassword';
