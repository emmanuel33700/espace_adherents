import {ExtraOptions, RouterModule, Routes} from '@angular/router';
import { NgModule } from '@angular/core';

import { AuthComponent } from './auth.component';




const routes: Routes = [{
  path: '',
  component: AuthComponent,
  children: [
    {
      path: 'login',
      loadChildren: () => import('./login/login.module')
        .then(m => m.LoginModule),
    },
    {
      path: '',
      redirectTo: 'login',
      pathMatch: 'full',
    },
  ],
}];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {
}
