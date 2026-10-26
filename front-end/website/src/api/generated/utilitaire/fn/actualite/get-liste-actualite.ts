/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ListeActualite } from '../../models/liste-actualite';

export interface GetListeActualite$Params {
}

export function getListeActualite(http: HttpClient, rootUrl: string, params?: GetListeActualite$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeActualite>> {
  const rb = new RequestBuilder(rootUrl, getListeActualite.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ListeActualite>;
    })
  );
}

getListeActualite.PATH = '/actualite/information';
