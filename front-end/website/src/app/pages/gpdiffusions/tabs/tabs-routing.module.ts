import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { TabsComponent} from './tabs.component';
import { ListeComponent} from './liste/liste.component';
import { EditeurComponent} from './editeur/editeur.component';

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
  ],
}];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TabsRoutingModule {
}
