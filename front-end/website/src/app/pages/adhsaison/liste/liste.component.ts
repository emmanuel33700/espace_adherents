import {Component, HostBinding, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ListingAdherentService} from '../../../../api/generated/adherents/services/listing-adherent.service';
import {LoggerService} from '../../../@core/utils/logger.service';
import {Adherent} from '../../../../api/generated/adherents/models';
import {NbToastrService} from '@nebular/theme';
import {AdherentService} from '../../../../api/generated/adherents/services/adherent.service';


@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./liste.component.scss'],
  templateUrl: './liste.component.html',
})


export class ListeComponent implements OnInit {


  adherents: Adherent[] = [];
  adherentsListeComplete: Adherent[] = [];
  isAdherentSaison: boolean;
  adherentSelected: Adherent = {};

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';

  // fin toaster

  constructor(
    private listingAdherentService: ListingAdherentService,
    private router: Router,
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
    private adherentService: AdherentService,
  ) {
  }


  ngOnInit(): void {
    this.isAdherentSaison = false;
    this.listingAdherentService.getListeAdherents({})
      .subscribe(
        (data) => {
          this.adherents = data;
          this.adherentsListeComplete = data;
          this.loggerService.info(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(error);
          this.toastrService.danger(
            'Erreur technique lors de recuperation des données',
            'Erreur ');
        },
        () => {
          this.loggerService.debug('fini');
        });
  }


  /**
   * Sélection d'un adhérent pour affichage détail
   * @param id
   */
  sectionAdherents(id: number) {
    localStorage.setItem('id_adh_selected', String(id));

    this.adherentService.getAdherent({
      idadh: id,
    }).subscribe(
      (data) => {
        this.adherentSelected = data;
      },
      (error) => {
        this.loggerService.error(error);
      },
      () => {
        this.loggerService.info('adherent recupe api ' + JSON.stringify(id));
        localStorage.setItem('adh_selected', JSON.stringify(this.adherentSelected));
        return this.router.navigateByUrl('pages/adhsaison/tabs/infosadh');
      });
  }

  /**
   * Selection d'un adhérent pour afficher la page d'ajout d'une adhésion
   * @param id
   */
  ajouterAdhesion(id: number) {
    localStorage.setItem('id_adh_selected', String(id));
    this.adherentService.getAdherent({
      idadh: id,
    }).subscribe(
      (data) => {
        this.adherentSelected = data;
      },
      (error) => {
        this.loggerService.error(error);
      },
      () => {
        this.loggerService.info('adherent recupe api ' + JSON.stringify(id));
        localStorage.setItem('adh_selected', JSON.stringify(this.adherentSelected));
        return this.router.navigateByUrl('pages/adhsaison/ajouteradhesion');
      });
  }

  /**
   * Changer de position le toogle pour afficher ou non les adhérents de la saison courante
   * @param $event
   */
  changeListe($event: Event) {
    this.loggerService.info('TOTOOT');
    this.isAdherentSaison = !this.isAdherentSaison;

    if (this.isAdherentSaison) {
      this.loggerService.info('Recupérer les adherents de la saison');
      this.listingAdherentService.getListeAdherentsSaison({})
        .subscribe(
          (data) => {
            this.adherents = data;
            this.adherentsListeComplete = data;
            this.loggerService.info(JSON.stringify(data));
          },
          (error) => {
            this.loggerService.error(error);
            this.toastrService.danger(
              'Erreur technique lors de recuperation des données',
              'Erreur ');
          },
          () => {
            this.loggerService.debug('fini');
          });
    } else {
      this.loggerService.info('Recupérer l\'ensemble des adherents');
      this.listingAdherentService.getListeAdherents({})
        .subscribe(
          (data) => {
            this.adherents = data;
            this.adherentsListeComplete = data;
            this.loggerService.info(JSON.stringify(data));
          },
          (error) => {
            this.loggerService.error(error);
            this.toastrService.danger(
              'Erreur technique lors de recuperation des données',
              'Erreur ');
          },
          () => {
            this.loggerService.debug('fini');
          });
    }
  }

  /**
   * Rechercher les adhérents
   * @param $event
   */
  rechercherAdherent($event: Event) {
    const target = $event.target as HTMLButtonElement;
    if (target) {

      const textRecherche: string = target.value;
      this.loggerService.info('adherent recherchés : ' + target.value);

      this.adherents = [];
      this.adherentsListeComplete.forEach((value, index) => {
          if ((value.nom != null && value.nom.toLowerCase().includes(textRecherche.toLowerCase()))
            || (value.prenom != null && value.prenom.toLowerCase().includes(textRecherche.toLowerCase()))
            || (value.email != null && value.email.toLowerCase().includes(textRecherche.toLowerCase()))
            || String(value.id).includes(textRecherche.toLowerCase())
            || (value.ville != null && value.ville.toLowerCase().includes(textRecherche.toLowerCase()))) {
            this.adherents.push(value);
          }
        },
      );
    }

  }
}
