import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse,
  HttpErrorResponse
} from '@angular/common/http';
import {ProgressbarService} from "./progressbar/progressbar.service";

import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class AppInterceptor implements HttpInterceptor {
  private count: number = 0;

  constructor(private progressbarService: ProgressbarService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.httpStart();
    // Clone the request to add the new header.
    // const authReq = req.clone({headers: req.headers.set('Authorization', authHeader)});
    return next.handle(req).do(event => {
      console.info('do');
      if (event instanceof HttpResponse) {
        console.info("http response");
        this.httpEnd();
      }
    }).catch(err => this.handleError(err));
  }

  private httpStart() {
    this.progressbarService.isLoading = true;
    this.count++;
  }

  private httpEnd() {
    if (this.count > 0)
      this.count--;

    if (this.count === 0) {
      this.progressbarService.isLoading = false;
    }
  }

  private handleError(err: any) {
    // console.error(err);
    if (err instanceof HttpErrorResponse) {
      console.info(err.message);
      this.httpEnd();
    }
    return Observable.throw(err);
  }
}
