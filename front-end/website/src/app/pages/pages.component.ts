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
    this.role = localStorage.getItem('ROLE');
  }

}
