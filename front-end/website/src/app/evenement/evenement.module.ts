import {ThemeModule} from '../@theme/theme.module';
import {
  NbActionsModule, NbAlertModule,
  NbButtonModule,
  NbCardModule, NbCheckboxModule, NbDatepickerModule, NbIconModule,
  NbInputModule,
  NbLayoutModule,
  NbMenuModule, NbRadioModule, NbSelectModule, NbUserModule,
} from '@nebular/theme';
import {FormsModule as ngFormsModule} from '@angular/forms';

import { EvenementComponent } from './evenement.component';
import {NgModule} from '@angular/core';
import { EvenementRoutingModule } from './evenement-routing.module';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import {
  NbAuthModule,
} from '@nebular/auth';


@NgModule({ declarations: [
        EvenementComponent,
    ], imports: [CommonModule,
        FormsModule,
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
        ngFormsModule, EvenementRoutingModule, NbAlertModule], providers: [provideHttpClient(withInterceptorsFromDi())] })
export class EvenementModule {
}
