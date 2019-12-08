import { NgModule } from '@angular/core';
import {
  NbActionsModule,
  NbAlertModule,
  NbButtonModule,
  NbCalendarKitModule,
  NbCalendarModule,
  NbCalendarRangeModule,
  NbCardModule,
  NbChatModule,
  NbIconModule,
  NbProgressBarModule,
  NbSelectModule,
  NbSpinnerModule,
  NbTabsetModule,
} from '@nebular/theme';

import { ThemeModule } from '../../@theme/theme.module';
import { AgendasRoutingModule } from './agendas-routing.module';

// components
import { AgendasComponent } from './agendas.component';


import { ListeComponent } from './liste/liste.component';
import { DayCellComponent } from './liste/day-cell/day-cell.component';


const COMPONENTS = [
  AgendasComponent,
  ListeComponent,
  DayCellComponent,
];

const MODULES = [
  NbAlertModule,
  NbActionsModule,
  NbButtonModule,
  NbCalendarModule,
  NbCalendarKitModule,
  NbCalendarRangeModule,
  NbCardModule,
  NbChatModule,
  NbIconModule,
  NbProgressBarModule,
  NbSelectModule,
  NbSpinnerModule,
  NbTabsetModule,
  ThemeModule,
  AgendasRoutingModule,
];

@NgModule({
  imports: [
    ...MODULES,
  ],
  declarations: [
    ...COMPONENTS,
  ],
})
export class AgendasModule { }
