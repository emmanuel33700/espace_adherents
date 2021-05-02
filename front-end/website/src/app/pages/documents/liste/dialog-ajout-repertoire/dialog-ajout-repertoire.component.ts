import {Component, HostBinding, Input} from '@angular/core';
import {NbDialogRef, NbToastrService} from '@nebular/theme';
import {NgForm} from '@angular/forms';
import {LoggerService} from '../../../../@core/utils';
import {DocumentationService} from '../../../../../api/generated/utilitaire/services/documentation.service';
import {Document} from '../../../../../api/generated/utilitaire/models/document';



@Component({
  selector: 'ngx-showcase-dialog',
  templateUrl: 'dialog-ajout-repertoire.component.html',
  styleUrls: ['dialog-ajout-repertoire.component.scss'],
})
export class DialogAjoutRepertoireComponent {

  @Input() idRepertoireRatachement: number;

  errors: string[] = [];
  messages: string[] = [];
  submitted = false;

  repertoireForm: any = {};

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  constructor(protected ref: NbDialogRef<DialogAjoutRepertoireComponent>,
              private loggerService: LoggerService,
              private documentationService: DocumentationService,
              private toastrService: NbToastrService) {
  }

  /**
   * Ajouter un répertoire
   * @param form
   */
  ajouterRepertoire(form: NgForm) {

    const idDossier: number = Date.now();

    let document: Document;
    document = {};

    document.idDossierRattachement = this.idRepertoireRatachement;
    document.libelleCourt = this.repertoireForm.repertoire;
    document.libelleLong = this.repertoireForm.repertoireDetail;
    document.idAuthority = 1;
    document.id = idDossier;

    this.repertoireForm.id = document.id;


    this.documentationService.addDossier({body: document})
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de enregistrement',
            'Erreur ');
        },
        () => {
          this.loggerService.info('Enregistrement fini');

          // cloture de la modal
          this.ref.close(this.repertoireForm);
        },
      );
  }
}
