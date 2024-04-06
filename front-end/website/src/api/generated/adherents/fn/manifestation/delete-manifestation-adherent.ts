/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface DeleteManifestationAdherent$Params {

/**
 * id de l'adherent à recuperer
 */
  idadh: number;

/**
 * id de la manifestation à supprimer
 */
  idManifestation: number;
}

export function deleteManifestationAdherent(http: HttpClient, rootUrl: string, params: DeleteManifestationAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, deleteManifestationAdherent.PATH, 'delete');
  if (params) {
    rb.path('idadh', params.idadh, {});
    rb.path('idManifestation', params.idManifestation, {});
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

deleteManifestationAdherent.PATH = '/adherent/{idadh}/manifestion/{idManifestation}';
