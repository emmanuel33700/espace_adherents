/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { Adhesion } from '../models/adhesion';
import { ajoutAdhesionAdherent } from '../fn/adhesion/ajout-adhesion-adherent';
import { AjoutAdhesionAdherent$Params } from '../fn/adhesion/ajout-adhesion-adherent';
import { deleteAdhesionAdherent } from '../fn/adhesion/delete-adhesion-adherent';
import { DeleteAdhesionAdherent$Params } from '../fn/adhesion/delete-adhesion-adherent';
import { getAdhesionAdherent } from '../fn/adhesion/get-adhesion-adherent';
import { GetAdhesionAdherent$Params } from '../fn/adhesion/get-adhesion-adherent';
import { getListeAdhesionsAdherent } from '../fn/adhesion/get-liste-adhesions-adherent';
import { GetListeAdhesionsAdherent$Params } from '../fn/adhesion/get-liste-adhesions-adherent';
import { ListeAdhesions } from '../models/liste-adhesions';
import { updateAdhesionAdherent } from '../fn/adhesion/update-adhesion-adherent';
import { UpdateAdhesionAdherent$Params } from '../fn/adhesion/update-adhesion-adherent';


/**
 * gestion des adhesions
 */
@Injectable({ providedIn: 'root' })
export class AdhesionService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `ajoutAdhesionAdherent()` */
  static readonly AjoutAdhesionAdherentPath = '/adherent/{idadh}/adhesion';

  /**
   * Ajouter une adhesions pour un adherent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `ajoutAdhesionAdherent()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutAdhesionAdherent$Response(params: AjoutAdhesionAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return ajoutAdhesionAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * Ajouter une adhesions pour un adherent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `ajoutAdhesionAdherent$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutAdhesionAdherent(params: AjoutAdhesionAdherent$Params, context?: HttpContext): Observable<void> {
    return this.ajoutAdhesionAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getListeAdhesionsAdherent()` */
  static readonly GetListeAdhesionsAdherentPath = '/adherent/{idadh}/adhesions';

  /**
   * rechercher la liste des adhésions pour un adherent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeAdhesionsAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdhesionsAdherent$Response(params: GetListeAdhesionsAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeAdhesions>> {
    return getListeAdhesionsAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * rechercher la liste des adhésions pour un adherent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getListeAdhesionsAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdhesionsAdherent(params: GetListeAdhesionsAdherent$Params, context?: HttpContext): Observable<ListeAdhesions> {
    return this.getListeAdhesionsAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeAdhesions>): ListeAdhesions => r.body)
    );
  }

  /** Path part for operation `getAdhesionAdherent()` */
  static readonly GetAdhesionAdherentPath = '/adherent/{idadh}/adhesion/{idAdhesion}';

  /**
   * rechercher une adhésion pour un adherent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAdhesionAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdhesionAdherent$Response(params: GetAdhesionAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<Adhesion>> {
    return getAdhesionAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * rechercher une adhésion pour un adherent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAdhesionAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdhesionAdherent(params: GetAdhesionAdherent$Params, context?: HttpContext): Observable<Adhesion> {
    return this.getAdhesionAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<Adhesion>): Adhesion => r.body)
    );
  }

  /** Path part for operation `updateAdhesionAdherent()` */
  static readonly UpdateAdhesionAdherentPath = '/adherent/{idadh}/adhesion/{idAdhesion}';

  /**
   * Mise à jour d'une adhesion pour cet adherent.
   *
   * Mise à jour d'une adhesion pour cet adherent
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateAdhesionAdherent()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateAdhesionAdherent$Response(params: UpdateAdhesionAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return updateAdhesionAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * Mise à jour d'une adhesion pour cet adherent.
   *
   * Mise à jour d'une adhesion pour cet adherent
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateAdhesionAdherent$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateAdhesionAdherent(params: UpdateAdhesionAdherent$Params, context?: HttpContext): Observable<void> {
    return this.updateAdhesionAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `deleteAdhesionAdherent()` */
  static readonly DeleteAdhesionAdherentPath = '/adherent/{idadh}/adhesion/{idAdhesion}';

  /**
   * Supression d'une adhesion pour cet adherent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteAdhesionAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteAdhesionAdherent$Response(params: DeleteAdhesionAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteAdhesionAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * Supression d'une adhesion pour cet adherent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteAdhesionAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteAdhesionAdherent(params: DeleteAdhesionAdherent$Params, context?: HttpContext): Observable<void> {
    return this.deleteAdhesionAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
