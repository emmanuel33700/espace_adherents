import {Component, HostBinding, Input} from '@angular/core';
import {NbDialogRef, NbToastrService} from '@nebular/theme';
import {FormGroup, NgForm} from '@angular/forms';
import {LoggerService} from '../../../../@core/utils';
import {DocumentationService} from '../../../../../api/generated/utilitaire/services/documentation.service';
import {Document} from '../../../../../api/generated/utilitaire/models/document';

@Component({
  selector: 'ngx-showcase-dialog',
  templateUrl: 'dialog-ajout-docs.component.html',
  styleUrls: ['dialog-ajout-docs.component.scss'],
})
export class DialogAjoutDocsComponent {

  @Input() idRepertoireRatachement: number;

  errors: string[] = [];
  messages: string[] = [];
  submitted = false;

  documentForm: any = {};

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  file: File;
  fileBlob: Blob;

  // indicateur su possibilité de valider le formulaire
  valideForm = false;

  // indicateur chargement du fichier en cours
  loadingFichier = false;

  constructor(protected ref: NbDialogRef<DialogAjoutDocsComponent>,
              private loggerService: LoggerService,
              private documentationService: DocumentationService,
              private toastrService: NbToastrService) {
  }

  dismiss() {
    this.ref.close();
  }

  /**
   *
   * @param form
   */
  ajouterDocument(form: NgForm) {
    // this.firstForm.markAsDirty();
  }

  validerDocument(form: NgForm) {

    // activation du spinner
    this.loadingFichier = true;

    let document: Document;
    document = {};

    if (this.fileBlob != null) {
      const idFichier: number = Date.now();

      // Netoyage du nom du fichier
      const sanitize = require('sanitize-filename');
      const filenameSanitize = sanitize(idFichier + '-' + this.file.name);

      document.idDossierRattachement = this.idRepertoireRatachement;
      document.libelleCourt = this.documentForm.fichier;
      document.libelleLong = this.documentForm.fichierDetail;
      document.idAuthority = 1;
      document.nomFichier = filenameSanitize;
      document.id = idFichier;

      this.documentForm.nomFichier = filenameSanitize;
      this.documentForm.id = document.id;

      // *************************************
      // On envoie le binaire
      // *************************************
      this.documentationService.addFichierBinaire({
        idFichier: idFichier,
        body: {userId: 123, orderId: 123, file: this.fileBlob},
      })
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

            // *********************************
            //  On créér le fichier
            // ************************************
            this.documentationService.addFichier({body: document})
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

                  // Fin d'activation du spinner
                  this.loadingFichier = false;

                  this.fileBlob = null;
                  this.file = null;

                  // cloture de la modal
                  this.ref.close(this.documentForm);
                },
              );

          },
        );


    }

  }

  /**
   *
   * @param event
   */
  async onFileSelected(event) {
    this.file = event.target.files[0];
this.errors = null;

    this.loggerService.info('Filename : ' + this.file.name);
    this.loggerService.info('Type : ' + this.file.type);
    this.loggerService.info('Syze : ' + this.file.size);

    if (this.file.type !== 'application/pdf') {
      this.loggerService.info('Le fichier téléchargé n\'est pas un PDF');
      this.errors.push('LE fichier téléchargé n\'est pas un PDF');
      this.file = null;
    } else if (this.file.size > 5000000) {
      this.loggerService.info('Le fichier téléchargé est trop gros ');
      this.errors.push('Le fichier téléchargé est trop gros');
      this.file = null;
    } else {
    const fileToBlob = async (file) => new Blob([new Uint8Array(await file.arrayBuffer())], {type: file.type});
    this.fileBlob = await fileToBlob(this.file);

    this.valideForm = true;
}
  }


  private changeFile(file) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = error => reject(error);
    });
  }
}
