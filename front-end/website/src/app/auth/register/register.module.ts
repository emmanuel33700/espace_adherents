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
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import {
  NbAuthModule,
} from '@nebular/auth';


@NgModule({ declarations: [
        RegisterComponent,
    ], imports: [CommonModule,
        FormsModule,
        NbAuthModule.forRoot({
            forms: {
                register: {
                    redirectDelay: 500,
                    strategy: 'email',
                    showMessages: {
                        success: true,
                        error: true,
                    },
                    terms: false,
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
        ngFormsModule, RegisterRoutingModule, NbAlertModule], providers: [provideHttpClient(withInterceptorsFromDi())] })
export class RegisterModule {
}
