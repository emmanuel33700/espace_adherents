import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { SaisonsComponent } from './saisons.component';
import { ListeComponent } from './liste/liste.component';


const routes: Routes = [
  {
    path: '',
    component: SaisonsComponent,
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
export class SaisonsRoutingModule {
}

