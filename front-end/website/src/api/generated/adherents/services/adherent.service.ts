/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { Adherent } from '../models/adherent';


/**
 * gestion des adhérents
 */
@Injectable({
  providedIn: 'root',
})
export class AdherentService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation ajoutAdherent
   */
  static readonly AjoutAdherentPath = '/adherent';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `ajoutAdherent()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutAdherent$Response(params: {
  
    /**
     * Objet adhérent
     */
    body: Adherent
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AdherentService.AjoutAdherentPath, 'post');
    if (params) {


      rb.body(params.body, 'application/json');
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
   * To access the full response (for headers, for example), `ajoutAdherent$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutAdherent(params: {
  
    /**
     * Objet adhérent
     */
    body: Adherent
  }): Observable<void> {

    return this.ajoutAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation getAdherent
   */
  static readonly GetAdherentPath = '/adherent/{idadh}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdherent$Response(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;

  }): Observable<StrictHttpResponse<Adherent>> {

    const rb = new RequestBuilder(this.rootUrl, AdherentService.GetAdherentPath, 'get');
    if (params) {

      rb.path('idadh', params.idadh);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Adherent>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdherent(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;

  }): Observable<Adherent> {

    return this.getAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<Adherent>) => r.body as Adherent)
    );
  }

  /**
   * Path part for operation updateUser
   */
  static readonly UpdateUserPath = '/adherent/{idadh}';

  /**
   * Mise à jour de l'adherent
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateUser()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateUser$Response(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;
  
    /**
     * Objet adherent
     */
    body: Adherent
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AdherentService.UpdateUserPath, 'put');
    if (params) {

      rb.path('idadh', params.idadh);

      rb.body(params.body, 'application/json');
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
   * Mise à jour de l'adherent
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `updateUser$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateUser(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;
  
    /**
     * Objet adherent
     */
    body: Adherent
  }): Observable<void> {

    return this.updateUser$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation deleteUser
   */
  static readonly DeleteUserPath = '/adherent/{idadh}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteUser()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteUser$Response(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;

  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AdherentService.DeleteUserPath, 'delete');
    if (params) {

      rb.path('idadh', params.idadh);

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
   * To access the full response (for headers, for example), `deleteUser$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteUser(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;

  }): Observable<void> {

    return this.deleteUser$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation updateUserPhoto
   */
  static readonly UpdateUserPhotoPath = '/adherent/{idadh}/photo';

  /**
   * Mise à jour de la photo de l'adhérent
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateUserPhoto()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  updateUserPhoto$Response(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;
      body: { 'fileName'?: string, 'file'?: Blob }
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AdherentService.UpdateUserPhotoPath, 'put');
    if (params) {

      rb.path('idadh', params.idadh);

      rb.body(params.body, 'multipart/form-data');
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
   * Mise à jour de la photo de l'adhérent
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `updateUserPhoto$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  updateUserPhoto(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;
      body: { 'fileName'?: string, 'file'?: Blob }
  }): Observable<void> {

    return this.updateUserPhoto$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

}
