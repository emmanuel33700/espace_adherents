/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */


import {Component, Inject} from '@angular/core';
import {Router} from '@angular/router';
import {
  NbAuthResult,
  NbAuthService,
  NB_AUTH_OPTIONS,
  nbAuthCreateToken,
  NbAuthJWTToken,
  NbAuthToken, NbAuthOAuth2Token,
} from '@nebular/auth';
import {getDeepFromObject} from '../../../framework/auth/helpers';
import {AdherentService} from '../../../api/generated/adherents/services/adherent.service';
import {LoggerService} from '../../@core/utils';
import {TokenService} from '../../@core/utils/token.service';

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
              protected router: Router,
              private adherentService: AdherentService,
              private loggerService: LoggerService,
              private tokenService: TokenService) {
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
localStorage.clear();
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

        const idAdh: number = this.tokenService.getIdAdherent();
        this.miseEnSessionInfoAdherent(idAdh);

        this.miseEnSessionRoleAdherent();

        this.recupererSaisonCourant();

        setTimeout(() => {
          return this.router.navigateByUrl(redirect);
        }, this.redirectDelay);
      }
    });
  }


  /**
   * CHarger dans le local storage les informations de l'adhérent conencté
   * @param idAdherents
   */
  miseEnSessionInfoAdherent(idAdherents: number) {
    // recuperation des informations de l'adhérents
    this.adherentService.getAdherent({
      idadh: idAdherents,
    }).subscribe(
      (data) => {
        localStorage.setItem('adherent', JSON.stringify(data));
      },
      (error) => {
        this.loggerService.error(error);
      },
      () => {

      });
  }


  logout() {
localStorage.clear();
    this.authService.logout('password')
      .subscribe(() => {
        this.token = null;
      });
  }

  getConfigValue(key: string): any {
    return getDeepFromObject(this.options, key, null);
  }

  /**
   * récupérer la saison courante
   */
  private recupererSaisonCourant() {
    // TODO à récupérer l'id de l'adhésion courante
    localStorage.setItem('id_annee_adhesion', '24');
  }

  /**
   * Mise en session du groupe (droit) auquel appartien l'adhérent
   */
  private miseEnSessionRoleAdherent() {
    const role: string = this.tokenService.getRoleAdherent();
    localStorage.setItem('ROLE', role);
  }
}
