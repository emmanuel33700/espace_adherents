/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { Roles } from '../models/roles';


/**
 * Gestion des roles
 */
@Injectable({
  providedIn: 'root',
})
export class RolesService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation getRoles
   */
  static readonly GetRolesPath = '/authentification/{idadh}/roles';

  /**
   * Recuperer les roles d'une personne
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getRoles()` instead.
   *
   * This method doesn't expect any request body.
   */
  getRoles$Response(params: {

    /**
     * id de la personne à modifier
     */
    idadh: number;

  }): Observable<StrictHttpResponse<Roles>> {

    const rb = new RequestBuilder(this.rootUrl, RolesService.GetRolesPath, 'get');
    if (params) {

      rb.path('idadh', params.idadh);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Roles>;
      })
    );
  }

  /**
   * Recuperer les roles d'une personne
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getRoles$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getRoles(params: {

    /**
     * id de la personne à modifier
     */
    idadh: number;

  }): Observable<Roles> {

    return this.getRoles$Response(params).pipe(
      map((r: StrictHttpResponse<Roles>) => r.body as Roles)
    );
  }

  /**
   * Path part for operation updateRoles
   */
  static readonly UpdateRolesPath = '/authentification/{idadh}/roles';

  /**
   * Mise à jour des roles d'une personne
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateRoles()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateRoles$Response(params: {

    /**
     * id de la personne à modifier
     */
    idadh: number;
  
    /**
     * mise à jour de l'objet role
     */
    body: Roles
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, RolesService.UpdateRolesPath, 'put');
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
   * Mise à jour des roles d'une personne
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `updateRoles$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateRoles(params: {

    /**
     * id de la personne à modifier
     */
    idadh: number;
  
    /**
     * mise à jour de l'objet role
     */
    body: Roles
  }): Observable<void> {

    return this.updateRoles$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

}
