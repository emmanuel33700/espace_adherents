import {ErrorHandler, Injectable} from '@angular/core';
import {LoggerService} from './logger.service';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  constructor(private loggerService: LoggerService) {}

  handleError(error: unknown): void {
    this.loggerService.error(error);
  }
}
