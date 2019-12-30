import {Component, NgModule} from '@angular/core';



@Component({
  selector: 'ngx-list',
  templateUrl: 'listecommunicationscomponent.html',
  styleUrls: ['listecommunications.component.scss'],
})

export class ListecommunicationsComponent {

  users: { libelle: string, name: string, title: string, color: string }[] = [
    { libelle: '4', name: 'Assemble générale du 1er janvier 2020', title: 'en cours', color: 'red' },
    { libelle: '3', name: 'mail Fete du la lune du 1er fevrier 2020', title: 'envoyé' , color: 'red'},
    { libelle: '2', name: 'mail Fete du soleil du 1er fevrier 2020', title: 'envoyé' , color: 'blue'},
    { libelle: '1', name: 'mail assemblé générale du 1er dec 2020', title: 'ouvert' , color: 'green' },
  ];

}
