import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdhsaisonComponent } from './adhsaison.component';
import { ListeComponent } from './liste/liste.component';
import { AjouteradherentComponent } from './ajouteradherent/ajouteradherent.component';
import { AjouteradhesionComponent } from './ajouteradhesion/ajouteradhesion.component';
import {  TabsComponent } from './tabs/tabs.component';

import { InfosadhComponent} from './tabs/infosadh/infosadh.component';
import {ListeadhesionsComponent} from './tabs/listeadhesions/listeadhesions.component';
import { ListeparticipationsComponent} from './tabs/listeparticipations/listeparticipations.component';
import { ListecommunicationsComponent } from './tabs/listecommunications/listecommunications.component';
import { ConnexionComponent } from './tabs/connexion/connexion.component';
import {LiensadherentComponent} from './tabs/liensadherent/liensadherent.component';

const routes: Routes = [{
  path: '',
  component: AdhsaisonComponent,
  children: [
    {
      path: 'liste',
      component: ListeComponent,
    },
    {
      path: 'ajouteradherent',
      component: AjouteradherentComponent,
    },
    {
      path: 'ajouteradhesion',
      component: AjouteradhesionComponent,
    },
    {
      path: 'tabs',
      component: TabsComponent,
      children: [
        {
          path: '',
          redirectTo: 'infosadh',
          pathMatch: 'full',
        },
        {
          path: 'infosadh',
          component: InfosadhComponent,
        },
        {
          path: 'liensadherent',
          component: LiensadherentComponent,
        },
        {
          path: 'lstadhesions',
          component: ListeadhesionsComponent,
        },
        {
          path: 'listeparticipations',
          component: ListeparticipationsComponent,
        },
        {
          path: 'listecommunications',
          component: ListecommunicationsComponent,
        },
        {
          path: 'connexion',
          component: ConnexionComponent,
        },
      ],
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdhsaisonRoutingModule {
}
