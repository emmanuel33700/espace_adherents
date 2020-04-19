import {Component, OnInit} from '@angular/core';
import {Adherent} from '../../../../api/generated/models/adherent';
import {ListingAdherentService} from '../../../../api/generated/services/listing-adherent.service';

@Component({
  selector: 'ngx-list',
  templateUrl: 'liste.component.html',
  styleUrls: ['liste.component.scss'],
})
export class ListeComponent implements OnInit {

  adherents: Adherent[] = [];

  constructor(
    private listingAdherentService: ListingAdherentService,
  ) {}



  ngOnInit(): void {
    // TODO Attention, appel de service à charger
    this.listingAdherentService.getListeAdherentsSaison( {})
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
