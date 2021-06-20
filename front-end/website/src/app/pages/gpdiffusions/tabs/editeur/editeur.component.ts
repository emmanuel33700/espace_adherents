import {Component, OnInit} from '@angular/core';
import {LoggerService} from '../../../../@core/utils';
import {NbToastrService} from '@nebular/theme';

@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./editeur.component.scss'],
  templateUrl: './editeur.component.html',
})
export class EditeurComponent implements OnInit {


  // indicateur de chargement
  loading = true;

  role: string;

  droitcomplet = false;


  constructor(
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
  ) {
  }


  ngOnInit(): void {
    this.role = localStorage.getItem('ROLE');

    // tester si la personne à le droit d'éditer la page
    if ((this.role === 'ADMIN') || (this.role === 'CONSEIL') ) {
      this.droitcomplet = true;
    }
  }
}



