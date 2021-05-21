import { NgModule } from '@angular/core';
import {
  NbActionsModule, NbAlertModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbDatepickerModule, NbIconModule,
  NbInputModule, NbPopoverModule,
  NbRadioModule,
  NbSelectModule, NbSpinnerModule,
  NbUserModule,
} from '@nebular/theme';

import { ThemeModule } from '../../@theme/theme.module';
import { ProfileRoutingModule } from './profile-routing.module';
import { ProfileComponent } from './profile.component';
import { ModifierComponent } from './modifier/modifier.component';
import {FormsModule as ngFormsModule, ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import {ImageCropperModule} from 'ngx-image-cropper';
import {DialogPhotoProfilComponent} from './modifier/dialog-photo-profil/dialog-photo-profil.component';

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
        NbAlertModule,
        NbSpinnerModule,
        ImageCropperModule,
        NbPopoverModule,
    ],
  declarations: [
    ProfileComponent,
    ModifierComponent,
    DialogPhotoProfilComponent,
  ],
})
export class ProfileModule { }
