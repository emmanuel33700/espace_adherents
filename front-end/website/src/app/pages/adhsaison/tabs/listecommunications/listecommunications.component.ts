import {Component, HostBinding, OnInit} from '@angular/core';
import {CommunicationService} from '../../../../../api/generated/adherents/services/communication.service';
import {Communication} from '../../../../../api/generated/adherents/models/communication';
import {Router} from '@angular/router';
import {LoggerService} from '../../../../@core/utils';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'ngx-list',
  templateUrl: 'listecommunicationscomponent.html',
  styleUrls: ['listecommunications.component.scss'],
})

export class ListecommunicationsComponent implements OnInit {


  public idAdherent: number = 0;
  public communications: Communication[] = [];

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster


  constructor(
    private communicationService: CommunicationService,
    private router: Router,
    private loggerService: LoggerService,
    private datePipe: DatePipe,
  ) {}

  ngOnInit(): void {

    this.idAdherent = Number(localStorage.getItem('id_adh_selected'));

    this.communicationService.getListeCommunicationAdhrent({idadh: this.idAdherent}).subscribe(
      (data) => {
        this.communications = data;
        this.loggerService.info(JSON.stringify(data));
        this.loggerService.info(JSON.stringify(this.communications));
      },
      (error) => {
        this.loggerService.error(error);
      },
      () => {
        this.loggerService.debug('fini');
      });
  }

  getColor(statut: 'unknown' | 'queued' | 'sent' | 'opened' | 'clicked' | 'bounce' | 'spam' | 'unsub' | 'blocked' | 'hardbounced' | 'softbounced' | 'deferred') {

    if ((statut === 'unknown') || (statut === 'queued') || (statut === 'sent')) {
      return 'blue';
    } else if ((statut === 'opened') || (statut === 'clicked')) {
      return 'green';
    } else {
      return 'red';
    }
  }

  dateFormat(dateArrive: string) {
    const date: Date = new Date(dateArrive);
    return this.datePipe.transform(date, 'dd-MM-yyyy à hh:mm:ss' );
  }
}
