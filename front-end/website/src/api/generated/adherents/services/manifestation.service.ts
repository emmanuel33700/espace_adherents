/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { ListeManifestations } from '../models/liste-manifestations';
import { ParticipationManifestation } from '../models/participation-manifestation';


/**
 * Gestion de la participation d'un adherent à une manifestion
 */
@Injectable({
  providedIn: 'root',
})
export class ManifestationService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation getListeManifestationsAdherent
   */
  static readonly GetListeManifestationsAdherentPath = '/adherent/{idadh}/manifestions';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeManifestationsAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeManifestationsAdherent$Response(params: {

    /**
     * id d&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * date de début
     */
    datedebut?: string;

    /**
     * date de fin
     */
    datefin?: string;

  }): Observable<StrictHttpResponse<ListeManifestations>> {

    const rb = new RequestBuilder(this.rootUrl, ManifestationService.GetListeManifestationsAdherentPath, 'get');
    if (params) {

      rb.path('idadh', params.idadh);
      rb.query('datedebut', params.datedebut);
      rb.query('datefin', params.datefin);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListeManifestations>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getListeManifestationsAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeManifestationsAdherent(params: {

    /**
     * id d&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * date de début
     */
    datedebut?: string;

    /**
     * date de fin
     */
    datefin?: string;

  }): Observable<ListeManifestations> {

    return this.getListeManifestationsAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<ListeManifestations>) => r.body as ListeManifestations)
    );
  }

  /**
   * Path part for operation updateManifestationAdherent
   */
  static readonly UpdateManifestationAdherentPath = '/adherent/{idadh}/manifestion/{idManifestation}';

  /**
   * Mise à jour d'une manifestation pour un adherent
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateManifestationAdherent()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateManifestationAdherent$Response(params: {

    /**
     * id de l&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de la manifestation à modifier
     */
    idManifestation: number;
  
    /**
     * mise à jour d'une adhesion
     */
    body: ParticipationManifestation
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, ManifestationService.UpdateManifestationAdherentPath, 'put');
    if (params) {

      rb.path('idadh', params.idadh);
      rb.path('idManifestation', params.idManifestation);

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
   * Mise à jour d'une manifestation pour un adherent
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `updateManifestationAdherent$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateManifestationAdherent(params: {

    /**
     * id de l&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de la manifestation à modifier
     */
    idManifestation: number;
  
    /**
     * mise à jour d'une adhesion
     */
    body: ParticipationManifestation
  }): Observable<void> {

    return this.updateManifestationAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation ajoutManifestationAdherent
   */
  static readonly AjoutManifestationAdherentPath = '/adherent/{idadh}/manifestion/{idManifestation}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `ajoutManifestationAdherent()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutManifestationAdherent$Response(params: {

    /**
     * id l&#x27;adherent à modifier
     */
    idadh: number;

    /**
     * id de la manifestation à modifier
     */
    idManifestation: number;
  
    /**
     * Besoin de l'objet manifestation le lier à un adherents
     */
    body: ParticipationManifestation
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, ManifestationService.AjoutManifestationAdherentPath, 'post');
    if (params) {

      rb.path('idadh', params.idadh);
      rb.path('idManifestation', params.idManifestation);

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
   * To access the full response (for headers, for example), `ajoutManifestationAdherent$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutManifestationAdherent(params: {

    /**
     * id l&#x27;adherent à modifier
     */
    idadh: number;

    /**
     * id de la manifestation à modifier
     */
    idManifestation: number;
  
    /**
     * Besoin de l'objet manifestation le lier à un adherents
     */
    body: ParticipationManifestation
  }): Observable<void> {

    return this.ajoutManifestationAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation deleteManifestationAdherent
   */
  static readonly DeleteManifestationAdherentPath = '/adherent/{idadh}/manifestion/{idManifestation}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteManifestationAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteManifestationAdherent$Response(params: {

    /**
     * id de l&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de la manifestation à supprimer
     */
    idManifestation: number;

  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, ManifestationService.DeleteManifestationAdherentPath, 'delete');
    if (params) {

      rb.path('idadh', params.idadh);
      rb.path('idManifestation', params.idManifestation);

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
   * To access the full response (for headers, for example), `deleteManifestationAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteManifestationAdherent(params: {

    /**
     * id de l&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de la manifestation à supprimer
     */
    idManifestation: number;

  }): Observable<void> {

    return this.deleteManifestationAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

}
