import { Injectable } from '@angular/core';

@Injectable()
export class LoggerService {

  logs: Array<string> = [];

  level: number = 0;  // debug:2, info:1, error:0


  debug(message: string) {
    this.logs.push(message);
    if (this.level === 2) console.info(message);
  }

  error(error: unknown) {
    let message: string;
    if (error instanceof Error) {
      message = error.stack || error.message;
    } else if (typeof error === 'string') {
      message = error;
    } else {
      try {
        message = JSON.stringify(error) ?? String(error);
      } catch {
        message = String(error);
      }
    }
    this.logs.push(message);
    if (this.level >= 0) console.error(error);
  }

  info(message: string) {
    this.logs.push(message);
    if (this.level >= 1) console.info(message);
  }

}
