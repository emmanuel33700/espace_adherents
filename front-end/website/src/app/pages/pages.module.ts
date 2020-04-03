import {forwardRef, NgModule, Provider} from '@angular/core';
import { NbMenuModule } from '@nebular/theme';

import { ThemeModule } from '../@theme/theme.module';
import { PagesComponent } from './pages.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { PagesRoutingModule } from './pages-routing.module';
import { MiscellaneousModule } from './miscellaneous/miscellaneous.module';
import {ApiModule} from '../../api/generated/api.module';



@NgModule({
  imports: [
    PagesRoutingModule,
    ThemeModule,
    NbMenuModule,
    DashboardModule,
    MiscellaneousModule,
    ApiModule.forRoot({ rootUrl: 'https://api.jalle-astro.fr/resource-adherents' }),
  ],
  declarations: [
    PagesComponent,
  ],
})
export class PagesModule {
}
