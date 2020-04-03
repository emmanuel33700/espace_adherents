import {Component, OnInit} from '@angular/core';
import {Adherent} from '../../../../api/generated/models/adherent';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Observable} from 'rxjs';
import {AdherentService} from '../../../../api/generated/services/adherent.service';

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./modifier.component.scss'],
  templateUrl: './modifier.component.html',
})
export class ModifierComponent implements OnInit {
  // adherent: Observable<Adherent>;
   adherent: Adherent;
  form: FormGroup;
  dataModel: any;

  constructor(
    private formBuilder: FormBuilder,
    private adherentService: AdherentService,
  ) {}

  ngOnInit(): void {

    this.adherent = JSON.parse (localStorage.getItem('adherent'));
    this.dataModel = this.adherent;

  console.info(this.adherent);

    this.form = this.formBuilder.group({
      nom: [this.adherent.nom, Validators.required],
      prenom: [this.adherent.prenom, Validators.required],
      civilite: [this.adherent.civilite, Validators.required],
      email: [this.adherent.email, Validators.required],
      adresse1: [this.adherent.adresse1, Validators.required],
      adresse2: [this.adherent.adresse2],
      ville: [this.adherent.ville, Validators.required],
      telPortable: [this.adherent.telPortable],
      telMaison: [this.adherent.telMaison],
      dateNaissance: [this.adherent.dateNaissance],
      commentaire: [this.adherent.commentaire],

    });

    this.form.valueChanges.subscribe(data => {
      this.dataModel = data;
    });

  }


  submit() {
    console.info(this.form.getRawValue());
    this.adherent = this.form.getRawValue();
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
          localStorage.setItem('adherent', JSON.stringify(this.adherent));
        },
      );
  }
}
