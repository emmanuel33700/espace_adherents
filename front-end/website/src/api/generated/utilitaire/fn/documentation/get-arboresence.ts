/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ArborescenceDocumentsInit } from '../../models/arborescence-documents-init';

export interface GetArboresence$Params {
}

export function getArboresence(http: HttpClient, rootUrl: string, params?: GetArboresence$Params, context?: HttpContext): Observable<StrictHttpResponse<ArborescenceDocumentsInit>> {
  const rb = new RequestBuilder(rootUrl, getArboresence.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ArborescenceDocumentsInit>;
    })
  );
}

getArboresence.PATH = '/documents/arboresence';
