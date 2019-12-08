import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdhsaisonComponent } from './adhsaison.component';
import { ListeComponent } from './liste/liste.component';

const routes: Routes = [{
  path: '',
  component: AdhsaisonComponent,
  children: [
    {
      path: 'liste',
      component: ListeComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdhsaisonRoutingModule {
}
