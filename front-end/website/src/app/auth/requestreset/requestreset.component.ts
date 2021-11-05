import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {
  NB_AUTH_OPTIONS,
} from '@nebular/auth';
import {AuthentificationService} from '../../../api/generated/authorization/services/authentification.service';
import {Login} from '../../../api/generated/authorization/models/login';


@Component({
  selector: 'ngx-register',
  styleUrls: ['./requestreset.component.scss'],
  templateUrl: './requestreset.component.html',
})
export class RequestresetComponent implements OnInit {

  showMessages: any = {};


  submitted = false;
  errors: string[] = [];
  messages: string[] = [];
  user: any = {};


  private login: Login = {};

  constructor(@Inject(NB_AUTH_OPTIONS) protected options = {},
              protected router: Router,
              private route: ActivatedRoute,
              private authentificationService: AuthentificationService) {

  }

  /**
   * demander la réinit du mpt de passe
   */
  requestreset(): void {

    localStorage.clear();
    this.errors = this.messages = [];
    this.submitted = true;

    this.login.login = this.user.email;

    this.authentificationService.resetPassword({body: this.login})
      .subscribe(
        (data) => {
          console.info(data);
        },
        (error) => {
          console.info(error);
        },
        () => {
          console.info('demande de réinit du mot de passe OK');
          return this.router.navigateByUrl('/auth/login');
        },
      );

  }


  ngOnInit(): void {
  }


}
