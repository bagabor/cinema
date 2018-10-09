import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { HttpHeaders } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

    API_URL = 'http://localhost:8080';

    constructor(private http: HttpClient) { }

    login(userName: string, password: string) {
        return this.http.post<any>(`${this.API_URL}/login`, JSON.stringify({ userName: userName, password: password }), { observe: 'response' })
            .pipe(map(response => {
                if (response.body) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('token', response.body['Authorization']);
                }
                return response;
            }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('token');
    }
}