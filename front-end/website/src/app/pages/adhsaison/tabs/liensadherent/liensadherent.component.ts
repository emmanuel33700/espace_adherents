import {Component, HostBinding, OnInit} from '@angular/core';
import {RolesService} from '../../../../../api/generated/authorization/services/roles.service';
import {Adherent} from '../../../../../api/generated/adherents/models/adherent';
import {NbToastrService} from '@nebular/theme';
import {LoggerService} from '../../../../@core/utils/logger.service';

@Component({
  selector: 'ngx-tab2',
  styleUrls: ['./liensadherent.component.scss'],
  templateUrl: './liensadherent.component.html',
})
export class LiensadherentComponent implements OnInit {

  public idAdherent: number = 0;
  adherent: Adherent;

  // Toaster
  private index: number = 0;
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  constructor(
    private rolesService: RolesService,
    private toastrService: NbToastrService,
    private loggerService: LoggerService,
  ) {
  }

  /**
   *
   */
  ngOnInit(): void {

    this.adherent = JSON.parse(localStorage.getItem('adh_selected'));
    this.idAdherent = Number(localStorage.getItem('id_adh_selected'));

    this.loggerService.info('id adh recupere ' + this.idAdherent);

  }

}
