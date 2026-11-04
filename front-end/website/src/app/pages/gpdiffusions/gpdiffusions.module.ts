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
import { TabsComponent } from './tabs/tabs.component';
import { EditeurComponent } from './tabs/editeur/editeur.component';
import { AdminComponent } from './tabs/admin/admin.component';
import { InscritgpdiffusionsComponent} from './tabs/inscritgpdiffusions/inscritgpdiffusions.component';
import {AngularEditorModule} from '@kolkov/angular-editor';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

@NgModule({ declarations: [
        GpdiffusionsComponent,
        ListeComponent,
        TabsComponent,
        EditeurComponent,
        AdminComponent,
        InscritgpdiffusionsComponent,
    ], imports: [ThemeModule,
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
        NbToggleModule,
        NbRouteTabsetModule,
        NbSpinnerModule,
        NbListModule,
        AngularEditorModule], providers: [provideHttpClient(withInterceptorsFromDi())] })
export class GpdiffusionsModule { }
