import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CalendrierComponent } from './calendrier.component';
import { ListeComponent } from './liste/liste.component';
import { SyntheseparticipationComponent } from './syntheseparticipation/syntheseparticipation.component';


const routes: Routes = [
  {
    path: '',
    component: CalendrierComponent,
    children: [
      {
        path: 'liste',
        component: ListeComponent,
      },
      {
        path: 'syntheseparticipation',
        component: SyntheseparticipationComponent,
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

