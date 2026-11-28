import { NgModule } from '@angular/core';
import {
    NbActionsModule, NbAlertModule,
    NbButtonModule,
    NbCardModule,
    NbCheckboxModule,
    NbDatepickerModule, NbIconModule,
    NbInputModule, NbListModule, NbPopoverModule,
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
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import {ImageCropperComponent} from 'ngx-image-cropper';
import {NgxMaskDirective} from 'ngx-mask';
import {DialogPhotoProfilComponent} from './modifier/dialog-photo-profil/dialog-photo-profil.component';

@NgModule({ declarations: [
        ProfileComponent,
        ModifierComponent,
        DialogPhotoProfilComponent,
    ], imports: [ThemeModule,
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
        ReactiveFormsModule,
        NbAlertModule,
        NbSpinnerModule,
        ImageCropperComponent,
        NgxMaskDirective,
        NbPopoverModule,
        NbListModule], providers: [provideHttpClient(withInterceptorsFromDi())] })
export class ProfileModule { }
