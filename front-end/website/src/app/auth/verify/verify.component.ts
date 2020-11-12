/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthentificationService} from '../../../api/generated/authorization/services/authentification.service';
import {Validation} from '../../../api/generated/authorization/models/validation';


@Component({
  selector: 'ngx-verify',
  styleUrls: ['./verify.component.scss'],
  templateUrl: './verify.component.html',
})
export class VerifyComponent implements OnInit {

  private validation: Validation = {};
  private token: string = '';
  private idAdherent: number = 0;
  errors: string[] = [];
  messages: string[] = [];
  submitted = false;


  constructor( protected router: Router,
              private route: ActivatedRoute,
              private authentificationService: AuthentificationService) {


  }



  ngOnInit(): void {
    this.token = this.route.snapshot.queryParamMap.get('token');
    if (!isNaN(Number(this.route.snapshot.queryParamMap.get('id')))) {
      this.idAdherent = Number(this.route.snapshot.queryParamMap.get('id'));
      console.info('id adherent' + this.idAdherent);
    } else {
      this.idAdherent = -1;
      this.errors = ['Erreur sur l url'];
      console.error('Erreur de récupération de id adherent ');
    }

    this.validation.cleeValidation = this.token;

    this.authentificationService.validationAuthentification({idadh: this.idAdherent, body: this.validation})
      .subscribe(
        (data) => {
          console.info(data);
        },
        (error) => {
          this.errors = ['Erreur sur la validation du compte. Contacter un administrateur'];
          console.error(error);
        },
        () => {
          this.messages = ['Votre compte est maintenant actif'];
          console.info('fini');
        },
      );

  }

  goLogin() {
    return this.router.navigateByUrl('/auth/login');
  }
}
