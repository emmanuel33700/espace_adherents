import {Component, HostBinding, OnInit, ViewChild} from '@angular/core';
import {RolesService} from '../../../../../api/generated/authorization/services/roles.service';
import {Adherent} from '../../../../../api/generated/adherents/models/adherent';
import {NbToastrService} from '@nebular/theme';
import {LoggerService} from '../../../../@core/utils/logger.service';
import {Observable, of} from 'rxjs';
import {map} from 'rxjs/operators';
import {ListingAdherentService} from '../../../../../api/generated/adherents/services/listing-adherent.service';
import {LiensAdherentsService} from '../../../../../api/generated/adherents/services/liens-adherents.service';
import {NgForm} from '@angular/forms';


interface ListeAdherentsOption {
  nomPrenom: string;
  idAdh: number;
}

@Component({
  selector: 'ngx-tab2',
  styleUrls: ['./liensadherent.component.scss'],
  templateUrl: './liensadherent.component.html',
})

export class LiensadherentComponent implements OnInit {

  public idAdherent: number = 0;
  adherent: Adherent;

  // Toaster
  private index: number = 0;
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster


  listeAdherentsOption: ListeAdherentsOption[] = [];
  filteredlisteAdherentsOptions$: Observable<ListeAdherentsOption[]>;
  disabledValidateBouton: boolean = true;


  lstAdhRepresente: {
    'idAdhRepresentant'?: number, 'idAdhRepresente'?: number, 'nomAdhRepresente'?: string,
    'prenomAdhRepresente'?: string, 'emailAdhRepresente'?: string,
  }[] = [];

  @ViewChild('autoInput') input;


  constructor(
    private listingAdherentService: ListingAdherentService,
    private liensAdherentsService: LiensAdherentsService,
    private rolesService: RolesService,
    private toastrService: NbToastrService,
    private loggerService: LoggerService,
  ) {
  }

  /**
   *
   */
  ngOnInit(): void {

    this.adherent = JSON.parse(localStorage.getItem('adh_selected'));
    this.idAdherent = Number(localStorage.getItem('id_adh_selected'));

    this.loggerService.info('id adh recupere ' + this.idAdherent);

    let adherents: Adherent[];

    this.listingAdherentService.getListeAdherents({})
      .subscribe(
        (data) => {
          adherents = data;
          this.loggerService.debug(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(error);
          this.toastrService.danger(
            'Erreur technique lors de recuperation des données',
            'Erreur ');
        },
        () => {
          this.loggerService.debug('fini');

          adherents.forEach((value, index) => {
            const nomPrenomConst: string = value.nom + ' ' + value.prenom;
            this.listeAdherentsOption.push({nomPrenom: nomPrenomConst, idAdh : value.id});
          });
        });

        this.initLiensAdherents();

  }

  /**
   * Initialisation des liens entres adhérents
   */
  initLiensAdherents() {
    this.liensAdherentsService.getLienAdherent({idadh: this.idAdherent})
      .subscribe(
        (data) => {
          this.lstAdhRepresente = data;
        },
        (error) => {
          this.loggerService.info('Pas de lien');
          this.toastrService.danger(
            'Erreur technique lors de recuperation des données',
            'Erreur ');
        },
        () => {
          this.loggerService.info('fini');
        });
  }

  private filter(value: string): ListeAdherentsOption[] {
    const filterValue = value.toLowerCase();
    // return this.options.filter(optionValue => optionValue.toLowerCase().includes(filterValue));
    return this.listeAdherentsOption.filter(optionValue => optionValue.nomPrenom.toLowerCase().includes(filterValue));

  }

  getFilteredOptions(value: string): Observable<ListeAdherentsOption[]> {
    return of(value).pipe(
      map(filterString => this.filter(filterString)),
    );
  }

  onChange() {
    this.filteredlisteAdherentsOptions$ = this.getFilteredOptions(this.input.nativeElement.value);
  }

  onSelectionChange($event) {
    this.filteredlisteAdherentsOptions$ = this.getFilteredOptions($event);
    this.loggerService.info('onSelectionChange');
    this.disabledValidateBouton = false;
  }

  /**
   * VAlider l'ajout d'un lien entre adhérents
   */
  validerAjoutLienAdherent(form: NgForm) {
    this.disabledValidateBouton = true;

    this.filteredlisteAdherentsOptions$.forEach((value) => {
      this.liensAdherentsService.ajoutLienAdherent({idadh : this.idAdherent, idAdhLien : value[0].idAdh})
        .subscribe(
          (data) => {
            this.loggerService.debug(JSON.stringify(data));
          },
          (error) => {
            this.loggerService.error(JSON.stringify(error));
            this.toastrService.danger(
              'Erreur technique lors de la supression',
              'Erreur ');
          },
          () => {
            this.loggerService.debug(' fini');
            this.initLiensAdherents();
            form.resetForm();
          },
        );
    });


  }

  /**
   * Supprimer liens entre adhérnts
   * @param idAdhRepresente
   * @param idAdhRepresentant
   */
  supprimerLien(idAdhRepresente: number, idAdhRepresentant: number) {

    this.liensAdherentsService.deleteLienAdherent({idadh : idAdhRepresentant, idAdhLien : idAdhRepresente})
      .subscribe(
        (data) => {
          this.loggerService.debug(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de la supression',
            'Erreur ');
        },
        () => {
          this.loggerService.debug('Suppression fini');
          this.initLiensAdherents();
        },
      );

  }
}
