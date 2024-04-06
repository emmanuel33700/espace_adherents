/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { ajoutManifestationAdherent } from '../fn/manifestation/ajout-manifestation-adherent';
import { AjoutManifestationAdherent$Params } from '../fn/manifestation/ajout-manifestation-adherent';
import { ajoutManifestationAdherentAccesDirect } from '../fn/manifestation/ajout-manifestation-adherent-acces-direct';
import { AjoutManifestationAdherentAccesDirect$Params } from '../fn/manifestation/ajout-manifestation-adherent-acces-direct';
import { deleteManifestationAdherent } from '../fn/manifestation/delete-manifestation-adherent';
import { DeleteManifestationAdherent$Params } from '../fn/manifestation/delete-manifestation-adherent';
import { getListeManifestationsAdherent } from '../fn/manifestation/get-liste-manifestations-adherent';
import { GetListeManifestationsAdherent$Params } from '../fn/manifestation/get-liste-manifestations-adherent';
import { getManifestationsAdherent } from '../fn/manifestation/get-manifestations-adherent';
import { GetManifestationsAdherent$Params } from '../fn/manifestation/get-manifestations-adherent';
import { ListeManifestations } from '../models/liste-manifestations';
import { Manifestation } from '../models/manifestation';
import { updateManifestationAdherent } from '../fn/manifestation/update-manifestation-adherent';
import { UpdateManifestationAdherent$Params } from '../fn/manifestation/update-manifestation-adherent';


/**
 * Gestion de la participation d'un adherent à une manifestion
 */
@Injectable({ providedIn: 'root' })
export class ManifestationService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getListeManifestationsAdherent()` */
  static readonly GetListeManifestationsAdherentPath = '/adherent/{idadh}/manifestions';

  /**
   * rechercher la liste des manifestations affectée au un adhérents.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeManifestationsAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeManifestationsAdherent$Response(params: GetListeManifestationsAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeManifestations>> {
    return getListeManifestationsAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * rechercher la liste des manifestations affectée au un adhérents.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getListeManifestationsAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeManifestationsAdherent(params: GetListeManifestationsAdherent$Params, context?: HttpContext): Observable<ListeManifestations> {
    return this.getListeManifestationsAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeManifestations>): ListeManifestations => r.body)
    );
  }

  /** Path part for operation `getManifestationsAdherent()` */
  static readonly GetManifestationsAdherentPath = '/adherent/{idadh}/manifestion/{idManifestation}';

  /**
   * rechercher le d'étail d'une  manifestations affectée au un adhérent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getManifestationsAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getManifestationsAdherent$Response(params: GetManifestationsAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<Manifestation>> {
    return getManifestationsAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * rechercher le d'étail d'une  manifestations affectée au un adhérent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getManifestationsAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getManifestationsAdherent(params: GetManifestationsAdherent$Params, context?: HttpContext): Observable<Manifestation> {
    return this.getManifestationsAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<Manifestation>): Manifestation => r.body)
    );
  }

  /** Path part for operation `updateManifestationAdherent()` */
  static readonly UpdateManifestationAdherentPath = '/adherent/{idadh}/manifestion/{idManifestation}';

  /**
   * Mise à jour d'une manifestation pour un adherent.
   *
   * Mise à jour d'une manifestation pour un adherent
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateManifestationAdherent()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateManifestationAdherent$Response(params: UpdateManifestationAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return updateManifestationAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * Mise à jour d'une manifestation pour un adherent.
   *
   * Mise à jour d'une manifestation pour un adherent
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateManifestationAdherent$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateManifestationAdherent(params: UpdateManifestationAdherent$Params, context?: HttpContext): Observable<void> {
    return this.updateManifestationAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `ajoutManifestationAdherent()` */
  static readonly AjoutManifestationAdherentPath = '/adherent/{idadh}/manifestion/{idManifestation}';

  /**
   * Ajouter la participation à une manifestation pour un adherent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `ajoutManifestationAdherent()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutManifestationAdherent$Response(params: AjoutManifestationAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return ajoutManifestationAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * Ajouter la participation à une manifestation pour un adherent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `ajoutManifestationAdherent$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutManifestationAdherent(params: AjoutManifestationAdherent$Params, context?: HttpContext): Observable<void> {
    return this.ajoutManifestationAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `deleteManifestationAdherent()` */
  static readonly DeleteManifestationAdherentPath = '/adherent/{idadh}/manifestion/{idManifestation}';

  /**
   * Supression d'une manifestation pour un adherent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteManifestationAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteManifestationAdherent$Response(params: DeleteManifestationAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteManifestationAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * Supression d'une manifestation pour un adherent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteManifestationAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteManifestationAdherent(params: DeleteManifestationAdherent$Params, context?: HttpContext): Observable<void> {
    return this.deleteManifestationAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `ajoutManifestationAdherentAccesDirect()` */
  static readonly AjoutManifestationAdherentAccesDirectPath = '/adherent/{idadh}/manifestion/{idManifestation}/accesdirect';

  /**
   * Ajouter la participation à une manifestation pour un adherent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `ajoutManifestationAdherentAccesDirect()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutManifestationAdherentAccesDirect$Response(params: AjoutManifestationAdherentAccesDirect$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return ajoutManifestationAdherentAccesDirect(this.http, this.rootUrl, params, context);
  }

  /**
   * Ajouter la participation à une manifestation pour un adherent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `ajoutManifestationAdherentAccesDirect$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutManifestationAdherentAccesDirect(params: AjoutManifestationAdherentAccesDirect$Params, context?: HttpContext): Observable<void> {
    return this.ajoutManifestationAdherentAccesDirect$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
