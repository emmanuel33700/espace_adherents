import {Component, OnInit, HostBinding} from '@angular/core';
import {Adherent} from '../../../../api/generated/adherents/models/adherent';
import {FormBuilder, FormGroup, NgForm} from '@angular/forms';
import {AdherentService} from '../../../../api/generated/adherents/services/adherent.service';
import {NbDialogService, NbToastrService} from '@nebular/theme';
import {LoggerService} from '../../../@core/utils/logger.service';
import {DateService} from '../../../@core/utils';
import {DialogPhotoProfilComponent} from './dialog-photo-profil/dialog-photo-profil.component';

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

    this.adherent = JSON.parse (localStorage.getItem('adherent'));
    // this.dataModel = this.adherent;

    this.loggerService.info(JSON.stringify(this.adherent));

    this.user.civilite = this.adherent.civilite;
    this.user.nom = this.adherent.nom;
    this.user.prenom = this.adherent.prenom;
    this.user.email = this.adherent.email;
    this.user.adresse1 = this.adherent.adresse1;
    this.user.adresse2 = this.adherent.adresse2;
    this.user.ville = this.adherent.ville;
    this.user.telPortable = this.adherent.telPortable;
    this.user.telMaison = this.adherent.telMaison;
    this.user.dateNaissance = this.dateService.dateFormatForPrint(this.adherent.dateNaissance);
    this.user.commentaire = this.adherent.commentaire;
    this.user.accordMail = this.adherent.accordMail;
    this.user.publicContact = this.adherent.publicContact;
    this.user.lienPhotoProfil = this.adherent.lienPhotoProfil;

    if (this.adherent.lienPhotoProfil != null) {
      this.lienPhotoProfil = 'https://api.jalle-astro.fr/partage/photosprofil/' + this.adherent.lienPhotoProfil;
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
    this.adherent.email = this.user.email;
    this.adherent.adresse1 = this.user.adresse1;
    this.adherent.adresse2 = this.user.adresse2;
    this.adherent.ville = this.user.ville;
    this.adherent.telPortable = this.user.telPortable;
    this.adherent.telMaison = this.user.telFixe;
    this.adherent.dateNaissance = this.dateService.convertIsoDate(this.user.dateNaissance);
    this.adherent.commentaire = this.user.commentaire;
    if (this.user.accordmail) {
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
            'Opértation réussit');
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
    });
    this.adherent = JSON.parse (localStorage.getItem('adherent'));
    if (this.adherent.lienPhotoProfil != null) {
      this.lienPhotoProfil = 'https://api.jalle-astro.fr/partage/photosprofil/' + this.adherent.lienPhotoProfil;
    } else {
      this.lienPhotoProfil = null;
    }
  }
}
