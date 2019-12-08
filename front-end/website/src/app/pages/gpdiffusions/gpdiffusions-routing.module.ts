import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { GpdiffusionsComponent } from './gpdiffusions.component';

import { ListeComponent } from './liste/liste.component';

const routes: Routes = [
  {
    path: '',
    component: GpdiffusionsComponent,
    children: [
      {
        path: 'liste',
        component: ListeComponent,
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

