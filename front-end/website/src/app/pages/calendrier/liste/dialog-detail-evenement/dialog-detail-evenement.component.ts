import { Component, Input } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';

@Component({
  selector: 'ngx-showcase-dialog',
  templateUrl: 'dialog-detail-evenement.component.html',
  styleUrls: ['dialog-detail-evenement.component.scss'],
})
export class DialogDetailEvenementComponent {

  @Input() title: string;
  @Input() idEvenement: string;

  constructor(protected ref: NbDialogRef<DialogDetailEvenementComponent>) {}

  dismiss() {
    this.ref.close();
  }


  supprimer() {
    //   if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
    //     clickInfo.event.remove();
    //   }
  }
}
