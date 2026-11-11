import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {LoggerService} from './logger.service';

@Injectable()
export class ApiInterceptor implements HttpInterceptor {
  constructor(private loggerService: LoggerService) {}

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(req).pipe(
      tap({
        next: event => {
          if (event instanceof HttpResponse) {
            this.logResponse(req, event.status, event.body);
          }
        },
        error: (error: HttpErrorResponse) => {
          this.logResponse(req, error.status, error.error);
          this.loggerService.error(`HTTP ${req.method} ${req.urlWithParams} : ${error.message}`);
        },
      }),
    );
  }

  private logResponse(req: HttpRequest<unknown>, status: number, body: unknown): void {
    this.loggerService.info(JSON.stringify({
      method: req.method,
      url: req.urlWithParams,
      status,
      body,
    }));
  }
}
