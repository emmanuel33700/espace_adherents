import {Component, HostBinding, OnInit} from '@angular/core';
import {LoggerService} from '../../../@core/utils/logger.service';
import {NbToastrService} from '@nebular/theme';
import {AgendaService} from '../../../../api/generated/utilitaire/services/agenda.service';
import {ParticipantsEvenement} from '../../../../api/generated/utilitaire/models/participants-evenement';
import {DateService} from '../../../@core/utils';
import {ParticipationManifestation} from '../../../../api/generated/adherents/models/participation-manifestation';
import {ManifestationService} from '../../../../api/generated/adherents/services/manifestation.service';
import {SyntheseEvenement} from '../../../../api/generated/utilitaire/models/synthese-evenement';


@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./syntheseevenement.component.scss'],
  templateUrl: './syntheseevenement.component.html',
})
export class SyntheseevenementComponent implements OnInit {


  // indicateur de chargement
  loading = true;

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  participantsEvenement: ParticipantsEvenement[] = [];

  idEvenement: number;
  evenementSelectionne: SyntheseEvenement = {};

  modificationParticipation = false;

  constructor(
    private manifestationService: ManifestationService,
    private agendaService: AgendaService,
    private dateService: DateService,
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
  ) {
  }



  ngOnInit(): void {


    this.idEvenement = Number(localStorage.getItem('id_evenement_selected'));
    this.evenementSelectionne = JSON.parse(localStorage.getItem('evenement_selected'));

    const role = localStorage.getItem('ROLE');
    if (role !== 'RES_ATELIER') {
      this.modificationParticipation = true;
    }

    this.initParticipation();

  }

  /**
   * Initialisation de la participation
   */
  initParticipation() {
    this.loading = true;
    this.agendaService.getSyntheseEvenement({idevenement : this.idEvenement})
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
          this.participantsEvenement = data;
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
   * CHanger de type de participation
   * @param idAdh
   * @param $event
   */
  changerParticipeEvenement(idAdh: any, event: any) {
    this.loggerService.info('Changer de participation idadh' + idAdh + ' id event : ' + this.idEvenement );
    const participationManifestation: ParticipationManifestation = {};



    this.loggerService.debug('statut toogle' + event);
    // Définition du type de manifestation
    // SI le statut toogle etat à true ==> La personne souhaite ne plus participer à la manifestation.
    // sinon, il souhaite participer à la manifestation
    if (event.target.checked) {
      participationManifestation.statutParticipation = 1;
    } else {
      participationManifestation.statutParticipation = 2;
    }


    this.manifestationService.updateManifestationAdherent({
      idadh: idAdh
      , idManifestation: this.idEvenement
      , body: participationManifestation,
    })
      .subscribe(
        (data) => {
          this.loggerService.debug(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de la supression',
            'Erreur ');
        },
        () => {
          this.loggerService.debug('Suppression fini');
        },
      );
  }

  /**
   * Indiquer si l'adhérent participe ou non à l'évènement
   * @param idAdh
   * @param typeParticipation 1=> PArticipe; 2=>Participe pas
   */
  indiquerParticipeEvenement(idAdh: number, typeParticipation: number) {
    this.loggerService.info('Indiquer une participationa un évènement' + idAdh + ' id event : ' + this.idEvenement );


    const participationManifestation: ParticipationManifestation = {};
    if (typeParticipation === 1) {
      participationManifestation.statutParticipation = 1;
    } else {
      participationManifestation.statutParticipation = 2;
    }


    this.manifestationService.updateManifestationAdherent({
      idadh: idAdh
      , idManifestation: this.idEvenement
      , body: participationManifestation})
      .subscribe(
        (data) => {
          this.loggerService.debug(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de la supression',
            'Erreur ');
        },
        () => {
          this.loggerService.debug('Enregistrement fini');
          this.initParticipation();
        },
      );

  }
}
