/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Document } from '../../models/document';

export interface GetFichier$Params {

/**
 * id du fichier
 */
  idFichier: number;
}

export function getFichier(http: HttpClient, rootUrl: string, params: GetFichier$Params, context?: HttpContext): Observable<StrictHttpResponse<Document>> {
  const rb = new RequestBuilder(rootUrl, getFichier.PATH, 'get');
  if (params) {
    rb.path('idFichier', params.idFichier, {});
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

getFichier.PATH = '/documents/fichier/{idFichier}';
