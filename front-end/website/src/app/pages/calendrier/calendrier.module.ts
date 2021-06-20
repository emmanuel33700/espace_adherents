import { NgModule } from '@angular/core';
import {
  NbActionsModule, NbAlertModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbDatepickerModule, NbIconModule,
  NbInputModule,
  NbRadioModule,
  NbSelectModule,
  NbUserModule,
  NbSpinnerModule, NbToggleModule,
} from '@nebular/theme';

import { ThemeModule } from '../../@theme/theme.module';
import { CalendrierRoutingModule } from './calendrier-routing.module';
import { CalendrierComponent } from './calendrier.component';
import { ListeComponent } from './liste/liste.component';
import { SyntheseparticipationComponent } from './syntheseparticipation/syntheseparticipation.component';
import { FormsModule as ngFormsModule } from '@angular/forms';
import { FullCalendarModule } from '@fullcalendar/angular'; // the main connector. must go first
import dayGridPlugin from '@fullcalendar/daygrid'; // a plugin
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction'; // a plugin
import listPlugin from '@fullcalendar/list';
import {DialogDetailEvenementComponent} from './liste/dialog-detail-evenement/dialog-detail-evenement.component';
import {DialogAjoutEvenementComponent} from './liste/dialog-ajout-evenement/dialog-ajout-evenement.component';

FullCalendarModule.registerPlugins([ // register FullCalendar plugins
  dayGridPlugin,
  timeGridPlugin,
  listPlugin,
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
    FullCalendarModule,
    NbAlertModule,
    NbSpinnerModule,
    NbToggleModule,
    // register FullCalendar with you app
  ],
  declarations: [
    CalendrierComponent,
    ListeComponent,
    DialogDetailEvenementComponent,
    DialogAjoutEvenementComponent,
    SyntheseparticipationComponent,
  ],
})
export class CalendrierModule { }
