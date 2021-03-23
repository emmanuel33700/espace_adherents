import {Component, OnInit} from '@angular/core';
import {Adherent} from '../../../../api/generated/adherents/models/adherent';
import {AdherentService} from '../../../../api/generated/adherents/services/adherent.service';
import {LoggerService} from '../../../@core/utils/logger.service';



@Component({
  selector: 'ngx-tabs',
  styleUrls: ['./tabs.component.scss'],
  templateUrl: './tabs.component.html',
})
export class TabsComponent implements OnInit {

  adherent: Adherent = JSON.parse(localStorage.getItem('adh_selected'));
  public idAdherent: number = 0;

  constructor(
    private adherentService: AdherentService,
    private loggerService: LoggerService,
  ) {}

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


  ngOnInit(): void {
    this.idAdherent = Number(localStorage.getItem('id_adh_selected'));
   // this.adherent = JSON.parse(localStorage.getItem('adh_selected'));

    this.loggerService.info('id adh recupere ' + this.idAdherent);

  }

}
