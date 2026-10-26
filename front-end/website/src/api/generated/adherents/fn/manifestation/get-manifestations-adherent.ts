/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Manifestation } from '../../models/manifestation';

export interface GetManifestationsAdherent$Params {

/**
 * id d'adherent à recuperer
 */
  idadh: number;

/**
 * id de la manifestation à recuperer
 */
  idManifestation: number;
}

export function getManifestationsAdherent(http: HttpClient, rootUrl: string, params: GetManifestationsAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<Manifestation>> {
  const rb = new RequestBuilder(rootUrl, getManifestationsAdherent.PATH, 'get');
  if (params) {
    rb.path('idadh', params.idadh, {});
    rb.path('idManifestation', params.idManifestation, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Manifestation>;
    })
  );
}

getManifestationsAdherent.PATH = '/adherent/{idadh}/manifestion/{idManifestation}';
