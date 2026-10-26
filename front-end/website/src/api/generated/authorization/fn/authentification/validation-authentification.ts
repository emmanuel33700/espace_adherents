/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Validation } from '../../models/validation';

export interface ValidationAuthentification$Params {

/**
 * id de l'adherent
 */
  idadh: number;
  
    /**
     * Clée de validation reçu par mail au moment de la création du compte
     */
    body: Validation
}

export function validationAuthentification(http: HttpClient, rootUrl: string, params: ValidationAuthentification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, validationAuthentification.PATH, 'post');
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

validationAuthentification.PATH = '/authentification/{idadh}/validation';
