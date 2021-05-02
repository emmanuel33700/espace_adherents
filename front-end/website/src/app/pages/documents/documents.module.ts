import { NgModule } from '@angular/core';
import {
    NbActionsModule, NbAlertModule, NbButtonModule,
    NbCardModule,
    NbIconModule,
    NbInputModule,
    NbPopoverModule, NbProgressBarModule, NbSpinnerModule, NbStepperModule,
    NbTreeGridModule,
} from '@nebular/theme';
import { Ng2SmartTableModule } from 'ng2-smart-table';

import { ThemeModule } from '../../@theme/theme.module';
import { DocumentsRoutingModule, routedComponents } from './documents-routing.module';
import { FsIconComponent } from './liste/liste.component';
import { DialogAjoutDocsComponent } from './liste/dialog-ajout-docs/dialog-ajout-docs.component';
import { DialogAjoutRepertoireComponent } from './liste/dialog-ajout-repertoire/dialog-ajout-repertoire.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
    imports: [
        NbCardModule,
        NbTreeGridModule,
        NbIconModule,
        NbInputModule,
        ThemeModule,
        DocumentsRoutingModule,
        Ng2SmartTableModule,
        NbActionsModule,
        NbPopoverModule,
        NbButtonModule,
        NbAlertModule,
        FormsModule,
        NbStepperModule,
        ReactiveFormsModule,
        NbProgressBarModule,
        NbSpinnerModule,
    ],
  declarations: [
    ...routedComponents,
    FsIconComponent,
    DialogAjoutDocsComponent,
    DialogAjoutRepertoireComponent,
  ],
})
export class DocumentsModule { }
