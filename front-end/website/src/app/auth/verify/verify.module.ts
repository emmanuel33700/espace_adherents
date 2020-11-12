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

import { VerifyComponent } from './verify.component';
import {NgModule} from '@angular/core';
import { VerifyRoutingModule } from './verify-routing.module';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
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
    ngFormsModule, VerifyRoutingModule, NbAlertModule,
  ],
  declarations: [
    VerifyComponent,
  ],
})
export class VerifyModule {
}
