/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ListeSyntheseEvenements } from '../../models/liste-synthese-evenements';

export interface GetSyntheseEvenements$Params {

/**
 * date max de début
 */
  datedebut?: string;

/**
 * date max de fin
 */
  datefin?: string;

/**
 * indique si il faut récupérer uniquement les manifestations avec une demande de participation
 */
  retourParticipationAdh?: boolean;
}

export function getSyntheseEvenements(http: HttpClient, rootUrl: string, params?: GetSyntheseEvenements$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeSyntheseEvenements>> {
  const rb = new RequestBuilder(rootUrl, getSyntheseEvenements.PATH, 'get');
  if (params) {
    rb.query('datedebut', params.datedebut, {});
    rb.query('datefin', params.datefin, {});
    rb.query('retourParticipationAdh', params.retourParticipationAdh, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ListeSyntheseEvenements>;
    })
  );
}

getSyntheseEvenements.PATH = '/agenda/synthese';
