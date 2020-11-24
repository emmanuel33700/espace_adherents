import {Component, HostBinding, OnInit} from '@angular/core';
import {CommunicationService} from '../../../../../api/generated/adherents/services/communication.service';
import {Communication} from '../../../../../api/generated/adherents/models/communication';
import {Router} from '@angular/router';
import {LoggerService} from '../../../../@core/utils';


@Component({
  selector: 'ngx-list',
  templateUrl: 'listecommunicationscomponent.html',
  styleUrls: ['listecommunications.component.scss'],
})

export class ListecommunicationsComponent implements OnInit {


  public idAdherent: number = 0;
  public communication: Communication[] = [];

  // Toaster
  private index: number = 0;
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  users: { libelle: string, name: string, title: string, color: string }[] = [
    { libelle: '4', name: 'Assemble générale du 1er janvier 2020', title: 'en cours', color: 'red' },
    { libelle: '3', name: 'mail Fete du la lune du 1er fevrier 2020', title: 'envoyé' , color: 'red'},
    { libelle: '2', name: 'mail Fete du soleil du 1er fevrier 2020', title: 'envoyé' , color: 'blue'},
    { libelle: '1', name: 'mail assemblé générale du 1er dec 2020', title: 'ouvert' , color: 'green' },
  ];

  constructor(
    private communicationService: CommunicationService,
    private router: Router,
    private loggerService: LoggerService,
  ) {}

  ngOnInit(): void {

    this.idAdherent = Number(localStorage.getItem('id_adh_selected'));

    this.communicationService.getListeCommunicationAdhrent({idadh: this.idAdherent}).subscribe(
      (data) => {
        this.communication = data;
        this.loggerService.info(JSON.stringify(data));
        this.loggerService.info(JSON.stringify(this.communication));
      },
      (error) => {
        this.loggerService.error(error);
      },
      () => {
        this.loggerService.debug('fini');
      });
  }
}
