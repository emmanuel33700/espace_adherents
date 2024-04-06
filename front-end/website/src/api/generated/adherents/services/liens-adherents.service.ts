/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { ajoutLienAdherent } from '../fn/liens-adherents/ajout-lien-adherent';
import { AjoutLienAdherent$Params } from '../fn/liens-adherents/ajout-lien-adherent';
import { deleteLienAdherent } from '../fn/liens-adherents/delete-lien-adherent';
import { DeleteLienAdherent$Params } from '../fn/liens-adherents/delete-lien-adherent';
import { getLienAdherent } from '../fn/liens-adherents/get-lien-adherent';
import { GetLienAdherent$Params } from '../fn/liens-adherents/get-lien-adherent';
import { LiensAdherent } from '../models/liens-adherent';


/**
 * Gestion des liens entre adherents
 */
@Injectable({ providedIn: 'root' })
export class LiensAdherentsService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getLienAdherent()` */
  static readonly GetLienAdherentPath = '/adherent/{idadh}/liens';

  /**
   * recuperer  les liens d'un adherent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getLienAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getLienAdherent$Response(params: GetLienAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<LiensAdherent>> {
    return getLienAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * recuperer  les liens d'un adherent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getLienAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getLienAdherent(params: GetLienAdherent$Params, context?: HttpContext): Observable<LiensAdherent> {
    return this.getLienAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<LiensAdherent>): LiensAdherent => r.body)
    );
  }

  /** Path part for operation `ajoutLienAdherent()` */
  static readonly AjoutLienAdherentPath = '/adherent/{idadh}/lien/{idAdhLien}';

  /**
   * Ajouter un lien entre 2 adherents.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `ajoutLienAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  ajoutLienAdherent$Response(params: AjoutLienAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return ajoutLienAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * Ajouter un lien entre 2 adherents.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `ajoutLienAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  ajoutLienAdherent(params: AjoutLienAdherent$Params, context?: HttpContext): Observable<void> {
    return this.ajoutLienAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `deleteLienAdherent()` */
  static readonly DeleteLienAdherentPath = '/adherent/{idadh}/lien/{idAdhLien}';

  /**
   * Supprimer un lien adherent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteLienAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteLienAdherent$Response(params: DeleteLienAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteLienAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * Supprimer un lien adherent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteLienAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteLienAdherent(params: DeleteLienAdherent$Params, context?: HttpContext): Observable<void> {
    return this.deleteLienAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
