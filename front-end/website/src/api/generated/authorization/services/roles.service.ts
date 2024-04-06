/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { getRoles } from '../fn/roles/get-roles';
import { GetRoles$Params } from '../fn/roles/get-roles';
import { Roles } from '../models/roles';
import { updateRoles } from '../fn/roles/update-roles';
import { UpdateRoles$Params } from '../fn/roles/update-roles';


/**
 * Gestion des roles
 */
@Injectable({ providedIn: 'root' })
export class RolesService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getRoles()` */
  static readonly GetRolesPath = '/authentification/{idadh}/roles';

  /**
   * Recuperer les roles d'une personne.
   *
   * Recuperer les roles d'une personne
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getRoles()` instead.
   *
   * This method doesn't expect any request body.
   */
  getRoles$Response(params: GetRoles$Params, context?: HttpContext): Observable<StrictHttpResponse<Roles>> {
    return getRoles(this.http, this.rootUrl, params, context);
  }

  /**
   * Recuperer les roles d'une personne.
   *
   * Recuperer les roles d'une personne
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getRoles$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getRoles(params: GetRoles$Params, context?: HttpContext): Observable<Roles> {
    return this.getRoles$Response(params, context).pipe(
      map((r: StrictHttpResponse<Roles>): Roles => r.body)
    );
  }

  /** Path part for operation `updateRoles()` */
  static readonly UpdateRolesPath = '/authentification/{idadh}/roles';

  /**
   * Mise à jour des roles d'une personne.
   *
   * Mise à jour des roles d'une personne
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateRoles()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateRoles$Response(params: UpdateRoles$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return updateRoles(this.http, this.rootUrl, params, context);
  }

  /**
   * Mise à jour des roles d'une personne.
   *
   * Mise à jour des roles d'une personne
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateRoles$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateRoles(params: UpdateRoles$Params, context?: HttpContext): Observable<void> {
    return this.updateRoles$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
