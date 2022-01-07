import {Component, HostBinding, OnInit} from '@angular/core';
import {ListeDeDiffusionService as ListeDeDiffusionServiceAdherent} from '../../../../../api/generated/adherents/services/liste-de-diffusion.service';
import {CsvdataService, DateService, LoggerService} from '../../../../@core/utils';
import {NbToastrService} from '@nebular/theme';
import {Router} from '@angular/router';
import {ListeDeDiffusionService as  ListeDeDiffusionServiceUtilitaire} from '../../../../../api/generated/utilitaire/services/liste-de-diffusion.service';
import {ListInscritsMailingListe} from '../../../../../api/generated/utilitaire/models/list-inscrits-mailing-liste';
import {environment} from '../../../../../environments/environment';
import {ListeDiffusion } from '../../../../../api/generated/utilitaire/models/liste-diffusion';

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./inscritgpdiffusions.component.scss'],
  templateUrl: './inscritgpdiffusions.component.html',
})
export class InscritgpdiffusionsComponent implements OnInit {

  // indicateur de chargement
  loading = true;

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster


  listInscritsMailingListe: ListInscritsMailingListe = {};

  // Role de la personne connecté
  role: string;

  droitEdition = false;
  submitted: boolean;

  listeDiffusion: ListeDiffusion;

  url_photo_profil: string = environment.url_photo_profil;


  constructor(
    private listeDeDiffusionServiceAdherent: ListeDeDiffusionServiceAdherent,
    private listeDeDiffusionServiceUtilitaire: ListeDeDiffusionServiceUtilitaire,
    private dateService: DateService,
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
    private router: Router,
    private csvdataService: CsvdataService,
  ) {
  }

  /**
   * Init
   */
  ngOnInit(): void {
    this.listeDiffusion =  JSON.parse(localStorage.getItem('mailinglist_selected'));


    this.role = localStorage.getItem('ROLE');

    // tester si la personne à le droit d'éditer la page
    if ((this.role === 'ADMIN') || (this.role === 'CONSEIL') ) {
      this.droitEdition = true;
    }

    this.listeDeDiffusionServiceUtilitaire.getAdherentsInscritListe({idListe: this.listeDiffusion.id})
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
          this.listInscritsMailingListe = data;
          this.listInscritsMailingListe.libelle = this.listeDiffusion.libelle;
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
  changerParticipeMailingList(idAdherent: number,  event: any) {
    this.loggerService.info('Changer de participation mailing list ' + idAdherent + ' id mailing list : '
      + this.listeDiffusion.id);

    if (event.target.checked) {
      // Ajouter d'un abonnement
      this.listeDeDiffusionServiceAdherent.ajoutListeDiffusionAdherent({idadh: idAdherent, idListe: this.listeDiffusion.id})
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
      this.listeDeDiffusionServiceAdherent.delListeDiffusionAdherent({idadh: idAdherent, idListe: this.listeDiffusion.id})
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

  /**
   * Récupérer le fichier CSV
   */
  recupererCSV() {
    const nomFichier = 'export_' + this.listInscritsMailingListe.id + '.csv';
    this.csvdataService.exportToCsv(nomFichier, this.listInscritsMailingListe.lstAdherents);
  }
}



