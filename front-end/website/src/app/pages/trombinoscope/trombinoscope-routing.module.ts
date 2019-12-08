import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TrombinoscopeComponent } from './trombinoscope.component';
import { ListeComponent } from './liste/liste.component';

const routes: Routes = [{
  path: '',
  component: TrombinoscopeComponent,
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
export class TrombinoscopeRoutingModule {
}
