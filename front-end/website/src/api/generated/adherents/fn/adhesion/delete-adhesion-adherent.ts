/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface DeleteAdhesionAdherent$Params {

/**
 * id de l'adherent à recuperer
 */
  idadh: number;

/**
 * id de l'adhesion à supprimer
 */
  idAdhesion: number;
}

export function deleteAdhesionAdherent(http: HttpClient, rootUrl: string, params: DeleteAdhesionAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, deleteAdhesionAdherent.PATH, 'delete');
  if (params) {
    rb.path('idadh', params.idadh, {});
    rb.path('idAdhesion', params.idAdhesion, {});
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

deleteAdhesionAdherent.PATH = '/adherent/{idadh}/adhesion/{idAdhesion}';
