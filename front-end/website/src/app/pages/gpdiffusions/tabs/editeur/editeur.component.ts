import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, NgForm} from '@angular/forms';
import {LoggerService} from '../../../../@core/utils';
import {NbToastrService} from '@nebular/theme';
import {ListeDeDiffusionService as  ListeDeDiffusionServiceUtilitaire} from '../../../../../api/generated/utilitaire/services/liste-de-diffusion.service';
import {ListeDiffusion as MailingListeUtilitaire} from '../../../../../api/generated/utilitaire/models/liste-diffusion';
import {MailAEnvoyer} from '../../../../../api/generated/utilitaire/models/mail-a-envoyer';
import {AngularEditorConfig} from '@kolkov/angular-editor';




interface Fichier {
  fileBlob: Blob;
  fileName: string;
}

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./editeur.component.scss'],
  templateUrl: './editeur.component.html',
})
export class EditeurComponent implements OnInit {

  editorConfig: AngularEditorConfig = {
    editable: true,
    spellcheck: true,
    height: 'auto',
    minHeight: '50',
    maxHeight: 'auto',
    width: 'auto',
    minWidth: '0',
    translate: 'yes',
    enableToolbar: true,
    showToolbar: true,
    placeholder: 'Votre email',
    defaultParagraphSeparator: '',
    defaultFontName: '',
    defaultFontSize: '',
    fonts: [
      {class: 'arial', name: 'Arial'},
      {class: 'times-new-roman', name: 'Times New Roman'},
      {class: 'calibri', name: 'Calibri'},
      {class: 'comic-sans-ms', name: 'Comic Sans MS'},
    ],
    customClasses: [
      {
        name: 'quote',
        class: 'quote',
      },
      {
        name: 'redText',
        class: 'redText',
      },
      {
        name: 'titleText',
        class: 'titleText',
        tag: 'h1',
      },
    ],
    uploadWithCredentials: false,
    sanitize: true,
    toolbarPosition: 'top',
    toolbarHiddenButtons: [
      ['insertImage', 'insertVideo'],
    ],
  };

  form: FormGroup;


  // indicateurs de chargement
  loading = true;
  loadingEnvoyerMail = false; // Indicateur de mail encours d'envoi
  submitted = false; // Indicateur de téléchargement de fichier

  role: string;

  droitcomplet = false;

  listeDiffusion: MailingListeUtilitaire[];


  /** Information du mail a envoyer saisi dnas l'IHM **/
  idMail: number = Date.now();

  // Id de la mailing liste sélectionnée
  selectedMailingListe: number = 0;

  // id de l'option d'nevoi de mail
  optionEnvoyerMail: string = '1';

  // mail en envoyé
  mailHtml: string = '';

  // Objet du mail
  objetMail: string = '';

  // Fichier à envoyer
  filesName: string[] = [];

  // nombre de fichier en cours de téléchargement
  nbFichierEnCourTelechargement: number = 0;


  constructor(
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
    private listeDeDiffusionServiceUtilitaire: ListeDeDiffusionServiceUtilitaire,
  ) {
  }


  ngOnInit(): void {
    this.role = localStorage.getItem('ROLE');

    // tester si la personne à le droit d'éditer la page
    if ((this.role === 'ADMIN') || (this.role === 'CONSEIL') ) {
      this.droitcomplet = true;
    }

    // Par défaut, envouer un mail aux adhérents de la saison
    this.optionEnvoyerMail = '1';


    this.idMail = Date.now();

    // Récupérer les listes des mailing list
    this.listeDeDiffusionServiceUtilitaire.getListes()
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
          this.listeDiffusion = data;
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de la récupération des données',
            'Erreur ');
        },
        () => {
          this.loading = false;
        },
      );


  }


  /**
   * Telechargement d'un fichier
   * @param event
   */
  async onFileSelected(event) {

    const nbFile: number =  event.target.files.length;
    this.loggerService.info('Nombre de fichier à télécharger: ' + nbFile);

    this.submitted = true;



    if (nbFile > 0) {

      Array.from(event.target.files).forEach(async (fichier: File , index) => {

         this.loggerService.info('Filename : ' + fichier.name);
         this.loggerService.info('Type : ' + fichier.type);
         this.loggerService.info('Size : ' + fichier.size);

         const fileToBlob = async (file2) => new Blob([new Uint8Array(await file2.arrayBuffer())], {type: file2.type});
         const fileBlob: Blob = await fileToBlob( fichier);

        this.nbFichierEnCourTelechargement++;

        this.listeDeDiffusionServiceUtilitaire.addBinaryToMail({idMail: this.idMail
          , body: {fileName: fichier.name, file: fileBlob}})
          .subscribe(
            (data) => {
              this.loggerService.info(JSON.stringify(data));
            },
            (error) => {
              this.loggerService.error(JSON.stringify(error));
              this.nbFichierEnCourTelechargement--;
              this.toastrService.danger(
                'Erreur technique lors de l\'envoie du fichier',
                'Erreur ');
            },
            () => {
              this.nbFichierEnCourTelechargement--;
              this.loggerService.info(' fini');
            },
          );

         this.filesName.push(fichier.name);
       });

    }
    this.submitted = false;

  }


  mail: MailAEnvoyer = {};

  envoyerMail(form: NgForm) {
    // activation du spinner
    this.loadingEnvoyerMail = true;

    this.loggerService.info('text HMTL : ' + this.mailHtml);
    this.loggerService.info('optionEnvoyerMail : ' + this.optionEnvoyerMail);
    this.loggerService.info('selectedMailingListe : ' + this.selectedMailingListe);


    this.mail.email = this.mailHtml;
    this.mail.titreEmail = this.objetMail;
    this.mail.idListeDiffusion = this.selectedMailingListe;
    if ((this.role === 'ADMIN' || this.role === 'CONSEIL' ) && String(this.optionEnvoyerMail) === '1') {
      this.mail.typeMail = 1;
    } else if ((this.role === 'ADMIN' || this.role === 'CONSEIL' ) &&  String(this.optionEnvoyerMail) === '2') {
      this.mail.typeMail = 2;
    } else if ((this.role === 'ADMIN' || this.role === 'CONSEIL' ) &&  String(this.optionEnvoyerMail) === '4') {
      this.mail.typeMail = 4;
    } else {
      this.mail.typeMail = 10;
      if (this.selectedMailingListe === 0 ) {
        this.toastrService.danger(
          'Attention, aucune mailing liste sélectionnée',
          'Erreur ');
        this.loggerService.error('Attention, aucune mailing liste sélectionnée');
        return 0;
      }
    }

    this.listeDeDiffusionServiceUtilitaire.sendMail({body: this.mail, idMail: this.idMail})
      .subscribe(
      (data) => {
        this.loggerService.info(JSON.stringify(data));
      },
      (error) => {
        this.loggerService.error(JSON.stringify(error));
        this.toastrService.danger(
          'Erreur technique lors de enregistrement',
          'Erreur ');
        this.loadingEnvoyerMail = false;
      },
      () => {
        this.loggerService.info(' fini');
        this.mailHtml = '';
        this.mail.email = '';
        this.filesName = [];
        this.idMail = Date.now(); // En change d'id de mail

        this.toastrService.success(
          'Mail envoyé',
          'Opération réussie');
        form.resetForm();
        this.submitted = false;
        this.loadingEnvoyerMail = false;
      },
    );

}


}



