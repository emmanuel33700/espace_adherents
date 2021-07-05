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
import {Router} from '@angular/router';
import {LiensAdherentsService} from '../../../api/generated/adherents/services/liens-adherents.service';
import {LiensAdherent} from '../../../api/generated/adherents/models/liens-adherent';


interface DocumentDashboard {
  nom: string;
  auteur: string;
  description: string;
  kind: string;
  id: number;
  lienFichier: string;
}

interface ListeEvenementsAdherent {
  adhRepresente: {
    'idAdhRepresentant'?: number, 'idAdhRepresente'?: number, 'nomAdhRepresente'?: string,
    'prenomAdhRepresente'?: string, 'emailAdhRepresente'?: string,
  };
  manifestations: Manifestation[];
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
  listeEvenementsAdherent: ListeEvenementsAdherent[] = [];


  // Gestion des documents
  documentsDashboard: DocumentDashboard[] = [];
  loadingDocument = true;

  // gestion adhérent représenté
  accesDelegue: boolean = false;


  optionsChart = {
    backgroundColor: echarts.bg,
    color: ['red'],
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
      },
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true,
    },
    xAxis: [
      {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
        axisTick: {
          alignWithLabel: true,
        },
        axisLine: {
          lineStyle: {
            color: echarts.axisLineColor,
          },
        },
        axisLabel: {
          textStyle: {
            color: echarts.textColor,
          },
        },
      },
    ],
    yAxis: [
      {
        type: 'value',
        axisLine: {
          lineStyle: {
            color: echarts.axisLineColor,
          },
        },
        splitLine: {
          lineStyle: {
            color: echarts.splitLineColor,
          },
        },
        axisLabel: {
          textStyle: {
            color: echarts.textColor,
          },
        },
      },
    ],
    series: [
      {
        name: 'Score',
        type: 'bar',
        barWidth: '60%',
        data: [10, 52, 200, 334, 390, 330, 220],
      },
    ],
  };


  constructor(private manifestationService: ManifestationService,
              private loggerService: LoggerService,
              private toastrService: NbToastrService,
              private dateService: DateService,
              private documentationService: DocumentationService,
              protected router: Router,
              private liensAdherentsService: LiensAdherentsService,
              private tokenService: TokenService) {
  }


  /**
   * Initialisation du tableau de bord
   */
  ngOnInit(): void {

    this.adherent = JSON.parse(localStorage.getItem('adherent'));

    if (this.adherent != null) {
      this.idAdh = this.adherent.id;
    } else {
      this.idAdh = this.tokenService.getIdAdherent();
    }

    this.initEvenements();

    this.initDocuments();

  }


  /**
   * CHangement de statut participe à une manifestation
   * @param premierSaisie
   * @param typeParticipation
   * @param idEvenement
   */
  indiquerParticipeEvenement(idAdh: number, typeParticipation: number, idEvenement: number) {
    this.loadingEvenement = true;

    if (idAdh === -1) {
      idAdh = this.idAdh;
    }

    const participationManifestation: ParticipationManifestation = {};
    if (typeParticipation === 1) {
      participationManifestation.statutParticipation = 1;
    } else {
      participationManifestation.statutParticipation = 2;
    }


    this.manifestationService.updateManifestationAdherent({
      idadh: idAdh
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
          this.loadingEvenement = false;
        },
        () => {
          this.loggerService.debug('Enregistrement fini');
          this.basculerEvenementASaisir(idAdh, typeParticipation, idEvenement);
          this.loadingEvenement = false;
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
   * Indiquer un changement de participation à un évèneent
   * @param idAdh
   * @param event
   * @param idManifestation
   */
  changerParticipeEvenement(idAdh: number, event: any, idManifestation: number) {
    const participationManifestation: ParticipationManifestation = {};

    if (idAdh === -1) {
      idAdh = this.idAdh;
    }


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
   * Complete this.listeEvenementsAdherent[] avec les manifestatio des personnes représentées (Attention doit débuter
   * avec l'index 1 du tableau
   * @param liensAdh
   */
  private recupererEvenementsPersonneRepresente(liensAdh: LiensAdherent) {
    this.loggerService.info('Recupere les évènement des adherents représenté ');


    // Gestion date debut et fin de recherche
    const dDebut = new Date();


    const dFin = new Date();
    dFin.setMonth(dFin.getMonth() + 8);

    const dateDebutString = this.dateService.convertDateToStringIsoWithOutHour(dDebut);
    const dateFinString = this.dateService.convertDateToStringIsoWithOutHour(dFin);


    liensAdh.forEach((value, index) => {

      // Recupérer les événement pour une personne représenté
      this.manifestationService.getListeManifestationsAdherent({
        idadh: value.idAdhRepresente
        , datedebut: dateDebutString
        , datefin: dateFinString})
        .subscribe(
          (data) => {
            this.loggerService.debug(JSON.stringify(data));

            this.listeEvenementsAdherent[index + 1] = {manifestations: data, adhRepresente: value};

          },
          (error) => {
            this.loggerService.error(error);
            this.toastrService.danger(
              'Erreur technique lors de recuperation des données',
              'Erreur ');
          },
          () => {
            this.loggerService.debug('fini');

            if (liensAdh.length === index + 1) {
              setTimeout(() => {
                this.loadingEvenement = false;
              }, 1000);
            }

          });
    });


  }

  /**
   * récupérer les évènement d'un adhérent (Attention complete la variable this.listeEvenementsAdherent[0]
   * @param idAdh
   */
  private recupererEvenementsAdherent(idAdh: number) {
    this.loggerService.info('Recupere les évènement de l_\'adherent ' + idAdh);

    // Gestion date debut et fin de recherche
    const dDebut = new Date();


    const dFin = new Date();
    dFin.setMonth(dFin.getMonth() + 8);

    const dateDebutString = this.dateService.convertDateToStringIsoWithOutHour(dDebut);
    const dateFinString = this.dateService.convertDateToStringIsoWithOutHour(dFin);


    this.manifestationService.getListeManifestationsAdherent({
      idadh: idAdh
      , datedebut: dateDebutString
      , datefin: dateFinString})
      .subscribe(
        (data) => {
          // La partie adhRepresente est renseigné par defaut, car il n'y a pas d'adh représenté
          this.listeEvenementsAdherent[0] = {
            manifestations: data, adhRepresente: {
              idAdhRepresentant: -1, idAdhRepresente: -1, nomAdhRepresente: 'xxx',
              prenomAdhRepresente: 'moi', emailAdhRepresente: null,
            },
          };
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
  }


  /**
   * Initialisation des évènements
   */
  private initEvenements() {

    this.liensAdherentsService.getLienAdherent({idadh: this.idAdh})
      .subscribe(
        (data) => {
          this.recupererEvenementsAdherent(this.idAdh);

          /**
           * Si la personne représente d'autre adhérent, récupération de ces évènements
           */
          if (data != null) {
            this.accesDelegue = true;
            this.recupererEvenementsPersonneRepresente(data);
          } else {
            setTimeout(() => {
              this.loadingEvenement = false;
            }, 1000);
          }
        },
        (error) => {
          this.loggerService.info('Pas de lien');
          this.toastrService.danger(
            'Erreur technique lors de recuperation des données',
            'Erreur ');
        },
        () => {
          this.loggerService.info('fini');
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
  private basculerEvenementASaisir(idAdh: number, typeParticipation: number, idEvenement: number) {

    // si modification du une manifestation relative à la personne connectée
    if (idAdh === this.idAdh) {
      this.listeEvenementsAdherent[0].manifestations.forEach((valueManif, indexManif) => {
        if (typeParticipation === 1) {
          valueManif.statutParticipation = 1;
        } else {
          valueManif.statutParticipation = 2;
        }
      });
    } else {
      // si modification d'une manifestation relative à une personne représnetée
      this.listeEvenementsAdherent.forEach((value, index) => {
        if (value.adhRepresente.idAdhRepresente === idAdh) {
          value.manifestations.forEach((valueManif, indexManif) => {
            if (valueManif.id === idEvenement) {
              if (typeParticipation === 1) {
                valueManif.statutParticipation = 1;
              } else {
                valueManif.statutParticipation = 2;
              }
            }
          });
        }
      });
    }


  }


}
