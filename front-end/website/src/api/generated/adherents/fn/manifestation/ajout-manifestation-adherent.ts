/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ParticipationManifestation } from '../../models/participation-manifestation';

export interface AjoutManifestationAdherent$Params {

/**
 * id l'adherent à modifier
 */
  idadh: number;

/**
 * id de la manifestation à modifier
 */
  idManifestation: number;
  
    /**
     * Besoin de l'objet manifestation le lier à un adherents
     */
    body: ParticipationManifestation
}

export function ajoutManifestationAdherent(http: HttpClient, rootUrl: string, params: AjoutManifestationAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, ajoutManifestationAdherent.PATH, 'post');
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

ajoutManifestationAdherent.PATH = '/adherent/{idadh}/manifestion/{idManifestation}';
