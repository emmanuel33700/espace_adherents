import {Component, NgModule, OnInit} from '@angular/core';
import { Routes, Router, RouterModule } from '@angular/router';
import {ListingAdherentService} from '../../../../api/generated/services/listing-adherent.service';


import {  TabsComponent } from '../tabs/tabs.component';
import {Adherent} from '../../../../api/generated/models';

const routes: Routes = [{
  path: 'tabs',
  component: TabsComponent,
}];


@Component({
  selector: 'ngx-list',
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
  ) {}



  ngOnInit(): void {
    this.listingAdherentService.getListeAdherents( {})
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


  sectionAdherents(id: number) {
    localStorage.setItem('id_adh_selected', String(id));
    return this.router.navigateByUrl('pages/adhsaison/tabs/infosadh');
  }
}
