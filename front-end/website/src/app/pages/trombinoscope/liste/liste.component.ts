import {Component, OnInit} from '@angular/core';
import {Adherent} from '../../../../api/generated/adherents/models/adherent';
import {ListingAdherentService} from '../../../../api/generated/adherents/services/listing-adherent.service';
import {environment} from '../../../../environments/environment';

@Component({
  selector: 'ngx-list',
  templateUrl: 'liste.component.html',
  styleUrls: ['liste.component.scss'],
})
export class ListeComponent implements OnInit {

  adherents: Adherent[] = [];

  url_photo_profil: string = environment.url_photo_profil;

  constructor(
    private listingAdherentService: ListingAdherentService,
  ) {}



  ngOnInit(): void {
    this.listingAdherentService.getListeAdherentsFiltreSaison( {})
      .subscribe(
        (data) => {
          this.adherents = data;
          console.info(data);
        },
        (error) => {
          console.error(error);
        },
        () => {
          console.info('fini');
        });
  }


}
