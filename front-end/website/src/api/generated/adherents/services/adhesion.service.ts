/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { Adhesion } from '../models/adhesion';
import { ListeAdhesions } from '../models/liste-adhesions';


/**
 * gestion des adhesions
 */
@Injectable({
  providedIn: 'root',
})
export class AdhesionService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation ajoutAdhesionAdherent
   */
  static readonly AjoutAdhesionAdherentPath = '/adherent/{idadh}/adhesion';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `ajoutAdhesionAdherent()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutAdhesionAdherent$Response(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;
  
    /**
     * Objet adhesion
     */
    body: Adhesion
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AdhesionService.AjoutAdhesionAdherentPath, 'post');
    if (params) {

      rb.path('idadh', params.idadh);

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
   * To access the full response (for headers, for example), `ajoutAdhesionAdherent$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  ajoutAdhesionAdherent(params: {

    /**
     * id de l&#x27;adherent
     */
    idadh: number;
  
    /**
     * Objet adhesion
     */
    body: Adhesion
  }): Observable<void> {

    return this.ajoutAdhesionAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation getListeAdhesionsAdherent
   */
  static readonly GetListeAdhesionsAdherentPath = '/adherent/{idadh}/adhesions';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeAdhesionsAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdhesionsAdherent$Response(params: {

    /**
     * id de l&#x27;adherent à recuperer
     */
    idadh: number;

  }): Observable<StrictHttpResponse<ListeAdhesions>> {

    const rb = new RequestBuilder(this.rootUrl, AdhesionService.GetListeAdhesionsAdherentPath, 'get');
    if (params) {

      rb.path('idadh', params.idadh);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListeAdhesions>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getListeAdhesionsAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeAdhesionsAdherent(params: {

    /**
     * id de l&#x27;adherent à recuperer
     */
    idadh: number;

  }): Observable<ListeAdhesions> {

    return this.getListeAdhesionsAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<ListeAdhesions>) => r.body as ListeAdhesions)
    );
  }

  /**
   * Path part for operation getAdhesionAdherent
   */
  static readonly GetAdhesionAdherentPath = '/adherent/{idadh}/adhesion/{idAdhesion}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAdhesionAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdhesionAdherent$Response(params: {

    /**
     * id de l&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de l&#x27;adhesion à recuperer
     */
    idAdhesion: number;

  }): Observable<StrictHttpResponse<Adhesion>> {

    const rb = new RequestBuilder(this.rootUrl, AdhesionService.GetAdhesionAdherentPath, 'get');
    if (params) {

      rb.path('idadh', params.idadh);
      rb.path('idAdhesion', params.idAdhesion);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Adhesion>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getAdhesionAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAdhesionAdherent(params: {

    /**
     * id de l&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de l&#x27;adhesion à recuperer
     */
    idAdhesion: number;

  }): Observable<Adhesion> {

    return this.getAdhesionAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<Adhesion>) => r.body as Adhesion)
    );
  }

  /**
   * Path part for operation updateAdhesionAdherent
   */
  static readonly UpdateAdhesionAdherentPath = '/adherent/{idadh}/adhesion/{idAdhesion}';

  /**
   * Mise à jour d'une adhesion pour cet adherent
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateAdhesionAdherent()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateAdhesionAdherent$Response(params: {

    /**
     * id de l&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de l&#x27;adhesion de modifier
     */
    idAdhesion: number;
  
    /**
     * mise à jour d'une adhesion
     */
    body: Adhesion
  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AdhesionService.UpdateAdhesionAdherentPath, 'put');
    if (params) {

      rb.path('idadh', params.idadh);
      rb.path('idAdhesion', params.idAdhesion);

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
   * Mise à jour d'une adhesion pour cet adherent
   *
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `updateAdhesionAdherent$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateAdhesionAdherent(params: {

    /**
     * id de l&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de l&#x27;adhesion de modifier
     */
    idAdhesion: number;
  
    /**
     * mise à jour d'une adhesion
     */
    body: Adhesion
  }): Observable<void> {

    return this.updateAdhesionAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation deleteAdhesionAdherent
   */
  static readonly DeleteAdhesionAdherentPath = '/adherent/{idadh}/adhesion/{idAdhesion}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteAdhesionAdherent()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteAdhesionAdherent$Response(params: {

    /**
     * id de l&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de l&#x27;adhesion à supprimer
     */
    idAdhesion: number;

  }): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, AdhesionService.DeleteAdhesionAdherentPath, 'delete');
    if (params) {

      rb.path('idadh', params.idadh);
      rb.path('idAdhesion', params.idAdhesion);

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
   * To access the full response (for headers, for example), `deleteAdhesionAdherent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteAdhesionAdherent(params: {

    /**
     * id de l&#x27;adherent à recuperer
     */
    idadh: number;

    /**
     * id de l&#x27;adhesion à supprimer
     */
    idAdhesion: number;

  }): Observable<void> {

    return this.deleteAdhesionAdherent$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

}
