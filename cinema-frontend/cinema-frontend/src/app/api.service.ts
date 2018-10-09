import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { throwError, Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CrudService {

  API_URL = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {
  }

  getMovies() {
    return this.httpClient.get(`${this.API_URL}/api/movies/`, this.httpOptionForGet());
  }

  reverse(reservation: Reservation): Observable<Reservation> {
    return this.httpClient.post<Reservation>(`${this.API_URL}/api/reservation/`, reservation, this.httpOptionForPost())
      .pipe(
        catchError(this.handleError)
      );
  }

  httpOptionForPost() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': localStorage.getItem('token')
      })
    }
  }

  httpOptionForGet() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }
  }

  errorHandler(error: any): void {
    console.log(error)
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };
}