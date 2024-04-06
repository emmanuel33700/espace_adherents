/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Roles } from '../../models/roles';

export interface UpdateRoles$Params {

/**
 * id de la personne à modifier
 */
  idadh: number;
  
    /**
     * mise à jour de l'objet role
     */
    body: Roles
}

export function updateRoles(http: HttpClient, rootUrl: string, params: UpdateRoles$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, updateRoles.PATH, 'put');
  if (params) {
    rb.path('idadh', params.idadh, {});
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

updateRoles.PATH = '/authentification/{idadh}/roles';
