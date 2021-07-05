import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProfileComponent } from './profile.component';
import { ModifierComponent } from './modifier/modifier.component';

const routes: Routes = [
  {
    path: '',
    component: ProfileComponent,
    children: [
      {
        path: 'modifier',
        component: ModifierComponent,
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
export class ProfileRoutingModule {
}

