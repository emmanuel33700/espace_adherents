/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface UpdateUserPhoto$Params {

/**
 * id de l'adherent
 */
  idadh: number;
      body: {
'fileName'?: string;
'file'?: Blob;
}
}

export function updateUserPhoto(http: HttpClient, rootUrl: string, params: UpdateUserPhoto$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, updateUserPhoto.PATH, 'put');
  if (params) {
    rb.path('idadh', params.idadh, {});
    rb.body(params.body, 'multipart/form-data');
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

updateUserPhoto.PATH = '/adherent/{idadh}/photo';
