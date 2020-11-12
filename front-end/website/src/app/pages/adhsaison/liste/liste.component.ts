import {Component, NgModule, OnInit} from '@angular/core';
import { Routes, Router, RouterModule } from '@angular/router';
import {ListingAdherentService} from '../../../../api/generated/adherents/services/listing-adherent.service';
import {LoggerService} from '../../../@core/utils/logger.service';

import {  TabsComponent } from '../tabs/tabs.component';
import {Adherent} from '../../../../api/generated/adherents/models';

export const routes: Routes = [{
  path: 'tabs',
  component: TabsComponent,
}];


@Component({
  selector: 'ngx-components',
  templateUrl: 'liste.component.html',
  styleUrls: ['liste.component.scss'],
})
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ListeComponent implements OnInit {



  adherents: Adherent[] = [];

  constructor(
    private listingAdherentService: ListingAdherentService,
    private router: Router,
    private loggerService: LoggerService,
  ) {}



  ngOnInit(): void {
    this.listingAdherentService.getListeAdherents( {})
      .subscribe(
        (data) => {
          this.adherents = data;
          this.loggerService.info(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(error);
        },
        () => {
          this.loggerService.debug('fini');
        });
  }


  sectionAdherents(id: number) {
    localStorage.setItem('id_adh_selected', String(id));
    return this.router.navigateByUrl('pages/adhsaison/tabs/infosadh');
  }
}
