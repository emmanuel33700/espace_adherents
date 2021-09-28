import {ThemeModule} from '../../@theme/theme.module';
import {
  NbActionsModule, NbAlertModule,
  NbButtonModule,
  NbCardModule, NbCheckboxModule, NbDatepickerModule, NbIconModule,
  NbInputModule,
  NbLayoutModule,
  NbMenuModule, NbRadioModule, NbSelectModule, NbUserModule,
} from '@nebular/theme';
import {FormsModule as ngFormsModule} from '@angular/forms';

import { ResetComponent } from './reset.component';
import {NgModule} from '@angular/core';
import { ResetRoutingModule } from './reset-routing.module';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {
  NbAuthModule,
} from '@nebular/auth';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    NbAuthModule,
    ThemeModule,
    NbMenuModule,
    NbLayoutModule,
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
    ngFormsModule, ResetRoutingModule, NbAlertModule,
  ],
  declarations: [
    ResetComponent,
  ],
})
export class ResetModule {
}
