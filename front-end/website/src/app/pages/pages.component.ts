import {Component, Inject, OnInit} from '@angular/core';

import { MENU_ITEMS_ADMIN } from './pages-menu';
import { MENU_ITEMS_ADH } from './pages-menu';
import {NbAuthService, NbAuthToken} from '@nebular/auth';

@Component({
  selector: 'ngx-pages',
  styleUrls: ['pages.component.scss'],
  template: `
    <ngx-one-column-layout >
        <nb-menu *ngIf="role==='ADMIN'" [items]="menu_admin"></nb-menu>
	<nb-menu *ngIf="role==='ADHERENT'" [items]="menu_adh"></nb-menu>
      <router-outlet></router-outlet>
    </ngx-one-column-layout>
  `,
})
export class PagesComponent  implements OnInit {

  role: string;

  menu_admin = MENU_ITEMS_ADMIN;
  menu_adh = MENU_ITEMS_ADH;

  constructor(private authService: NbAuthService) {}

  ngOnInit() {


    this.authService.getToken()
      .subscribe((token: NbAuthToken) => {
        if (token && token.getValue()) {
          this.role = this.getClaims(token.getValue());
        }
      });


    if (this.role.includes('ADMIN')) {
      this.role = 'ADMIN';
    } else if (this.role.includes('BUREAU')) {
      this.role = 'BUREAU';
    }  else if (this.role.includes('CONSEIL')) {
      this.role = 'CONSEIL';
    }  else {
      this.role = 'ADHERENT';
    }

    console.info(this.role);
    localStorage.setItem('ROLE', this.role);

  }

  readLocalStorageValue(key: string): string {
    return localStorage.getItem(key);
  }


  getClaims(rawToken: string): string {
    console.info(rawToken);
    const obj = JSON.parse(rawToken);
    return obj.authorities ;
  }
}
