import {Component, HostBinding, OnInit} from '@angular/core';
import {FormBuilder, NgForm} from '@angular/forms';
import {RolesService} from '../../../../../api/generated/authorization/services/roles.service';
import {Roles} from '../../../../../api/generated/authorization/models/roles';
import {Adherent} from '../../../../../api/generated/adherents/models/adherent';
import { NbToastrService } from '@nebular/theme';
import {LoggerService} from '../../../../@core/utils/logger.service';

@Component({
  selector: 'ngx-tab2',
  styleUrls: ['./connexion.component.scss'],
  templateUrl: './connexion.component.html',
})
export class ConnexionComponent implements OnInit {

  public idAdherent: number = 0;
  adherent: Adherent;
  roles: Roles;
  user: any = {};
  roleStringJzon: string;

  errors: string[] = [];
  messages: string[] = [];
  submitted = false;

  // Toaster
  private index: number = 0;
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  // indicateur de chargement
  loading = true;

  // Indicateur de role affecté à l'adhérent
  aRole: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
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

    this.rolesService.getRoles({idadh: this.idAdherent}).subscribe(
      (data) => {
        this.roles = data;
      },
      (error) => {
        this.loggerService.error(error);
      },
      () => {

        if (this.isEmptyUnderkill(this.roles.roles)) {
          this.loggerService.info('Pas de role pour cet utilisateur');
          this.aRole = false;
          this.loading = false;
        } else {
          this.loggerService.info('role recuperer par API ' + JSON.stringify(this.roles));
          this.roleStringJzon = this.getClaims(this.roles);

          if (this.roleStringJzon.includes('ADMIN')) {
            this.user.role = 'ADMIN';
          } else if (this.roleStringJzon.includes('BUREAU')) {
            this.user.role = 'BUREAU';
          } else if (this.roleStringJzon.includes('CONSEIL')) {
            this.user.role = 'CONSEIL';
          } else {
            this.user.role = 'ADHERENT';
          }
          this.user.email = this.adherent.email;

          this.aRole = true;
          this.loading = false;
        }


      });

  }


  /**
   * Vérifier si la liste est vide
   * @param obj
   */
  private isEmptyUnderkill(obj: any): boolean {
    return Object.keys(obj).length === 0;
  }

  /**
   * @param roles
   */
  getClaims(roles: Roles): string {
    const obj = JSON.parse(JSON.stringify(roles.roles));
    return obj;
  }

  /**
   *
   * @param form
   */
  submit(form: NgForm) {

    // Initialisation des variables
    this.submitted = true;
    this.errors = [];
    this.messages = [];


    this.loggerService.info('role saisie ' + this.user.role);
    if (this.user.role === 'ADMIN') {
      this.roles.roles = ['ADMIN', 'CONSEIL', 'ADHERENT', 'BUREAU'];
    } else if (this.user.role === 'BUREAU') {
      this.roles.roles = ['CONSEIL', 'ADHERENT', 'BUREAU'];
    } else if (this.user.role === 'CONSEIL') {
      this.roles.roles = ['CONSEIL', 'ADHERENT'];
    } else {
      this.roles.roles = ['ADHERENT'];
    }
    this.roles.login = this.adherent.email;

    this.rolesService.updateRoles({idadh: this.idAdherent, body: this.roles})
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(error);
          this.toastrService.danger(
            'Erreur technique lors de enregistrement',
            'Erreur ');
          this.submitted = false;
        },
        () => {
          this.loggerService.info('MàJ OK');
          this.toastrService.success(
            'Mise à jour finalisée',
            'Opértation réussit');
          this.submitted = false;
        },
      );
  }

}
