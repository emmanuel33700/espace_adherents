/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { ListeMailingListe } from '../models/liste-mailing-liste';


/**
 * inscription aux listes de diffusion
 */
@Injectable({
  providedIn: 'root',
})
export class ListeDeDiffusionService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation ajoutListeDiffusionAdherent
   */
  static readonly AjoutListeDiffusionAdherentPath = '/adherent/{idadh}/diffusion/liste/{idListe}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `ajoutListeDiffusionAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  ajoutListeDiffusionAdherent$Response(params: {

    /**
     * id d&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de la liste de diffusion
     */
    idListe: number;

  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, ListeDeDiffusionService.AjoutListeDiffusionAdherentPath, 'post');
    if (params) {

      rb.path('idadh', params.idadh);
      rb.path('idListe', params.idListe);

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
   * To access the full response (for headers, for example), `ajoutListeDiffusionAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  ajoutListeDiffusionAdherent(params: {

    /**
     * id d&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de la liste de diffusion
     */
    idListe: number;

  }): Observable<void> {

    return this.ajoutListeDiffusionAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation delListeDiffusionAdherent
   */
  static readonly DelListeDiffusionAdherentPath = '/adherent/{idadh}/diffusion/liste/{idListe}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delListeDiffusionAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  delListeDiffusionAdherent$Response(params: {

    /**
     * id d&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de la liste de diffusion
     */
    idListe: number;

  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, ListeDeDiffusionService.DelListeDiffusionAdherentPath, 'delete');
    if (params) {

      rb.path('idadh', params.idadh);
      rb.path('idListe', params.idListe);

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
   * To access the full response (for headers, for example), `delListeDiffusionAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delListeDiffusionAdherent(params: {

    /**
     * id d&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de la liste de diffusion
     */
    idListe: number;

  }): Observable<void> {

    return this.delListeDiffusionAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation getListesDiffusionAdherent
   */
  static readonly GetListesDiffusionAdherentPath = '/adherent/{idadh}/diffusion/liste/';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListesDiffusionAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListesDiffusionAdherent$Response(params: {

    /**
     * id d&#x27;adherent à recuperer
     */
    idadh: number;

  }): Observable<StrictHttpResponse<ListeMailingListe>> {

    const rb = new RequestBuilder(this.rootUrl, ListeDeDiffusionService.GetListesDiffusionAdherentPath, 'get');
    if (params) {

      rb.path('idadh', params.idadh);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListeMailingListe>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getListesDiffusionAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListesDiffusionAdherent(params: {

    /**
     * id d&#x27;adherent à recuperer
     */
    idadh: number;

  }): Observable<ListeMailingListe> {

    return this.getListesDiffusionAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<ListeMailingListe>) => r.body as ListeMailingListe)
    );
  }

}
