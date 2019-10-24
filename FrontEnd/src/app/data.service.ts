import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Car } from './Car';
import { MessageService } from './message.service'
import { throwMatDialogContentAlreadyAttachedError } from '@angular/material';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  constructor(private http: HttpClient, private messageService: MessageService) { }
  adminUrl = "http://localhost:8082/";
  userUrl = "http://localhost:8083/";


  getAllCars(): Observable<Car[]> {
    return this.http.get<Car[]>(this.adminUrl + "cars/read").pipe(
      catchError(this.handleError<Car[]>('getAllCars',[]))
    );
  }

  getFilteredCars(column: string, searchValue: string): Observable<Car[]> {
    return this.http.get<Car[]>(this.adminUrl + "cars/read/" + column + "/" + searchValue);
  }

  createCar(car: Car): Observable<string> {
    return this.http.post<string>(this.adminUrl + "cars/create", car);
  }

  updateCar(car: Car): Observable<string> {
    console.log(car);
    return this.http.put<string>(this.adminUrl + "cars/update", car);
  }



  private handleError<T>(method = 'method', result?: T) {
    return (error: any): Observable<T> => {
      switch(error.status) {
        case 204:
          console.error("204 - No_Content, Error Message; " + error.message);
          this.messageService.set("204 - No_Content, Error Message; " + error.message);
          break;
      }

      return of(result as T);
    }
  }



  order(cars: Car[], orderByOption: string) {
    switch(orderByOption){
      case "Year":
        cars.sort(this.compareByYear);
        break;
      case "Make":
        cars.sort(this.compareByMake);
        break;
      case "Model":
        cars.sort(this.compareByModel);
        break;
      case "Type":
        cars.sort(this.compareByType);
        break;
      case "Color":
        cars.sort(this.compareByColor);
        break;
    }
  }

  //compares year greatest to least
  compareByYear(a, b) {
    let comparison = 0;

    if (a.year < b.year) {
      comparison = 1;
    } else if (a.year > b.year) {
      comparison = -1;
    }

    return comparison;
  }

  //compares make in alphabetic order
  compareByMake(a, b) {
    let comparison = 0;

    if (a.make > b.make) {
      comparison = 1;
    } else if (a.make < b.make) {
      comparison = -1;
    }

    return comparison;
  }

  //compares model in alphabetic order
  compareByModel(a, b) {
    let comparison = 0;

    if (a.model > b.model) {
      comparison = 1;
    } else if (a.model < b.model) {
      comparison = -1;
    }

    return comparison;
  }

  //compares type in alphabetic order
  compareByType(a, b) {
    let comparison = 0;

    if (a.type > b.type) {
      comparison = 1;
    } else if (a.type < b.type) {
      comparison = -1;
    }

    return comparison;
  }

  //compares color in alphabetic order
  compareByColor(a, b) {
    let comparison = 0;

    if (a.color > b.color) {
      comparison = 1;
    } else if (a.color < b.color) {
      comparison = -1;
    }

    return comparison;
  }

}
