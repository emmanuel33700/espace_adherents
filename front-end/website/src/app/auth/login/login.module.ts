import {ThemeModule} from '../../@theme/theme.module';
import {
  NbActionsModule, NbAlertModule,
  NbButtonModule,
  NbCardModule, NbCheckboxModule, NbDatepickerModule, NbIconModule,
  NbInputModule,
  NbLayoutModule,
  NbMenuModule, NbRadioModule, NbSelectModule, NbUserModule,
} from '@nebular/theme';
import {FormsModule as ngFormsModule} from '@angular/forms';

import { LoginComponent } from './login.component';
import {NgModule} from '@angular/core';
import { LoginRoutingModule } from './login-routing.module';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import {
  NbAuthModule,
  NbAuthOAuth2JWTToken,
  NbOAuth2AuthStrategy,
  NbOAuth2ClientAuthMethod,
  NbOAuth2GrantType,
} from '@nebular/auth';
import {ApiModule} from '../../../api/generated/api.module';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,

    NbAuthModule.forRoot({
      forms: {
        login: {
          redirectDelay: 0,
          showMessages: {
            error: true,
            success: false,
          },
          strategy: 'password',
        },
      },
      strategies: [
        NbOAuth2AuthStrategy.setup({
          name: 'password',
          clientId: 'espaceAdh',
          clientSecret: 'secret',
          clientAuthMethod: NbOAuth2ClientAuthMethod.BASIC,
          baseEndpoint: 'https://api.jalle-astro.fr/authorization-server/oauth/',
          token: {
            endpoint: 'token',
            grantType: NbOAuth2GrantType.PASSWORD,
            class: NbAuthOAuth2JWTToken,
            requireValidToken: true,
          },
          redirect: {
            success: '/pages/dashboard',
          },
        }),
      ],
    }),

    ApiModule.forRoot({ rootUrl: 'https://api.jalle-astro.fr/resource-adherents' }),
    ThemeModule,
    NbMenuModule,
    NbLayoutModule,
    NbInputModule,
    NbCardModule,
    NbButtonModule,
    NbActionsModule,
    NbUserModule,
    NbCheckboxModule,
    NbRadioModule,
    NbDatepickerModule,
    NbSelectModule,
    NbIconModule,
    ngFormsModule, LoginRoutingModule, NbAlertModule,
  ],
  declarations: [
    LoginComponent,
  ],
})
export class LoginModule {
}
