/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Authentification } from '../../models/authentification';

export interface UpdateAuthentification$Params {

/**
 * id de l'adherent
 */
  idadh: number;
  
    /**
     * mise à jour du login de la personne
     */
    body: Authentification
}

export function updateAuthentification(http: HttpClient, rootUrl: string, params: UpdateAuthentification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, updateAuthentification.PATH, 'put');
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

updateAuthentification.PATH = '/authentification/{idadh}';
