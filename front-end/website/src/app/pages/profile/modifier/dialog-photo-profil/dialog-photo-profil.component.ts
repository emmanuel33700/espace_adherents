import {Component, HostBinding, Input} from '@angular/core';
import {NbDialogRef, NbToastrService} from '@nebular/theme';
import {NgForm} from '@angular/forms';
import {LoggerService} from '../../../../@core/utils';
import {Document} from '../../../../../api/generated/utilitaire/models/document';
import {ImageCroppedEvent, LoadedImage, base64ToFile} from 'ngx-image-cropper';
import {AdherentService} from '../../../../../api/generated/adherents/services/adherent.service';
import {Adherent} from '../../../../../api/generated/adherents/models/adherent';



@Component({
  selector: 'ngx-showcase-dialog',
  templateUrl: 'dialog-photo-profil.component.html',
  styleUrls: ['dialog-photo-profil.component.scss'],
})
export class DialogPhotoProfilComponent {


  errors: string[] = [];
  messages: string[] = [];
  submitted = false;


  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster


  // gestion de l'image cropper
  imageChangedEvent: any = '';
  croppedImage: any = '';


  // Gestion du bouton de validation
  valideUpload = false;
  loadingPhoto = false;


  @Input() idAdherent: number;

  constructor(protected ref: NbDialogRef<DialogPhotoProfilComponent>,
              private loggerService: LoggerService,
              private adherentService: AdherentService,
              private toastrService: NbToastrService) {
  }



  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
    this.valideUpload = true;
    this.loggerService.info('fileChangeEvent');
  }
  imageCropped(event: ImageCroppedEvent) {
    this.croppedImage = event.base64;
    this.loggerService.info('imageCropped');
  }
  imageLoaded(image: LoadedImage) {
    this.loggerService.info('imageLoaded');
  }
  cropperReady() {
    // cropper ready
    this.loggerService.info('cropperReady');
  }
  loadImageFailed() {
    // show message
    this.loggerService.info('loadImageFailed');
  }


  /**
   * Valider le chargement de la photo
   */
  async validerLoadingPhoto() {

    this.valideUpload = false;

    const filetmp = base64ToFile(this.croppedImage);
    const fileToBlob = async (file) => new Blob([new Uint8Array(await file.arrayBuffer())], {type: file.type});
    const fileBlob = await fileToBlob(filetmp);

    const fileNane: string = this.idAdherent + '_' + Date.now() + '.png';

    this.adherentService.updateUserPhoto({idadh: this.idAdherent, body: {fileName: fileNane, file: fileBlob} })
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
          this.loadingPhoto = false;

          // changer le lien de la photo l'objet en session
          this.changerLienPhotoLocalStorage(fileNane);

          // cloture de la modal
          this.ref.close();
        },
      );
  }

  /**
   * Changer le lien de la photo dans l'objet adhérent du local storage
   */
  private changerLienPhotoLocalStorage(fileNane: string) {
    const adherent: Adherent = JSON.parse (localStorage.getItem('adherent'));
    adherent.lienPhotoProfil = fileNane;
    localStorage.setItem('adherent', JSON.stringify(adherent));
  }
}
