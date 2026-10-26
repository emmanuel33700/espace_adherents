/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ListeManifestations } from '../../models/liste-manifestations';

export interface GetListeManifestationsAdherent$Params {

/**
 * id d'adherent à recuperer
 */
  idadh: number;

/**
 * date de début
 */
  datedebut?: string;

/**
 * date de fin
 */
  datefin?: string;

/**
 * indique si il faut récupérer uniquement les manifestations avec une demande de participation
 */
  retourParticipationAdh?: boolean;
}

export function getListeManifestationsAdherent(http: HttpClient, rootUrl: string, params: GetListeManifestationsAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeManifestations>> {
  const rb = new RequestBuilder(rootUrl, getListeManifestationsAdherent.PATH, 'get');
  if (params) {
    rb.path('idadh', params.idadh, {});
    rb.query('datedebut', params.datedebut, {});
    rb.query('datefin', params.datefin, {});
    rb.query('retourParticipationAdh', params.retourParticipationAdh, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ListeManifestations>;
    })
  );
}

getListeManifestationsAdherent.PATH = '/adherent/{idadh}/manifestions';
