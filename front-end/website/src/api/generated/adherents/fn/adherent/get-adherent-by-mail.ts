/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Adherent } from '../../models/adherent';

export interface GetAdherentByMail$Params {

/**
 * mail de l'adhérent
 */
  mailadherent: string;
}

export function getAdherentByMail(http: HttpClient, rootUrl: string, params: GetAdherentByMail$Params, context?: HttpContext): Observable<StrictHttpResponse<Adherent>> {
  const rb = new RequestBuilder(rootUrl, getAdherentByMail.PATH, 'get');
  if (params) {
    rb.query('mailadherent', params.mailadherent, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Adherent>;
    })
  );
}

getAdherentByMail.PATH = '/adherent';
