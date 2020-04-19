import {Component, OnInit} from '@angular/core';
import {Adherent} from '../../../../../api/generated/models/adherent';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AdherentService} from '../../../../../api/generated/services/adherent.service';

@Component({
  selector: 'ngx-tab2',
  styleUrls: ['./infosadh.component.scss'],
  templateUrl: './infosadh.component.html',
})
export class InfosadhComponent implements OnInit {

  adherent: Adherent;
  form: FormGroup ;
  dataModel: any;
  public idAdherent: number = 0;

  constructor(
    private formBuilder: FormBuilder,
    private adherentService: AdherentService,
  ) {}

  ngOnInit(): void {

    this.idAdherent = Number(localStorage.getItem('id_adh_selected'));

    console.info('io adh recupere ' + this.idAdherent);

    this.adherentService.getAdherent({
      idadh: this.idAdherent,
    }).subscribe(
      (data) => {
        this.adherent = data;
      },
      (error) => {
        console.info(error);
      },
      () => {
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
      });


    this.form.valueChanges.subscribe(data => {
      this.dataModel = data;
    });

  }

  submit() {

  }
}
