import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {GpdiffusionsComponent} from './gpdiffusions.component';

import {TabsComponent} from './tabs/tabs.component';
import {ListeComponent} from './tabs/liste/liste.component';
import {EditeurComponent} from './tabs/editeur/editeur.component';
import { AdminComponent} from './tabs/admin/admin.component';
import {InscritgpdiffusionsComponent} from './tabs/inscritgpdiffusions/inscritgpdiffusions.component';

const routes: Routes = [
  {
    path: '',
    component: GpdiffusionsComponent,
    children: [
      {
        path: 'tabs',
        component: TabsComponent,
        children: [
          {
            path: '',
            component: ListeComponent,
          },
          {
            path: 'liste',
            component: ListeComponent,
          },
          {
            path: 'editeur',
            component: EditeurComponent,
          },
          {
            path: 'admin',
            component: AdminComponent,
          },
          {
            path: 'lstinscrit',
            component: InscritgpdiffusionsComponent,
          },
        ],
      },
    ],
  },
];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [
    RouterModule,
  ],
})
export class GpdiffusionsRoutingModule {
}

