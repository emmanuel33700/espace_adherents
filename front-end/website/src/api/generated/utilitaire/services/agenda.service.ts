/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { Evenement } from '../models/evenement';
import { ListeEvenements } from '../models/liste-evenements';
import { ListeParticipantsEvenement } from '../models/liste-participants-evenement';
import { ListeSyntheseEvenements } from '../models/liste-synthese-evenements';


/**
 * gestion de l'agendas
 */
@Injectable({
  providedIn: 'root',
})
export class AgendaService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation addEvenement
   */
  static readonly AddEvenementPath = '/agenda/evenement';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addEvenement()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addEvenement$Response(params: {
  
    /**
     * Objet adhérent
     */
    body: Evenement
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AgendaService.AddEvenementPath, 'post');
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
   * To access the full response (for headers, for example), `addEvenement$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addEvenement(params: {
  
    /**
     * Objet adhérent
     */
    body: Evenement
  }): Observable<void> {

    return this.addEvenement$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation getEvenement
   */
  static readonly GetEvenementPath = '/agenda/evenement/{idevenement}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getEvenement()` instead.
   *
   * This method doesn't expect any request body.
   */
  getEvenement$Response(params: {

    /**
     * id de l&#x27;evenement
     */
    idevenement: number;

  }): Observable<StrictHttpResponse<Evenement>> {

    const rb = new RequestBuilder(this.rootUrl, AgendaService.GetEvenementPath, 'get');
    if (params) {

      rb.path('idevenement', params.idevenement);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Evenement>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getEvenement$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getEvenement(params: {

    /**
     * id de l&#x27;evenement
     */
    idevenement: number;

  }): Observable<Evenement> {

    return this.getEvenement$Response(params).pipe(
      map((r: StrictHttpResponse<Evenement>) => r.body as Evenement)
    );
  }

  /**
   * Path part for operation updateEvenement
   */
  static readonly UpdateEvenementPath = '/agenda/evenement/{idevenement}';

  /**
   * Mise à jour d'un evenement
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateEvenement()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateEvenement$Response(params: {

    /**
     * id de l&#x27;evenement
     */
    idevenement: number;
  
    /**
     * Objet adherent
     */
    body: Evenement
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AgendaService.UpdateEvenementPath, 'put');
    if (params) {

      rb.path('idevenement', params.idevenement);

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
   * Mise à jour d'un evenement
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `updateEvenement$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateEvenement(params: {

    /**
     * id de l&#x27;evenement
     */
    idevenement: number;
  
    /**
     * Objet adherent
     */
    body: Evenement
  }): Observable<void> {

    return this.updateEvenement$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation deleteEvenement
   */
  static readonly DeleteEvenementPath = '/agenda/evenement/{idevenement}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteEvenement()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteEvenement$Response(params: {

    /**
     * id de l&#x27;evenement
     */
    idevenement: number;

  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AgendaService.DeleteEvenementPath, 'delete');
    if (params) {

      rb.path('idevenement', params.idevenement);

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
   * To access the full response (for headers, for example), `deleteEvenement$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteEvenement(params: {

    /**
     * id de l&#x27;evenement
     */
    idevenement: number;

  }): Observable<void> {

    return this.deleteEvenement$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation getSyntheseEvenement
   */
  static readonly GetSyntheseEvenementPath = '/agenda/evenement/{idevenement}/synthese';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getSyntheseEvenement()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSyntheseEvenement$Response(params: {

    /**
     * id de l&#x27;evenement
     */
    idevenement: number;

  }): Observable<StrictHttpResponse<ListeParticipantsEvenement>> {

    const rb = new RequestBuilder(this.rootUrl, AgendaService.GetSyntheseEvenementPath, 'get');
    if (params) {

      rb.path('idevenement', params.idevenement);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListeParticipantsEvenement>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getSyntheseEvenement$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSyntheseEvenement(params: {

    /**
     * id de l&#x27;evenement
     */
    idevenement: number;

  }): Observable<ListeParticipantsEvenement> {

    return this.getSyntheseEvenement$Response(params).pipe(
      map((r: StrictHttpResponse<ListeParticipantsEvenement>) => r.body as ListeParticipantsEvenement)
    );
  }

  /**
   * Path part for operation getListeEvenements
   */
  static readonly GetListeEvenementsPath = '/agenda/evenements';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeEvenements()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeEvenements$Response(params?: {

  }): Observable<StrictHttpResponse<ListeEvenements>> {

    const rb = new RequestBuilder(this.rootUrl, AgendaService.GetListeEvenementsPath, 'get');
    if (params) {


    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListeEvenements>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getListeEvenements$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeEvenements(params?: {

  }): Observable<ListeEvenements> {

    return this.getListeEvenements$Response(params).pipe(
      map((r: StrictHttpResponse<ListeEvenements>) => r.body as ListeEvenements)
    );
  }

  /**
   * Path part for operation getSyntheseEvenements
   */
  static readonly GetSyntheseEvenementsPath = '/agenda/synthese';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getSyntheseEvenements()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSyntheseEvenements$Response(params?: {

    /**
     * date max de début
     */
    datedebut?: string;

    /**
     * date max de fin
     */
    datefin?: string;

    /**
     * indique si il faut récupérer uniquement les manifestations avec une demande de participation
     */
    retourParticipationAdh?: boolean;

  }): Observable<StrictHttpResponse<ListeSyntheseEvenements>> {

    const rb = new RequestBuilder(this.rootUrl, AgendaService.GetSyntheseEvenementsPath, 'get');
    if (params) {

      rb.query('datedebut', params.datedebut);
      rb.query('datefin', params.datefin);
      rb.query('retourParticipationAdh', params.retourParticipationAdh);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListeSyntheseEvenements>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getSyntheseEvenements$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSyntheseEvenements(params?: {

    /**
     * date max de début
     */
    datedebut?: string;

    /**
     * date max de fin
     */
    datefin?: string;

    /**
     * indique si il faut récupérer uniquement les manifestations avec une demande de participation
     */
    retourParticipationAdh?: boolean;

  }): Observable<ListeSyntheseEvenements> {

    return this.getSyntheseEvenements$Response(params).pipe(
      map((r: StrictHttpResponse<ListeSyntheseEvenements>) => r.body as ListeSyntheseEvenements)
    );
  }

}
