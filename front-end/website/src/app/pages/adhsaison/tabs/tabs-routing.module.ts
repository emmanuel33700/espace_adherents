import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TabsComponent} from './tabs.component';
import { InfosadhComponent} from './infosadh/infosadh.component';
import { ListeadhesionsComponent} from './listeadhesions/listeadhesions.component';
import { ListeparticipationsComponent} from './listeparticipations/listeparticipations.component';
import { ListecommunicationsComponent } from './listecommunications/listecommunications.component';
import { ConnexionComponent } from './connexion/connexion.component';

const routes: Routes = [{
  path: '',
  component: TabsComponent,
  children: [
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
}];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TabsRoutingModule {
}
