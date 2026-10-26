/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ListeMailingListe } from '../../models/liste-mailing-liste';

export interface GetListesDiffusionAdherent$Params {

/**
 * id d'adherent à recuperer
 */
  idadh: number;
}

export function getListesDiffusionAdherent(http: HttpClient, rootUrl: string, params: GetListesDiffusionAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeMailingListe>> {
  const rb = new RequestBuilder(rootUrl, getListesDiffusionAdherent.PATH, 'get');
  if (params) {
    rb.path('idadh', params.idadh, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ListeMailingListe>;
    })
  );
}

getListesDiffusionAdherent.PATH = '/adherent/{idadh}/diffusion/liste/';
