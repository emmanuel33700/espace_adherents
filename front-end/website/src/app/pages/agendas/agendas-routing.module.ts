import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AgendasComponent } from './agendas.component';
import { LireComponent } from './lire/lire.component';

const routes: Routes = [{
  path: '',
  component: AgendasComponent,
  children: [
    {
      path: 'lire',
      component: LireComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AgendasRoutingModule {
}
