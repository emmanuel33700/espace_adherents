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
    { picture: 'assets/images/nick.png', name: 'CHENAIS Emmanuel', title: 'manu@gmail.com' },
    { picture: 'assets/images/jack.png', name: 'ROBERT Didier', title: 'didier@gmail.com' },
    { picture: 'assets/images/kate.png', name: 'SULIVAN Janitor', title: 'didier@gmail.com' },
    { picture: 'assets/images/eva.png', name: 'COX Perry', title: 'didier@gmail.com' },
    { picture: 'assets/images/lee.png', name: 'SULIVAN Ben', title: 'didier@gmail.com' },
  ];
}
