import {Component, HostBinding, OnInit} from '@angular/core';
import {Adherent} from '../../../../../api/generated/adherents/models/adherent';
import {FormBuilder, FormGroup, NgForm} from '@angular/forms';
import {AdherentService} from '../../../../../api/generated/adherents/services/adherent.service';
import { NbToastrService } from '@nebular/theme';
import {LoggerService} from '../../../../@core/utils/logger.service';


@Component({
  selector: 'ngx-tab2',
  styleUrls: ['./infosadh.component.scss'],
  templateUrl: './infosadh.component.html',
})
export class InfosadhComponent implements OnInit {

  adherent: Adherent;
  form: FormGroup ;
  public idAdherent: number = 0;

  user: any = {};

  errors: string[] = [];
  messages: string[] = [];
  submitted = false;

  // Toaster
  private index: number = 0;
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  constructor(
    private formBuilder: FormBuilder,
    private adherentService: AdherentService,
    private toastrService: NbToastrService,
    private loggerService: LoggerService,
  ) {}

  ngOnInit(): void {

    this.idAdherent = Number(localStorage.getItem('id_adh_selected'));
    this.loggerService.info('id adh recupere ' + this.idAdherent);

    this.adherentService.getAdherent({
      idadh: this.idAdherent,
    }).subscribe(
      (data) => {
        this.adherent = data;
      },
      (error) => {
        this.loggerService.error(error);
      },
      () => {

        this.loggerService.info('adherent recupe api '  + JSON.stringify(this.adherent));
        localStorage.setItem('adh_selected', JSON.stringify(this.adherent));

        this.user.civilite = this.adherent.civilite;
        this.user.nom = this.adherent.nom;
        this.user.prenom = this.adherent.prenom;
        this.user.email = this.adherent.email;
        this.user.adresse1 = this.adherent.adresse1;
        this.user.adresse2 = this.adherent.adresse2;
        this.user.ville = this.adherent.ville;
        this.user.telPortable = this.adherent.telPortable;
        this.user.telMaison = this.adherent.telMaison;
        this.user.dateNaissance = null; // TODO a revoir la date de naisance
        this.user.commentaire = this.adherent.commentaire;
        this.user.accordMail = this.adherent.accordMail;
        this.user.publicContact = this.adherent.publicContact;
      });


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
    this.adherent.ville = this.user.ville;
    this.adherent.telPortable = this.user.telPortable;
    this.adherent.telMaison = this.user.telFixe;
    this.adherent.dateNaissance = null; // TODO a revoir la date de naisance
    this.adherent.commentaire = this.user.commentaire;
    if (this.user.accordmail) {
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
            'Opértation réussit');
          this.submitted = false;
        },
      );

  }
}
