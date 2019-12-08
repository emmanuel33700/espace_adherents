import { Component } from '@angular/core';
import { fruits } from './fruits-list';

@Component({
  selector: 'ngx-list',
  templateUrl: 'liste.component.html',
  styleUrls: ['liste.component.scss'],
})
export class ListeComponent {
  fruits = fruits;

  users: {picture: string, name: string, title: string }[] = [
    { picture: 'assets/images/nick.png', name: 'CHENAIS Emmanuel', title: 'Adhérents' },
    { picture: 'assets/images/nick.png', name: 'ROBERT Didier', title: 'Jeune' },
    { picture: 'assets/images/nick.png', name: 'SULIVAN Janitor', title: 'Adhérents' },
    { picture: 'assets/images/nick.png', name: 'COX Perry', title: 'Resp famille' },
    { picture: 'assets/images/nick.png', name: 'SULIVAN Ben', title: 'Adhérents' },
  ];
}
