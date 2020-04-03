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
import { ProfileRoutingModule } from './profile-routing.module';
import { ProfileComponent } from './profile.component';
import { ModifierComponent } from './modifier/modifier.component';
import {FormsModule as ngFormsModule, ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

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
    ProfileRoutingModule,
    NbSelectModule,
    NbIconModule,
    ngFormsModule,
    CommonModule,
    ngFormsModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  declarations: [
    ProfileComponent,
    ModifierComponent,
  ],
})
export class ProfileModule { }
