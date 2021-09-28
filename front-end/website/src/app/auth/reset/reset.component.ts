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
  styleUrls: ['./reset.component.scss'],
  templateUrl: './reset.component.html',
})
export class ResetComponent implements OnInit {

  showMessages: any = {};


  submitted = false;
  errors: string[] = [];
  messages: string[] = [];
  user: any = {};


  private authDto: Authentification = {};

  constructor(@Inject(NB_AUTH_OPTIONS) protected options = {},
              protected router: Router,
              private route: ActivatedRoute,
              private authentificationService: AuthentificationService) {

  }

  register(): void {
    this.errors = this.messages = [];
    this.submitted = true;

    this.authDto.login = this.user.mail;
    this.authDto.password =  this.user.password;

    /**
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
     **/
  }


  ngOnInit(): void {
  }


}
