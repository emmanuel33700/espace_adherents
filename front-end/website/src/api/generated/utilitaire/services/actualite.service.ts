/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { addActualite } from '../fn/actualite/add-actualite';
import { AddActualite$Params } from '../fn/actualite/add-actualite';
import { delActualite } from '../fn/actualite/del-actualite';
import { DelActualite$Params } from '../fn/actualite/del-actualite';
import { getListeActualite } from '../fn/actualite/get-liste-actualite';
import { GetListeActualite$Params } from '../fn/actualite/get-liste-actualite';
import { ListeActualite } from '../models/liste-actualite';


/**
 * Gestion des actualité
 */
@Injectable({ providedIn: 'root' })
export class ActualiteService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getListeActualite()` */
  static readonly GetListeActualitePath = '/actualite/information';

  /**
   * récupérer la liste des actualités.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeActualite()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeActualite$Response(params?: GetListeActualite$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeActualite>> {
    return getListeActualite(this.http, this.rootUrl, params, context);
  }

  /**
   * récupérer la liste des actualités.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getListeActualite$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeActualite(params?: GetListeActualite$Params, context?: HttpContext): Observable<ListeActualite> {
    return this.getListeActualite$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeActualite>): ListeActualite => r.body)
    );
  }

  /** Path part for operation `addActualite()` */
  static readonly AddActualitePath = '/actualite/information';

  /**
   * Ajouter d'une actualité.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addActualite()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addActualite$Response(params: AddActualite$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return addActualite(this.http, this.rootUrl, params, context);
  }

  /**
   * Ajouter d'une actualité.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addActualite$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addActualite(params: AddActualite$Params, context?: HttpContext): Observable<void> {
    return this.addActualite$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `delActualite()` */
  static readonly DelActualitePath = '/actualite/information/{idActualite}';

  /**
   * Supprimer d'une actualité.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delActualite()` instead.
   *
   * This method doesn't expect any request body.
   */
  delActualite$Response(params: DelActualite$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return delActualite(this.http, this.rootUrl, params, context);
  }

  /**
   * Supprimer d'une actualité.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `delActualite$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delActualite(params: DelActualite$Params, context?: HttpContext): Observable<void> {
    return this.delActualite$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
