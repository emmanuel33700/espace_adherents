/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { addBinaryToMail } from '../fn/liste-de-diffusion/add-binary-to-mail';
import { AddBinaryToMail$Params } from '../fn/liste-de-diffusion/add-binary-to-mail';
import { addListe } from '../fn/liste-de-diffusion/add-liste';
import { AddListe$Params } from '../fn/liste-de-diffusion/add-liste';
import { delBinarysToMail } from '../fn/liste-de-diffusion/del-binarys-to-mail';
import { DelBinarysToMail$Params } from '../fn/liste-de-diffusion/del-binarys-to-mail';
import { delListe } from '../fn/liste-de-diffusion/del-liste';
import { DelListe$Params } from '../fn/liste-de-diffusion/del-liste';
import { getAdherentsInscritListe } from '../fn/liste-de-diffusion/get-adherents-inscrit-liste';
import { GetAdherentsInscritListe$Params } from '../fn/liste-de-diffusion/get-adherents-inscrit-liste';
import { getListes } from '../fn/liste-de-diffusion/get-listes';
import { GetListes$Params } from '../fn/liste-de-diffusion/get-listes';
import { ListeListeDiffusion } from '../models/liste-liste-diffusion';
import { ListInscritsMailingListe } from '../models/list-inscrits-mailing-liste';
import { majListe } from '../fn/liste-de-diffusion/maj-liste';
import { MajListe$Params } from '../fn/liste-de-diffusion/maj-liste';
import { sendMail } from '../fn/liste-de-diffusion/send-mail';
import { SendMail$Params } from '../fn/liste-de-diffusion/send-mail';


/**
 * gestion des listes de diffusions et envoi de mails
 */
@Injectable({ providedIn: 'root' })
export class ListeDeDiffusionService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `majListe()` */
  static readonly MajListePath = '/diffusion/liste/{idListe}';

  /**
   * Mise d'une liste de diffusion.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `majListe()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  majListe$Response(params: MajListe$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return majListe(this.http, this.rootUrl, params, context);
  }

  /**
   * Mise d'une liste de diffusion.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `majListe$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  majListe(params: MajListe$Params, context?: HttpContext): Observable<void> {
    return this.majListe$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `addListe()` */
  static readonly AddListePath = '/diffusion/liste/{idListe}';

  /**
   * Ajouter une liste de diffusion.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addListe()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addListe$Response(params: AddListe$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return addListe(this.http, this.rootUrl, params, context);
  }

  /**
   * Ajouter une liste de diffusion.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addListe$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addListe(params: AddListe$Params, context?: HttpContext): Observable<void> {
    return this.addListe$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `delListe()` */
  static readonly DelListePath = '/diffusion/liste/{idListe}';

  /**
   * Supprimer une liste de diffusion.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delListe()` instead.
   *
   * This method doesn't expect any request body.
   */
  delListe$Response(params: DelListe$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return delListe(this.http, this.rootUrl, params, context);
  }

  /**
   * Supprimer une liste de diffusion.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `delListe$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delListe(params: DelListe$Params, context?: HttpContext): Observable<void> {
    return this.delListe$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getAdherentsInscritListe()` */
  static readonly GetAdherentsInscritListePath = '/diffusion/liste/{idListe}/inscrits';

  /**
   * Recupérer les adhérents inscrits à une liste de diffusion.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAdherentsInscritListe()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdherentsInscritListe$Response(params: GetAdherentsInscritListe$Params, context?: HttpContext): Observable<StrictHttpResponse<ListInscritsMailingListe>> {
    return getAdherentsInscritListe(this.http, this.rootUrl, params, context);
  }

  /**
   * Recupérer les adhérents inscrits à une liste de diffusion.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAdherentsInscritListe$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdherentsInscritListe(params: GetAdherentsInscritListe$Params, context?: HttpContext): Observable<ListInscritsMailingListe> {
    return this.getAdherentsInscritListe$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListInscritsMailingListe>): ListInscritsMailingListe => r.body)
    );
  }

  /** Path part for operation `getListes()` */
  static readonly GetListesPath = '/diffusion/listes';

  /**
   * Recupérer la liste des listes de diffusion.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListes()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListes$Response(params?: GetListes$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeListeDiffusion>> {
    return getListes(this.http, this.rootUrl, params, context);
  }

  /**
   * Recupérer la liste des listes de diffusion.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getListes$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListes(params?: GetListes$Params, context?: HttpContext): Observable<ListeListeDiffusion> {
    return this.getListes$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeListeDiffusion>): ListeListeDiffusion => r.body)
    );
  }

  /** Path part for operation `sendMail()` */
  static readonly SendMailPath = '/diffusion/mail/{idMail}';

  /**
   * Envoyer un email.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `sendMail()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendMail$Response(params: SendMail$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return sendMail(this.http, this.rootUrl, params, context);
  }

  /**
   * Envoyer un email.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `sendMail$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendMail(params: SendMail$Params, context?: HttpContext): Observable<void> {
    return this.sendMail$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `addBinaryToMail()` */
  static readonly AddBinaryToMailPath = '/diffusion/mail/{idMail}/fichier';

  /**
   * Attacher un  fichier a un mail a envoyer.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addBinaryToMail()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  addBinaryToMail$Response(params: AddBinaryToMail$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return addBinaryToMail(this.http, this.rootUrl, params, context);
  }

  /**
   * Attacher un  fichier a un mail a envoyer.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addBinaryToMail$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  addBinaryToMail(params: AddBinaryToMail$Params, context?: HttpContext): Observable<void> {
    return this.addBinaryToMail$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `delBinarysToMail()` */
  static readonly DelBinarysToMailPath = '/diffusion/mail/{idMail}/fichier';

  /**
   * Supprimer l'ensemble des fichiers attaché à un mail.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delBinarysToMail()` instead.
   *
   * This method doesn't expect any request body.
   */
  delBinarysToMail$Response(params: DelBinarysToMail$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return delBinarysToMail(this.http, this.rootUrl, params, context);
  }

  /**
   * Supprimer l'ensemble des fichiers attaché à un mail.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `delBinarysToMail$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delBinarysToMail(params: DelBinarysToMail$Params, context?: HttpContext): Observable<void> {
    return this.delBinarysToMail$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
