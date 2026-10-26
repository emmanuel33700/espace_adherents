/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface AddBinaryToMail$Params {

/**
 * id du mail à envoyer
 */
  idMail: number;
      body?: {
'file'?: Blob;
'fileName'?: string;
}
}

export function addBinaryToMail(http: HttpClient, rootUrl: string, params: AddBinaryToMail$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, addBinaryToMail.PATH, 'post');
  if (params) {
    rb.path('idMail', params.idMail, {});
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

addBinaryToMail.PATH = '/diffusion/mail/{idMail}/fichier';
