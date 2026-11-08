import {Component, HostBinding, OnInit} from '@angular/core';
import {Manifestation} from '../../../api/generated/adherents/models/manifestation';
import {NbToastrService} from '@nebular/theme';
import {ManifestationService} from '../../../api/generated/adherents/services/manifestation.service';
import {DateService, LoggerService} from '../../@core/utils';
import {Adherent} from '../../../api/generated/adherents/models/adherent';
import {ParticipationManifestation} from '../../../api/generated/adherents/models/participation-manifestation';
import {DocumentationService} from '../../../api/generated/utilitaire/services/documentation.service';
import {Document} from '../../../api/generated/utilitaire/models/document.js';
import {Actualitelecture} from '../../../api/generated/utilitaire/models/actualitelecture';
import {Actualiteecriture} from '../../../api/generated/utilitaire/models/actualiteecriture';
import {TokenService} from '../../@core/utils';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {LiensAdherentsService} from '../../../api/generated/adherents/services/liens-adherents.service';
import {AgendaService} from '../../../api/generated/utilitaire/services/agenda.service';
import {ActualiteService} from '../../../api/generated/utilitaire/services/actualite.service';
import {environment} from '../../../environments/environment';
import {forkJoin, Observable, of} from 'rxjs';
import {catchError, finalize, map, switchMap} from 'rxjs/operators';


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
    standalone: false
})
export class DashboardComponent implements OnInit {

  adherent: Adherent;
  idAdh: number = 0;

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  //Gestion des actualite
  actualite: Actualitelecture[] = [];


  // gestion des évènements
  loadingEvenement = true;
  listeEvenementsAdherent: ListeEvenementsAdherent[] = [];


  // Gestion des documents
  documentsDashboard: DocumentDashboard[] = [];
  loadingDocument = true;

  // gestion adhérent représenté
  accesDelegue: boolean = false;


  // droit acces à la synthèse participation
  accesSynthesParticipation: boolean = false;

  //droit de gestion de l'encart sur l'actualité de l'association
  gestionEncartActualit: boolean = false;

  // gestion du graphique de participation aux évènement
  loadingSyntheseEvenement = true;


  //Message a ajouter dans l'encart de l'info de l'association
  messageEncarForm: string;
  submittedMessageEncart: boolean;


  url_documents: string = environment.url_document;

  optionsChart : any  = {
    color: ['blue'],
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
      },
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: 65,
      containLabel: true,
    },
    xAxis: {
      type: 'category',
      axisLabel: {
        interval: 0,
        width: 140,
        overflow: 'break',
        lineHeight: 16,
      },
      data: [],
    },
    yAxis: {
      type: 'value',
    },
    series: [
    ],
  };


  constructor(private manifestationService: ManifestationService,
              private loggerService: LoggerService,
              private toastrService: NbToastrService,
              private dateService: DateService,
              private documentationService: DocumentationService,
              protected router: Router,
              private liensAdherentsService: LiensAdherentsService,
              private tokenService: TokenService,
              private agendaService: AgendaService,
              private actualiteService: ActualiteService) {
  }


  /**
   * Initialisation du tableau de bord
   */
  ngOnInit(): void {

    this.adherent = JSON.parse(localStorage.getItem('adherent'));
    const role = localStorage.getItem('ROLE');
    if (role !== 'ADHERENT') {
      this.accesSynthesParticipation = true;
    }
    if ((role === 'ADMIN') || (role === 'CONSEIL')) {
      this.gestionEncartActualit = true;
    }

    if (this.adherent != null) {
      this.idAdh = this.adherent.id;
    } else {
      this.idAdh = this.tokenService.getIdAdherent();
    }

    this.initActualite();

    this.initEvenements();

    this.initDocuments();

    if (this.accesSynthesParticipation) {
      this.initSyntheseEvenements();
    }

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
   * Récupérer les événements d'un adhérent ou d'une personne représentée.
   */
  private recupererEvenementsAdherent(
    idAdh: number,
    adhRepresente: ListeEvenementsAdherent['adhRepresente'],
  ): Observable<ListeEvenementsAdherent> {
    const dateDebut = new Date();
    const dateFin = new Date();
    dateFin.setMonth(dateFin.getMonth() + 8);

    return this.manifestationService.getListeManifestationsAdherent({
      idadh: idAdh,
      datedebut: this.dateService.convertDateToStringIsoWithOutHour(dateDebut),
      datefin: this.dateService.convertDateToStringIsoWithOutHour(dateFin),
      retourParticipationAdh: true,
    }).pipe(
      map(data => ({manifestations: data ?? [], adhRepresente})),
      catchError(error => {
        this.loggerService.error(error);
        this.toastrService.danger(
          'Erreur technique lors de recuperation des données',
          'Erreur ');
        return of({manifestations: [], adhRepresente});
      }),
    );
  }

  /**
   * Attendre toutes les réponses avant d'afficher les événements.
   */
  private initEvenements() {
    this.loadingEvenement = true;
    this.liensAdherentsService.getLienAdherent({idadh: this.idAdh}).pipe(
      switchMap(data => {
        this.accesDelegue = (data?.length ?? 0) > 0;
        return forkJoin([
          this.recupererEvenementsAdherent(this.idAdh, {
            idAdhRepresentant: -1, idAdhRepresente: -1, nomAdhRepresente: 'xxx',
            prenomAdhRepresente: 'moi', emailAdhRepresente: null,
          }),
          ...(data ?? []).map(lien => this.recupererEvenementsAdherent(lien.idAdhRepresente, lien)),
        ]);
      }),
      finalize(() => this.loadingEvenement = false),
    ).subscribe({
      next: evenements => this.listeEvenementsAdherent = evenements,
      error: error => {
        this.loggerService.error(error);
        this.toastrService.danger(
          'Erreur technique lors de recuperation des données',
          'Erreur ');
      },
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
            if (valueManif.id === idEvenement) {
              if (typeParticipation === 1) {
                valueManif.statutParticipation = 1;
              } else {
                valueManif.statutParticipation = 2;
              }
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


  /**
   * Initialisation de la synthèse des participations aux évènements
   */
  private initSyntheseEvenements() {



    this.loadingSyntheseEvenement = true;

    // Gestion date debut et fin de recherche
    const dDebut = new Date();


    const dFin = new Date();
    dFin.setMonth(dFin.getMonth() + 8);

    const dateDebutString = this.dateService.convertDateToStringIsoWithOutHour(dDebut);
    const dateFinString = this.dateService.convertDateToStringIsoWithOutHour(dFin);


    let seriesParticipe;
    seriesParticipe = {
      data: [],
      color: ['#58d68d'],
      type: 'bar',
      name: 'Participe',
    };
    let seriesParticipePas;
    seriesParticipePas = {
      color: ['#ba4a00'],
      data: [],
      type: 'bar',
      name: 'Participe pas',
    };
    let seriesEnAttente;
    seriesEnAttente = {
      color: ['#f9e79f'],
      data: [],
      type: 'bar',
      name: 'En attente',
    };

    this.agendaService.getSyntheseEvenements({
      datedebut: dateDebutString
      , datefin: dateFinString
      , retourParticipationAdh: true})
      .pipe(finalize(() => this.loadingSyntheseEvenement = false))
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
          const descriptions: string[] = [];
          (data ?? []).forEach(value => {
            const titre = value.description || 'Évènement sans titre';
            const date = this.dateService.dateFormatForPrint(value.datedebut);
            descriptions.push(date ? `${titre}\n${date}` : titre);
            seriesParticipe.data.push(value.nbAdherentParticipant);
            seriesParticipePas.data.push(value.nbAdherentParticipantPas);
            seriesEnAttente.data.push(value.nbAdherentEnAttente);
          });
          // ngx-echarts détecte les changements via la référence des options.
          this.optionsChart = {
            ...this.optionsChart,
            xAxis: {...this.optionsChart.xAxis, data: descriptions},
            // Garder les libellés lisibles et permettre de parcourir tous les événements.
            dataZoom: descriptions.length > 4 ? [{
              type: 'slider',
              xAxisIndex: 0,
              startValue: 0,
              endValue: 3,
              bottom: 10,
              height: 20,
              showDetail: false,
            }] : [],
            series: [seriesParticipe, seriesParticipePas, seriesEnAttente],
          };

          this.loggerService.info(JSON.stringify(this.optionsChart));
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

  /*
  Supprimer un encart
   */
  supprimerEncart(id: number) {
    this.actualiteService.delActualite({idActualite:id})
      .subscribe(
        (data) => {
          this.loggerService.debug(JSON.stringify(data));
          this.actualite  = [];
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de suppression d\'une actualite',
            'Erreur ');
        },
        () => {
        },
      );
  }

  /*
  Ajouter un message dans l'encart
   */
  ajouterEncart(form: NgForm) {
    const actualite: Actualiteecriture= {};
    actualite.libelleActualite = this.messageEncarForm;

    this.actualiteService.addActualite({body:actualite})
      .subscribe(
        (data) => {
          this.loggerService.debug(JSON.stringify(data));
          this.initActualite();
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de ajout d\'une actualite',
            'Erreur ');
        },
        () => {
        },
      );

  }

  /**
   * initialisation actualite
   * @private
   */
  private initActualite() {
    this.actualiteService.getListeActualite()
      .subscribe(
        (data) => {
          this.actualite = data;

        },
        (error) => {
          this.loggerService.info('Erreur sur la réxcupération des actualite');
          this.toastrService.danger(
            'Erreur technique lors de recuperation des données',
            'Erreur ');
        },
        () => {
          this.loggerService.info('fini');
        });
  }
}
