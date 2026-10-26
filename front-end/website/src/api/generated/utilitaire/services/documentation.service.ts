/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { addDossier } from '../fn/documentation/add-dossier';
import { AddDossier$Params } from '../fn/documentation/add-dossier';
import { addFichier } from '../fn/documentation/add-fichier';
import { AddFichier$Params } from '../fn/documentation/add-fichier';
import { addFichierBinaire } from '../fn/documentation/add-fichier-binaire';
import { AddFichierBinaire$Params } from '../fn/documentation/add-fichier-binaire';
import { ArborescenceDocumentsInit } from '../models/arborescence-documents-init';
import { delDossier } from '../fn/documentation/del-dossier';
import { DelDossier$Params } from '../fn/documentation/del-dossier';
import { delFichier } from '../fn/documentation/del-fichier';
import { DelFichier$Params } from '../fn/documentation/del-fichier';
import { Document } from '../models/document';
import { getArboresence } from '../fn/documentation/get-arboresence';
import { GetArboresence$Params } from '../fn/documentation/get-arboresence';
import { getDocuments } from '../fn/documentation/get-documents';
import { GetDocuments$Params } from '../fn/documentation/get-documents';
import { getDossier } from '../fn/documentation/get-dossier';
import { GetDossier$Params } from '../fn/documentation/get-dossier';
import { getFichier } from '../fn/documentation/get-fichier';
import { GetFichier$Params } from '../fn/documentation/get-fichier';
import { ListeDocuments } from '../models/liste-documents';
import { majDossier } from '../fn/documentation/maj-dossier';
import { MajDossier$Params } from '../fn/documentation/maj-dossier';
import { majFichier } from '../fn/documentation/maj-fichier';
import { MajFichier$Params } from '../fn/documentation/maj-fichier';


/**
 * gestion de la documentation de l'association
 */
@Injectable({ providedIn: 'root' })
export class DocumentationService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getDocuments()` */
  static readonly GetDocumentsPath = '/documents';

  /**
   * Rechercher une liste de document.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getDocuments()` instead.
   *
   * This method doesn't expect any request body.
   */
  getDocuments$Response(params?: GetDocuments$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeDocuments>> {
    return getDocuments(this.http, this.rootUrl, params, context);
  }

  /**
   * Rechercher une liste de document.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getDocuments$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getDocuments(params?: GetDocuments$Params, context?: HttpContext): Observable<ListeDocuments> {
    return this.getDocuments$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeDocuments>): ListeDocuments => r.body)
    );
  }

  /** Path part for operation `getArboresence()` */
  static readonly GetArboresencePath = '/documents/arboresence';

  /**
   * Recupérer l'arboresence complet.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getArboresence()` instead.
   *
   * This method doesn't expect any request body.
   */
  getArboresence$Response(params?: GetArboresence$Params, context?: HttpContext): Observable<StrictHttpResponse<ArborescenceDocumentsInit>> {
    return getArboresence(this.http, this.rootUrl, params, context);
  }

  /**
   * Recupérer l'arboresence complet.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getArboresence$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getArboresence(params?: GetArboresence$Params, context?: HttpContext): Observable<ArborescenceDocumentsInit> {
    return this.getArboresence$Response(params, context).pipe(
      map((r: StrictHttpResponse<ArborescenceDocumentsInit>): ArborescenceDocumentsInit => r.body)
    );
  }

  /** Path part for operation `addDossier()` */
  static readonly AddDossierPath = '/documents/dossier';

  /**
   * Ajouter un dossier.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addDossier()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addDossier$Response(params: AddDossier$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return addDossier(this.http, this.rootUrl, params, context);
  }

  /**
   * Ajouter un dossier.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addDossier$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addDossier(params: AddDossier$Params, context?: HttpContext): Observable<void> {
    return this.addDossier$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getDossier()` */
  static readonly GetDossierPath = '/documents/dossier/{idDossier}';

  /**
   * Recupérer les informations d'un dossier.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getDossier()` instead.
   *
   * This method doesn't expect any request body.
   */
  getDossier$Response(params: GetDossier$Params, context?: HttpContext): Observable<StrictHttpResponse<Document>> {
    return getDossier(this.http, this.rootUrl, params, context);
  }

  /**
   * Recupérer les informations d'un dossier.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getDossier$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getDossier(params: GetDossier$Params, context?: HttpContext): Observable<Document> {
    return this.getDossier$Response(params, context).pipe(
      map((r: StrictHttpResponse<Document>): Document => r.body)
    );
  }

  /** Path part for operation `majDossier()` */
  static readonly MajDossierPath = '/documents/dossier/{idDossier}';

  /**
   * Mise à jour d'un dossier.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `majDossier()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  majDossier$Response(params: MajDossier$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return majDossier(this.http, this.rootUrl, params, context);
  }

  /**
   * Mise à jour d'un dossier.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `majDossier$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  majDossier(params: MajDossier$Params, context?: HttpContext): Observable<void> {
    return this.majDossier$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `delDossier()` */
  static readonly DelDossierPath = '/documents/dossier/{idDossier}';

  /**
   * Supprimer un dossier (avec les sous fichier associé).
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delDossier()` instead.
   *
   * This method doesn't expect any request body.
   */
  delDossier$Response(params: DelDossier$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return delDossier(this.http, this.rootUrl, params, context);
  }

  /**
   * Supprimer un dossier (avec les sous fichier associé).
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `delDossier$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delDossier(params: DelDossier$Params, context?: HttpContext): Observable<void> {
    return this.delDossier$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `addFichier()` */
  static readonly AddFichierPath = '/documents/fichier';

  /**
   * Ajouter un fichier (sans le binaire).
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addFichier()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addFichier$Response(params: AddFichier$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return addFichier(this.http, this.rootUrl, params, context);
  }

  /**
   * Ajouter un fichier (sans le binaire).
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addFichier$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addFichier(params: AddFichier$Params, context?: HttpContext): Observable<void> {
    return this.addFichier$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getFichier()` */
  static readonly GetFichierPath = '/documents/fichier/{idFichier}';

  /**
   * Recupérer les informations d'un fichier.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getFichier()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFichier$Response(params: GetFichier$Params, context?: HttpContext): Observable<StrictHttpResponse<Document>> {
    return getFichier(this.http, this.rootUrl, params, context);
  }

  /**
   * Recupérer les informations d'un fichier.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getFichier$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFichier(params: GetFichier$Params, context?: HttpContext): Observable<Document> {
    return this.getFichier$Response(params, context).pipe(
      map((r: StrictHttpResponse<Document>): Document => r.body)
    );
  }

  /** Path part for operation `majFichier()` */
  static readonly MajFichierPath = '/documents/fichier/{idFichier}';

  /**
   * Mise à jour d'un fichier.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `majFichier()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  majFichier$Response(params: MajFichier$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return majFichier(this.http, this.rootUrl, params, context);
  }

  /**
   * Mise à jour d'un fichier.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `majFichier$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  majFichier(params: MajFichier$Params, context?: HttpContext): Observable<void> {
    return this.majFichier$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `addFichierBinaire()` */
  static readonly AddFichierBinairePath = '/documents/fichier/{idFichier}';

  /**
   * Ajouter fichier binaire.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addFichierBinaire()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  addFichierBinaire$Response(params: AddFichierBinaire$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return addFichierBinaire(this.http, this.rootUrl, params, context);
  }

  /**
   * Ajouter fichier binaire.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addFichierBinaire$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  addFichierBinaire(params: AddFichierBinaire$Params, context?: HttpContext): Observable<void> {
    return this.addFichierBinaire$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `delFichier()` */
  static readonly DelFichierPath = '/documents/fichier/{idFichier}';

  /**
   * Supprimer un fichier.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delFichier()` instead.
   *
   * This method doesn't expect any request body.
   */
  delFichier$Response(params: DelFichier$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return delFichier(this.http, this.rootUrl, params, context);
  }

  /**
   * Supprimer un fichier.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `delFichier$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delFichier(params: DelFichier$Params, context?: HttpContext): Observable<void> {
    return this.delFichier$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
