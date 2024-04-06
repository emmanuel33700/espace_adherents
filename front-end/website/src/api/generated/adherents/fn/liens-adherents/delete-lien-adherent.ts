/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface DeleteLienAdherent$Params {

/**
 * id de l'adherent representant
 */
  idadh: number;

/**
 * id de l'adherent representé
 */
  idAdhLien: number;
}

export function deleteLienAdherent(http: HttpClient, rootUrl: string, params: DeleteLienAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, deleteLienAdherent.PATH, 'delete');
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

deleteLienAdherent.PATH = '/adherent/{idadh}/lien/{idAdhLien}';
