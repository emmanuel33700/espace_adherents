/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { InfoAuthentification } from '../../models/info-authentification';

export interface GetAuthentification$Params {

/**
 * id de la personne à modifier
 */
  idadh: number;
}

export function getAuthentification(http: HttpClient, rootUrl: string, params: GetAuthentification$Params, context?: HttpContext): Observable<StrictHttpResponse<InfoAuthentification>> {
  const rb = new RequestBuilder(rootUrl, getAuthentification.PATH, 'get');
  if (params) {
    rb.path('idadh', params.idadh, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<InfoAuthentification>;
    })
  );
}

getAuthentification.PATH = '/authentification/{idadh}';
