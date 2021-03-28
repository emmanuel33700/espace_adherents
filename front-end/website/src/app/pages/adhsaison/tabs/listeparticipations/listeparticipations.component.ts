import {Component, HostBinding, OnInit} from '@angular/core';
import {ManifestationService} from '../../../../../api/generated/adherents/services/manifestation.service';
import {DateService, LoggerService} from '../../../../@core/utils';
import {NbToastrService} from '@nebular/theme';
import {Manifestation} from '../../../../../api/generated/adherents/models/manifestation';
import {DatePipe} from '@angular/common';



@Component({
  selector: 'ngx-list',
  templateUrl: 'listeparticipations.component.html',
  styleUrls: ['listeparticipations.component.scss'],
})

export class ListeparticipationsComponent implements OnInit  {

  public idAdherent: number = 0;

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  // indicateur de chargement
  loading = true;


  manifestations: Manifestation[] = [];

  users: { libelle: string, name: string, title: string, color: string }[] = [
    { libelle: '4', name: 'Assemble générale', title: 'Participe pas', color: 'red' },
    { libelle: '3', name: 'Fete du la lune', title: 'Participe pas' , color: 'red'},
    { libelle: '2', name: 'Fete du soleil', title: 'Participe' , color: 'blue'},
    { libelle: '1', name: 'Fete des etoiles', title: 'Participe' , color: 'blue' },
  ];

  constructor( private manifestationService: ManifestationService,
               private loggerService: LoggerService,
               private toastrService: NbToastrService,
               private dateService: DateService,
               private datePipe: DatePipe) {
  }

  ngOnInit(): void {

    this.idAdherent = Number(localStorage.getItem('id_adh_selected'));

    // Gestion date debut et fin de recherche
    const dDebut = new Date();
    dDebut.setMonth(dDebut.getMonth() - 1);

    const dFin = new Date();
    dFin.setMonth(dFin.getMonth() + 8);

    const dateDebutString = this.dateService.convertDateToStringIsoWithOutHour(dDebut);
    const dateFinString = this.dateService.convertDateToStringIsoWithOutHour(dFin);


    this.manifestationService.getListeManifestationsAdherent({idadh: this.idAdherent
      , datedebut: dateDebutString
      , datefin: dateFinString})
      .subscribe(
        (data) => {
          this.manifestations = data;
          this.loggerService.debug(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(error);
          this.toastrService.danger(
            'Erreur technique lors de recuperation des données',
            'Erreur ');
        },
        () => {
          this.loggerService.debug('fini');
          this.loading = false;
        });
  }

  dateFormat(dateDebut: string) {
    const date: Date = new Date(dateDebut);
    return this.datePipe.transform(date, 'dd-MM-yyyy à hh:mm:ss' );
  }

  getColor(statutParticipation: 1 | 2 | 3) {
    if (statutParticipation === 1) {
      return 'green';
    } else if (statutParticipation === 2) {
      return 'red';
    } else {
      return 'blue';
    }
  }
}
