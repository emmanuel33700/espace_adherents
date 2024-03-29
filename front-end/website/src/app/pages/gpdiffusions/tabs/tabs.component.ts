import {Component, OnInit} from '@angular/core';



@Component({
  selector: 'ngx-tabs',
  styleUrls: ['./tabs.component.scss'],
  templateUrl: './tabs.component.html',
})
export class TabsComponent implements OnInit {

  tabs: any[] = [
    {
      title: 'Liste groupes',
      route: '/pages/gpdiffusions/tabs/liste',
    },


  ];

  ngOnInit(): void {


    const role = localStorage.getItem('ROLE');

    if (role !== 'ADHERENT') {
      this.tabs.push(
        {
          title: 'Envoyer mail',
          route: '/pages/gpdiffusions/tabs/editeur',
        },
        {
          title: 'Administration',
          route: '/pages/gpdiffusions/tabs/admin',
        },
      );
    }
  }

}
