/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Saison } from '../../models/saison';

export interface GetSaisonCourante$Params {
}

export function getSaisonCourante(http: HttpClient, rootUrl: string, params?: GetSaisonCourante$Params, context?: HttpContext): Observable<StrictHttpResponse<Saison>> {
  const rb = new RequestBuilder(rootUrl, getSaisonCourante.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Saison>;
    })
  );
}

getSaisonCourante.PATH = '/saison/active';
