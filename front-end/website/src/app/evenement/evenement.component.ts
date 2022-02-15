import {Component, HostBinding, OnInit} from '@angular/core';
import {ManifestationService} from '../../api/generated/adherents/services/manifestation.service';
import {ActivatedRoute, Router} from '@angular/router';
import {LoggerService} from '../@core/utils';
import {NbToastrService} from '@nebular/theme';
import {ParticipationManifestationAccesDirect} from '../../api/generated/adherents/models/participation-manifestation-acces-direct';



@Component({
  selector: 'ngx-register',
  styleUrls: ['./evenement.component.scss'],
  templateUrl: './evenement.component.html',
})
export class EvenementComponent  implements OnInit {

  showMessages: any = {};


  submitted = false;
  errors: string[] = [];
  messages: string[] = [];

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster


  constructor(
    private manifestationService: ManifestationService,
    private route: ActivatedRoute,
    protected router: Router,
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
  ) {
  }




  ngOnInit(): void {
    localStorage.clear();
    const mailAdh = this.route.snapshot.queryParamMap.get('mailadh');
    const idAdh = Number(this.route.snapshot.queryParamMap.get('idadh'));
    const idManif = Number(this.route.snapshot.queryParamMap.get('idevt'));
    const participeEvt = this.route.snapshot.queryParamMap.get('participation');


    const participationManifestation: ParticipationManifestationAccesDirect = {};
    if (participeEvt === 'TRUE') {
      participationManifestation.statutParticipation = 1;
    } else {
      participationManifestation.statutParticipation = 2;

    }
    participationManifestation.emailadherent = mailAdh;


    this.manifestationService.ajoutManifestationAdherentAccesDirect({
      idadh: idAdh
      , idManifestation: idManif
      , body: participationManifestation})
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
          this.loggerService.debug('Enregistrement fini');
        },
      );

  }


}
