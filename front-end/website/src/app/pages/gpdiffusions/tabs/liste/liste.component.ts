import {Component, HostBinding, OnInit} from '@angular/core';
import {MailingListe as MailingListeAdherent} from '../../../../../api/generated/adherents/models/mailing-liste';
import {ListeDeDiffusionService as ListeDeDiffusionServiceAdherent} from '../../../../../api/generated/adherents/services/liste-de-diffusion.service';
import {DateService, LoggerService} from '../../../../@core/utils';
import {NbToastrService} from '@nebular/theme';
import {Router} from '@angular/router';
import {Adherent} from '../../../../../api/generated/adherents/models/adherent';
import {NgForm} from '@angular/forms';
import {ListeDeDiffusionService as  ListeDeDiffusionServiceUtilitaire} from '../../../../../api/generated/utilitaire/services/liste-de-diffusion.service';
import {ListeDiffusion as MailingListeUtilitaire} from '../../../../../api/generated/utilitaire/models/liste-diffusion';

@Component({
    selector: 'ngx-form-layouts',
    styleUrls: ['./liste.component.scss'],
    templateUrl: './liste.component.html',
    standalone: false
})
export class ListeComponent implements OnInit {

  // indicateur de chargement
  loading = true;

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster


  adherent: Adherent;
  mailingListes: MailingListeAdherent[] = [];

  // Role de la personne connecté
  role: string;

  droitEdition = false;
  submitted: boolean;


  libelleMailListForm: string;

  constructor(
    private listeDeDiffusionServiceAdherent: ListeDeDiffusionServiceAdherent,
    private listeDeDiffusionServiceUtilitaire: ListeDeDiffusionServiceUtilitaire,
    private dateService: DateService,
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
    private router: Router,
  ) {
  }

  /**
   * Init
   */
  ngOnInit(): void {
    this.adherent = JSON.parse(localStorage.getItem('adherent'));
    this.role = localStorage.getItem('ROLE');

    // tester si la personne à le droit d'éditer la page
    if ((this.role === 'ADMIN') || (this.role === 'CONSEIL') ) {
      this.droitEdition = true;
    }

    this.listeDeDiffusionServiceAdherent.getListesDiffusionAdherent({idadh: this.adherent.id})
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
          this.mailingListes = data;
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
   *
   * @param id
   * @param event
   */
  changerParticipeMailingList(id: number,  event: any) {
    this.loggerService.info('Changer de participation mailing list ' + this.adherent.id + ' id mailing list : ' + id );

    if (event.target.checked) {
      // Ajouter d'un abonnement
      this.listeDeDiffusionServiceAdherent.ajoutListeDiffusionAdherent({idadh: this.adherent.id, idListe: id})
        .subscribe(
          (data) => {
            this.loggerService.debug(JSON.stringify(data));
          },
          (error) => {
            this.loggerService.error(JSON.stringify(error));
            this.toastrService.danger(
              'Erreur technique lors de la màj des données',
              'Erreur ');
          },
          () => {

          },
        );

    } else {
      // Suppression de l'abonnement
      this.listeDeDiffusionServiceAdherent.delListeDiffusionAdherent({idadh: this.adherent.id, idListe: id})
        .subscribe(
          (data) => {
            this.loggerService.debug(JSON.stringify(data));
          },
          (error) => {
            this.loggerService.error(JSON.stringify(error));
            this.toastrService.danger(
              'Erreur technique lors de la màj des données',
              'Erreur ');
          },
          () => {

          },
        );

    }
  }

}



