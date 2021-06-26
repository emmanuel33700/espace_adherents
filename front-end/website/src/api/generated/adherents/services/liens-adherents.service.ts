/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { LiensAdherent } from '../models/liens-adherent';


/**
 * Gestion des liens entre adherents
 */
@Injectable({
  providedIn: 'root',
})
export class LiensAdherentsService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation getLienAdherent
   */
  static readonly GetLienAdherentPath = '/adherent/{idadh}/liens';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getLienAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getLienAdherent$Response(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;

  }): Observable<StrictHttpResponse<LiensAdherent>> {

    const rb = new RequestBuilder(this.rootUrl, LiensAdherentsService.GetLienAdherentPath, 'get');
    if (params) {

      rb.path('idadh', params.idadh);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<LiensAdherent>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getLienAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getLienAdherent(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;

  }): Observable<LiensAdherent> {

    return this.getLienAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<LiensAdherent>) => r.body as LiensAdherent)
    );
  }

  /**
   * Path part for operation ajoutLienAdherent
   */
  static readonly AjoutLienAdherentPath = '/adherent/{idadh}/lien/{idAdhLien}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `ajoutLienAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  ajoutLienAdherent$Response(params: {

    /**
     * id de l&#x27;adherent representant
     */
    idadh: number;

    /**
     * id de l&#x27;adherent representé
     */
    idAdhLien: number;

  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, LiensAdherentsService.AjoutLienAdherentPath, 'post');
    if (params) {

      rb.path('idadh', params.idadh);
      rb.path('idAdhLien', params.idAdhLien);

    }
    return this.http.request(rb.build({
      responseType: 'text',
      accept: '*/*'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return (r as HttpResponse<any>).clone({ body: undefined }) as StrictHttpResponse<void>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `ajoutLienAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  ajoutLienAdherent(params: {

    /**
     * id de l&#x27;adherent representant
     */
    idadh: number;

    /**
     * id de l&#x27;adherent representé
     */
    idAdhLien: number;

  }): Observable<void> {

    return this.ajoutLienAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation deleteLienAdherent
   */
  static readonly DeleteLienAdherentPath = '/adherent/{idadh}/lien/{idAdhLien}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteLienAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteLienAdherent$Response(params: {

    /**
     * id de l&#x27;adherent representant
     */
    idadh: number;

    /**
     * id de l&#x27;adherent representé
     */
    idAdhLien: number;

  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, LiensAdherentsService.DeleteLienAdherentPath, 'delete');
    if (params) {

      rb.path('idadh', params.idadh);
      rb.path('idAdhLien', params.idAdhLien);

    }
    return this.http.request(rb.build({
      responseType: 'text',
      accept: '*/*'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return (r as HttpResponse<any>).clone({ body: undefined }) as StrictHttpResponse<void>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `deleteLienAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteLienAdherent(params: {

    /**
     * id de l&#x27;adherent representant
     */
    idadh: number;

    /**
     * id de l&#x27;adherent representé
     */
    idAdhLien: number;

  }): Observable<void> {

    return this.deleteLienAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

}
