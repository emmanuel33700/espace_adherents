/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ListInscritsMailingListe } from '../../models/list-inscrits-mailing-liste';

export interface GetAdherentsInscritListe$Params {

/**
 * id du fichier
 */
  idListe: number;

/**
 * type de filtre
 */
  filter?: 'ALL' | 'ONLYADH' | 'ONLYINSCRIT';
}

export function getAdherentsInscritListe(http: HttpClient, rootUrl: string, params: GetAdherentsInscritListe$Params, context?: HttpContext): Observable<StrictHttpResponse<ListInscritsMailingListe>> {
  const rb = new RequestBuilder(rootUrl, getAdherentsInscritListe.PATH, 'get');
  if (params) {
    rb.path('idListe', params.idListe, {});
    rb.query('filter', params.filter, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ListInscritsMailingListe>;
    })
  );
}

getAdherentsInscritListe.PATH = '/diffusion/liste/{idListe}/inscrits';
