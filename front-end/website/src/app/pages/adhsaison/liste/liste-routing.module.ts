import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {  TabsComponent } from '../tabs/tabs.component';


export const routes: Routes = [{
  path: 'tabs',
  component: TabsComponent,
}];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ListeRoutingModule {
}
