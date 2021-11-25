import {Component, OnInit} from '@angular/core';
import {Adherent} from '../../../../api/generated/adherents/models/adherent';
import {ListingAdherentService} from '../../../../api/generated/adherents/services/listing-adherent.service';

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
