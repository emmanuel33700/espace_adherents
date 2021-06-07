/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { Authentification } from '../models/authentification';
import { InfoAuthentification } from '../models/info-authentification';
import { Login } from '../models/login';
import { ReinitAuthentification } from '../models/reinit-authentification';
import { Validation } from '../models/validation';
import { ActivationAuthentification } from '../models/activation-authentification';


/**
 * Gestion des authentifications
 */
@Injectable({
  providedIn: 'root',
})
export class AuthentificationService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation addAuthentification
   */
  static readonly AddAuthentificationPath = '/authentification';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addAuthentification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addAuthentification$Response(params: {
  
    /**
     * ajout de l'objet authentification
     */
    body: Authentification
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AuthentificationService.AddAuthentificationPath, 'post');
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
   * To access the full response (for headers, for example), `addAuthentification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addAuthentification(params: {
  
    /**
     * ajout de l'objet authentification
     */
    body: Authentification
  }): Observable<void> {

    return this.addAuthentification$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation getAuthentification
   */
  static readonly GetAuthentificationPath = '/authentification/{idadh}';

  /**
   * vérifier si un login existe dans la BD
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAuthentification()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAuthentification$Response(params: {

    /**
     * id de la personne à modifier
     */
    idadh: number;

  }): Observable<StrictHttpResponse<InfoAuthentification>> {

    const rb = new RequestBuilder(this.rootUrl, AuthentificationService.GetAuthentificationPath, 'get');
    if (params) {

      rb.path('idadh', params.idadh);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<InfoAuthentification>;
      })
    );
  }

  /**
   * vérifier si un login existe dans la BD
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getAuthentification$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAuthentification(params: {

    /**
     * id de la personne à modifier
     */
    idadh: number;

  }): Observable<InfoAuthentification> {

    return this.getAuthentification$Response(params).pipe(
      map((r: StrictHttpResponse<InfoAuthentification>) => r.body as InfoAuthentification)
    );
  }

  /**
   * Path part for operation updateAuthentification
   */
  static readonly UpdateAuthentificationPath = '/authentification/{idadh}';

  /**
   * Mise à jour  info d'authentification d'une personne
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateAuthentification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateAuthentification$Response(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;
  
    /**
     * mise à jour du login de la personne
     */
    body: Authentification
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AuthentificationService.UpdateAuthentificationPath, 'put');
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
   * Mise à jour  info d'authentification d'une personne
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `updateAuthentification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateAuthentification(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;
  
    /**
     * mise à jour du login de la personne
     */
    body: Authentification
  }): Observable<void> {

    return this.updateAuthentification$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation deleteAuthentification
   */
  static readonly DeleteAuthentificationPath = '/authentification/{idadh}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteAuthentification()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteAuthentification$Response(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;

  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AuthentificationService.DeleteAuthentificationPath, 'delete');
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
   * To access the full response (for headers, for example), `deleteAuthentification$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteAuthentification(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;

  }): Observable<void> {

    return this.deleteAuthentification$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation resetPassword
   */
  static readonly ResetPasswordPath = '/authentification/resetPassword';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `resetPassword()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  resetPassword$Response(params?: {
  
    /**
     * demander la réinitialisation du mot de passe
     */
    body?: Login
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AuthentificationService.ResetPasswordPath, 'post');
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
   * To access the full response (for headers, for example), `resetPassword$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  resetPassword(params?: {
  
    /**
     * demander la réinitialisation du mot de passe
     */
    body?: Login
  }): Observable<void> {

    return this.resetPassword$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation desactiverAllAuthentification
   */
  static readonly DesactiverAllAuthentificationPath = '/authentification/all/desactiver';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `desactiverAllAuthentification()` instead.
   *
   * This method doesn't expect any request body.
   */
  desactiverAllAuthentification$Response(params?: {

  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AuthentificationService.DesactiverAllAuthentificationPath, 'put');
    if (params) {


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
   * To access the full response (for headers, for example), `desactiverAllAuthentification$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  desactiverAllAuthentification(params?: {

  }): Observable<void> {

    return this.desactiverAllAuthentification$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation valideResetPassword
   */
  static readonly ValideResetPasswordPath = '/authentification/{idadh}/validerResetPassword';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `valideResetPassword()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  valideResetPassword$Response(params: {

    /**
     * id de la personne à modifier
     */
    idadh: number;
  
    /**
     * demander la validation de la réinitialisation du mot de passe
     */
    body?: ReinitAuthentification
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AuthentificationService.ValideResetPasswordPath, 'post');
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
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `valideResetPassword$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  valideResetPassword(params: {

    /**
     * id de la personne à modifier
     */
    idadh: number;
  
    /**
     * demander la validation de la réinitialisation du mot de passe
     */
    body?: ReinitAuthentification
  }): Observable<void> {

    return this.valideResetPassword$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation validationAuthentification
   */
  static readonly ValidationAuthentificationPath = '/authentification/{idadh}/validation';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `validationAuthentification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  validationAuthentification$Response(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;
  
    /**
     * Clée de validation reçu par mail au moment de la création du compte
     */
    body: Validation
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AuthentificationService.ValidationAuthentificationPath, 'post');
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
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `validationAuthentification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  validationAuthentification(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;
  
    /**
     * Clée de validation reçu par mail au moment de la création du compte
     */
    body: Validation
  }): Observable<void> {

    return this.validationAuthentification$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation activationAuthentification
   */
  static readonly ActivationAuthentificationPath = '/authentification/{idadh}/activation';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `activationAuthentification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  activationAuthentification$Response(params: {

    /**
     * id de la personne à modifier
     */
    idadh: number;
  
    /**
     * information d'activation ou desactivation d'un authentification
     */
    body: ActivationAuthentification
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AuthentificationService.ActivationAuthentificationPath, 'put');
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
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `activationAuthentification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  activationAuthentification(params: {

    /**
     * id de la personne à modifier
     */
    idadh: number;
  
    /**
     * information d'activation ou desactivation d'un authentification
     */
    body: ActivationAuthentification
  }): Observable<void> {

    return this.activationAuthentification$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

}
