import {Component, HostBinding, OnInit} from '@angular/core';
import {Adherent} from '../../../../../api/generated/adherents/models/adherent';
import {FormBuilder, FormGroup, NgForm, NgModel} from '@angular/forms';
import {AdherentService} from '../../../../../api/generated/adherents/services/adherent.service';
import { NbToastrService } from '@nebular/theme';
import {LoggerService} from '../../../../@core/utils/logger.service';
import {DateService} from '../../../../@core/utils/date.service';



@Component({
  selector: 'ngx-tab2',
  styleUrls: ['./infosadh.component.scss'],
  templateUrl: './infosadh.component.html',
})
export class InfosadhComponent implements OnInit {

  adherent: Adherent;
  form: FormGroup ;
  public idAdherent: number = 0;
  valideEmail = true;

  user: any = {};

  errors: string[] = [];
  messages: string[] = [];
  submitted = false;

  // Toaster
  private index: number = 0;
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
  ) {}

  ngOnInit(): void {

    this.idAdherent = Number(localStorage.getItem('id_adh_selected'));
    this.adherent = JSON.parse(localStorage.getItem('adh_selected'));

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

    this.loading = false;

  }

  submit(form: NgForm) {

    this.loggerService.info('tel portable' + this.user.telPortable);
    this.loggerService.info('tel fixe' + this.user.telFixe);
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

    this.adherentService.updateUser({idadh: this.idAdherent, body: this.adherent})
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(error);
          this.toastrService.danger(
            'Erreur technique lors de enregistrement',
            'Erreur ');
          this.submitted = false;
        },
        () => {
          this.loggerService.info('MàJ OK');
          this.toastrService.success(
            'Mise à jour finalisée',
            'Opération réussie');
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
      this.loggerService.info('Mail null ');
      this.valideEmail = true;
    }  else if (email.value === this.adherent.email) {
      this.loggerService.info('Mail non modifié, pas besoin de vérifier si il existe déjà en BD ' + email.value);
      this.valideEmail = true;
    } else if (this.validateEmail(email.value)) {
      this.adherentService.getAdherentByMail({mailadherent: email.value})
        .subscribe(
          (data) => {
            if (data == null) {
              this.loggerService.info('Le mail n existe pas en BD');
              alert('Attention, vous allez modifier le login de ' + this.adherent.prenom);
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
    } else {
      this.valideEmail = true;
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
