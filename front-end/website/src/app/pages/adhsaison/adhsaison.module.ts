import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
    NbAccordionModule, NbActionsModule, NbAlertModule,
    NbButtonModule,
    NbCardModule, NbCheckboxModule, NbDatepickerModule, NbIconModule, NbInputModule,
    NbListModule, NbRadioModule,
    NbRouteTabsetModule, NbSelectModule,
    NbStepperModule,
    NbTabsetModule, NbToggleModule, NbUserModule,
} from '@nebular/theme';

import { ThemeModule } from '../../@theme/theme.module';
import { AdhsaisonRoutingModule } from './adhsaison-routing.module';
import { AdhsaisonComponent } from './adhsaison.component';
import { ListeComponent } from './liste/liste.component';
import { AdhsaisonService } from './adhsaison.service';
import { TabsComponent } from './tabs/tabs.component';
import { InfosadhComponent} from './tabs/infosadh/infosadh.component';
import { ListeadhesionsComponent} from './tabs/listeadhesions/listeadhesions.component';
import { ListeparticipationsComponent} from './tabs/listeparticipations/listeparticipations.component';
import { ListecommunicationsComponent } from './tabs/listecommunications/listecommunications.component';
import { ConnexionComponent } from './tabs/connexion/connexion.component';
import { AjouterComponent } from './ajouter/ajouter.component';
import {NbSecurityModule} from '@nebular/security';
import { FormsModule as ngFormsModule } from '@angular/forms';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
    imports: [
        FormsModule,
        ReactiveFormsModule,
        ThemeModule,
        NbCardModule,
        NbTabsetModule,
        NbRouteTabsetModule,
        NbStepperModule,
        NbButtonModule,
        NbListModule,
        NbAccordionModule,
        NbUserModule,
        AdhsaisonRoutingModule,
        NbActionsModule,
        NbSecurityModule,
        NbCheckboxModule,
        NbRadioModule,
        NbIconModule,
        NbToggleModule,
        ngFormsModule,
        NbDatepickerModule,
        NbSelectModule,
        CommonModule,
        HttpClientModule,
        NbInputModule,
        NbAlertModule,
    ],
  declarations: [
    AdhsaisonComponent,
    ListeComponent,
    TabsComponent,
    InfosadhComponent,
    ListeadhesionsComponent,
    ListeparticipationsComponent,
    ListecommunicationsComponent,
    ConnexionComponent,
    AjouterComponent,
  ],
  providers: [
    AdhsaisonService,
  ],
})
export class AdhsaisonModule { }
