import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdhsaisonComponent } from './adhsaison.component';
import { ListeComponent } from './liste/liste.component';

import { Tab1Component, Tab2Component, TabsComponent } from './tabs/tabs.component';

const routes: Routes = [{
  path: '',
  component: AdhsaisonComponent,
  children: [
    {
      path: 'liste',
      component: ListeComponent,
    },
    {
      path: 'tabs',
      component: TabsComponent,
      children: [
        {
          path: '',
          redirectTo: 'tab1',
          pathMatch: 'full',
        },
        {
          path: 'tab1',
          component: Tab1Component,
        },
        {
          path: 'tab2',
          component: Tab2Component,
        },
      ],
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdhsaisonRoutingModule {
}
