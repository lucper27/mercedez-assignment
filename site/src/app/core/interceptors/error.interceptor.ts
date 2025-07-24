import {
    HttpErrorResponse,
  HttpInterceptorFn
} from '@angular/common/http';
import { catchError, throwError } from 'rxjs';
import { MessageService } from 'primeng/api';
import { inject } from '@angular/core';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const messageService = inject(MessageService);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {      
      messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Service unvailable',
        life: 4000
      });
      return throwError(() => error);
    })
  );
};
