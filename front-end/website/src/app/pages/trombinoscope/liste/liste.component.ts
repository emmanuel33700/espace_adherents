import { Component } from '@angular/core';
import { fruits } from './fruits-list';

@Component({
  selector: 'ngx-list',
  templateUrl: 'liste.component.html',
  styleUrls: ['liste.component.scss'],
})
export class ListeComponent {
  fruits = fruits;

  users: { name: string, title: string }[] = [
    { name: 'CHENAIS Emmanuel', title: 'Adhérents' },
    { name: 'ROBERT Didier', title: 'Jeune' },
    { name: 'SULIVAN Janitor', title: 'Adhérents' },
    { name: 'COX Perry', title: 'Resp famille' },
    { name: 'SULIVAN Ben', title: 'Adhérents' },
  ];
}
