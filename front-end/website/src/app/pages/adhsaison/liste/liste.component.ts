import {Component, HostBinding, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ListingAdherentService} from '../../../../api/generated/adherents/services/listing-adherent.service';
import {LoggerService} from '../../../@core/utils/logger.service';
import {Adherent} from '../../../../api/generated/adherents/models';
import {Adherent2} from '../../../../api/generated/adherents/models';
import {NbToastrService} from '@nebular/theme';
import {AdherentService} from '../../../../api/generated/adherents/services/adherent.service';
import {environment} from '../../../../environments/environment';
import {CsvdataService} from '../../../@core/utils/csvdata.service';



interface Adherentexport {
  adhesionsSaisonCourante?: boolean;
  adhesionsSaisonPrecedente?: boolean;
  adresse1?: string;
  adresse2?: string;
  civilite?: 'Mr' | 'Mme';
  codePostal?: string;
  commentaire?: string;
  dateEnregistrement?: string;
  dateMiseAJour?: string;
  dateNaissance?: string;
  email?: string;
  id?: number;
  nom?: string;
  prenom?: string;
  publicContact?: boolean;
  telMaison?: string;
  telPortable?: string;
  telTravail?: string;
  ville?: string;


  cheque?: boolean;
  comptaBanque?: string;
  comptaNumCheque?: string;
  comptaSomme?: number;
  espece?: boolean;
  idadhesion?: number;
  idAnneeAdhesion?: number;

  /**
   * Types d'adhersions. Valeurs possibles :
   * * 1 : ADULTE
   * * 2 : FAMILLE
   * * 3 : RESPONSABLE DE FAMILLE
   * * 4 : ENFANT
   * * 5 : BIENFAITEUR
   * * 6 : HONNEUR
   * * 7 : ETUDIANT
   * * 8 : DEMANDEUR EMPLOI
   */
  idTypeAdhesion?: 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8;
  libelleAnneeAdhesion?: string;
}

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
  url_photo_profil: string = environment.url_photo_profil;


  // Utilisé uniquement pour l'export csv de la saison courante
  adherents2: Adherent2[] = [];
  adherentexport: Adherentexport[] = [];

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
    private csvdataService: CsvdataService,
  ) {
  }


  ngOnInit(): void {
    this.isAdherentSaison = true;
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

  /**
   * Récupérer le fichier CSV
   */
  recupererCSV() {

    let nomFichier: string;
    if (this.isAdherentSaison) {
      nomFichier = 'export_adherent_de_la_saison';
      this.listingAdherentService.getListeAdherentsSaison2({})
        .subscribe(
          (data) => {
            this.adherents2 = data;
            this.adherents2.forEach((value, index) => {
                  this.adherentexport[index] = {
                    id: value.adherent.id,
                    civilite: value.adherent.civilite,
                    nom: value.adherent.nom,
                    prenom: value.adherent.prenom,
                    email: value.adherent.email,
                    adresse1: value.adherent.adresse1,
                    adresse2: value.adherent.adresse2,
                    codePostal: value.adherent.codePostal,
                    ville: value.adherent.ville,
                    adhesionsSaisonCourante: value.adherent.adhesionsSaisonCourante,
                    adhesionsSaisonPrecedente: value.adherent.adhesionsSaisonPrecedente,
                    telMaison : value.adherent.telMaison,
                    telPortable: value.adherent.telPortable,
                    dateEnregistrement: value.adherent.dateEnregistrement,
                    dateMiseAJour: value.adherent.dateMiseAJour,
                    dateNaissance: value.adherent.dateNaissance,


                    idadhesion: value.adhesion.id,
                    idTypeAdhesion: value.adhesion.idTypeAdhesion,
                    cheque: value.adhesion.cheque,
                    espece: value.adhesion.espace,
                    comptaSomme: value.adhesion.comptaSomme,
                    comptaBanque: value.adhesion.comptaBanque,
                    comptaNumCheque: value.adhesion.comptaNumCheque,
                    libelleAnneeAdhesion: value.adhesion.libelleAnneeAdhesion,
                  };
            });
          },
          (error) => {
            this.loggerService.error(error);
            this.toastrService.danger(
              'Erreur technique lors de recuperation des données',
              'Erreur ');
          },
          () => {
            this.loggerService.debug('fini');
            this.csvdataService.exportToCsv(nomFichier, this.adherentexport);
          });
    } else {
      nomFichier = 'export_complet_adherent';
      this.csvdataService.exportToCsv(nomFichier, this.adherentsListeComplete);
    }

  }
}
