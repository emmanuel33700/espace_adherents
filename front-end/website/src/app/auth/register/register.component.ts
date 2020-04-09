/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { getDeepFromObject } from '../../../framework/auth/helpers';
import {
  NB_AUTH_OPTIONS,
} from '@nebular/auth';



@Component({
  selector: 'ngx-register',
  styleUrls: ['./register.component.scss'],
  templateUrl: './register.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NbRegisterComponent {

  redirectDelay: number = 0;
  showMessages: any = {};
  strategy: string = '';

  submitted = false;
  errors: string[] = [];
  messages: string[] = [];
  user: any = {};

  constructor(@Inject(NB_AUTH_OPTIONS) protected options = {},
              protected cd: ChangeDetectorRef,
              protected router: Router) {

    this.redirectDelay = this.getConfigValue('forms.register.redirectDelay');
    this.showMessages = this.getConfigValue('forms.register.showMessages');
    this.strategy = this.getConfigValue('forms.register.strategy');

  }

  register(): void {
    this.errors = this.messages = [];
    this.submitted = true;

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
}
