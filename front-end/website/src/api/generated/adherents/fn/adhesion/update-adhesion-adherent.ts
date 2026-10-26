/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Adhesion } from '../../models/adhesion';

export interface UpdateAdhesionAdherent$Params {

/**
 * id de l'adherent à recuperer
 */
  idadh: number;

/**
 * id de l'adhesion de modifier
 */
  idAdhesion: number;
  
    /**
     * mise à jour d'une adhesion
     */
    body: Adhesion
}

export function updateAdhesionAdherent(http: HttpClient, rootUrl: string, params: UpdateAdhesionAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, updateAdhesionAdherent.PATH, 'put');
  if (params) {
    rb.path('idadh', params.idadh, {});
    rb.path('idAdhesion', params.idAdhesion, {});
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

updateAdhesionAdherent.PATH = '/adherent/{idadh}/adhesion/{idAdhesion}';
