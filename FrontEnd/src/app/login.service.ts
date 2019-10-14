import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  getLoginResult(user: User): Observable<number> {
    var httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    return this.http.post<number>('http://localhost:8081/authenticate', user, httpOptions);
  }

  constructor(private http: HttpClient) { }
}
