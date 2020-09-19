import {Component, ViewChild} from '@angular/core';
import {FullCalendarComponent} from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGrigPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import {EventInput} from '@fullcalendar/core';
import frLocale from '@fullcalendar/core/locales/fr';
import { CalendarOptions } from '@fullcalendar/angular'; // useful for typechecking

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./liste.component.scss'],
  templateUrl: './liste.component.html',
})
export class ListeComponent {

  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    dateClick: this.handleDateClick.bind(this), // bind is important!
    locale: frLocale,
    events: [
      { id: 'a', title: 'My Birthday', allDay: true },
      { id: 'b', title: 'Conférence sur le soleil', start: '2020-01-18T21:00:00', end: '2020-01-18T23:00:00' ,
        backgroundColor: 'red'},
      { id: 'c', title: 'Assemblée générale', start: '2020-01-10T21:00:00', end: '2020-01-10T23:30:00' ,
        backgroundColor: 'red'},
      { id: 'd', title: 'Permanence au club', daysOfWeek: [3, 6], startTime: '14:00:00', endTime: '18:00:00',
        startRecur: '2020-01-01', backgroundColor: 'blue'},
    ],
  };

  handleDateClick(arg) {
    alert('date click! ' + arg.dateStr);
  }
}
