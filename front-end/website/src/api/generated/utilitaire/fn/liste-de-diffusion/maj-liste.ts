/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ListeDiffusion } from '../../models/liste-diffusion';

export interface MajListe$Params {

/**
 * id du fichier
 */
  idListe: number;
  
    /**
     * Objet listeDiffusion
     */
    body: ListeDiffusion
}

export function majListe(http: HttpClient, rootUrl: string, params: MajListe$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, majListe.PATH, 'put');
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

majListe.PATH = '/diffusion/liste/{idListe}';
