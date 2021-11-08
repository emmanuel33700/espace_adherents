import {Component, HostBinding, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, NgForm, NgModel} from '@angular/forms';
import {Adherent} from '../../../../api/generated/adherents/models/adherent';
import {AdherentService} from '../../../../api/generated/adherents/services/adherent.service';
import {NbToastrService} from '@nebular/theme';
import {LoggerService} from '../../../@core/utils/logger.service';
import {DateService} from '../../../@core/utils';

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./ajouteradherent.component.scss'],
  templateUrl: './ajouteradherent.component.html',
})
export class AjouteradherentComponent implements OnInit {
  form: FormGroup;
  adherent: Adherent = {};

  errors: string[] = [];
  messages: string[] = [];
  submitted = false;
  valideEmail = true;

  user: any = {};

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';

  // fin toaster

  constructor(
    private formBuilder: FormBuilder,
    private adherentService: AdherentService,
    private toastrService: NbToastrService,
    private loggerService: LoggerService,
    private dateService: DateService,
  ) {
  }

  ngOnInit(): void {
  }

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
    this.adherent.codePostal = this.user.codePostal;
    this.adherent.ville = this.user.ville;
    this.adherent.telPortable = this.user.telPortable;
    this.adherent.telMaison = this.user.telFixe;
    this.adherent.dateNaissance = this.dateService.convertIsoDate(this.user.dateNaissance);
    this.adherent.commentaire = this.user.commentaire;
    if (this.user.accordMail) {
      this.adherent.accordMail = true;
    }
    if (this.user.publicContact) {
      this.adherent.publicContact = true;
    }


    this.adherentService.ajoutAdherent({body: this.adherent})
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de enregistrement',
            'Erreur ');
          this.submitted = false;
        },
        () => {
          this.loggerService.info('Enregistrement fini');
          this.toastrService.success(
            'Mise à jour finalisée',
            'Opération réussie');
          form.resetForm();
          this.submitted = false;
        },
      );
  }


  /**
   * Vérifie si le mail existe déjà en BD
   * @param email
   */
  eMailValide(email: NgModel) {

    this.loggerService.info('Validation du mail ' + email.value);
    if (this.empty(email.value)) {
      this.valideEmail = true;
    }
    if (this.validateEmail(email.value)) {
      this.adherentService.getAdherentByMail({mailadherent: email.value})
        .subscribe(
          (data) => {
            if (data == null) {
              this.loggerService.info('Le mail n existe pas en BD');
              this.valideEmail = true;
            } else {
              this.valideEmail = false;
              this.loggerService.info('Le mail existe  en BD');
              this.loggerService.debug(JSON.stringify(data));
            }
          },
          (error) => {
            this.loggerService.error(JSON.stringify(error));
          },
          () => {
          },
        );
    }
  }


  /**
   * Vérifier si valeur est null
   */
  empty(e): boolean {
    switch (e) {
      case '':
      case 0:
      case '0':
      case null:
      case false:
      case typeof(e) === 'undefined':
        return true;
      default:
        return false;
    }
  }


  /**
   * VAlidation du format du mail
   * @param email
   */
  validateEmail(email): boolean {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
  }
}
