import { Component, Input } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';

@Component({
  selector: 'ngx-showcase-dialog',
  templateUrl: 'dialog-admin-docs.component.html',
  styleUrls: ['dialog-admin-docs.component.scss'],
})
export class DialogAdminDocsComponent {

  @Input() title: string;

  constructor(protected ref: NbDialogRef<DialogAdminDocsComponent>) {}

  dismiss() {
    this.ref.close();
  }
}
