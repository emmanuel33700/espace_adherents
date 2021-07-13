import {Component, OnInit} from '@angular/core';
import frLocale from '@fullcalendar/core/locales/fr';
import {CalendarOptions, DateSelectArg, EventClickArg, EventApi} from '@fullcalendar/angular';
import {AgendaService} from '../../../../api/generated/utilitaire/services/agenda.service';
import {ManifestationService} from '../../../../api/generated/adherents/services/manifestation.service';
import {Evenement} from '../../../../api/generated/utilitaire/models/evenement';
import {Manifestation} from '../../../../api/generated/adherents/models/manifestation';
import {LoggerService} from '../../../@core/utils/logger.service';
import {NbToastrService, NbDialogService} from '@nebular/theme';
import {EventInput} from '@fullcalendar/angular';
import {Adherent} from '../../../../api/generated/adherents/models/adherent';
import {DialogDetailEvenementComponent} from './dialog-detail-evenement/dialog-detail-evenement.component';
import {DialogAjoutEvenementComponent} from './dialog-ajout-evenement/dialog-ajout-evenement.component';
import {DateService} from '../../../@core/utils';

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./liste.component.scss'],
  templateUrl: './liste.component.html',
})
export class ListeComponent implements OnInit {
  adherent: Adherent;
  role: string;

  calendarVisible = false;
  manifestations: Manifestation[] = [];
  initialEvent: EventInput[] = [];
  currentEvents: EventApi[] = [];

  calendarOptions: CalendarOptions = {};

  // indicateur de chargement
  loading = true;

// https://github.com/fullcalendar/fullcalendar-example-projects/tree/master/angular/src/app
  constructor(
    private agendaService: AgendaService,
    private manifestationService: ManifestationService,
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
    private dialogService: NbDialogService,
    private dateService: DateService,
  ) {
  }


  handleDateClick(arg) {
    alert('date click! ' + arg.dateStr);
  }


  ngOnInit(): void {

    this.adherent = JSON.parse (localStorage.getItem('adherent'));
    this.role = localStorage.getItem('ROLE');


    this.calendarVisible = false;

    // Gestion date debut et fin de recherche
    const dDebut = new Date();
    dDebut.setMonth(dDebut.getMonth() - 2);

    const dFin = new Date();
    dFin.setMonth(dFin.getMonth() + 8);

    const dateDebutString = this.dateService.convertDateToStringIsoWithOutHour(dDebut);
    const dateFinString = this.dateService.convertDateToStringIsoWithOutHour(dFin);

    this.manifestationService.getListeManifestationsAdherent({idadh: this.adherent.id
      , datedebut: dateDebutString
      , datefin: dateFinString
      , retourParticipationAdh: false})
      .subscribe(
        (data) => {
          this.manifestations = data;
          this.loggerService.debug(JSON.stringify(data));


          this.manifestations.forEach((value, index) => {


            const eventprivate: EventInput = {};
            eventprivate.id = String(value.id);
            eventprivate.title = value.descriptionCourte;
            eventprivate.start = value.dateDebut;
            eventprivate.end = value.dateFin;

            if (String(value.statutParticipation) === '2' ) {
              // Si l'utilisateur ne participe pas
              eventprivate.backgroundColor = '#b4c5cd';
            } else if (String (value.statutParticipation) === '3' ) {
              // si aucune réponse
              eventprivate.backgroundColor = '#8ecae2';
            }


            this.initialEvent.push(eventprivate);

          });

          this.calendarVisible = true;

          if ((this.role === 'ADMIN') || (this.role === 'CONSEIL')) {
            this.calendarOptions = {
              headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek',
              },
              initialView: 'timeGridWeek',
              scrollTime: '11:00:00',
              weekends: true,
              editable: true,
              selectable: true,
              selectMirror: true,
              dayMaxEvents: true,
              select: this.handleDateSelect.bind(this),
              eventClick: this.handleEventClick.bind(this),
              eventsSet: this.handleEvents.bind(this),
              eventDrop: this.updateEvent.bind(this),
              eventResize: this.updateEvent.bind(this),
              locale: frLocale,
              events: this.initialEvent,
            };
          } else if (this.role === 'RES_ATELIER') {
            this.calendarOptions = {
              headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek',
              },
              initialView: 'timeGridWeek',
              scrollTime: '11:00:00',
              weekends: true,
              editable: true,
              selectable: true,
              selectMirror: true,
              dayMaxEvents: true,
              select: this.handleDateSelect.bind(this),
              eventClick: this.handleEventClick.bind(this),
              eventsSet: this.handleEvents.bind(this),
              locale: frLocale,
              events: this.initialEvent,
            };
          } else {
            this.calendarOptions = {
              headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek',
              },
              initialView: 'dayGridMonth',
              weekends: true,
              editable: false,
              selectable: false,
              selectMirror: false,
              dayMaxEvents: true,
              eventClick: this.handleEventClick.bind(this),
              locale: frLocale,
              events: this.initialEvent,
            };
          }
          // Le calendrier peut-etre affiché
          this.loading = false;

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
   * Ajouter un évènement
   * @param selectInfo
   */
  handleDateSelect(selectInfo: DateSelectArg) {

    this.dialogService.open(DialogAjoutEvenementComponent, {
      context: {
        dateDebut: selectInfo.startStr,
        dateFin: selectInfo.endStr,
        selectInfo: selectInfo,
      },
    });


  }

  /**
   * Détail d'un évènement
   * @param clickInfo
   */
  handleEventClick(clickInfo: EventClickArg) {
    this.dialogService.open(DialogDetailEvenementComponent, {
      context: {
        title: clickInfo.event.title,
        idEvenement: clickInfo.event.id,
        clickInfo: clickInfo,
      },
    });

  }

  handleEvents(events: EventApi[]) {
    this.loggerService.info('handleEvents');
    this.currentEvents = events;
  }

  updateEvent(clickInfo: EventClickArg) {
    this.loggerService.debug('Evenement modifié ' + clickInfo.event.id);
    this.loggerService.debug(' --> Date de début' + clickInfo.event.startStr);
    this.loggerService.debug(' --> Date de fin ' + clickInfo.event.endStr);

    const evenement: Evenement = {};
    evenement.id = Number(clickInfo.event.id);
    evenement.datedebut = clickInfo.event.startStr;
    evenement.datefin = clickInfo.event.endStr;


    this.agendaService.updateEvenement( {idevenement: Number(clickInfo.event.id), body: evenement})
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de enregistrement',
            'Erreur ');
        },
        () => {
          this.loggerService.info('Enregistrement fini');
        },
      );
  }



}
