/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { addEvenement } from '../fn/agenda/add-evenement';
import { AddEvenement$Params } from '../fn/agenda/add-evenement';
import { deleteEvenement } from '../fn/agenda/delete-evenement';
import { DeleteEvenement$Params } from '../fn/agenda/delete-evenement';
import { Evenement } from '../models/evenement';
import { getEvenement } from '../fn/agenda/get-evenement';
import { GetEvenement$Params } from '../fn/agenda/get-evenement';
import { getListeEvenements } from '../fn/agenda/get-liste-evenements';
import { GetListeEvenements$Params } from '../fn/agenda/get-liste-evenements';
import { getSyntheseEvenement } from '../fn/agenda/get-synthese-evenement';
import { GetSyntheseEvenement$Params } from '../fn/agenda/get-synthese-evenement';
import { getSyntheseEvenements } from '../fn/agenda/get-synthese-evenements';
import { GetSyntheseEvenements$Params } from '../fn/agenda/get-synthese-evenements';
import { ListeEvenements } from '../models/liste-evenements';
import { ListeParticipantsEvenement } from '../models/liste-participants-evenement';
import { ListeSyntheseEvenements } from '../models/liste-synthese-evenements';
import { updateEvenement } from '../fn/agenda/update-evenement';
import { UpdateEvenement$Params } from '../fn/agenda/update-evenement';


/**
 * gestion de l'agendas
 */
@Injectable({ providedIn: 'root' })
export class AgendaService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `addEvenement()` */
  static readonly AddEvenementPath = '/agenda/evenement';

  /**
   * Ajouter un évenement.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addEvenement()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addEvenement$Response(params: AddEvenement$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return addEvenement(this.http, this.rootUrl, params, context);
  }

  /**
   * Ajouter un évenement.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addEvenement$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addEvenement(params: AddEvenement$Params, context?: HttpContext): Observable<void> {
    return this.addEvenement$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getEvenement()` */
  static readonly GetEvenementPath = '/agenda/evenement/{idevenement}';

  /**
   * rechercher un evenement.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getEvenement()` instead.
   *
   * This method doesn't expect any request body.
   */
  getEvenement$Response(params: GetEvenement$Params, context?: HttpContext): Observable<StrictHttpResponse<Evenement>> {
    return getEvenement(this.http, this.rootUrl, params, context);
  }

  /**
   * rechercher un evenement.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getEvenement$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getEvenement(params: GetEvenement$Params, context?: HttpContext): Observable<Evenement> {
    return this.getEvenement$Response(params, context).pipe(
      map((r: StrictHttpResponse<Evenement>): Evenement => r.body)
    );
  }

  /** Path part for operation `updateEvenement()` */
  static readonly UpdateEvenementPath = '/agenda/evenement/{idevenement}';

  /**
   * Mise à jour d'une evenement.
   *
   * Mise à jour d'un evenement
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateEvenement()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateEvenement$Response(params: UpdateEvenement$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return updateEvenement(this.http, this.rootUrl, params, context);
  }

  /**
   * Mise à jour d'une evenement.
   *
   * Mise à jour d'un evenement
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateEvenement$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateEvenement(params: UpdateEvenement$Params, context?: HttpContext): Observable<void> {
    return this.updateEvenement$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `deleteEvenement()` */
  static readonly DeleteEvenementPath = '/agenda/evenement/{idevenement}';

  /**
   * Supprimer un evenement.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteEvenement()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteEvenement$Response(params: DeleteEvenement$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteEvenement(this.http, this.rootUrl, params, context);
  }

  /**
   * Supprimer un evenement.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteEvenement$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteEvenement(params: DeleteEvenement$Params, context?: HttpContext): Observable<void> {
    return this.deleteEvenement$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getSyntheseEvenement()` */
  static readonly GetSyntheseEvenementPath = '/agenda/evenement/{idevenement}/synthese';

  /**
   * rechercher la synthese des participations à un évènement.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getSyntheseEvenement()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSyntheseEvenement$Response(params: GetSyntheseEvenement$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeParticipantsEvenement>> {
    return getSyntheseEvenement(this.http, this.rootUrl, params, context);
  }

  /**
   * rechercher la synthese des participations à un évènement.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getSyntheseEvenement$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSyntheseEvenement(params: GetSyntheseEvenement$Params, context?: HttpContext): Observable<ListeParticipantsEvenement> {
    return this.getSyntheseEvenement$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeParticipantsEvenement>): ListeParticipantsEvenement => r.body)
    );
  }

  /** Path part for operation `getListeEvenements()` */
  static readonly GetListeEvenementsPath = '/agenda/evenements';

  /**
   * rechercher la liste d'évenement.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeEvenements()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeEvenements$Response(params?: GetListeEvenements$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeEvenements>> {
    return getListeEvenements(this.http, this.rootUrl, params, context);
  }

  /**
   * rechercher la liste d'évenement.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getListeEvenements$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeEvenements(params?: GetListeEvenements$Params, context?: HttpContext): Observable<ListeEvenements> {
    return this.getListeEvenements$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeEvenements>): ListeEvenements => r.body)
    );
  }

  /** Path part for operation `getSyntheseEvenements()` */
  static readonly GetSyntheseEvenementsPath = '/agenda/synthese';

  /**
   * rechercher la synthèse des participations aux évènement.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getSyntheseEvenements()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSyntheseEvenements$Response(params?: GetSyntheseEvenements$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeSyntheseEvenements>> {
    return getSyntheseEvenements(this.http, this.rootUrl, params, context);
  }

  /**
   * rechercher la synthèse des participations aux évènement.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getSyntheseEvenements$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSyntheseEvenements(params?: GetSyntheseEvenements$Params, context?: HttpContext): Observable<ListeSyntheseEvenements> {
    return this.getSyntheseEvenements$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeSyntheseEvenements>): ListeSyntheseEvenements => r.body)
    );
  }

}
