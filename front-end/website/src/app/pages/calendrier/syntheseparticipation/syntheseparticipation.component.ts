import {Component, OnInit} from '@angular/core';
import {LoggerService} from '../../../@core/utils/logger.service';
import {NbToastrService} from '@nebular/theme';


@Component({
  selector: 'ngx-form-layouts',
  styleUrls: ['./syntheseparticipation.component.scss'],
  templateUrl: './syntheseparticipation.component.html',
})
export class SyntheseparticipationComponent implements OnInit {


  // indicateur de chargement
  loading = true;

  constructor(
    private loggerService: LoggerService,
    private toastrService: NbToastrService,
  ) {
  }



  ngOnInit(): void {

  }




}
