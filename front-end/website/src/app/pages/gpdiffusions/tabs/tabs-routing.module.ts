import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TabsComponent} from './tabs.component';
import { ListeComponent} from './liste/liste.component';
import { EditeurComponent} from './editeur/editeur.component';
import { AdminComponent} from './admin/admin.component';

const routes: Routes = [{
  path: '',
  component: TabsComponent,
  children: [
    {
      path: 'liste',
      component: ListeComponent,
    },
    {
      path: 'editeur',
      component: EditeurComponent,
    },
    {
      path: 'admin',
      component: AdminComponent,
    },
  ],
}];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TabsRoutingModule {
}
