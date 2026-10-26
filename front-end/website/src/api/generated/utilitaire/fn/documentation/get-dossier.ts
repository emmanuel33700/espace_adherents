/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Document } from '../../models/document';

export interface GetDossier$Params {

/**
 * id du dossier
 */
  idDossier: number;
}

export function getDossier(http: HttpClient, rootUrl: string, params: GetDossier$Params, context?: HttpContext): Observable<StrictHttpResponse<Document>> {
  const rb = new RequestBuilder(rootUrl, getDossier.PATH, 'get');
  if (params) {
    rb.path('idDossier', params.idDossier, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Document>;
    })
  );
}

getDossier.PATH = '/documents/dossier/{idDossier}';
