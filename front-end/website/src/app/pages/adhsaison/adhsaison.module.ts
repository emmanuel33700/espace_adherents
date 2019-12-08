import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  NbAccordionModule,
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
  ],
  declarations: [
    AdhsaisonComponent,
    ListeComponent,
  ],
  providers: [
    AdhsaisonService,
  ],
})
export class AdhsaisonModule { }
