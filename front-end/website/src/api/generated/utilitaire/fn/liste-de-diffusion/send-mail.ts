/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { MailAEnvoyer } from '../../models/mail-a-envoyer';

export interface SendMail$Params {

/**
 * id du mail à envoyer
 */
  idMail: number;
  
    /**
     * Objet listeDiffusion
     */
    body?: MailAEnvoyer
}

export function sendMail(http: HttpClient, rootUrl: string, params: SendMail$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, sendMail.PATH, 'post');
  if (params) {
    rb.path('idMail', params.idMail, {});
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

sendMail.PATH = '/diffusion/mail/{idMail}';
