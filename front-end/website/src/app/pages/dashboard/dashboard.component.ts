import {Component, HostBinding, OnInit} from '@angular/core';
import {Manifestation} from '../../../api/generated/adherents/models/manifestation';
import {NbToastrService} from '@nebular/theme';
import {ManifestationService} from '../../../api/generated/adherents/services/manifestation.service';
import {DateService, LoggerService} from '../../@core/utils';
import {Adherent} from '../../../api/generated/adherents/models/adherent';
import {ParticipationManifestation} from '../../../api/generated/adherents/models/participation-manifestation';
import {DocumentationService} from '../../../api/generated/utilitaire/services/documentation.service';
import {Document} from '../../../api/generated/utilitaire/models/document.js';
import {TokenService} from '../../@core/utils/token.service';


interface DocumentDashboard {
  nom: string;
  auteur: string;
  description: string;
  kind: string;
  id: number;
  lienFichier: string;
}

@Component({
  selector: 'ngx-dashboard',
  styleUrls: ['./dashboard.component.scss'],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit {

  adherent: Adherent;
  idAdh: number = 0;

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
  documentsDashboard: DocumentDashboard[] = [];
  loadingDocument = true;

  constructor( private manifestationService: ManifestationService,
              private loggerService: LoggerService,
              private toastrService: NbToastrService,
              private dateService: DateService,
              private documentationService: DocumentationService,
              private tokenService: TokenService) {
  }


  /**
   * Initialisation du tableau de bord
   */
  ngOnInit(): void {


    this.idAdh = this.tokenService.getIdAdherent();

    this.adherent = JSON.parse (localStorage.getItem('adherent'));

    this.initEvenements();

    this.initDocuments();

  }


  /**
   * CHangement de statut participe à une manifestation
   * @param premierSaisie
   * @param typeParticipation
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
    this.manifestationService.updateManifestationAdherent({idadh: this.idAdh
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
            this.basculerEvenementASaisie(typeParticipation, idEvenement );
            this.loadingEvenement = false;
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
      idadh: this.idAdh
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


    this.manifestationService.getListeManifestationsAdherent({idadh: this.idAdh
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
    // Gestion date debut et fin de recherche
    const dateMin = new Date();
    dateMin.setMonth(dateMin.getMonth() - 2);

    const dateMax = new Date();

    const dateMinString = this.dateService.convertDateToStringIsoWithOutHour(dateMin);
    const dateMaxString = this.dateService.convertDateToStringIsoWithOutHour(dateMax);

    let document: Document[] = [];
    this.documentationService.getDocuments({minDateCreation: dateMinString, maxDateCreation: dateMaxString})
      .subscribe(
        (data) => {
          document = data;

          this.documentsDashboard = this.convertDocuments(document);

        },
        (error) => {
          this.loggerService.error(error);
          this.toastrService.danger(
            'Erreur technique lors de recuperation des données',
            'Erreur ');
        },
        () => {
          this.loggerService.debug('fini');
        });

    this.loadingDocument = false;
  }

  /**
   * Concertir document restitué par l'API pour permettre un affichage IHM
   * @param document
   */
  private convertDocuments(document: Document[]): DocumentDashboard[] {

    let documents: DocumentDashboard[] = [];
    documents = [];

    document.forEach((value, index) => {

      if (value.nomFichier != null) {
        const documentTransforme: DocumentDashboard = {
          nom: value.libelleCourt,
          auteur: 'MANU',
          description: value.libelleLong,
          kind: 'doc',
          id: value.id,
          lienFichier: value.nomFichier,
        };
        documents.push(documentTransforme);
      }

    });

    return documents;
  }

  /**
   * BAsculer un évènement à saisie (suppression d'un évènement dans la liste des
   * manifestations en attente à manifestation renseignée)
   * @param typeParticipation
   * @param idEvenement
   */
  private basculerEvenementASaisie(typeParticipation: number, idEvenement: number) {

    this.manifestationsNonSaisieParticipation.forEach((value, index) => {
      if (value.id === idEvenement) {
        this.loggerService.debug('Bascule la manifestation ' + value.id );
        if (typeParticipation === 1) {
          value.statutParticipation = 1;
        } else {
          value.statutParticipation = 2;
        }

        this.manifestationsSaisieParticipation.push(value);
        this.manifestationsNonSaisieParticipation.splice(index, 1);
      }
    });
  }
}
