/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Adherent } from '../../models/adherent';

export interface GetAdherent$Params {

/**
 * id de l'adherent
 */
  idadh: number;
}

export function getAdherent(http: HttpClient, rootUrl: string, params: GetAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<Adherent>> {
  const rb = new RequestBuilder(rootUrl, getAdherent.PATH, 'get');
  if (params) {
    rb.path('idadh', params.idadh, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Adherent>;
    })
  );
}

getAdherent.PATH = '/adherent/{idadh}';
