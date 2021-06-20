import {Component, OnInit, HostBinding} from '@angular/core';
import {NbToastrService} from '@nebular/theme';
import {LoggerService} from '../../../@core/utils/logger.service';


@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./relationsadherent.component.scss'],
  templateUrl: './relationsadherent.component.html',
})
export class RelationsadherentComponent implements OnInit {


  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  // indicateur de chargement
  loading = true;



  constructor(
    private toastrService: NbToastrService,
    private loggerService: LoggerService,
  ) {}

  /**
   * Initialisation du formulaire
   */
  ngOnInit(): void {


  }



}
