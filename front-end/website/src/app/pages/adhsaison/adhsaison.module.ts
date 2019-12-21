import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  NbAccordionModule, NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbListModule,
  NbRouteTabsetModule,
  NbStepperModule,
  NbTabsetModule, NbUserModule,
} from '@nebular/theme';

import { ThemeModule } from '../../@theme/theme.module';
import { AdhsaisonRoutingModule } from './adhsaison-routing.module';
import { AdhsaisonComponent } from './adhsaison.component';
import { ListeComponent } from './liste/liste.component';
import { AdhsaisonService } from './adhsaison.service';
import { Tab1Component, Tab2Component, TabsComponent } from './tabs/tabs.component';
import {NbSecurityModule} from "@nebular/security";

@NgModule({
  imports: [
    FormsModule,
    ReactiveFormsModule,
    ThemeModule,
    NbTabsetModule,
    NbRouteTabsetModule,
    NbStepperModule,
    NbCardModule,
    NbButtonModule,
    NbListModule,
    NbAccordionModule,
    NbUserModule,
    AdhsaisonRoutingModule,
    NbActionsModule,
    NbSecurityModule,
  ],
  declarations: [
    AdhsaisonComponent,
    ListeComponent,
    TabsComponent,
    Tab1Component,
    Tab2Component,
  ],
  providers: [
    AdhsaisonService,
  ],
})
export class AdhsaisonModule { }
