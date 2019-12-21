import {Component, NgModule} from '@angular/core';
import { fruits } from './fruits-list';
import { Routes, RouterModule } from '@angular/router';



import {  TabsComponent } from '../tabs/tabs.component';

const routes: Routes = [{
  path: 'tabs',
  component: TabsComponent,
}];


@Component({
  selector: 'ngx-list',
  templateUrl: 'liste.component.html',
  styleUrls: ['liste.component.scss'],
})
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
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


  afficherAlert(name: string) {
    console.info('la fiche de' + name);
  }
}
