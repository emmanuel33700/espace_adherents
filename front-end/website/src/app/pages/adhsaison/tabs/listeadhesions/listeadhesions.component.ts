import {Component, NgModule} from '@angular/core';



@Component({
  selector: 'ngx-list',
  templateUrl: 'listeadhesions.component.html',
  styleUrls: ['listeadhesions.component.scss'],
})

export class ListeadhesionsComponent {

  users: { libelle: string, name: string, title: string }[] = [
    { libelle: '18', name: '2019/2020', title: 'Adhérents' },
    { libelle: '17', name: '2018/2019', title: 'Adhérents' },
    { libelle: '16', name: '2017/2018', title: 'Adhérents' },
    { libelle: '15', name: '2015/2016', title: 'Adhérents'  },
  ];

}
