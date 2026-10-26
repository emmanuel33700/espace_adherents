/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { getListeCommunicationAdhrent } from '../fn/communication/get-liste-communication-adhrent';
import { GetListeCommunicationAdhrent$Params } from '../fn/communication/get-liste-communication-adhrent';
import { ListeCommunications } from '../models/liste-communications';


/**
 * Gestion de communication d'un adherent
 */
@Injectable({ providedIn: 'root' })
export class CommunicationService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getListeCommunicationAdhrent()` */
  static readonly GetListeCommunicationAdhrentPath = '/adherent/{idadh}/communications';

  /**
   * rechercher la liste de communications que le client a recu.
   *
   *
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeCommunicationAdhrent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeCommunicationAdhrent$Response(params: GetListeCommunicationAdhrent$Params, context?: HttpContext): Observable<StrictHttpResponse<ListeCommunications>> {
    return getListeCommunicationAdhrent(this.http, this.rootUrl, params, context);
  }

  /**
   * rechercher la liste de communications que le client a recu.
   *
   *
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getListeCommunicationAdhrent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeCommunicationAdhrent(params: GetListeCommunicationAdhrent$Params, context?: HttpContext): Observable<ListeCommunications> {
    return this.getListeCommunicationAdhrent$Response(params, context).pipe(
      map((r: StrictHttpResponse<ListeCommunications>): ListeCommunications => r.body)
    );
  }

}
