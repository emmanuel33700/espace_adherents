import {Component, HostBinding, OnInit} from '@angular/core';
import {LoggerService} from '../../../@core/utils/logger.service';
import {NbToastrService} from '@nebular/theme';
import {AgendaService} from '../../../../api/generated/utilitaire/services/agenda.service';
import {SyntheseEvenement} from '../../../../api/generated/utilitaire/models/synthese-evenement';
import {DateService} from '../../../@core/utils';
import {Router} from '@angular/router';


@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./syntheseparticipation.component.scss'],
  templateUrl: './syntheseparticipation.component.html',
})
export class SyntheseparticipationComponent implements OnInit {


  // indicateur de chargement
  loading = true;

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  syntheseEvenements: SyntheseEvenement[] = [];


  constructor(
    private agendaService: AgendaService,
    private dateService: DateService,
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
    private router: Router,
  ) {
  }



  ngOnInit(): void {

    // Gestion date debut et fin de recherche
    const dDebut = new Date();
    dDebut.setMonth(dDebut.getMonth() - 1);

    const dFin = new Date();
    dFin.setMonth(dFin.getMonth() + 12);

    const dateDebutString = this.dateService.convertDateToStringIsoWithOutHour(dDebut);
    const dateFinString = this.dateService.convertDateToStringIsoWithOutHour(dFin);


    this.agendaService.getSyntheseEvenements({
      datedebut: dateDebutString
      , datefin: dateFinString
      , retourParticipationAdh: true})
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
          this.syntheseEvenements = data;
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de la récupération des données',
            'Erreur ');
        },
        () => {
          this.loading = false;

        },
      );

  }

  /**
   * Afficher dans la page HTML la date via une date ISO
   * @param dateDebut
   */
  afficherDate(dateDebut: string) {
    return this.dateService.dateFormatForPrint(dateDebut);
  }

  /**
   * Afficher dans la page HTML l'heure via une date ISO
   * @param dateDebut
   */
  afficherHeure(dateDebut: string) {
    return this.dateService.heureFormatForPrint(dateDebut);
  }

  /**
   * Naviger vers le détail du manifestation
   * @param id
   */
  accederDetailSynthese(id: number) {
    this.loggerService.info('navigation vers la synthèse de levenement ' + id);

    this.syntheseEvenements.forEach((value, index) => {
      if (value.id === id) {
        localStorage.setItem('evenement_selected', JSON.stringify(value));
      }
    });
    localStorage.setItem('id_evenement_selected', String(id));
    return this.router.navigateByUrl('pages/calendrier/syntheseevenement');

  }
}
