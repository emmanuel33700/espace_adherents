import { NgModule } from '@angular/core';
import {
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbDatepickerModule, NbIconModule,
  NbInputModule, NbListModule,
  NbRadioModule,
  NbSelectModule, NbSpinnerModule, NbTabsetModule, NbToggleModule,
  NbUserModule,
} from '@nebular/theme';


import { ThemeModule } from '../../@theme/theme.module';
import { DashboardComponent } from './dashboard.component';

import {FormsModule as ngFormsModule} from '@angular/forms';
import {NgxEchartsModule} from 'ngx-echarts';




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
    NbSelectModule,
    NbIconModule,
    ngFormsModule,
    NbSpinnerModule,
    NbListModule,
    NbToggleModule,
    NgxEchartsModule,
    NbTabsetModule,
  ],
  declarations: [
    DashboardComponent,
  ],
})
export class DashboardModule { }
