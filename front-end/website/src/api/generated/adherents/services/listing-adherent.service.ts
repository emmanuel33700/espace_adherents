/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { getListeAdherents } from '../fn/listing-adherent/get-liste-adherents';
import { GetListeAdherents$Params } from '../fn/listing-adherent/get-liste-adherents';
import { getListeAdherentsFiltreSaison } from '../fn/listing-adherent/get-liste-adherents-filtre-saison';
import { GetListeAdherentsFiltreSaison$Params } from '../fn/listing-adherent/get-liste-adherents-filtre-saison';
import { getListeAdherentsSaison } from '../fn/listing-adherent/get-liste-adherents-saison';
import { GetListeAdherentsSaison$Params } from '../fn/listing-adherent/get-liste-adherents-saison';
import { getListeAdherentsSaison2 } from '../fn/listing-adherent/get-liste-adherents-saison-2';
import { GetListeAdherentsSaison2$Params } from '../fn/listing-adherent/get-liste-adherents-saison-2';
import { ListeAdherent2 } from '../models/liste-adherent-2';
import { ListeAdherents } from '../models/liste-adherents';


/**
 * listing des adhérents
 */
@Injectable({ providedIn: 'root' })
export class ListingAdherentService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getListeAdherents()` */
  static readonly GetListeAdherentsPath = '/adherent/liste';

  /**
   * Récupérer l'ensemble des adhérents.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeAdherents()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherents$Response(params?: GetListeAdherents$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeAdherents>> {
    return getListeAdherents(this.http, this.rootUrl, params, context);
  }

  /**
   * Récupérer l'ensemble des adhérents.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getListeAdherents$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherents(params?: GetListeAdherents$Params, context?: HttpContext): Observable<ListeAdherents> {
    return this.getListeAdherents$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeAdherents>): ListeAdherents => r.body)
    );
  }

  /** Path part for operation `getListeAdherentsSaison()` */
  static readonly GetListeAdherentsSaisonPath = '/adherent/listeSaison';

  /**
   * Récupérer l'ensemble des adhérents de la saison.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeAdherentsSaison()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherentsSaison$Response(params?: GetListeAdherentsSaison$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeAdherents>> {
    return getListeAdherentsSaison(this.http, this.rootUrl, params, context);
  }

  /**
   * Récupérer l'ensemble des adhérents de la saison.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getListeAdherentsSaison$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherentsSaison(params?: GetListeAdherentsSaison$Params, context?: HttpContext): Observable<ListeAdherents> {
    return this.getListeAdherentsSaison$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeAdherents>): ListeAdherents => r.body)
    );
  }

  /** Path part for operation `getListeAdherentsSaison2()` */
  static readonly GetListeAdherentsSaison2Path = '/adherent/listeSaison2';

  /**
   * Récupérer l'ensemble des adhérents de la saison enrichi avec les informations de l'adhésion.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeAdherentsSaison2()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherentsSaison2$Response(params?: GetListeAdherentsSaison2$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeAdherent2>> {
    return getListeAdherentsSaison2(this.http, this.rootUrl, params, context);
  }

  /**
   * Récupérer l'ensemble des adhérents de la saison enrichi avec les informations de l'adhésion.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getListeAdherentsSaison2$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherentsSaison2(params?: GetListeAdherentsSaison2$Params, context?: HttpContext): Observable<ListeAdherent2> {
    return this.getListeAdherentsSaison2$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeAdherent2>): ListeAdherent2 => r.body)
    );
  }

  /** Path part for operation `getListeAdherentsFiltreSaison()` */
  static readonly GetListeAdherentsFiltreSaisonPath = '/adherent/listeSaisonFiltre';

  /**
   * Récupérer les  adhérents de la saison ayant accepté d'apparaitre sur le site.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeAdherentsFiltreSaison()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherentsFiltreSaison$Response(params?: GetListeAdherentsFiltreSaison$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeAdherents>> {
    return getListeAdherentsFiltreSaison(this.http, this.rootUrl, params, context);
  }

  /**
   * Récupérer les  adhérents de la saison ayant accepté d'apparaitre sur le site.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getListeAdherentsFiltreSaison$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdherentsFiltreSaison(params?: GetListeAdherentsFiltreSaison$Params, context?: HttpContext): Observable<ListeAdherents> {
    return this.getListeAdherentsFiltreSaison$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeAdherents>): ListeAdherents => r.body)
    );
  }

}
