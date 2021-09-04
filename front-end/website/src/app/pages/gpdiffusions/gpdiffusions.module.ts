import { NgModule } from '@angular/core';
import {
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbCheckboxModule,
  NbDatepickerModule, NbIconModule,
  NbInputModule, NbListModule,
  NbRadioModule, NbRouteTabsetModule,
  NbSelectModule, NbSpinnerModule, NbToggleModule,
  NbUserModule,
} from '@nebular/theme';

import { ThemeModule } from '../../@theme/theme.module';
import { GpdiffusionsRoutingModule } from './gpdiffusions-routing.module';
import { GpdiffusionsComponent } from './gpdiffusions.component';
import { ListeComponent } from './tabs/liste/liste.component';
import { FormsModule as ngFormsModule } from '@angular/forms';
import { NgToggleModule } from '@nth-cloud/ng-toggle';
import { TabsComponent } from './tabs/tabs.component';
import { CKEditorModule } from 'ng2-ckeditor';
import { EditeurComponent } from './tabs/editeur/editeur.component';

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
    NbRouteTabsetModule,
    CKEditorModule,
    NbSpinnerModule,
    NbListModule,
  ],
  declarations: [
    GpdiffusionsComponent,
    ListeComponent,
    TabsComponent,
    EditeurComponent,
  ],
})
export class GpdiffusionsModule { }
