import {Component, OnInit, ViewChild} from '@angular/core';
import frLocale from '@fullcalendar/core/locales/fr';
import {CalendarOptions, DateSelectArg, EventClickArg, EventApi} from '@fullcalendar/angular';
import {AgendaService} from '../../../../api/generated/utilitaire/services/agenda.service';
import {Evenement} from '../../../../api/generated/utilitaire/models/evenement';
import {LoggerService} from '../../../@core/utils/logger.service';
import {NbToastrService} from '@nebular/theme';
import {EventInput} from '@fullcalendar/angular';

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./liste.component.scss'],
  templateUrl: './liste.component.html',
})
export class ListeComponent implements OnInit {

  calendarVisible = false;
  evenements: Evenement[] = [];
  initialEvent: EventInput[] = [];
  currentEvents: EventApi[] = [];

  calendarOptions: CalendarOptions = {};

// https://github.com/fullcalendar/fullcalendar-example-projects/tree/master/angular/src/app
  constructor(
    private agendaService: AgendaService,
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
  ) {
  }


  handleDateClick(arg) {
    alert('date click! ' + arg.dateStr);
  }


  ngOnInit(): void {
    this.calendarVisible = false;
    this.agendaService.getListeEvenements({})
      .subscribe(
        (data) => {
          this.evenements = data;
          this.loggerService.info(JSON.stringify(data));


          this.evenements.forEach((value, index) => {


            const eventprivate: EventInput = {};
            eventprivate.id = String(value.id);
            eventprivate.title = value.description;
            eventprivate.start = value.datedebut;
            eventprivate.end = value.datefin;

            this.initialEvent.push(eventprivate);

          });

          this.calendarVisible = true;
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


  handleDateSelect(selectInfo: DateSelectArg) {
    this.loggerService.info('handleDateSelect' + selectInfo.startStr);
    const title = prompt('Entrer le nom de l\'evenement');
    const calendarApi = selectInfo.view.calendar;

    calendarApi.unselect(); // clear date selection

    if (title) {
      const evenement: Evenement = {};
      evenement.id = Date.now();
      evenement.description = title;
      evenement.datedebut = selectInfo.startStr;
      evenement.datefin = selectInfo.endStr;
      evenement.detail = 'TODO A compléter';
      evenement.type = 1;

      this.agendaService.addEvenement({body: evenement})
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

      calendarApi.addEvent({
        id: '10',
        title,
        start: selectInfo.startStr,
        end: selectInfo.endStr,
        allDay: selectInfo.allDay,
      });
    }
  }

  handleEventClick(clickInfo: EventClickArg) {
    this.loggerService.info('handleEventClick');
    if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
      clickInfo.event.remove();
    }
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
