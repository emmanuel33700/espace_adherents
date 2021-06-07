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

  // indicateur de chargement
  loading = true;


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
        this.loggerService.debug(JSON.stringify(data));
        this.loggerService.debug(JSON.stringify(this.communications));
      },
      (error) => {
        this.loggerService.error(error);
      },
      () => {
        this.loggerService.debug('fini');
        this.loading = false;
      });
  }


  dateFormat(dateArrive: string) {
    const date: Date = new Date(dateArrive);
    return this.datePipe.transform(date, 'dd-MM-yyyy à hh:mm:ss' );
  }
}
