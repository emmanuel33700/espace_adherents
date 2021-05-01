import { Component, Input } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';

@Component({
  selector: 'ngx-showcase-dialog',
  templateUrl: 'dialog-ajout-docs.component.html',
  styleUrls: ['dialog-ajout-docs.component.scss'],
})
export class DialogAjoutDocsComponent {

  @Input() title: string;

  constructor(protected ref: NbDialogRef<DialogAjoutDocsComponent>) {}

  dismiss() {
    this.ref.close();
  }
}
