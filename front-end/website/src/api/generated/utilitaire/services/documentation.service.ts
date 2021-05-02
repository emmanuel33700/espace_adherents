/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { ArborescenceDocumentsInit } from '../models/arborescence-documents-init';
import { Document } from '../models/document';


/**
 * gestion de la documentation de l'association
 */
@Injectable({
  providedIn: 'root',
})
export class DocumentationService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation getDocuments
   */
  static readonly GetDocumentsPath = '/documents';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getDocuments()` instead.
   *
   * This method doesn't expect any request body.
   */
  getDocuments$Response(params?: {

  }): Observable<StrictHttpResponse<ArborescenceDocumentsInit>> {

    const rb = new RequestBuilder(this.rootUrl, DocumentationService.GetDocumentsPath, 'get');
    if (params) {


    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ArborescenceDocumentsInit>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getDocuments$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getDocuments(params?: {

  }): Observable<ArborescenceDocumentsInit> {

    return this.getDocuments$Response(params).pipe(
      map((r: StrictHttpResponse<ArborescenceDocumentsInit>) => r.body as ArborescenceDocumentsInit)
    );
  }

  /**
   * Path part for operation addDossier
   */
  static readonly AddDossierPath = '/documents/dossier';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addDossier()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addDossier$Response(params: {
  
    /**
     * Objet dossier
     */
    body: Document
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, DocumentationService.AddDossierPath, 'post');
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
   * To access the full response (for headers, for example), `addDossier$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addDossier(params: {
  
    /**
     * Objet dossier
     */
    body: Document
  }): Observable<void> {

    return this.addDossier$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation getDossier
   */
  static readonly GetDossierPath = '/documents/dossier/{idDossier}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getDossier()` instead.
   *
   * This method doesn't expect any request body.
   */
  getDossier$Response(params: {

    /**
     * id du dossier
     */
    idDossier: number;

  }): Observable<StrictHttpResponse<Document>> {

    const rb = new RequestBuilder(this.rootUrl, DocumentationService.GetDossierPath, 'get');
    if (params) {

      rb.path('idDossier', params.idDossier);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Document>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getDossier$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getDossier(params: {

    /**
     * id du dossier
     */
    idDossier: number;

  }): Observable<Document> {

    return this.getDossier$Response(params).pipe(
      map((r: StrictHttpResponse<Document>) => r.body as Document)
    );
  }

  /**
   * Path part for operation majDossier
   */
  static readonly MajDossierPath = '/documents/dossier/{idDossier}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `majDossier()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  majDossier$Response(params: {

    /**
     * id du dossier
     */
    idDossier: number;
  
    /**
     * Objet dossier
     */
    body: Document
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, DocumentationService.MajDossierPath, 'put');
    if (params) {

      rb.path('idDossier', params.idDossier);

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
   * To access the full response (for headers, for example), `majDossier$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  majDossier(params: {

    /**
     * id du dossier
     */
    idDossier: number;
  
    /**
     * Objet dossier
     */
    body: Document
  }): Observable<void> {

    return this.majDossier$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation delDossier
   */
  static readonly DelDossierPath = '/documents/dossier/{idDossier}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delDossier()` instead.
   *
   * This method doesn't expect any request body.
   */
  delDossier$Response(params: {

    /**
     * id du dossier
     */
    idDossier: number;

  }): Observable<StrictHttpResponse<Document>> {

    const rb = new RequestBuilder(this.rootUrl, DocumentationService.DelDossierPath, 'delete');
    if (params) {

      rb.path('idDossier', params.idDossier);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Document>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `delDossier$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delDossier(params: {

    /**
     * id du dossier
     */
    idDossier: number;

  }): Observable<Document> {

    return this.delDossier$Response(params).pipe(
      map((r: StrictHttpResponse<Document>) => r.body as Document)
    );
  }

  /**
   * Path part for operation addFichier
   */
  static readonly AddFichierPath = '/documents/fichier';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addFichier()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addFichier$Response(params: {
  
    /**
     * Objet fichier
     */
    body: Document
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, DocumentationService.AddFichierPath, 'post');
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
   * To access the full response (for headers, for example), `addFichier$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addFichier(params: {
  
    /**
     * Objet fichier
     */
    body: Document
  }): Observable<void> {

    return this.addFichier$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation getFichier
   */
  static readonly GetFichierPath = '/documents/fichier/{idFichier}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getFichier()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFichier$Response(params: {

    /**
     * id du fichier
     */
    idFichier: number;

  }): Observable<StrictHttpResponse<Document>> {

    const rb = new RequestBuilder(this.rootUrl, DocumentationService.GetFichierPath, 'get');
    if (params) {

      rb.path('idFichier', params.idFichier);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Document>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getFichier$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getFichier(params: {

    /**
     * id du fichier
     */
    idFichier: number;

  }): Observable<Document> {

    return this.getFichier$Response(params).pipe(
      map((r: StrictHttpResponse<Document>) => r.body as Document)
    );
  }

  /**
   * Path part for operation majFichier
   */
  static readonly MajFichierPath = '/documents/fichier/{idFichier}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `majFichier()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  majFichier$Response(params: {

    /**
     * id du fichier
     */
    idFichier: number;
  
    /**
     * Objet fichier
     */
    body: Document
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, DocumentationService.MajFichierPath, 'put');
    if (params) {

      rb.path('idFichier', params.idFichier);

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
   * To access the full response (for headers, for example), `majFichier$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  majFichier(params: {

    /**
     * id du fichier
     */
    idFichier: number;
  
    /**
     * Objet fichier
     */
    body: Document
  }): Observable<void> {

    return this.majFichier$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation addFichierBinaire
   */
  static readonly AddFichierBinairePath = '/documents/fichier/{idFichier}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addFichierBinaire()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  addFichierBinaire$Response(params: {

    /**
     * id du fichier
     */
    idFichier: number;
      body?: { 'orderId'?: number, 'userId'?: number, 'file'?: Blob }
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, DocumentationService.AddFichierBinairePath, 'post');
    if (params) {

      rb.path('idFichier', params.idFichier);

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
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `addFichierBinaire$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  addFichierBinaire(params: {

    /**
     * id du fichier
     */
    idFichier: number;
      body?: { 'orderId'?: number, 'userId'?: number, 'file'?: Blob }
  }): Observable<void> {

    return this.addFichierBinaire$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation delFichier
   */
  static readonly DelFichierPath = '/documents/fichier/{idFichier}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delFichier()` instead.
   *
   * This method doesn't expect any request body.
   */
  delFichier$Response(params: {

    /**
     * id du fichier
     */
    idFichier: number;

  }): Observable<StrictHttpResponse<Document>> {

    const rb = new RequestBuilder(this.rootUrl, DocumentationService.DelFichierPath, 'delete');
    if (params) {

      rb.path('idFichier', params.idFichier);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Document>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `delFichier$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delFichier(params: {

    /**
     * id du fichier
     */
    idFichier: number;

  }): Observable<Document> {

    return this.delFichier$Response(params).pipe(
      map((r: StrictHttpResponse<Document>) => r.body as Document)
    );
  }

}
