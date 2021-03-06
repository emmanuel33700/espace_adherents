/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */


import { Component, Inject } from '@angular/core';
import { Router } from '@angular/router';
import {
  NbAuthResult,
  NbAuthService,
  NB_AUTH_OPTIONS,
  nbAuthCreateToken,
  NbAuthJWTToken,
  NbAuthToken, NbAuthOAuth2Token,
} from '@nebular/auth';
import { getDeepFromObject } from '../../../framework/auth/helpers';

@Component({
  selector: 'ngx-playground-auth',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})


export class LoginComponent {


  token: NbAuthToken;
  redirectDelay: number = 0;
  showMessages: any = {};
  strategy: string = '';

  errors: string[] = [];
  messages: string[] = [];
  user: any = {};
  submitted: boolean = false;


  constructor(private authService: NbAuthService, @Inject(NB_AUTH_OPTIONS) protected options = {},
              protected router: Router) {
    this.redirectDelay = this.getConfigValue('forms.login.redirectDelay');
    this.showMessages = this.getConfigValue('forms.login.showMessages');
    this.strategy = this.getConfigValue('forms.login.strategy');

    this.authService.onTokenChange()
      .subscribe((token: NbAuthOAuth2Token) => {
        this.token = null;
        if (token && token.isValid()) {
          this.token = token;
        }
      });
  }

  login(): void {

    this.errors = this.messages = [];
    this.submitted = true;

    this.authService.authenticate(this.strategy, this.user).subscribe((result: NbAuthResult) => {
      this.submitted = false;
      this.token = result.getToken();
      if (result.isSuccess()) {
        this.messages = result.getMessages();
      } else {
        this.errors = result.getErrors();
      }



      const redirect = result.getRedirect();
      if (redirect) {
        setTimeout(() => {
          return this.router.navigateByUrl(redirect);
        }, this.redirectDelay);
      }
    });
  }


  logout() {
    this.authService.logout('password')
      .subscribe(() => {
        this.token = null;
      });
  }

  getConfigValue(key: string): any {
    return getDeepFromObject(this.options, key, null);
  }

  getClaims(rawToken: string): string {
    if (!rawToken) {
      return null;
    }
    return nbAuthCreateToken(NbAuthJWTToken, rawToken, this.strategy).getPayload();
  }

}
