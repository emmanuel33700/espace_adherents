import {Component, OnInit, ViewChild} from '@angular/core';
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

// https://github.com/fullcalendar/fullcalendar-example-projects/tree/master/angular/src/app
  constructor(
    private agendaService: AgendaService,
    private manifestationService: ManifestationService,
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
    private dialogService: NbDialogService,
  ) {
  }


  handleDateClick(arg) {
    alert('date click! ' + arg.dateStr);
  }


  ngOnInit(): void {

    this.adherent = JSON.parse (localStorage.getItem('adherent'));
    this.role = localStorage.getItem('ROLE');


    this.calendarVisible = false;

    this.manifestationService.getListeManifestationsAdherent({idadh: this.adherent.id})
      .subscribe(
        (data) => {
          this.manifestations = data;
          this.loggerService.info(JSON.stringify(data));


          this.manifestations.forEach((value, index) => {


            const eventprivate: EventInput = {};
            eventprivate.id = String(value.id);
            eventprivate.title = value.descriptionCourte;
            eventprivate.start = value.dateDebut;
            eventprivate.end = value.dateFin;

            if (value.statutParticipation === 2 ) {
              // Si l'utilisateur ne participe pas
              eventprivate.backgroundColor = '#b4c5cd';
            } else if (value.statutParticipation === 1 ) {
              // si il participe
              eventprivate.backgroundColor = '#58df89';
            } else {
                // si aucune réponse
              eventprivate.backgroundColor = '#8ecae2';

            }


            this.initialEvent.push(eventprivate);

          });

          this.calendarVisible = true;

          if (this.role.includes('ADMIN') || this.role.includes('BUREAU')) {
            this.calendarOptions = {
              headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek',
              },
              initialView: 'timeGridWeek',
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
          } else {
            this.calendarOptions = {
              headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek',
              },
              initialView: 'timeGridWeek',
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
      },
    });

  }

  handleEvents(events: EventApi[]) {
    this.loggerService.info('handleEvents');
    this.currentEvents = events;
  }

  updateEvent(clickInfo: EventClickArg) {
    this.loggerService.info('Evenement modifié ' + clickInfo.event.id);
    this.loggerService.info(' --> Date de début' + clickInfo.event.startStr);
    this.loggerService.info(' --> Date de fin ' + clickInfo.event.endStr);
  }



}
