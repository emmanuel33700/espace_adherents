import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from '@angular/forms';
import {Adherent} from '../../../../api/generated/models/adherent';
import {AdherentService} from '../../../../api/generated/services/adherent.service';

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

  constructor(
    private formBuilder: FormBuilder,
    private adherentService: AdherentService,
    ) {}

  ngOnInit(): void {
  }

  submit(form: NgForm) {
    this.submitted = true;

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
    this.adherent.accordMail = this.user.accordmail;
    this.adherent.publicContact = this.user.publicContact;


    this.adherentService.ajoutAdherent({body: this.adherent})
      .subscribe(
        (data) => {
          console.info(data);
        },
        (error) => {
          console.error(error);
          this.errors = ['Error lors de enregistrement'];
          this.submitted = false;
        },
        () => {
          this.messages = ['Enregistrement fini'];
          form.resetForm();
          this.submitted = false;
        },
      );
  }
}
