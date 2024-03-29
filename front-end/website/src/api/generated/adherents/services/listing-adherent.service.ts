/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { ListeAdherent2 } from '../models/liste-adherent-2';
import { ListeAdherents } from '../models/liste-adherents';


/**
 * listing des adhérents
 */
@Injectable({
  providedIn: 'root',
})
export class ListingAdherentService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation getListeAdherents
   */
  static readonly GetListeAdherentsPath = '/adherent/liste';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeAdherents()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherents$Response(params?: {

  }): Observable<StrictHttpResponse<ListeAdherents>> {

    const rb = new RequestBuilder(this.rootUrl, ListingAdherentService.GetListeAdherentsPath, 'get');
    if (params) {


    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListeAdherents>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getListeAdherents$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherents(params?: {

  }): Observable<ListeAdherents> {

    return this.getListeAdherents$Response(params).pipe(
      map((r: StrictHttpResponse<ListeAdherents>) => r.body as ListeAdherents)
    );
  }

  /**
   * Path part for operation getListeAdherentsSaison
   */
  static readonly GetListeAdherentsSaisonPath = '/adherent/listeSaison';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeAdherentsSaison()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherentsSaison$Response(params?: {

  }): Observable<StrictHttpResponse<ListeAdherents>> {

    const rb = new RequestBuilder(this.rootUrl, ListingAdherentService.GetListeAdherentsSaisonPath, 'get');
    if (params) {


    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListeAdherents>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getListeAdherentsSaison$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherentsSaison(params?: {

  }): Observable<ListeAdherents> {

    return this.getListeAdherentsSaison$Response(params).pipe(
      map((r: StrictHttpResponse<ListeAdherents>) => r.body as ListeAdherents)
    );
  }

  /**
   * Path part for operation getListeAdherentsSaison2
   */
  static readonly GetListeAdherentsSaison2Path = '/adherent/listeSaison2';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeAdherentsSaison2()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherentsSaison2$Response(params?: {

  }): Observable<StrictHttpResponse<ListeAdherent2>> {

    const rb = new RequestBuilder(this.rootUrl, ListingAdherentService.GetListeAdherentsSaison2Path, 'get');
    if (params) {


    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListeAdherent2>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getListeAdherentsSaison2$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherentsSaison2(params?: {

  }): Observable<ListeAdherent2> {

    return this.getListeAdherentsSaison2$Response(params).pipe(
      map((r: StrictHttpResponse<ListeAdherent2>) => r.body as ListeAdherent2)
    );
  }

  /**
   * Path part for operation getListeAdherentsFiltreSaison
   */
  static readonly GetListeAdherentsFiltreSaisonPath = '/adherent/listeSaisonFiltre';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeAdherentsFiltreSaison()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherentsFiltreSaison$Response(params?: {

  }): Observable<StrictHttpResponse<ListeAdherents>> {

    const rb = new RequestBuilder(this.rootUrl, ListingAdherentService.GetListeAdherentsFiltreSaisonPath, 'get');
    if (params) {


    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListeAdherents>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getListeAdherentsFiltreSaison$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherentsFiltreSaison(params?: {

  }): Observable<ListeAdherents> {

    return this.getListeAdherentsFiltreSaison$Response(params).pipe(
      map((r: StrictHttpResponse<ListeAdherents>) => r.body as ListeAdherents)
    );
  }

}
