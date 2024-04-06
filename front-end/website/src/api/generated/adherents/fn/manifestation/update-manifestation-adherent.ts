/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ParticipationManifestation } from '../../models/participation-manifestation';

export interface UpdateManifestationAdherent$Params {

/**
 * id de l'adherent à recuperer
 */
  idadh: number;

/**
 * id de la manifestation à modifier
 */
  idManifestation: number;
  
    /**
     * mise à jour d'une adhesion
     */
    body: ParticipationManifestation
}

export function updateManifestationAdherent(http: HttpClient, rootUrl: string, params: UpdateManifestationAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, updateManifestationAdherent.PATH, 'put');
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

updateManifestationAdherent.PATH = '/adherent/{idadh}/manifestion/{idManifestation}';
