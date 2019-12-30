import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdhsaisonComponent } from './adhsaison.component';
import { ListeComponent } from './liste/liste.component';
import { AjouterComponent } from './ajouter/ajouter.component';
import {  TabsComponent } from './tabs/tabs.component';

import { InfosadhComponent} from './tabs/infosadh/infosadh.component';
import {ListeadhesionsComponent} from './tabs/listeadhesions/listeadhesions.component';
import { ListeparticipationsComponent} from './tabs/listeparticipations/listeparticipations.component';
import { ListecommunicationsComponent } from './tabs/listecommunications/listecommunications.component';
import { ConnexionComponent } from './tabs/connexion/connexion.component';

const routes: Routes = [{
  path: '',
  component: AdhsaisonComponent,
  children: [
    {
      path: 'liste',
      component: ListeComponent,
    },
    {
      path: 'ajouter',
      component: AjouterComponent,
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
