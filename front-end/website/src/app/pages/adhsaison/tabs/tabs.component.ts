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

  adherent: Adherent;
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
    this.loggerService.info('id adh recupere ' + this.idAdherent);

    this.adherentService.getAdherent({
      idadh: this.idAdherent,
    }).subscribe(
      (data) => {
        this.adherent = data;
      },
      (error) => {
        this.loggerService.error(error);
      },
      () => {
        this.loggerService.info('adherent recupe api '  + JSON.stringify(this.adherent));
        localStorage.setItem('adh_selected', JSON.stringify(this.adherent));
      });

  }

}
