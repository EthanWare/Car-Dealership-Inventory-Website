import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Car } from './Car';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  readAllCars(): Observable<Car[]> {
    return this.http.get<Car[]>('http://localhost:8082/cars/read');
  }

  readFilteredCars(column: string, searchValue: string): Observable<Car[]> {
    return this.http.get<Car[]>('http://localhost:8082/cars/read/' + column + '/' + searchValue);
  }

  constructor(private http: HttpClient) { }
}
