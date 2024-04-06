/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Evenement } from '../../models/evenement';

export interface UpdateEvenement$Params {

/**
 * id de l'evenement
 */
  idevenement: number;
  
    /**
     * Objet adherent
     */
    body: Evenement
}

export function updateEvenement(http: HttpClient, rootUrl: string, params: UpdateEvenement$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, updateEvenement.PATH, 'put');
  if (params) {
    rb.path('idevenement', params.idevenement, {});
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'text', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return (r as HttpResponse<any>).clone({ body: undefined }) as StrictHttpResponse<void>;
    })
  );
}

updateEvenement.PATH = '/agenda/evenement/{idevenement}';
