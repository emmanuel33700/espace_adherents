/* tslint:disable */
import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiConfiguration, ApiConfigurationParams } from './api-configuration';

import { AdherentService } from './services/adherent.service';
import { LiensAdherentsService } from './services/liens-adherents.service';
import { AdhesionService } from './services/adhesion.service';
import { ListingAdherentService } from './services/listing-adherent.service';
import { ManifestationService } from './services/manifestation.service';
import { CommunicationService } from './services/communication.service';
import { ListeDeDiffusionService } from './services/liste-de-diffusion.service';

/**
 * Module that provides all services and configuration.
 */
@NgModule({
  imports: [],
  exports: [],
  declarations: [],
  providers: [
    AdherentService,
    LiensAdherentsService,
    AdhesionService,
    ListingAdherentService,
    ManifestationService,
    CommunicationService,
    ListeDeDiffusionService,
    ApiConfiguration
  ],
})
export class ApiModule {
  static forRoot(params: ApiConfigurationParams): ModuleWithProviders<ApiModule> {
    return {
      ngModule: ApiModule,
      providers: [
        {
          provide: ApiConfiguration,
          useValue: params
        }
      ]
    }
  }

  constructor( 
    @Optional() @SkipSelf() parentModule: ApiModule,
    @Optional() http: HttpClient
  ) {
    if (parentModule) {
      throw new Error('ApiModule is already loaded. Import in your base AppModule only.');
    }
    if (!http) {
      throw new Error('You need to import the HttpClientModule in your AppModule! \n' +
      'See also https://github.com/angular/angular/issues/20575');
    }
  }
}
