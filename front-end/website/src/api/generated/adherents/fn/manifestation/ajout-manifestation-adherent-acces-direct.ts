/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ParticipationManifestationAccesDirect } from '../../models/participation-manifestation-acces-direct';

export interface AjoutManifestationAdherentAccesDirect$Params {

/**
 * id l'adherent
 */
  idadh: number;

/**
 * id de la manifestation
 */
  idManifestation: number;
  
    /**
     * Besoin de l'objet manifestation le lier à un adherents
     */
    body: ParticipationManifestationAccesDirect
}

export function ajoutManifestationAdherentAccesDirect(http: HttpClient, rootUrl: string, params: AjoutManifestationAdherentAccesDirect$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, ajoutManifestationAdherentAccesDirect.PATH, 'post');
  if (params) {
    rb.path('idadh', params.idadh, {});
    rb.path('idManifestation', params.idManifestation, {});
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

ajoutManifestationAdherentAccesDirect.PATH = '/adherent/{idadh}/manifestion/{idManifestation}/accesdirect';
