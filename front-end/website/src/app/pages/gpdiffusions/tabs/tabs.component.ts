import { Component } from '@angular/core';



@Component({
  selector: 'ngx-tabs',
  styleUrls: ['./tabs.component.scss'],
  templateUrl: './tabs.component.html',
})
export class TabsComponent {

  tabs: any[] = [
    {
      title: 'Liste groupes',
      route: '/pages/gpdiffusions/tabs/liste',
    },
    {
      title: 'Envoyer mail',
      route: '/pages/gpdiffusions/tabs/editeur',
    },

  ];

}
