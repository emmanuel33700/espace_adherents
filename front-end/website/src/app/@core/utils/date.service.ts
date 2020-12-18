import { Injectable } from '@angular/core';

@Injectable()
export class DateService {


   convertIsoDate(date: string) {
    if (this.isEmpty(date)) {
      return null;
    }
    const darr = date.split('/');
    const dobj = new Date(Date.UTC(parseInt(darr[2], 10), parseInt(darr[1], 10) - 1, parseInt(darr[0], 10), 0, 0, 0));
    return dobj.toISOString();
  }

  dateFormatForPrint(dateString: string) {
    if (this.isEmpty(dateString)) {
      return null;
    }
    const date: Date = new Date(dateString);
    return (date.getDate().toString()).padStart(2, '0') + '/' + ((date.getMonth() + 1).toString()).padStart(2, '0')  + '/' + date.getFullYear();
  }

  private  isEmpty(str) {
    return (!str || 0 === str.length);
  }

  private  isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }

}
