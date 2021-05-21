import {Injectable, Injector} from '@angular/core';
import {NbAuthService, NbAuthToken} from '@nebular/auth';
import {LoggerService} from './logger.service';

@Injectable()
export class TokenService {
  constructor(private authService: NbAuthService,
              private loggerService: LoggerService,
              private injector: Injector) {
  }

  /**
   * Récupérer l'id de l'adhérent dans le token
   */
  getIdAdherent(): number {
    let idAdh: number = 0;
    this.authService.getToken()
      .subscribe((token: NbAuthToken) => {
        if (token && token.getValue()) {
          const obj = JSON.parse(token.getValue());
          this.loggerService.debug('id adhérent de la personne connectée ' + obj.idAdherent);
          idAdh = Number(obj.idAdherent);
        }
      });

    return idAdh;
  }

  /**
   * récupérer le role de l'adhérent
   */
  getRoleAdherent(): string {
    let role: string = 'ADHERENT';
    let roles: string = 'ADHERENT';
    this.authService.getToken()
      .subscribe((token: NbAuthToken) => {
        if (token && token.getValue()) {
          const obj = JSON.parse(token.getValue());
          this.loggerService.debug('groupe de la personne connectée ' + obj.authorities);
          roles = obj.authorities;
        }
      });

    if (roles.includes('ADMIN')) {
      role = 'ADMIN';
    } else if (roles.includes('BUREAU')) {
      role = 'BUREAU';
    } else if (roles.includes('CONSEIL')) {
      role = 'CONSEIL';
    } else {
      role = 'ADHERENT';
    }

    return role;
  }

}
