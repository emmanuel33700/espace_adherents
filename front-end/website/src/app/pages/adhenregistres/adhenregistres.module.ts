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
import { AdhenregistresRoutingModule } from './adhenregistres-routing.module';
import { AdhenregistresComponent } from './adhenregistres.component';
import { ListeComponent } from './liste/liste.component';
import { AdhenregistresService } from './adhenregistres.service';

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
    AdhenregistresRoutingModule,
  ],
  declarations: [
    AdhenregistresComponent,
    ListeComponent,
  ],
  providers: [
    AdhenregistresService,
  ],
})
export class AdhenregistresModule { }
