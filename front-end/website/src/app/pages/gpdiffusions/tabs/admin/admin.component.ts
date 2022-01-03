import {Component, HostBinding, OnInit} from '@angular/core';
import {MailingListe as MailingListeAdherent} from '../../../../../api/generated/adherents/models/mailing-liste';
import {ListeDeDiffusionService as ListeDeDiffusionServiceAdherent} from '../../../../../api/generated/adherents/services/liste-de-diffusion.service';
import {DateService, LoggerService} from '../../../../@core/utils';
import {NbToastrService} from '@nebular/theme';
import {Router} from '@angular/router';
import {Adherent} from '../../../../../api/generated/adherents/models/adherent';
import {NgForm} from '@angular/forms';
import {ListeDeDiffusionService as  ListeDeDiffusionServiceUtilitaire} from '../../../../../api/generated/utilitaire/services/liste-de-diffusion.service';
import {ListeDiffusion as MailingListeUtilitaire} from '../../../../../api/generated/utilitaire/models/liste-diffusion';

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./admin.component.scss'],
  templateUrl: './admin.component.html',
})
export class AdminComponent implements OnInit {

  // indicateur de chargement
  loading = true;

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster


  adherent: Adherent;

  listeDiffusion: MailingListeUtilitaire[];

  // Role de la personne connecté
  role: string;

  droitEdition = false;
  submitted: boolean;


  libelleMailListForm: string;
  visibilitePublicMailList: boolean;

  constructor(
    private listeDeDiffusionServiceAdherent: ListeDeDiffusionServiceAdherent,
    private listeDeDiffusionServiceUtilitaire: ListeDeDiffusionServiceUtilitaire,
    private dateService: DateService,
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
    private router: Router,
  ) {
  }

  /**
   * Init
   */
  ngOnInit(): void {
    this.adherent = JSON.parse(localStorage.getItem('adherent'));
    this.role = localStorage.getItem('ROLE');

    // tester si la personne à le droit d'éditer la page
    if ((this.role === 'ADMIN') || (this.role === 'CONSEIL')) {
      this.droitEdition = true;
    }

    this.listeDeDiffusionServiceUtilitaire.getListes()
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
          this.listeDiffusion = data;
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de la récupération des données',
            'Erreur ');
        },
        () => {
          this.loading = false;
        },
      );
  }


  /**
   * Ajouter une mailing liste
   * @param form
   */
  ajouterMailingList(form: NgForm) {
    this.loggerService.info('Ajouter une mailing liste ');
    this.submitted = true;

    const mailingListeUtilitaire: MailingListeUtilitaire = {};
    mailingListeUtilitaire.id = Date.now();
    mailingListeUtilitaire.libelle = this.libelleMailListForm;
    if (this.visibilitePublicMailList) {
      mailingListeUtilitaire.idAuthority = 2;
    } else {
      mailingListeUtilitaire.idAuthority = 4;
    }
    mailingListeUtilitaire.nbInscrit = 0;

    this.listeDeDiffusionServiceUtilitaire.addListe({idListe: mailingListeUtilitaire.id, body: mailingListeUtilitaire})
      .subscribe(
        (data) => {
          this.loggerService.debug(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.submitted = false;
          this.toastrService.danger(
            'Erreur technique lors de ajout de la mailing liste',
            'Erreur ');
        },
        () => {
          this.submitted = false;
        },
      );

    this.listeDiffusion.push(mailingListeUtilitaire);

    this.libelleMailListForm = null;
  }

  /**
   * Supprimer une mailing list
   * @param id
   */
  supprimerMailingList(id: number) {
    this.loggerService.info('Suppression de la mailing liste ' + id);

    this.listeDeDiffusionServiceUtilitaire.delListe({idListe: id})
      .subscribe(
        (data) => {
          this.loggerService.debug(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de la suppression da la liste',
            'Erreur ');
        },
        () => {

        },
      );

    // Supression de la mailing liste pour affichage
    this.listeDiffusion.forEach((value, index, array) => {
      if (value.id === id) {
        this.listeDiffusion.splice(index, 1);
      }
    });

  }

  /**
   * Naviger vers la page des inscrits
   * @param id
   */
  goVoirInscrit(id: number) {
    localStorage.setItem('id_mailinglist_selected', String(id));
    return this.router.navigateByUrl('pages/gpdiffusions/tabs/lstinscrit');
  }

  /**
   * CHanger la visibilité de la mailing liste
   * @param $event
   */
  changerVisibiliteMailingList(event: any) {
    if (event.target.checked) {
      this.visibilitePublicMailList = true;
    } else {
      this.visibilitePublicMailList = false;
    }

  }
}
