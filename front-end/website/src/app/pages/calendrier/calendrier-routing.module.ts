import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CalendrierComponent } from './calendrier.component';
import { ListeComponent } from './liste/liste.component';


const routes: Routes = [
  {
    path: '',
    component: CalendrierComponent,
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
export class CalendrierRoutingModule {
}

