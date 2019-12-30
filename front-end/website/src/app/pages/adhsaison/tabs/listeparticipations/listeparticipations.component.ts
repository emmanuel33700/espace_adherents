import {Component, NgModule} from '@angular/core';



@Component({
  selector: 'ngx-list',
  templateUrl: 'listeparticipations.component.html',
  styleUrls: ['listeparticipations.component.scss'],
})

export class ListeparticipationsComponent {

  users: { libelle: string, name: string, title: string, color: string }[] = [
    { libelle: '4', name: 'Assemble générale', title: 'Participe pas', color: 'red' },
    { libelle: '3', name: 'Fete du la lune', title: 'Participe pas' , color: 'red'},
    { libelle: '2', name: 'Fete du soleil', title: 'Participe' , color: 'blue'},
    { libelle: '1', name: 'Fete des etoiles', title: 'Participe' , color: 'blue' },
  ];

}
