/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface AjoutLienAdherent$Params {

/**
 * id de l'adherent representant
 */
  idadh: number;

/**
 * id de l'adherent representé
 */
  idAdhLien: number;
}

export function ajoutLienAdherent(http: HttpClient, rootUrl: string, params: AjoutLienAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, ajoutLienAdherent.PATH, 'post');
  if (params) {
    rb.path('idadh', params.idadh, {});
    rb.path('idAdhLien', params.idAdhLien, {});
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

ajoutLienAdherent.PATH = '/adherent/{idadh}/lien/{idAdhLien}';
