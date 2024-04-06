/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ListeListeDiffusion } from '../../models/liste-liste-diffusion';

export interface GetListes$Params {
}

export function getListes(http: HttpClient, rootUrl: string, params?: GetListes$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeListeDiffusion>> {
  const rb = new RequestBuilder(rootUrl, getListes.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ListeListeDiffusion>;
    })
  );
}

getListes.PATH = '/diffusion/listes';
