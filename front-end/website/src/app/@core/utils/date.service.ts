import {Injectable} from '@angular/core';

@Injectable()
export class DateService {

  /**
   * Transforme un date au format date en format ISO YYY-mm-dd
   * @param date
   */
  convertDateToStringIsoWithOutHour(date: Date) {
    return date.getFullYear()
      + '-' + ((date.getMonth() + 1).toString()).padStart(2, '0')
      + '-' + (date.getDate().toString()).padStart(2, '0');
  }

  /**
   * Concertion d'une date au format dd/mm/YYYY en format iso
   * @param date
   */
  convertIsoDate(date: string) {
    if (this.isEmpty(date)) {
      return null;
    }
    const darr = date.split('/');
    const dobj = new Date(Date.UTC(parseInt(darr[2], 10), parseInt(darr[1], 10) - 1, parseInt(darr[0], 10), 0, 0, 0));
    return dobj.toISOString();
  }

  /**
   * Transformation d'une date au  format ISO en date DD/MM/YYYY
   * @param dateStringISO
   */
  dateFormatForPrint(dateStringISO: string) {
    if (this.isEmpty(dateStringISO)) {
      return null;
    }
    const date: Date = new Date(dateStringISO);
    return (date.getDate().toString()).padStart(2, '0')
      + '/' + ((date.getMonth() + 1).toString()).padStart(2, '0') + '/' + date.getFullYear();
  }

  /**
   * Convertion d'une date au format iso en heure au format hh:mm
   * @param dateStringISO
   */
  heureFormatForPrint(dateStringISO: string) {
    if (this.isEmpty(dateStringISO)) {
      return null;
    }
    const date: Date = new Date(dateStringISO);
    return (date.getHours().toString()).padStart(2, '0') + ':' + (date.getMinutes().toString()).padStart(2, '0') ;
  }

  /**
   *  Convertion d'une date et heure au format ISO
   * @param date au format dd/mm/yyyy
   * @param heure au format hh:mm
   */
  convertISODate(sdate: string, heure: string) {
    if (this.isEmpty(sdate)) {
      return null;
    }
    const darr = sdate.split('/');
    const harr = heure.split(':');


    const date = new Date(
      parseInt(darr[2], 10)
      , parseInt(darr[1], 10) - 1
      , parseInt(darr[0], 10)
      , parseInt(harr[0], 10)
      , parseInt(harr[1], 10)
      , 0);


    return date.getFullYear()
      + '-' + ((date.getMonth() + 1).toString()).padStart(2, '0')
      + '-' + (date.getDate().toString()).padStart(2, '0')
      + 'T'
      + (date.getHours().toString()).padStart(2, '0')
      + ':' + (date.getMinutes().toString()).padStart(2, '0')
      + ':' + (date.getSeconds().toString()).padStart(2, '0')
      + this.getTimeZone();
  }

  getTimeZone() {
    const offset = new Date().getTimezoneOffset(), o = Math.abs(offset);
    return (offset < 0 ? '+' : '-') + ('00' + Math.floor(o / 60)).slice(-2) + ':' + ('00' + (o % 60)).slice(-2);
  }

  private  isEmpty(str) {
    return (!str || 0 === str.length);
  }

  private  isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }

}
