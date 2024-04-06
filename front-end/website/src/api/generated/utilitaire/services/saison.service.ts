/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { getListeSaison } from '../fn/saison/get-liste-saison';
import { GetListeSaison$Params } from '../fn/saison/get-liste-saison';
import { getSaisonCourante } from '../fn/saison/get-saison-courante';
import { GetSaisonCourante$Params } from '../fn/saison/get-saison-courante';
import { ListeSaison } from '../models/liste-saison';
import { Saison } from '../models/saison';
import { updateSaisonCourante } from '../fn/saison/update-saison-courante';
import { UpdateSaisonCourante$Params } from '../fn/saison/update-saison-courante';


/**
 * Gestion des saisons
 */
@Injectable({ providedIn: 'root' })
export class SaisonService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getSaisonCourante()` */
  static readonly GetSaisonCourantePath = '/saison/active';

  /**
   * récupérer la saison courante.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getSaisonCourante()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSaisonCourante$Response(params?: GetSaisonCourante$Params, context?: HttpContext): Observable<StrictHttpResponse<Saison>> {
    return getSaisonCourante(this.http, this.rootUrl, params, context);
  }

  /**
   * récupérer la saison courante.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getSaisonCourante$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getSaisonCourante(params?: GetSaisonCourante$Params, context?: HttpContext): Observable<Saison> {
    return this.getSaisonCourante$Response(params, context).pipe(
      map((r: StrictHttpResponse<Saison>): Saison => r.body)
    );
  }

  /** Path part for operation `updateSaisonCourante()` */
  static readonly UpdateSaisonCourantePath = '/saison/{idSaison}/active';

  /**
   * Mise à jour de la saison courante.
   *
   * Mise à jour de la saison courante
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateSaisonCourante()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateSaisonCourante$Response(params: UpdateSaisonCourante$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return updateSaisonCourante(this.http, this.rootUrl, params, context);
  }

  /**
   * Mise à jour de la saison courante.
   *
   * Mise à jour de la saison courante
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateSaisonCourante$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateSaisonCourante(params: UpdateSaisonCourante$Params, context?: HttpContext): Observable<void> {
    return this.updateSaisonCourante$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getListeSaison()` */
  static readonly GetListeSaisonPath = '/saison/liste';

  /**
   * récupérer la liste des saisons.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeSaison()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeSaison$Response(params?: GetListeSaison$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeSaison>> {
    return getListeSaison(this.http, this.rootUrl, params, context);
  }

  /**
   * récupérer la liste des saisons.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getListeSaison$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeSaison(params?: GetListeSaison$Params, context?: HttpContext): Observable<ListeSaison> {
    return this.getListeSaison$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeSaison>): ListeSaison => r.body)
    );
  }

}
