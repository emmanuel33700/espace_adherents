import {Component, OnInit} from '@angular/core';
import {LoggerService} from '../../../@core/utils/logger.service';
import {NbToastrService} from '@nebular/theme';
import {AgendaService} from '../../../../api/generated/utilitaire/services/agenda.service';
import {DateService} from '../../../@core/utils';


@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./syntheseparticipation.component.scss'],
  templateUrl: './syntheseparticipation.component.html',
})
export class SyntheseparticipationComponent implements OnInit {


  // indicateur de chargement
  loading = true;

  constructor(
    private agendaService: AgendaService,
    private dateService: DateService,
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
  ) {
  }



  ngOnInit(): void {


    // Gestion date debut et fin de recherche
    const dDebut = new Date();
    dDebut.setMonth(dDebut.getMonth() - 2);

    const dFin = new Date();
    dFin.setMonth(dFin.getMonth() + 8);

    const dateDebutString = this.dateService.convertDateToStringIsoWithOutHour(dDebut);
    const dateFinString = this.dateService.convertDateToStringIsoWithOutHour(dFin);


    this.agendaService.getSyntheseEvenements({
      datedebut: dateDebutString
      , datefin: dateFinString
      , retourParticipationAdh: true})
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de la récupération des données',
            'Erreur ');
        },
        () => {

        },
      );

  }




}
