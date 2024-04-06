/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ListeDiffusion } from '../../models/liste-diffusion';

export interface AddListe$Params {

/**
 * id de la liste de diffusion
 */
  idListe: number;
  
    /**
     * Objet listeDiffusion
     */
    body: ListeDiffusion
}

export function addListe(http: HttpClient, rootUrl: string, params: AddListe$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, addListe.PATH, 'post');
  if (params) {
    rb.path('idListe', params.idListe, {});
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

addListe.PATH = '/diffusion/liste/{idListe}';
