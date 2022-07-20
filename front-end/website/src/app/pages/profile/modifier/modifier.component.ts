import {Component, OnInit, HostBinding} from '@angular/core';
import {Adherent} from '../../../../api/generated/adherents/models/adherent';
import {FormBuilder, FormGroup, NgForm} from '@angular/forms';
import {AdherentService} from '../../../../api/generated/adherents/services/adherent.service';
import {NbDialogService, NbToastrService} from '@nebular/theme';
import {LoggerService} from '../../../@core/utils/logger.service';
import {DateService} from '../../../@core/utils';
import {DialogPhotoProfilComponent} from './dialog-photo-profil/dialog-photo-profil.component';
import {environment} from '../../../../environments/environment';

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./modifier.component.scss'],
  templateUrl: './modifier.component.html',
})
export class ModifierComponent implements OnInit {
  adherent: Adherent;
  form: FormGroup;
  lienPhotoProfil: string;


  errors: string[] = [];
  messages: string[] = [];
  submitted = false;

  user: any = {};

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  // indicateur de chargement
  loading = true;



  constructor(
    private formBuilder: FormBuilder,
    private adherentService: AdherentService,
    private toastrService: NbToastrService,
    private loggerService: LoggerService,
    private dateService: DateService,
    private dialogService: NbDialogService,
  ) {}

  /**
   * Initialisation du formulaire
   */
  ngOnInit(): void {

    this.loading = true;
    this.adherent = JSON.parse (localStorage.getItem('adherent'));
    // this.dataModel = this.adherent;

    this.loggerService.info(JSON.stringify(this.adherent));

    this.user.civilite = this.adherent.civilite;
    this.user.nom = this.adherent.nom;
    this.user.prenom = this.adherent.prenom;
    this.user.email = this.adherent.email;
    this.user.adresse1 = this.adherent.adresse1;
    this.user.adresse2 = this.adherent.adresse2;
    this.user.codePostal = this.adherent.codePostal;
    this.user.ville = this.adherent.ville;
    if (this.adherent.telPortable) {
      this.user.telPortable = this.adherent.telPortable.replace(/\s/g, '');
    }
    if (this.adherent.telMaison) {
      this.user.telFixe = this.adherent.telMaison.replace(/\s/g, '');
    }
    this.user.dateNaissance = this.dateService.dateFormatForPrint(this.adherent.dateNaissance);
    this.user.commentaire = this.adherent.commentaire;
    this.user.accordMail = this.adherent.accordMail;
    this.user.publicContact = this.adherent.publicContact;
    this.user.lienPhotoProfil = this.adherent.lienPhotoProfil;

    if (this.adherent.lienPhotoProfil != null) {
      this.lienPhotoProfil = environment.url_photo_profil + this.adherent.lienPhotoProfil;
    } else {
      this.lienPhotoProfil = null;
    }
    this.loading = false;
  }


  /**
   * Validation du formulaire
   * @param form
   */
  submit(form: NgForm) {

    // Initialisation des variables
    this.submitted = true;
    this.errors = [];
    this.messages = [];
    this.adherent.accordMail = false;
    this.adherent.publicContact = false;

    // Valorisation du model pour appel de l'api
    this.adherent.civilite = this.user.civilite;
    this.adherent.nom = this.user.nom;
    this.adherent.prenom = this.user.prenom;
    this.adherent.adresse1 = this.user.adresse1;
    this.adherent.adresse2 = this.user.adresse2;
    this.adherent.codePostal = this.user.codePostal;
    this.adherent.ville = this.user.ville;
    if (this.user.telPortable) {
      this.adherent.telPortable = this.user.telPortable.replace(/\s/g, '');
    } else {
      this.adherent.telPortable = null;
    }
    if (this.user.telFixe) {
      this.adherent.telMaison = this.user.telFixe.replace(/\s/g, '');
    } else {
      this.adherent.telMaison = null;
    }
    this.adherent.dateNaissance = this.dateService.convertIsoDate(this.user.dateNaissance);
    this.adherent.commentaire = this.user.commentaire;
    if (this.user.accordMail) {
      this.adherent.accordMail = true;
    }
    if (this.user.publicContact) {
      this.adherent.publicContact = true;
    }

    this.adherentService.updateUser({idadh: this.adherent.id, body: this.adherent})
      .subscribe(
        (data) => {
          console.info(data);
        },
        (error) => {
          this.loggerService.error('Error lors de appel du api' + error);
          this.toastrService.danger(
            'Erreur technique lors de enregistrement',
            'Erreur ');
          this.submitted = false;
        },
        () => {
          this.loggerService.debug('Mise à jour finalisée');
          this.toastrService.success(
            'Mise à jour finalisée',
            'Opération réussie');
          localStorage.setItem('adherent', JSON.stringify(this.adherent));
          this.submitted = false;
        },
      );

  }


  /**
   * Editer la photo de profil
   */
  editerPhoto() {
    this.dialogService.open(DialogPhotoProfilComponent, {
      context: {
        idAdherent: this.adherent.id,
      },
    }).onClose.subscribe(data => {
      this.adherent = JSON.parse(localStorage.getItem('adherent'));

      if (this.adherent.lienPhotoProfil != null) {
        this.lienPhotoProfil = environment.url_photo_profil
          + this.adherent.lienPhotoProfil + '?' + (new Date()).getTime();
      } else {
        this.lienPhotoProfil = null;
      }
    });


  }
}
