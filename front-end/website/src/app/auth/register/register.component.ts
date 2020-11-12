/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { getDeepFromObject } from '../../../framework/auth/helpers';
import {
  NB_AUTH_OPTIONS,
} from '@nebular/auth';
import {AuthentificationService} from '../../../api/generated/authorization/services/authentification.service';
import {Authentification} from '../../../api/generated/authorization/models/authentification';


@Component({
  selector: 'ngx-register',
  styleUrls: ['./register.component.scss'],
  templateUrl: './register.component.html',
})
export class RegisterComponent implements OnInit {

  redirectDelay: number = 0;
  showMessages: any = {};
  strategy: string = '';

  submitted = false;
  errors: string[] = [];
  messages: string[] = [];
  user: any = {};

  mail: string = '';
  id: number = 0;
  private authDto: Authentification = {};

  constructor(@Inject(NB_AUTH_OPTIONS) protected options = {},
              protected router: Router,
              private route: ActivatedRoute,
              private authentificationService: AuthentificationService) {

    this.redirectDelay = this.getConfigValue('forms.register.redirectDelay');
    this.showMessages = this.getConfigValue('forms.register.showMessages');
    this.strategy = this.getConfigValue('forms.register.strategy');

  }

  register(): void {
    this.errors = this.messages = [];
    this.submitted = true;

    this.authDto.idAdh = this.id;
    this.authDto.login = this.mail;
    this.authDto.password =  this.user.password;

    this.authentificationService.addAuthentification({body: this.authDto})
      .subscribe(
        (data) => {
          console.info(data);
        },
        (error) => {
          console.info(error);
        },
        () => {
          console.info('fini');
          return this.router.navigateByUrl('/auth/login');
        },
      );
 //   this.service.register(this.strategy, this.user).subscribe((result: NbAuthResult) => {
 //     this.submitted = false;
 //     if (result.isSuccess()) {
 //       this.messages = result.getMessages();
 //     } else {
 //       this.errors = result.getErrors();
 //     }

  //    const redirect = result.getRedirect();
  //    if (redirect) {
  //      setTimeout(() => {
  //        return this.router.navigateByUrl(redirect);
  //      }, this.redirectDelay);
  //    }
  //    this.cd.detectChanges();
  //  });
  }

  getConfigValue(key: string): any {
    return getDeepFromObject(this.options, key, null);
  }

  ngOnInit(): void {
    this.mail = this.route.snapshot.queryParamMap.get('mail');
    if (!isNaN(Number(this.route.snapshot.queryParamMap.get('id')))) {
      this.id = Number(this.route.snapshot.queryParamMap.get('id'));
      console.info('id ' + this.id);
    } else {
      this.id = -1;
      console.error('Erreur de récupération de id adherent ');
    }

    this.user.email = this.mail;
  }
}
