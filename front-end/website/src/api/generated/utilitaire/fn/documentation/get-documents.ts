/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ListeDocuments } from '../../models/liste-documents';

export interface GetDocuments$Params {

/**
 * borne min de date de création du document
 */
  minDateCreation?: string;

/**
 * borne min de date de création du document
 */
  maxDateCreation?: string;
}

export function getDocuments(http: HttpClient, rootUrl: string, params?: GetDocuments$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeDocuments>> {
  const rb = new RequestBuilder(rootUrl, getDocuments.PATH, 'get');
  if (params) {
    rb.query('minDateCreation', params.minDateCreation, {});
    rb.query('maxDateCreation', params.maxDateCreation, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ListeDocuments>;
    })
  );
}

getDocuments.PATH = '/documents';
