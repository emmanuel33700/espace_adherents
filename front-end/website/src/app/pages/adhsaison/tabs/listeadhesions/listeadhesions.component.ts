import {Component, HostBinding, OnInit} from '@angular/core';
import {AdhesionService} from '../../../../../api/generated/adherents/services/adhesion.service';
import {Adhesion} from '../../../../../api/generated/adherents/models/adhesion';
import {Router} from '@angular/router';
import {LoggerService} from '../../../../@core/utils';
import {DatePipe} from '@angular/common';
import {NbToastrService} from '@nebular/theme';



@Component({
  selector: 'ngx-list',
  templateUrl: 'listeadhesions.component.html',
  styleUrls: ['listeadhesions.component.scss'],
})



export class ListeadhesionsComponent  implements OnInit {

  public idAdherent: number = 0;
  public adhesions: Adhesion[] = [];

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  constructor(
    private adhesionService: AdhesionService,
    private router: Router,
    private loggerService: LoggerService,
    private datePipe: DatePipe,
    private toastrService: NbToastrService,
  ) {}


  ngOnInit(): void {

    this.idAdherent = Number(localStorage.getItem('id_adh_selected'));

    this.adhesionService.getListeAdhesionsAdherent({idadh: this.idAdherent}).subscribe(
      (data) => {
        this.adhesions = data;
        this.loggerService.debug(JSON.stringify(data));
        this.loggerService.debug(JSON.stringify(this.adhesions));
      },
      (error) => {
        this.loggerService.error(error);
        this.toastrService.danger(
          'Erreur technique lors de le recupération des informations',
          'Erreur ');
      },
      () => {
        this.loggerService.debug('fini');
      });
  }

  /**
   1 : ADULTE
   2 : FAMILLE
   3 : RESPONSABLE DE FAMILLE
   4 : ENFANT
   5 : BIENFAITEUR
   6 : HONNEUR
   7 : ETUDIANT
   8 : DEMANDEUR EMPLOI
   **/
  getTypeAdhesion(idTypeAdhesion: 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8) {

    if (idTypeAdhesion === 1) {
      return 'ADULTE';
    } else if (idTypeAdhesion === 2) {
      return 'FAMILLE';
    } else if (idTypeAdhesion === 3) {
      return ' RESPONSABLE DE FAMILLE';
    } else if (idTypeAdhesion === 4) {
      return 'ENFANT';
    } else if (idTypeAdhesion === 5) {
      return 'BIENFAITEUR';
    } else if (idTypeAdhesion === 6) {
      return 'HONNEUR';
    } else if (idTypeAdhesion === 7) {
      return 'ETUDIANT';
    } else {
      return 'DEMANDEUR EMPLOI';
    }
  }
}
