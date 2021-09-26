import {Component, HostBinding, OnInit} from '@angular/core';
import {LoggerService} from '../../../@core/utils';
import {NbToastrService} from '@nebular/theme';
import {SaisonService} from '../../../../api/generated/utilitaire/services/saison.service';
import {Saison} from '../../../../api/generated/utilitaire/models/saison';
import {FormGroup, NgForm} from '@angular/forms';

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./liste.component.scss'],
  templateUrl: './liste.component.html',
})
export class ListeComponent implements OnInit {

  form: FormGroup ;
  submitted = false;

  // Toaster
  private index: number = 0;
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  // indicateur de chargement
  loading = true;

  saisons: Saison[] = [];

  selectedOption = 0;

  constructor(    private loggerService: LoggerService,
                  private toastrService: NbToastrService,
                  private saisonService: SaisonService,

  ) {
  }

  /**
   * Initialisation des saisions
   */
  ngOnInit(): void {

    this.selectedOption = 15;

    // récupéer la liste des saisions
    this.saisonService.getListeSaison({}).subscribe(
      (data) => {
        this.saisons = data;
        this.loggerService.debug(JSON.stringify(data));

        this.loading = false;
      },
      (error) => {
        this.loggerService.error(error);
        this.toastrService.danger(
          'Erreur technique lors de le recupération des informations',
          'Erreur ');
      },
      () => {
        this.loggerService.debug('fini');

        this.loading = false;
      });

  }

  /**
   * Validation du formulaire
   * @param form
   */
  submit(form: NgForm) {

    if (this.selectedOption !== 0) {
      this.submitted = true;
      this.loggerService.info('Changement de saisie avec ID ' + this.selectedOption);

      this.saisonService.updateSaisonCourante({idSaison: this.selectedOption})
        .subscribe(
          (data) => {
            this.loggerService.info(JSON.stringify(data));
          },
          (error) => {
            this.loggerService.error(error);
            this.submitted = false;
            this.toastrService.danger(
              'Erreur technique lors de enregistrement',
              'Erreur ');
          },
          () => {
            this.loggerService.info('MàJ OK');
            this.submitted = false;
            localStorage.setItem('id_annee_adhesion', String(this.selectedOption));
            this.toastrService.success(
              'Mise à jour finalisée',
              'Opértation réussit');
          },
        );
    }


  }
}
