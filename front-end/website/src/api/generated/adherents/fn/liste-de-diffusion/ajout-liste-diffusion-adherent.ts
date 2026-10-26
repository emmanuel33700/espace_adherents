/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface AjoutListeDiffusionAdherent$Params {

/**
 * id d'adherent à recuperer
 */
  idadh: number;

/**
 * id de la liste de diffusion
 */
  idListe: number;
}

export function ajoutListeDiffusionAdherent(http: HttpClient, rootUrl: string, params: AjoutListeDiffusionAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, ajoutListeDiffusionAdherent.PATH, 'post');
  if (params) {
    rb.path('idadh', params.idadh, {});
    rb.path('idListe', params.idListe, {});
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

ajoutListeDiffusionAdherent.PATH = '/adherent/{idadh}/diffusion/liste/{idListe}';
