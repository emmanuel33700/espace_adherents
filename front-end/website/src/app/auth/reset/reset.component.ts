import {LoggerService} from '../../@core/utils/logger.service';
import {Component, HostBinding, Inject, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {
  NB_AUTH_OPTIONS,
} from '@nebular/auth';
import {AuthentificationService} from '../../../api/generated/authorization/services/authentification.service';
import {ReinitAuthentification} from '../../../api/generated/authorization/models/reinit-authentification';
import {NbToastrService} from '@nebular/theme';


@Component({
    selector: 'ngx-register',
    styleUrls: ['./reset.component.scss'],
    templateUrl: './reset.component.html',
    standalone: false
})
export class ResetComponent implements OnInit {

  showMessages: any = {};

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';


  submitted = false;
  errors: string[] = [];
  messages: string[] = [];


  private reinitAuthentification: ReinitAuthentification = {};
  idAdh: number = 0;

  user: any = {};

  constructor(private loggerService: LoggerService,
              @Inject(NB_AUTH_OPTIONS) protected options = {},
              protected router: Router,
              private route: ActivatedRoute,
              private authentificationService: AuthentificationService,
              private toastrService: NbToastrService) {

  }

  /**
   * Valider la demande de changement de mot de passe
   */
  register(): void {
    localStorage.clear();
    this.errors = this.messages = [];
    this.submitted = true;

    this.reinitAuthentification.password =  this.user.password;


    this.authentificationService.valideResetPassword({idadh: this.idAdh, body: this.reinitAuthentification})
      .subscribe(
        (data) => {
        },
        (error) => {
          this.loggerService.error(error);
          this.toastrService.danger(
            'Erreur technique lors de enregistrement',
            'Erreur ');
          this.submitted = false;
        },
        () => {
          return this.router.navigateByUrl('/auth/login');
        },
      );

  }


  ngOnInit(): void {
    this.reinitAuthentification.cleeValidation = this.route.snapshot.queryParamMap.get('key');
    if (!isNaN(Number(this.route.snapshot.queryParamMap.get('id')))) {
      this.idAdh = Number(this.route.snapshot.queryParamMap.get('id'));
    } else {
      this.idAdh = -1;
      this.loggerService.error('Erreur de récupération de id adherent ');
    }
  }


}
