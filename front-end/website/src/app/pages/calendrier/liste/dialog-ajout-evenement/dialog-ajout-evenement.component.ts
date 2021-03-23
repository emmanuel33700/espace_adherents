import {Component, HostBinding, Input, OnInit} from '@angular/core';
import {NbDialogRef, NbToastrService} from '@nebular/theme';
import {DateSelectArg} from '@fullcalendar/angular';
import {Evenement} from '../../../../../api/generated/utilitaire/models/evenement';
import {DateService, LoggerService} from '../../../../@core/utils';
import {AgendaService} from '../../../../../api/generated/utilitaire/services/agenda.service';
import { NgForm} from '@angular/forms';

@Component({
  selector: 'ngx-showcase-dialog',
  templateUrl: 'dialog-ajout-evenement.component.html',
  styleUrls: ['dialog-ajout-evenement.component.scss'],
})
export class DialogAjoutEvenementComponent implements OnInit {

  errors: string[] = [];
  messages: string[] = [];
  submitted = false;

  evenementForm: any = {};

  // Toaster
  @HostBinding('class')
  classes = 'example-items-rows';
  // fin toaster

  @Input() dateDebut: string;
  @Input() dateFin: string;
  @Input() selectInfo: DateSelectArg;

  constructor(protected ref: NbDialogRef<DialogAjoutEvenementComponent>,
              private loggerService: LoggerService,
              private toastrService: NbToastrService,
              private agendaService: AgendaService,
              private dateService: DateService,
  ) {
  }


  /**
   * Initialisation du formulaire
   */
  ngOnInit(): void {
    this.evenementForm.dateDebut = this.dateService.dateFormatForPrint(this.dateDebut);
    this.evenementForm.heureDebut = this.dateService.heureFormatForPrint(this.dateDebut);
    this.evenementForm.dateFin = this.dateService.dateFormatForPrint(this.dateFin);
    this.evenementForm.heureFin = this.dateService.heureFormatForPrint(this.dateFin);
    this.evenementForm.titre = '';
    this.evenementForm.description = '';
    this.evenementForm.demandeEnvoyerMail = false;
  }


  /**
   * Ajouter un évènememnt
   * @param form
   */
  ajouterEvenement(form: NgForm) {
    this.loggerService.error('Validation du formulaire');

    const calendarApi = this.selectInfo.view.calendar;

    calendarApi.unselect(); // clear date selection
    const evenement: Evenement = {};
    evenement.id = Date.now();
    evenement.detail = this.evenementForm.description;
    evenement.description = this.evenementForm.titre;
    evenement.datedebut = this.dateService.convertISODate(this.evenementForm.dateDebut, this.evenementForm.heureDebut);
    evenement.datefin = this.dateService.convertISODate(this.evenementForm.dateFin, this.evenementForm.heureFin);
    evenement.type = 1;

    this.agendaService.addEvenement({body: evenement})
      .subscribe(
        (data) => {
          this.loggerService.info(JSON.stringify(data));
        },
        (error) => {
          this.loggerService.error(JSON.stringify(error));
          this.toastrService.danger(
            'Erreur technique lors de enregistrement',
            'Erreur ');
        },
        () => {
          this.loggerService.info('Enregistrement fini');
        },
      );

    calendarApi.addEvent({
      id: String(evenement.id),
      title: this.evenementForm.titre,
      start: this.dateService.convertISODate(this.evenementForm.dateDebut, this.evenementForm.heureDebut),
      end: this.dateService.convertISODate(this.evenementForm.dateFin, this.evenementForm.heureFin),
      allDay: this.selectInfo.allDay,
    });


    this.ref.close();
  }
}
