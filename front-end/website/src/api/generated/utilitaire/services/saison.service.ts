/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { ListeSaison } from '../models/liste-saison';
import { Saison } from '../models/saison';


/**
 * Gestion des saisons
 */
@Injectable({
  providedIn: 'root',
})
export class SaisonService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation getSaisonCourante
   */
  static readonly GetSaisonCourantePath = '/saison/active';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getSaisonCourante()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSaisonCourante$Response(params?: {

  }): Observable<StrictHttpResponse<Saison>> {

    const rb = new RequestBuilder(this.rootUrl, SaisonService.GetSaisonCourantePath, 'get');
    if (params) {


    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Saison>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getSaisonCourante$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSaisonCourante(params?: {

  }): Observable<Saison> {

    return this.getSaisonCourante$Response(params).pipe(
      map((r: StrictHttpResponse<Saison>) => r.body as Saison)
    );
  }

  /**
   * Path part for operation updateSaisonCourante
   */
  static readonly UpdateSaisonCourantePath = '/saison/{idSaison}/active';

  /**
   * Mise à jour de la saison courante
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateSaisonCourante()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateSaisonCourante$Response(params: {

    /**
     * id de la saison
     */
    idSaison: number;

  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, SaisonService.UpdateSaisonCourantePath, 'put');
    if (params) {

      rb.path('idSaison', params.idSaison);

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
   * Mise à jour de la saison courante
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `updateSaisonCourante$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateSaisonCourante(params: {

    /**
     * id de la saison
     */
    idSaison: number;

  }): Observable<void> {

    return this.updateSaisonCourante$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation getListeSaison
   */
  static readonly GetListeSaisonPath = '/saison/liste';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeSaison()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeSaison$Response(params?: {

  }): Observable<StrictHttpResponse<ListeSaison>> {

    const rb = new RequestBuilder(this.rootUrl, SaisonService.GetListeSaisonPath, 'get');
    if (params) {


    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListeSaison>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getListeSaison$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeSaison(params?: {

  }): Observable<ListeSaison> {

    return this.getListeSaison$Response(params).pipe(
      map((r: StrictHttpResponse<ListeSaison>) => r.body as ListeSaison)
    );
  }

}
