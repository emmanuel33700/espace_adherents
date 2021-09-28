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
      path: 'register',
      loadChildren: () => import('./register/register.module')
        .then(m => m.RegisterModule),
    },
    {
      path: 'verify',
      loadChildren: () => import('./verify/verify.module')
        .then(m => m.VerifyModule),
    },
    {
      path: 'reset',
      loadChildren: () => import('./reset/reset.module')
        .then(m => m.ResetModule),
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
