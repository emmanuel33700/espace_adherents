import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';


@Injectable()
export class ApiInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Apply the headers
    req = req.clone({
      setHeaders: {
        'Authorization': 'Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0ZXN0NUBnbWFpbC5jb20iLCJpZEFkaGVyZW50IjoiNiIsInNjb3BlIjpbInJlc3MtYXV0b3JpemF0aW9uLXJlYWQiLCJyZXNzLWF1dG9yaXphdGlvbi13cml0ZSIsInJlc3MtYXV0b3JpemF0aW9uLWRlbCIsInJlc3MtYWRoZXJlbnQtYWRtaW4iLCJyZXNzLWFkaGVyZW50LXJlYWQiLCJyZXNzLWFkaGVyZW50LXdyaXRlIiwicmVzcy1hZGhlcmVudC1kZWwiXSwib3JnYW5pemF0aW9uIjoiSkFMTEUgQVNUUk8iLCJleHAiOjE1ODQ4MDE0ODcsImF1dGhvcml0aWVzIjoiQURIRVJFTlQgIiwianRpIjoiMjdmZDIyYjktNDRkOS00NzQxLWJlY2UtMjY3OTE3MjIwMzZlIiwiY2xpZW50X2lkIjoiZXNwYWNlQWRoIn0.P7WwbyJrDk8bOQ6OCqP9MpUVE8V_JXeizTGEpVGd5HjAjNbcJRcs7XRyrJ1c54EtMU89bZp4WQ7rkOCHhfjl3yn5WS3EDK_QZ8f0Klv1D0y8l4qQQelJTGKUZpAezgvp2HL-hn7Zq0Kd2n0vpGLfqSPdblWgdJTW7MOHXEhmnnRW9IJKYu-3kRZmhptiGIfAiWvuTYQHFcGVcQ99JpT2OSw90s12YcpBPp5BfjjM3RDnkPGAPMQ0da2a0gXqgqxnGssZksRM1i1jWhyvnficn1LH9Hj3o2lRIHZjJ5euQWqKoYUKSHf8v6gALDy5eTmU0H0YOpkA55JIDSq_UAOdVQ',
      },
    });

    // Also handle errors globally
    return next.handle(req).pipe(
      tap(x => x, err => {
        // Handle this err
        console.error(`Error performing request, status code = ${err.status}`);
      }),
    );
  }
}
