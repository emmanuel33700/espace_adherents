import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Adherent} from '../../../../api/generated/models/adherent';
import {AdherentService} from '../../../../api/generated/services/adherent.service';

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./ajouter.component.scss'],
  templateUrl: './ajouter.component.html',
})
export class AjouterComponent implements OnInit {
  form: FormGroup;
  adherent: Adherent;

  constructor(
    private formBuilder: FormBuilder,
    private adherentService: AdherentService,
    ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      civilite: ['', Validators.required],
      email: ['', Validators.required],
      adresse1: ['', Validators.required],
      ville: ['', Validators.required],
      telPortable: [''],
      telMaison: [''],
      dateNaissance: [''],
      commentaire: [''],
    });
  }

  submit() {
    console.info(this.form.getRawValue());
    this.adherent = this.form.getRawValue();
    this.adherent.accordMail = true;
    this.adherent.publicContact = true;
    this.adherent.dateNaissance = null;
    this.adherentService.ajoutAdherent({body: this.adherent})
      .subscribe(
        (data) => {
          console.info(data);
        },
        (error) => {
          console.info(error);
        },
        () => {
          console.info('fini');
        },
      );
  }
}
