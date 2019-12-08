import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { PagesComponent } from './pages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NotFoundComponent } from './miscellaneous/not-found/not-found.component';

const routes: Routes = [{
  path: '',
  component: PagesComponent,
  children: [
    {
      path: 'dashboard',
      component: DashboardComponent,
    },
    {
      path: 'documents',
      loadChildren: () => import('./documents/documents.module')
        .then(m => m.DocumentsModule),
    },
    {
      path: 'agendas',
      loadChildren: () => import('./agendas/agendas.module')
        .then(m => m.AgendasModule),
    },
    {
      path: 'gpdiffusions',
      loadChildren: () => import('./gpdiffusions/gpdiffusions.module')
        .then(m => m.GpdiffusionsModule),
    },
    {
      path: 'trombinoscope',
      loadChildren: () => import('./trombinoscope/trombinoscope.module')
        .then(m => m.TrombinoscopeModule),
    },
    {
      path: 'saisons',
      loadChildren: () => import('./saisons/saisons.module')
        .then(m => m.SaisonsModule),
    },
    {
      path: 'adhsaison',
      loadChildren: () => import('./adhsaison/adhsaison.module')
        .then(m => m.AdhsaisonModule),
    },
    {
      path: 'adhenregistres',
      loadChildren: () => import('./adhenregistres/adhenregistres.module')
        .then(m => m.AdhenregistresModule),
    },
    {
      path: 'profile',
      loadChildren: () => import('./profile/profile.module')
        .then(m => m.ProfileModule),
    },
    {
      path: '',
      redirectTo: 'dashboard',
      pathMatch: 'full',
    },
    {
      path: '**',
      component: NotFoundComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesRoutingModule {
}
