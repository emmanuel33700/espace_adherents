import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DocumentsComponent } from './documents.component';
import { ListeComponent } from './liste/liste.component';

const routes: Routes = [{
  path: '',
  component: DocumentsComponent,
  children: [
    {
      path: 'liste',
      component: ListeComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DocumentsRoutingModule { }

export const routedComponents = [
  DocumentsComponent,
  ListeComponent,
];
