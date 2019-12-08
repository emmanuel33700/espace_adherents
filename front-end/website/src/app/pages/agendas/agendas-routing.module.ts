import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AgendasComponent } from './agendas.component';
import { ListeComponent } from './liste/liste.component';

const routes: Routes = [{
  path: '',
  component: AgendasComponent,
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
export class AgendasRoutingModule {
}
