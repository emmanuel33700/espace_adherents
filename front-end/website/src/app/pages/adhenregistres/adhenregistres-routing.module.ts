import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdhenregistresComponent } from './adhenregistres.component';
import { ListeComponent } from './liste/liste.component';

const routes: Routes = [{
  path: '',
  component: AdhenregistresComponent,
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
export class AdhenregistresRoutingModule {
}
