/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ListeEvenements } from '../../models/liste-evenements';

export interface GetListeEvenements$Params {
}

export function getListeEvenements(http: HttpClient, rootUrl: string, params?: GetListeEvenements$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeEvenements>> {
  const rb = new RequestBuilder(rootUrl, getListeEvenements.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ListeEvenements>;
    })
  );
}

getListeEvenements.PATH = '/agenda/evenements';
