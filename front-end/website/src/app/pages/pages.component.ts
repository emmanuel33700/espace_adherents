import {Component, OnInit} from '@angular/core';

import { MENU_ITEMS_ADMIN } from './pages-menu';
import { MENU_ITEMS_ADH } from './pages-menu';

@Component({
  selector: 'ngx-pages',
  styleUrls: ['pages.component.scss'],
  template: `
    <ngx-one-column-layout >
        <nb-menu *ngIf="role==='ADMIN'" [items]="menu_admin"></nb-menu>
	<nb-menu *ngIf="role==='ADH'" [items]="menu_adh"></nb-menu>
      <router-outlet></router-outlet>
    </ngx-one-column-layout>
  `,
})
export class PagesComponent  implements OnInit {

  role: string;

  menu_admin = MENU_ITEMS_ADMIN;
  menu_adh = MENU_ITEMS_ADH;

  ngOnInit() {
    this.role = this.readLocalStorageValue('Role');
 	console.info(this.role);
  }

  readLocalStorageValue(key: string): string {
    return localStorage.getItem(key);
  }





}
