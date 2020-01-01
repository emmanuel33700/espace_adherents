import { Component, ViewChild } from '@angular/core';
import { FullCalendarComponent } from '@fullcalendar/angular';
import { EventInput } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGrigPlugin from '@fullcalendar/timegrid';
import frLocale from '@fullcalendar/core/locales/fr';
import interactionPlugin from '@fullcalendar/interaction'; // for dateClick


@Component({
  selector: 'ngx-dashboard',
  styleUrls: ['./dashboard.component.scss'],
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent {

  // @ts-ignore
  @ViewChild('calendar') calendarComponent: FullCalendarComponent; // the #calendar in the template

  calendarVisible = true;
  calendarPlugins = [dayGridPlugin, timeGrigPlugin, interactionPlugin];
  calendarWeekends = true;
  locales = [frLocale];
  calendarEvents: EventInput[] = [
      { id: 'a', title: 'My Birthday', allDay: true },
      { id: 'b', title: 'Conférence sur le soleil', start: '2020-01-18T21:00:00', end: '2020-01-18T23:00:00' ,
        backgroundColor: 'red'},
      { id: 'c', title: 'Assemblée générale', start: '2020-01-10T21:00:00', end: '2020-01-10T23:30:00' ,
        backgroundColor: 'red'},
      { id: 'd', title: 'Permanence au club', daysOfWeek: [3, 6], startTime: '14:00:00', endTime: '18:00:00',
        startRecur: '2020-01-01', backgroundColor: 'blue'},
  ];



  toggleVisible() {
    this.calendarVisible = !this.calendarVisible;
  }

  toggleWeekends() {
    this.calendarWeekends = !this.calendarWeekends;
  }

  gotoPast() {
    const calendarApi = this.calendarComponent.getApi();
    calendarApi.setOption('locale', 'fr');
  }

  handleDateClick(arg) {
    if (confirm('Would you like to add an event to ' + arg.dateStr + ' ?')) {
      this.calendarEvents = this.calendarEvents.concat({ // add new event data. must create new array
        title: 'New Event',
        start: arg.date,
        allDay: arg.allDay,
    });
    }
  }

}
