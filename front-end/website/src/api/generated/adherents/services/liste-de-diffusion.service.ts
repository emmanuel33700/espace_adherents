/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { ajoutListeDiffusionAdherent } from '../fn/liste-de-diffusion/ajout-liste-diffusion-adherent';
import { AjoutListeDiffusionAdherent$Params } from '../fn/liste-de-diffusion/ajout-liste-diffusion-adherent';
import { delListeDiffusionAdherent } from '../fn/liste-de-diffusion/del-liste-diffusion-adherent';
import { DelListeDiffusionAdherent$Params } from '../fn/liste-de-diffusion/del-liste-diffusion-adherent';
import { getListesDiffusionAdherent } from '../fn/liste-de-diffusion/get-listes-diffusion-adherent';
import { GetListesDiffusionAdherent$Params } from '../fn/liste-de-diffusion/get-listes-diffusion-adherent';
import { ListeMailingListe } from '../models/liste-mailing-liste';


/**
 * inscription aux listes de diffusion
 */
@Injectable({ providedIn: 'root' })
export class ListeDeDiffusionService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `ajoutListeDiffusionAdherent()` */
  static readonly AjoutListeDiffusionAdherentPath = '/adherent/{idadh}/diffusion/liste/{idListe}';

  /**
   * Ajouter une inscrition à une liste de diffusion.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `ajoutListeDiffusionAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  ajoutListeDiffusionAdherent$Response(params: AjoutListeDiffusionAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return ajoutListeDiffusionAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * Ajouter une inscrition à une liste de diffusion.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `ajoutListeDiffusionAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  ajoutListeDiffusionAdherent(params: AjoutListeDiffusionAdherent$Params, context?: HttpContext): Observable<void> {
    return this.ajoutListeDiffusionAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `delListeDiffusionAdherent()` */
  static readonly DelListeDiffusionAdherentPath = '/adherent/{idadh}/diffusion/liste/{idListe}';

  /**
   * Supprimer une inscrition à une liste de diffusion.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delListeDiffusionAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  delListeDiffusionAdherent$Response(params: DelListeDiffusionAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return delListeDiffusionAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * Supprimer une inscrition à une liste de diffusion.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `delListeDiffusionAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delListeDiffusionAdherent(params: DelListeDiffusionAdherent$Params, context?: HttpContext): Observable<void> {
    return this.delListeDiffusionAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getListesDiffusionAdherent()` */
  static readonly GetListesDiffusionAdherentPath = '/adherent/{idadh}/diffusion/liste/';

  /**
   * rechercher la liste de diffusion avec le statut de participation pour cet adhérent.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListesDiffusionAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListesDiffusionAdherent$Response(params: GetListesDiffusionAdherent$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeMailingListe>> {
    return getListesDiffusionAdherent(this.http, this.rootUrl, params, context);
  }

  /**
   * rechercher la liste de diffusion avec le statut de participation pour cet adhérent.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getListesDiffusionAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListesDiffusionAdherent(params: GetListesDiffusionAdherent$Params, context?: HttpContext): Observable<ListeMailingListe> {
    return this.getListesDiffusionAdherent$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeMailingListe>): ListeMailingListe => r.body)
    );
  }

}
