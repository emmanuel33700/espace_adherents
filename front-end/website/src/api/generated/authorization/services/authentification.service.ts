/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { activationAuthentification } from '../fn/authentification/activation-authentification';
import { ActivationAuthentification$Params } from '../fn/authentification/activation-authentification';
import { addAuthentification } from '../fn/authentification/add-authentification';
import { AddAuthentification$Params } from '../fn/authentification/add-authentification';
import { deleteAuthentification } from '../fn/authentification/delete-authentification';
import { DeleteAuthentification$Params } from '../fn/authentification/delete-authentification';
import { desactiverAllAuthentification } from '../fn/authentification/desactiver-all-authentification';
import { DesactiverAllAuthentification$Params } from '../fn/authentification/desactiver-all-authentification';
import { getAuthentification } from '../fn/authentification/get-authentification';
import { GetAuthentification$Params } from '../fn/authentification/get-authentification';
import { InfoAuthentification } from '../models/info-authentification';
import { resetPassword } from '../fn/authentification/reset-password';
import { ResetPassword$Params } from '../fn/authentification/reset-password';
import { updateAuthentification } from '../fn/authentification/update-authentification';
import { UpdateAuthentification$Params } from '../fn/authentification/update-authentification';
import { validationAuthentification } from '../fn/authentification/validation-authentification';
import { ValidationAuthentification$Params } from '../fn/authentification/validation-authentification';
import { valideResetPassword } from '../fn/authentification/valide-reset-password';
import { ValideResetPassword$Params } from '../fn/authentification/valide-reset-password';


/**
 * Gestion des authentifications
 */
@Injectable({ providedIn: 'root' })
export class AuthentificationService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `addAuthentification()` */
  static readonly AddAuthentificationPath = '/authentification';

  /**
   * Ajouter  compte d'accès d'une personne.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addAuthentification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addAuthentification$Response(params: AddAuthentification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return addAuthentification(this.http, this.rootUrl, params, context);
  }

  /**
   * Ajouter  compte d'accès d'une personne.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addAuthentification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addAuthentification(params: AddAuthentification$Params, context?: HttpContext): Observable<void> {
    return this.addAuthentification$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getAuthentification()` */
  static readonly GetAuthentificationPath = '/authentification/{idadh}';

  /**
   * Récupérer les informations d'authentification (login et statut).
   *
   * vérifier si un login existe dans la BD
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAuthentification()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAuthentification$Response(params: GetAuthentification$Params, context?: HttpContext): Observable<StrictHttpResponse<InfoAuthentification>> {
    return getAuthentification(this.http, this.rootUrl, params, context);
  }

  /**
   * Récupérer les informations d'authentification (login et statut).
   *
   * vérifier si un login existe dans la BD
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAuthentification$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAuthentification(params: GetAuthentification$Params, context?: HttpContext): Observable<InfoAuthentification> {
    return this.getAuthentification$Response(params, context).pipe(
      map((r: StrictHttpResponse<InfoAuthentification>): InfoAuthentification => r.body)
    );
  }

  /** Path part for operation `updateAuthentification()` */
  static readonly UpdateAuthentificationPath = '/authentification/{idadh}';

  /**
   * Mise à jour  info d'authentification d'une personne.
   *
   * Mise à jour  info d'authentification d'une personne
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateAuthentification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateAuthentification$Response(params: UpdateAuthentification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return updateAuthentification(this.http, this.rootUrl, params, context);
  }

  /**
   * Mise à jour  info d'authentification d'une personne.
   *
   * Mise à jour  info d'authentification d'une personne
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateAuthentification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateAuthentification(params: UpdateAuthentification$Params, context?: HttpContext): Observable<void> {
    return this.updateAuthentification$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `deleteAuthentification()` */
  static readonly DeleteAuthentificationPath = '/authentification/{idadh}';

  /**
   * suppression du compte accès d'une personne.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteAuthentification()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteAuthentification$Response(params: DeleteAuthentification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteAuthentification(this.http, this.rootUrl, params, context);
  }

  /**
   * suppression du compte accès d'une personne.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteAuthentification$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteAuthentification(params: DeleteAuthentification$Params, context?: HttpContext): Observable<void> {
    return this.deleteAuthentification$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `resetPassword()` */
  static readonly ResetPasswordPath = '/authentification/resetPassword';

  /**
   * demander la réinitialisation du mot de passe.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `resetPassword()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  resetPassword$Response(params?: ResetPassword$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return resetPassword(this.http, this.rootUrl, params, context);
  }

  /**
   * demander la réinitialisation du mot de passe.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `resetPassword$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  resetPassword(params?: ResetPassword$Params, context?: HttpContext): Observable<void> {
    return this.resetPassword$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `desactiverAllAuthentification()` */
  static readonly DesactiverAllAuthentificationPath = '/authentification/all/desactiver';

  /**
   * Désactiver l'ensemble des authentifications (hors role administrateur et représentant de l'association qui restent activé).
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `desactiverAllAuthentification()` instead.
   *
   * This method doesn't expect any request body.
   */
  desactiverAllAuthentification$Response(params?: DesactiverAllAuthentification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return desactiverAllAuthentification(this.http, this.rootUrl, params, context);
  }

  /**
   * Désactiver l'ensemble des authentifications (hors role administrateur et représentant de l'association qui restent activé).
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `desactiverAllAuthentification$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  desactiverAllAuthentification(params?: DesactiverAllAuthentification$Params, context?: HttpContext): Observable<void> {
    return this.desactiverAllAuthentification$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `valideResetPassword()` */
  static readonly ValideResetPasswordPath = '/authentification/{idadh}/validerResetPassword';

  /**
   * demander la validation de la réinitialisation du mot de passe.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `valideResetPassword()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  valideResetPassword$Response(params: ValideResetPassword$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return valideResetPassword(this.http, this.rootUrl, params, context);
  }

  /**
   * demander la validation de la réinitialisation du mot de passe.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `valideResetPassword$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  valideResetPassword(params: ValideResetPassword$Params, context?: HttpContext): Observable<void> {
    return this.valideResetPassword$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `validationAuthentification()` */
  static readonly ValidationAuthentificationPath = '/authentification/{idadh}/validation';

  /**
   * validation d'enregistrement du login d'une personne.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `validationAuthentification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  validationAuthentification$Response(params: ValidationAuthentification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return validationAuthentification(this.http, this.rootUrl, params, context);
  }

  /**
   * validation d'enregistrement du login d'une personne.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `validationAuthentification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  validationAuthentification(params: ValidationAuthentification$Params, context?: HttpContext): Observable<void> {
    return this.validationAuthentification$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `activationAuthentification()` */
  static readonly ActivationAuthentificationPath = '/authentification/{idadh}/activation';

  /**
   * activation ou désactivation d'une authentification.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `activationAuthentification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  activationAuthentification$Response(params: ActivationAuthentification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return activationAuthentification(this.http, this.rootUrl, params, context);
  }

  /**
   * activation ou désactivation d'une authentification.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `activationAuthentification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  activationAuthentification(params: ActivationAuthentification$Params, context?: HttpContext): Observable<void> {
    return this.activationAuthentification$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
