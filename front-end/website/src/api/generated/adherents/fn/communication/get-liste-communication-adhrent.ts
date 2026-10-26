/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ListeCommunications } from '../../models/liste-communications';

export interface GetListeCommunicationAdhrent$Params {

/**
 * id d'adherent à recuperer
 */
  idadh: number;
}

export function getListeCommunicationAdhrent(http: HttpClient, rootUrl: string, params: GetListeCommunicationAdhrent$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeCommunications>> {
  const rb = new RequestBuilder(rootUrl, getListeCommunicationAdhrent.PATH, 'get');
  if (params) {
    rb.path('idadh', params.idadh, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ListeCommunications>;
    })
  );
}

getListeCommunicationAdhrent.PATH = '/adherent/{idadh}/communications';
