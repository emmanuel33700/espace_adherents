import { NgModule } from '@angular/core';
import {
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbDatepickerModule, NbIconModule,
  NbInputModule,
  NbRadioModule,
  NbSelectModule,
  NbUserModule,
} from '@nebular/theme';

import { ThemeModule } from '../../@theme/theme.module';
import { CalendrierRoutingModule } from './calendrier-routing.module';
import { CalendrierComponent } from './calendrier.component';
import { ListeComponent } from './liste/liste.component';
import { FormsModule as ngFormsModule } from '@angular/forms';
import { FullCalendarModule } from '@fullcalendar/angular'; // the main connector. must go first
import dayGridPlugin from '@fullcalendar/daygrid'; // a plugin
import interactionPlugin from '@fullcalendar/interaction'; // a plugin

FullCalendarModule.registerPlugins([ // register FullCalendar plugins
  dayGridPlugin,
  interactionPlugin,
]);

@NgModule({
  imports: [
    ThemeModule,
    NbInputModule,
    NbCardModule,
    NbButtonModule,
    NbActionsModule,
    NbUserModule,
    NbCheckboxModule,
    NbRadioModule,
    NbDatepickerModule,
    CalendrierRoutingModule,
    NbSelectModule,
    NbIconModule,
    ngFormsModule,
    FullCalendarModule, // register FullCalendar with you app
  ],
  declarations: [
    CalendrierComponent,
    ListeComponent,
  ],
})
export class CalendrierModule { }
