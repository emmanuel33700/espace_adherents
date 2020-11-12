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

import { RegisterComponent } from './register.component';
import {NgModule} from '@angular/core';
import { RegisterRoutingModule } from './register-routing.module';

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

    NbAuthModule.forRoot({
      forms: {
        register: {
          redirectDelay: 500,
          strategy: 'email',
          showMessages: {
            success: true,
            error: true,
          },
          terms: true,
        },
      },
    }),

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
    ngFormsModule, RegisterRoutingModule, NbAlertModule,
  ],
  declarations: [
    RegisterComponent,
  ],
})
export class RegisterModule {
}
