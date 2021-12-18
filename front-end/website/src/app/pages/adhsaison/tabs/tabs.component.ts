import {Component, OnInit} from '@angular/core';
import {Adherent} from '../../../../api/generated/adherents/models/adherent';
import {AdherentService} from '../../../../api/generated/adherents/services/adherent.service';
import {LoggerService} from '../../../@core/utils/logger.service';
import {environment} from '../../../../environments/environment';


@Component({
  selector: 'ngx-tabs',
  styleUrls: ['./tabs.component.scss'],
  templateUrl: './tabs.component.html',
})
export class TabsComponent implements OnInit {

  adherent: Adherent = JSON.parse(localStorage.getItem('adh_selected'));
  public idAdherent: number = 0;
  url_photo_profil: string = environment.url_photo_profil;

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
      title: 'liens de l\'adhérent',
      route: '/pages/adhsaison/tabs/liensadherent',
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
  ];


  ngOnInit(): void {
    this.idAdherent = Number(localStorage.getItem('id_adh_selected'));

    const role = localStorage.getItem('ROLE');

    if (role === 'ADMIN') {
      this.tabs.push(    {
        title: 'Connexion',
        route: '/pages/adhsaison/tabs/connexion',
      });
    }

    this.loggerService.info('id adh recupere ' + this.idAdherent);

  }

}
