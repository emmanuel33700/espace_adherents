/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Adhesion } from '../../models/adhesion';

export interface GetAdhesionAdherent$Params {

/**
 * id de l'adherent à recuperer
 */
  idadh: number;

/**
 * id de l'adhesion à recuperer
 */
  idAdhesion: number;
}

export function getAdhesionAdherent(http: HttpClient, rootUrl: string, params: GetAdhesionAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<Adhesion>> {
  const rb = new RequestBuilder(rootUrl, getAdhesionAdherent.PATH, 'get');
  if (params) {
    rb.path('idadh', params.idadh, {});
    rb.path('idAdhesion', params.idAdhesion, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Adhesion>;
    })
  );
}

getAdhesionAdherent.PATH = '/adherent/{idadh}/adhesion/{idAdhesion}';
