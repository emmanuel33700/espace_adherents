import { Component } from '@angular/core';



@Component({
  selector: 'ngx-tabs',
  styleUrls: ['./tabs.component.scss'],
  templateUrl: './tabs.component.html',
})
export class TabsComponent {

  tabs: any[] = [
    {
      title: 'Info adhérent',
      route: '/pages/adhsaison/tabs/infosadh',
    },
    {
      title: 'Adhésions',
      route: '/pages/adhsaison/tabs/lstadhesions',
    },
    {
      title: 'Participation',
      route: '/pages/adhsaison/tabs/listeparticipations',
    },
    {
      title: 'Communication',
      route: '/pages/adhsaison/tabs/listecommunications',
    },
    {
      title: 'Connexion',
      route: '/pages/adhsaison/tabs/connexion',
    },
  ];

}
