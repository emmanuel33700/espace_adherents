/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ErrorHandler, NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { CoreModule } from './@core/core.module';
import { ThemeModule } from './@theme/theme.module';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import {
  NbChatModule,
  NbDatepickerModule,
  NbDialogModule,
  NbMenuModule,
  NbSidebarModule,
  NbToastrModule,
  NbWindowModule,
} from '@nebular/theme';
import {ApiInterceptor} from './@core/utils/api.interceptor';
import {GlobalErrorHandler} from './@core/utils/global-error-handler.service';
import {TokenInterceptor} from './@core/utils/token.interceptor';
import {ApiModule as ApiModuleAdherents} from '../api/generated/adherents/api.module';
import {ApiModule as ApiModuleAuthorization} from '../api/generated/authorization/api.module';
import {ApiModule as ApiModuleUtilitaire} from '../api/generated/utilitaire/api.module';
import {environment} from '../environments/environment';
import { provideNgxMask } from 'ngx-mask';

@NgModule({ declarations: [AppComponent],
    bootstrap: [AppComponent], imports: [BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        NbSidebarModule.forRoot(),
        NbMenuModule.forRoot(),
        NbDatepickerModule.forRoot(),
        NbDialogModule.forRoot(),
        NbWindowModule.forRoot(),
        NbToastrModule.forRoot(),
        NbChatModule.forRoot({
            messageGoogleMapKey: 'AIzaSyA_wNuCzia92MAmdLRzmqitRGvCF7wCZPY',
        }),
        CoreModule.forRoot(),
        ThemeModule.forRoot(),
        ApiModuleAdherents.forRoot({ rootUrl: environment.resource_adherents }),
        ApiModuleAuthorization.forRoot({ rootUrl: environment.resource_authorization }),
        ApiModuleUtilitaire.forRoot({ rootUrl: environment.resource_utilitaire })], providers: [
        {provide: ErrorHandler, useClass: GlobalErrorHandler},
        provideBrowserGlobalErrorListeners(),
        provideNgxMask({
            showMaskTyped: true,
            // clearIfNotMatch : true
        }),
        {
            provide: HTTP_INTERCEPTORS,
            useClass: TokenInterceptor,
            multi: true,
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ApiInterceptor,
            multi: true,
        },
        provideHttpClient(withInterceptorsFromDi()),
    ] })
export class AppModule {
}
