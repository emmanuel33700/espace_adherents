/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { ListInscritsMailingListe } from '../models/list-inscrits-mailing-liste';
import { ListeDiffusion } from '../models/liste-diffusion';
import { ListeListeDiffusion } from '../models/liste-liste-diffusion';
import { MailAEnvoyer } from '../models/mail-a-envoyer';


/**
 * gestion des listes de diffusions et envoi de mails
 */
@Injectable({
  providedIn: 'root',
})
export class ListeDeDiffusionService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation majListe
   */
  static readonly MajListePath = '/diffusion/liste/{idListe}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `majListe()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  majListe$Response(params: {

    /**
     * id du fichier
     */
    idListe: number;
  
    /**
     * Objet listeDiffusion
     */
    body: ListeDiffusion
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, ListeDeDiffusionService.MajListePath, 'put');
    if (params) {

      rb.path('idListe', params.idListe);

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
   * To access the full response (for headers, for example), `majListe$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  majListe(params: {

    /**
     * id du fichier
     */
    idListe: number;
  
    /**
     * Objet listeDiffusion
     */
    body: ListeDiffusion
  }): Observable<void> {

    return this.majListe$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation addListe
   */
  static readonly AddListePath = '/diffusion/liste/{idListe}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addListe()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addListe$Response(params: {

    /**
     * id de la liste de diffusion
     */
    idListe: number;
  
    /**
     * Objet listeDiffusion
     */
    body: ListeDiffusion
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, ListeDeDiffusionService.AddListePath, 'post');
    if (params) {

      rb.path('idListe', params.idListe);

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
   * To access the full response (for headers, for example), `addListe$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addListe(params: {

    /**
     * id de la liste de diffusion
     */
    idListe: number;
  
    /**
     * Objet listeDiffusion
     */
    body: ListeDiffusion
  }): Observable<void> {

    return this.addListe$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation delListe
   */
  static readonly DelListePath = '/diffusion/liste/{idListe}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delListe()` instead.
   *
   * This method doesn't expect any request body.
   */
  delListe$Response(params: {

    /**
     * id du fichier
     */
    idListe: number;

  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, ListeDeDiffusionService.DelListePath, 'delete');
    if (params) {

      rb.path('idListe', params.idListe);

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
   * To access the full response (for headers, for example), `delListe$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delListe(params: {

    /**
     * id du fichier
     */
    idListe: number;

  }): Observable<void> {

    return this.delListe$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation getAdherentsInscritListe
   */
  static readonly GetAdherentsInscritListePath = '/diffusion/liste/{idListe}/inscrits';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAdherentsInscritListe()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdherentsInscritListe$Response(params: {

    /**
     * id du fichier
     */
    idListe: number;

    /**
     * type de filtre
     */
    filter?: 'ALL' | 'ONLYADH' | 'ONLYINSCRIT';

  }): Observable<StrictHttpResponse<ListInscritsMailingListe>> {

    const rb = new RequestBuilder(this.rootUrl, ListeDeDiffusionService.GetAdherentsInscritListePath, 'get');
    if (params) {

      rb.path('idListe', params.idListe);
      rb.query('filter', params.filter);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListInscritsMailingListe>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getAdherentsInscritListe$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdherentsInscritListe(params: {

    /**
     * id du fichier
     */
    idListe: number;

    /**
     * type de filtre
     */
    filter?: 'ALL' | 'ONLYADH' | 'ONLYINSCRIT';

  }): Observable<ListInscritsMailingListe> {

    return this.getAdherentsInscritListe$Response(params).pipe(
      map((r: StrictHttpResponse<ListInscritsMailingListe>) => r.body as ListInscritsMailingListe)
    );
  }

  /**
   * Path part for operation getListes
   */
  static readonly GetListesPath = '/diffusion/listes';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListes()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListes$Response(params?: {

  }): Observable<StrictHttpResponse<ListeListeDiffusion>> {

    const rb = new RequestBuilder(this.rootUrl, ListeDeDiffusionService.GetListesPath, 'get');
    if (params) {


    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListeListeDiffusion>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getListes$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListes(params?: {

  }): Observable<ListeListeDiffusion> {

    return this.getListes$Response(params).pipe(
      map((r: StrictHttpResponse<ListeListeDiffusion>) => r.body as ListeListeDiffusion)
    );
  }

  /**
   * Path part for operation sendMail
   */
  static readonly SendMailPath = '/diffusion/mail/{idMail}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `sendMail()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendMail$Response(params: {

    /**
     * id du mail à envoyer
     */
    idMail: number;
  
    /**
     * Objet listeDiffusion
     */
    body?: MailAEnvoyer
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, ListeDeDiffusionService.SendMailPath, 'post');
    if (params) {

      rb.path('idMail', params.idMail);

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
   * To access the full response (for headers, for example), `sendMail$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendMail(params: {

    /**
     * id du mail à envoyer
     */
    idMail: number;
  
    /**
     * Objet listeDiffusion
     */
    body?: MailAEnvoyer
  }): Observable<void> {

    return this.sendMail$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation addBinaryToMail
   */
  static readonly AddBinaryToMailPath = '/diffusion/mail/{idMail}/fichier';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addBinaryToMail()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  addBinaryToMail$Response(params: {

    /**
     * id du mail à envoyer
     */
    idMail: number;
      body?: { 'file'?: Blob, 'fileName'?: string }
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, ListeDeDiffusionService.AddBinaryToMailPath, 'post');
    if (params) {

      rb.path('idMail', params.idMail);

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
   * To access the full response (for headers, for example), `addBinaryToMail$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  addBinaryToMail(params: {

    /**
     * id du mail à envoyer
     */
    idMail: number;
      body?: { 'file'?: Blob, 'fileName'?: string }
  }): Observable<void> {

    return this.addBinaryToMail$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation delBinarysToMail
   */
  static readonly DelBinarysToMailPath = '/diffusion/mail/{idMail}/fichier';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delBinarysToMail()` instead.
   *
   * This method doesn't expect any request body.
   */
  delBinarysToMail$Response(params: {

    /**
     * id du mail à envoyer
     */
    idMail: number;

  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, ListeDeDiffusionService.DelBinarysToMailPath, 'delete');
    if (params) {

      rb.path('idMail', params.idMail);

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
   * To access the full response (for headers, for example), `delBinarysToMail$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delBinarysToMail(params: {

    /**
     * id du mail à envoyer
     */
    idMail: number;

  }): Observable<void> {

    return this.delBinarysToMail$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

}
