/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { activerCompteAdherent } from '../fn/adherent/activer-compte-adherent';
import { ActiverCompteAdherent$Params } from '../fn/adherent/activer-compte-adherent';
import { Adherent } from '../models/adherent';
import { ajoutAdherent } from '../fn/adherent/ajout-adherent';
import { AjoutAdherent$Params } from '../fn/adherent/ajout-adherent';
import { deleteUser } from '../fn/adherent/delete-user';
import { DeleteUser$Params } from '../fn/adherent/delete-user';
import { getAdherent } from '../fn/adherent/get-adherent';
import { GetAdherent$Params } from '../fn/adherent/get-adherent';
import { getAdherentByMail } from '../fn/adherent/get-adherent-by-mail';
import { GetAdherentByMail$Params } from '../fn/adherent/get-adherent-by-mail';
import { updateUser } from '../fn/adherent/update-user';
import { UpdateUser$Params } from '../fn/adherent/update-user';
import { updateUserPhoto } from '../fn/adherent/update-user-photo';
import { UpdateUserPhoto$Params } from '../fn/adherent/update-user-photo';


/**
 * gestion des adhérents
 */
@Injectable({ providedIn: 'root' })
export class AdherentService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getAdherentByMail()` */
  static readonly GetAdherentByMailPath = '/adherent';

  /**
   * rechercher adherent via son mail.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAdherentByMail()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdherentByMail$Response(params: GetAdherentByMail$Params, context?: HttpContext): Observable<StrictHttpResponse<Adherent>> {
    return getAdherentByMail(this.http, this.rootUrl, params, context);
  }

  /**
   * rechercher adherent via son mail.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAdherentByMail$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdherentByMail(params: GetAdherentByMail$Params, context?: HttpContext): Observable<Adherent> {
    return this.getAdherentByMail$Response(params, context).pipe(
      map((r: StrictHttpResponse<Adherent>): Adherent => r.body)
    );
  }

  /** Path part for operation `ajoutAdherent()` */
  static readonly AjoutAdherentPath = '/adherent';

  /**
   * Ajouter un adhérent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `ajoutAdherent()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutAdherent$Response(params: AjoutAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return ajoutAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * Ajouter un adhérent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `ajoutAdherent$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutAdherent(params: AjoutAdherent$Params, context?: HttpContext): Observable<void> {
    return this.ajoutAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getAdherent()` */
  static readonly GetAdherentPath = '/adherent/{idadh}';

  /**
   * recuperer adherent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdherent$Response(params: GetAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<Adherent>> {
    return getAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * recuperer adherent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdherent(params: GetAdherent$Params, context?: HttpContext): Observable<Adherent> {
    return this.getAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<Adherent>): Adherent => r.body)
    );
  }

  /** Path part for operation `updateUser()` */
  static readonly UpdateUserPath = '/adherent/{idadh}';

  /**
   * Mise à jour de l'adherent.
   *
   * Mise à jour de l'adherent
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateUser()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateUser$Response(params: UpdateUser$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return updateUser(this.http, this.rootUrl, params, context);
  }

  /**
   * Mise à jour de l'adherent.
   *
   * Mise à jour de l'adherent
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateUser$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateUser(params: UpdateUser$Params, context?: HttpContext): Observable<void> {
    return this.updateUser$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `deleteUser()` */
  static readonly DeleteUserPath = '/adherent/{idadh}';

  /**
   * Supression de l'adherent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteUser()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteUser$Response(params: DeleteUser$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteUser(this.http, this.rootUrl, params, context);
  }

  /**
   * Supression de l'adherent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteUser$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteUser(params: DeleteUser$Params, context?: HttpContext): Observable<void> {
    return this.deleteUser$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `activerCompteAdherent()` */
  static readonly ActiverCompteAdherentPath = '/adherent/{idadh}/activerCompte';

  /**
   * Activer le compte d'un adhérent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `activerCompteAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  activerCompteAdherent$Response(params: ActiverCompteAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return activerCompteAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * Activer le compte d'un adhérent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `activerCompteAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  activerCompteAdherent(params: ActiverCompteAdherent$Params, context?: HttpContext): Observable<void> {
    return this.activerCompteAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `updateUserPhoto()` */
  static readonly UpdateUserPhotoPath = '/adherent/{idadh}/photo';

  /**
   * Mise à jour de la photo de l'adhérent.
   *
   * Mise à jour de la photo de l'adhérent
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateUserPhoto()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  updateUserPhoto$Response(params: UpdateUserPhoto$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return updateUserPhoto(this.http, this.rootUrl, params, context);
  }

  /**
   * Mise à jour de la photo de l'adhérent.
   *
   * Mise à jour de la photo de l'adhérent
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateUserPhoto$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  updateUserPhoto(params: UpdateUserPhoto$Params, context?: HttpContext): Observable<void> {
    return this.updateUserPhoto$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
