import {Component, HostBinding, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, NgForm} from '@angular/forms';
import {Adherent} from '../../../../api/generated/adherents/models/adherent';
import {Adhesion} from '../../../../api/generated/adherents/models/adhesion';
import {AdherentService} from '../../../../api/generated/adherents/services/adherent.service';
import {AdhesionService} from '../../../../api/generated/adherents/services/adhesion.service';
import { NbToastrService } from '@nebular/theme';
import {LoggerService} from '../../../@core/utils/logger.service';
import {Router} from '@angular/router';

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./ajouteradhesion.component.scss'],
  templateUrl: './ajouteradhesion.component.html',
})
export class AjouteradhesionComponent implements OnInit {
  adherent: Adherent = JSON.parse(localStorage.getItem('adh_selected'));
  adhesion: Adhesion = {};
  form: FormGroup ;
  public idAdherent: number = Number(localStorage.getItem('id_adh_selected'));


  errors: string[] = [];
  messages: string[] = [];
  submitted = false;

  adhesionform: any = {};

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  constructor(
    private formBuilder: FormBuilder,
    private adherentService: AdherentService,
    private toastrService: NbToastrService,
    private loggerService: LoggerService,
    private adhesionService: AdhesionService,
    private router: Router,
    ) {}

  ngOnInit(): void {
  }

  submit(form: NgForm) {
    // Initialisation des variables
    this.submitted = true;
    this.errors = [];
    this.messages = [];
    this.adherent = JSON.parse(localStorage.getItem('adh_selected'));
    this.idAdherent = Number(localStorage.getItem('id_adh_selected'));

    this.adhesion.idAdherent = this.idAdherent;
    this.adhesion.idAnneeAdhesion = Number(localStorage.getItem('id_annee_adhesion'));
    this.adhesion.idTypeAdhesion = this.adhesionform.idTypeAdhesion;
    this.adhesion.comptaSomme = Number(this.adhesionform.comptaSomme);

    this.adhesion.cheque = false;
    this.adhesion.espace = false;
    if (this.adhesionform.cheque) {
      this.adhesion.cheque = true;
    }
    if (this.adhesionform.espace) {
      this.adhesion.espace = true;
    }
    this.adhesion.comptaBanque = this.adhesionform.comptaBanque;
    this.adhesion.comptaNumCheque = this.adhesionform.comptaNumCheque;

    this.adhesionService.ajoutAdhesionAdherent({idadh: this.idAdherent, body: this.adhesion})
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
            'Création adhésion finalisé',
            'Opértation réussit');
          form.resetForm();
          this.submitted = false;
          return this.router.navigateByUrl('pages/adhsaison/tabs/infosadh');
        },
      );
  }
}
