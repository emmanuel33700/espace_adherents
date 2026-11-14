import {Component, HostBinding, Input} from '@angular/core';
import {NbDialogRef, NbToastrService} from '@nebular/theme';
import {FormGroup, NgForm} from '@angular/forms';
import {LoggerService} from '../../../../@core/utils';
import {DocumentationService} from '../../../../../api/generated/utilitaire/services/documentation.service';
import {Document} from '../../../../../api/generated/utilitaire/models/document';
vimport {validatePdfFile} from './pdf-validation';

@Component({
    selector: 'ngx-showcase-dialog',
    templateUrl: 'dialog-ajout-docs.component.html',
    styleUrls: ['dialog-ajout-docs.component.scss'],
    standalone: false
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
    if (this.loadingFichier || !this.valideForm || !this.file || !this.fileBlob) {
      return;
    }

    // activation du spinner
    this.loadingFichier = true;

    let document: Document;
    document = {};

    if (this.fileBlob != null) {
      const idFichier: number = Date.now();

      // Nom ASCII portable, limité à 255 octets, en conservant l'extension.
      const sanitize = require('sanitize-filename');
      const nomNettoye = sanitize(this.file.name.normalize('NFKD')
        .replace(/[\u0300-\u036f]/g, '')
        .replace(/[^a-zA-Z0-9._-]+/g, '-')
        .replace(/-+/g, '-')
        .replace(/\.{2,}/g, '.')
        .replace(/^[._-]+|[._-]+$/g, ''));
      const prefixe = idFichier + '-';
      const dernierPoint = nomNettoye.lastIndexOf('.');
      const indexExtension = nomNettoye.length - dernierPoint < 255 - prefixe.length ? dernierPoint : -1;
      const extension = indexExtension > 0 ? nomNettoye.slice(indexExtension) : '';
      const nomSansExtension = indexExtension > 0 ? nomNettoye.slice(0, indexExtension) : nomNettoye;
      const nomTronque = (nomSansExtension || 'document')
        .slice(0, 255 - prefixe.length - extension.length)
        .replace(/[._-]+$/g, '') || 'document';
      const filenameSanitize = prefixe + nomTronque + extension;

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
            this.loadingFichier = false;
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
                  this.loadingFichier = false;
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
  async onFileSelected(event: Event) {
    if (this.loadingFichier) {
      return;
    }
    const input = event.target as HTMLInputElement;
    const file = input.files?.[0];
    this.valideForm = false;
    this.fileBlob = null;
    this.file = file || null;
    this.errors = [];

    if (!file) {
      return;
    }

    const errors = await validatePdfFile(file);
    // Une sélection plus récente doit primer sur une lecture encore en cours.
    if (this.file !== file) {
      return;
    }
    this.errors = errors;
    if (errors.length) {
      this.file = null;
      input.value = '';
      return;
    }

    this.fileBlob = new Blob([file], {type: 'application/pdf'});
    this.valideForm = true;
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
