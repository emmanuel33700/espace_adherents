/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ActivationAuthentification } from '../../models/activation-authentification';

export interface ActivationAuthentification$Params {

/**
 * id de la personne à modifier
 */
  idadh: number;
  
    /**
     * information d'activation ou desactivation d'un authentification
     */
    body: ActivationAuthentification
}

export function activationAuthentification(http: HttpClient, rootUrl: string, params: ActivationAuthentification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, activationAuthentification.PATH, 'put');
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

activationAuthentification.PATH = '/authentification/{idadh}/activation';
