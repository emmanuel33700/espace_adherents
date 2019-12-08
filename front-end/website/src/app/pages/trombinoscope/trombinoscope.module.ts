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
import { TrombinoscopeRoutingModule } from './trombinoscope-routing.module';
import { TrombinoscopeComponent } from './trombinoscope.component';
import { ListeComponent } from './liste/liste.component';
import { TrombinoscopeService } from './trombinoscope.service';

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
    TrombinoscopeRoutingModule,
  ],
  declarations: [
    TrombinoscopeComponent,
    ListeComponent,
  ],
  providers: [
    TrombinoscopeService,
  ],
})
export class TrombinoscopeModule { }
