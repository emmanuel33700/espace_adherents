/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { ListeCommunications } from '../models/liste-communications';


/**
 * Gestion de communication d'un adherent
 */
@Injectable({
  providedIn: 'root',
})
export class CommunicationService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation getListeCommunicationAdhrent
   */
  static readonly GetListeCommunicationAdhrentPath = '/adherent/{idadh}/communications';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getListeCommunicationAdhrent()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeCommunicationAdhrent$Response(params: {

    /**
     * id d&#x27;adherent à recuperer
     */
    idadh: number;

  }): Observable<StrictHttpResponse<ListeCommunications>> {

    const rb = new RequestBuilder(this.rootUrl, CommunicationService.GetListeCommunicationAdhrentPath, 'get');
    if (params) {

      rb.path('idadh', params.idadh);

    }
    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json'
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ListeCommunications>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `getListeCommunicationAdhrent$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getListeCommunicationAdhrent(params: {

    /**
     * id d&#x27;adherent à recuperer
     */
    idadh: number;

  }): Observable<ListeCommunications> {

    return this.getListeCommunicationAdhrent$Response(params).pipe(
      map((r: StrictHttpResponse<ListeCommunications>) => r.body as ListeCommunications)
    );
  }

}
