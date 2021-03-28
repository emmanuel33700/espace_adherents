import {Component, HostBinding, OnInit} from '@angular/core';
import {Manifestation} from '../../../api/generated/adherents/models/manifestation';
import {NbToastrService} from '@nebular/theme';
import {ManifestationService} from '../../../api/generated/adherents/services/manifestation.service';
import {DateService, LoggerService} from '../../@core/utils';
import {Adherent} from '../../../api/generated/adherents/models/adherent';
import {ParticipationManifestation} from '../../../api/generated/adherents/models/participation-manifestation';


@Component({
  selector: 'ngx-dashboard',
  styleUrls: ['./dashboard.component.scss'],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit {

  adherent: Adherent;

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster


  // gestion des évènements
  loadingEvenement = true;
  manifestationsComplet: Manifestation[] = [];
  manifestationsSaisieParticipation: Manifestation[] = [];
  manifestationsNonSaisieParticipation: Manifestation[] = [];

  // Gestion des documents
  private documents = [
    { titre: 'Conférence sur le soleil', libelle: 'conférence du 18/02', date: '2021-02-01' },
    { titre: 'Conférence sur la lune', libelle: 'conférence du 18/02' , date: '2021-02-01' },
    { titre: 'Conférence sur la terre', libelle: 'conférence du 18/02', date: '2021-02-01' },
    { titre: 'AG', libelle: 'conférence du 18/02' , date: '2021-02-01'},
  ];
  loadingDocument = true;

  constructor( private manifestationService: ManifestationService,
              private loggerService: LoggerService,
              private toastrService: NbToastrService,
              private dateService: DateService) {
  }


  /**
   * Initialisation du tableau de bord
   */
  ngOnInit(): void {


    this.adherent = JSON.parse (localStorage.getItem('adherent'));

    this.initEvenements();

    this.initDocuments();

  }

  /**
   * Changement statut de participation à un évenement
   * @param idEvenement
   */
  participeEvenement(premierSaisie: boolean, typeParticipation: number, idEvenement: number) {
    const participationManifestation: ParticipationManifestation = {};
    if (typeParticipation === 1 ) {
      participationManifestation.statutParticipation = 1;
    } else {
      participationManifestation.statutParticipation = 2;
    }

    if (premierSaisie) {
      this.loadingEvenement = true;
    }
    this.manifestationService.updateManifestationAdherent({idadh: this.adherent.id
      , idManifestation: idEvenement
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
          if (premierSaisie) {
            this.initEvenements();
          }
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

  participerEvenement(event: any, idManifestation: number) {
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
      idadh: this.adherent.id
      , idManifestation: idManifestation
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
   * Initialisation des évènements
   */
  private initEvenements() {
    // Gestion date debut et fin de recherche
    const dDebut = new Date();


    const dFin = new Date();
    dFin.setMonth(dFin.getMonth() + 8);

    const dateDebutString = this.dateService.convertDateToStringIsoWithOutHour(dDebut);
    const dateFinString = this.dateService.convertDateToStringIsoWithOutHour(dFin);


    this.manifestationService.getListeManifestationsAdherent({idadh: this.adherent.id
      , datedebut: dateDebutString
      , datefin: dateFinString})
      .subscribe(
        (data) => {
          this.manifestationsComplet = data;
          this.loggerService.debug(JSON.stringify(data));

          this.manifestationsComplet.forEach((value, index) => {

            if (String(value.statutParticipation) === '3') {
              this.manifestationsNonSaisieParticipation.push(value);
            } else {
              this.manifestationsSaisieParticipation.push(value);
            }
          });

        },
        (error) => {
          this.loggerService.error(error);
          this.toastrService.danger(
            'Erreur technique lors de recuperation des données',
            'Erreur ');
        },
        () => {
          this.loggerService.debug('fini');
          this.loadingEvenement = false;
        });

  }

  /**
   * Initialisation des documents à afficher dans le TDB
   */
  private initDocuments() {
    this.loadingDocument = false;
  }
}
