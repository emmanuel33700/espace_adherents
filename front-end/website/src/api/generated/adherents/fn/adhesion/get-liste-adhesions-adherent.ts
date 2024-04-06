/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ListeAdhesions } from '../../models/liste-adhesions';

export interface GetListeAdhesionsAdherent$Params {

/**
 * id de l'adherent à recuperer
 */
  idadh: number;
}

export function getListeAdhesionsAdherent(http: HttpClient, rootUrl: string, params: GetListeAdhesionsAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeAdhesions>> {
  const rb = new RequestBuilder(rootUrl, getListeAdhesionsAdherent.PATH, 'get');
  if (params) {
    rb.path('idadh', params.idadh, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ListeAdhesions>;
    })
  );
}

getListeAdhesionsAdherent.PATH = '/adherent/{idadh}/adhesions';
