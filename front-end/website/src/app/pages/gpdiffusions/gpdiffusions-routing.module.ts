import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {GpdiffusionsComponent} from './gpdiffusions.component';

import {TabsComponent} from './tabs/tabs.component';
import {ListeComponent} from './tabs/liste/liste.component';
import {EditeurComponent} from './tabs/editeur/editeur.component';

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
            redirectTo: 'liste',
            pathMatch: 'full',
          },
          {
            path: 'liste',
            component: ListeComponent,
          },
          {
            path: 'editeur',
            component: EditeurComponent,
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

