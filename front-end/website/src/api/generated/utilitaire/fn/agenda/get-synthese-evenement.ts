/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ListeParticipantsEvenement } from '../../models/liste-participants-evenement';

export interface GetSyntheseEvenement$Params {

/**
 * id de l'evenement
 */
  idevenement: number;
}

export function getSyntheseEvenement(http: HttpClient, rootUrl: string, params: GetSyntheseEvenement$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeParticipantsEvenement>> {
  const rb = new RequestBuilder(rootUrl, getSyntheseEvenement.PATH, 'get');
  if (params) {
    rb.path('idevenement', params.idevenement, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ListeParticipantsEvenement>;
    })
  );
}

getSyntheseEvenement.PATH = '/agenda/evenement/{idevenement}/synthese';
