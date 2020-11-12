import {Component, HostBinding, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, NgForm} from '@angular/forms';
import {Adherent} from '../../../../api/generated/adherents/models/adherent';
import {AdherentService} from '../../../../api/generated/adherents/services/adherent.service';
import { NbToastrService } from '@nebular/theme';
import {LoggerService} from '../../../@core/utils/logger.service';

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./ajouter.component.scss'],
  templateUrl: './ajouter.component.html',
})
export class AjouterComponent implements OnInit {
  form: FormGroup;
  adherent: Adherent = {};

  errors: string[] = [];
  messages: string[] = [];
  submitted = false;

  user: any = {};

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
            'Opértation réussit');
          form.resetForm();
          this.submitted = false;
        },
      );
  }
}
