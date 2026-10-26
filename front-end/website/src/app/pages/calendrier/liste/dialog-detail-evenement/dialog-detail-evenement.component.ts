import {Component, HostBinding, Input, OnInit} from '@angular/core';
import {NbDialogRef, NbToastrService} from '@nebular/theme';
import {EventClickArg} from '@fullcalendar/core';
import {ManifestationService} from '../../../../../api/generated/adherents/services/manifestation.service';
import {Adherent} from '../../../../../api/generated/adherents/models/adherent';
import {ParticipationManifestation} from '../../../../../api/generated/adherents/models/participation-manifestation';
import {Manifestation} from '../../../../../api/generated/adherents/models/manifestation';
import {DateService, LoggerService} from '../../../../@core/utils';
import {Evenement} from '../../../../../api/generated/utilitaire/models/evenement';
import {AgendaService} from '../../../../../api/generated/utilitaire/services/agenda.service';
import {NgForm} from '@angular/forms';
import {ListeDiffusion as MailingListeUtilitaire} from "../../../../../api/generated/utilitaire/models/liste-diffusion";
import {
  ListeDeDiffusionService as ListeDeDiffusionServiceUtilitaire
} from "../../../../../api/generated/utilitaire/services/liste-de-diffusion.service";

@Component({
  selector: 'ngx-showcase-dialog',
  templateUrl: 'dialog-detail-evenement.component.html',
  styleUrls: ['dialog-detail-evenement.component.scss'],
})
export class DialogDetailEvenementComponent implements OnInit {

  adherent: Adherent;
  manifestation: Manifestation = {};

  errors: string[] = [];
  messages: string[] = [];
  submitted = false;

  evenementForm: any = {};

  // statut du toogle de confirmation de participation à la manifestation
  statutToogle = false;

  // Role de la personne connecté
  role: string;

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  droitEditionEvenement = false;

  // indicateur de désactivation du selction des groupe
  selectionGroupeActive: boolean = false;

  // indicateur d'affichage de la selection du groupe de diffusion
  afficherSelctionActive : boolean = false;

  // Id de la mailing liste sélectionnée
  selectedMailingListe: number = 0;

  listeDiffusion: MailingListeUtilitaire[];

  // Type participation Evènement 1 => Participe; 2 => Ne participe pas 3=> Ne sais pas
  typeParticipationEvenement: number;

  @Input() title: string;
  @Input() idEvenement: string;
  @Input() clickInfo: EventClickArg;

  constructor(protected ref: NbDialogRef<DialogDetailEvenementComponent>,
              private manifestationService: ManifestationService,
              private agendaService: AgendaService,
              private loggerService: LoggerService,
              private toastrService: NbToastrService,
              private dateService: DateService,
              private listeDeDiffusionServiceUtilitaire: ListeDeDiffusionServiceUtilitaire,) {
  }


  /**
   * Récupération des informations pour init de la page
   */
  ngOnInit(): void {
    this.adherent = JSON.parse(localStorage.getItem('adherent'));
    this.role = localStorage.getItem('ROLE');

    // tester si la personne à le droit d'éditer la page
    if ((this.role === 'ADMIN') || (this.role === 'CONSEIL') ) {
      this.droitEditionEvenement = true;
    }

    this.manifestationService.getManifestationsAdherent({
      idadh: this.adherent.id
      , idManifestation: Number(this.idEvenement),
    })
      .subscribe(
        (data) => {
          this.manifestation = data;
          this.evenementForm.dateDebut = this.dateService.dateFormatForPrint(this.manifestation.dateDebut);
          this.evenementForm.heureDebut = this.dateService.heureFormatForPrint(this.manifestation.dateDebut);
          this.evenementForm.dateFin = this.dateService.dateFormatForPrint(this.manifestation.dateFin);
          this.evenementForm.heureFin = this.dateService.heureFormatForPrint(this.manifestation.dateFin);
          this.evenementForm.titre = this.manifestation.descriptionCourte;
          this.evenementForm.description = this.manifestation.descriptionLongue;
          this.evenementForm.demandeEnvoyerMail = false;
          this.evenementForm.lieux = this.manifestation.lieux;
          this.evenementForm.demandeEnvoyerMail = this.manifestation.envoyerInfoAdherents;
          this.evenementForm.confirmationParticipation = this.manifestation.demanderConfirmationParticipation;
          this.evenementForm.selectedMailingListe = this.manifestation.idGPDiffusion;

          this.typeParticipationEvenement = this.manifestation.statutParticipation;
          if (String(this.manifestation.statutParticipation) === '1') {
            this.statutToogle = true;
          }

          // si selection de la case encvoyé mail, afficher la sélection de la liste de diffusion
          if (this.evenementForm.demandeEnvoyerMail){
            this.listeDeDiffusionServiceUtilitaire.getListes()
              .subscribe(
                (data) => {
                  this.loggerService.info(JSON.stringify(data));
                  this.listeDiffusion = data;
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
            //this.selectionGroupeActive = true;
            this.afficherSelctionActive = true;
          }

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


  dismiss() {
    this.ref.close();
  }


  /**
   * Participer à un évènement
   * @param $event
   */
  participerEvenement($event: Event) {


    const participationManifestation: ParticipationManifestation = {};

    this.loggerService.debug('statut toogle' + this.statutToogle);
    // Définition du type de manifestation
    // SI le statut toogle etat à true ==> La personne souhaite ne plus participer à la manifestation.
    // sinon, il souhaite participer à la manifestation
    if (this.statutToogle) {
      participationManifestation.statutParticipation = 1;
    } else {
      participationManifestation.statutParticipation = 2;

    }



    this.manifestationService.updateManifestationAdherent({
      idadh: this.adherent.id
      , idManifestation: this.manifestation.id
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
   * Supprimer un évènement
   */
  supprimerEvenement() {

    if (confirm('Etes vous sur de supprimer cet évènement :')) {
      this.agendaService.deleteEvenement({idevenement: this.manifestation.id})
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
      this.clickInfo.event.remove();
      this.ref.close();
    }
  }


  /**
   * Modificer un évènement
   * @param form
   */
  modifierEvenement(form: NgForm) {

    const calendarApi = this.clickInfo.view.calendar;
    calendarApi.unselect(); // clear date selection


    const evenement: Evenement = {};
    evenement.id = this.manifestation.id;
    evenement.detail = this.evenementForm.description;
    evenement.description = this.evenementForm.titre;
    evenement.datedebut = this.dateService.convertISODate(this.evenementForm.dateDebut, this.evenementForm.heureDebut);
    evenement.datefin = this.dateService.convertISODate(this.evenementForm.dateFin, this.evenementForm.heureFin);
    evenement.type = 1;
    evenement.envoyerInfoAdherents = this.evenementForm.demandeEnvoyerMail;
    evenement.demanderConfirmationParticipation = this.evenementForm.confirmationParticipation;
    evenement.idGPDiffusion = this.selectedMailingListe;

    this.loggerService.info('gp'+evenement.idGPDiffusion);

    this.agendaService.updateEvenement({idevenement: this.manifestation.id, body: evenement})
      .subscribe(
        (data) => {
          this.loggerService.debug(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de enregistrement',
            'Erreur ');
        },
        () => {
          this.loggerService.debug('Enregistrement fini');
        },
      );

    this.clickInfo.event.remove();
    calendarApi.addEvent({
      id: String(this.manifestation.id),
      title: this.evenementForm.titre,
      start: this.dateService.convertISODate(this.evenementForm.dateDebut, this.evenementForm.heureDebut),
      end: this.dateService.convertISODate(this.evenementForm.dateFin, this.evenementForm.heureFin),
    });

    this.ref.close();
  }
  /*
  Activer la sélection d'un groupe de diffusion
   */
  activerSelectionGroupe() {

    if (this.selectionGroupeActive) {
      this.selectionGroupeActive = false;
    } else {
      this.listeDeDiffusionServiceUtilitaire.getListes()
        .subscribe(
          (data) => {
            this.loggerService.info(JSON.stringify(data));
            this.listeDiffusion = data;
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
      this.selectionGroupeActive = true;
    }

  }

  /**
   * Afichier la selction d'un groupe
   */
  afficherSelectionGroupe() {
    if (this.afficherSelctionActive) {
      this.afficherSelctionActive = false;
    } else {
      this.afficherSelctionActive = true;
    }
  }}
