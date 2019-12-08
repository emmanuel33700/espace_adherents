import { NgModule } from '@angular/core';
import {
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbDatepickerModule, NbIconModule,
  NbInputModule,
  NbRadioModule,
  NbSelectModule, NbToggleModule,
  NbUserModule,
} from '@nebular/theme';

import { ThemeModule } from '../../@theme/theme.module';
import { GpdiffusionsRoutingModule } from './gpdiffusions-routing.module';
import { GpdiffusionsComponent } from './gpdiffusions.component';
import { ListeComponent } from './liste/liste.component';
import { FormsModule as ngFormsModule } from '@angular/forms';
import { NgToggleModule } from '@nth-cloud/ng-toggle';

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
    GpdiffusionsRoutingModule,
    NbSelectModule,
    NbIconModule,
    ngFormsModule,
    NgToggleModule,
    NbToggleModule,
  ],
  declarations: [
    GpdiffusionsComponent,
    ListeComponent,
  ],
})
export class GpdiffusionsModule { }
