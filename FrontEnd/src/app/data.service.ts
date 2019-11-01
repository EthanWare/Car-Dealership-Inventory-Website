import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Car } from './Car';
import { MessageService } from './message.service'

@Injectable({
  providedIn: 'root'
})
export class DataService {
  constructor(private http: HttpClient, private messageService: MessageService) { }
  adminUrl = "http://localhost:8082/";
  userUrl = "http://localhost:8083/";
  


  //Admin data access methods
  adminGetAllCars(): Observable<Car[]> {
    return this.http.get<Car[]>(this.adminUrl + "cars/read")
      .pipe(
        catchError(this.handleError<Car[]>("adminGetAllCars",[]))
      );
  }
  adminGetFilteredCars(column: string, searchValue: string): Observable<Car[]> {
    return this.http.get<Car[]>(this.adminUrl + "cars/read/" + column + "/" + searchValue)
      .pipe(
        catchError(this.handleError<Car[]>("adminGetFilteredCars",[]))
      );
  }
  adminCreateCar(car: Car): Observable<string> {
    return this.http.post<string>(this.adminUrl + "cars/create", car)
      .pipe(
        tap(_ => this.messageService.set("Car created successfully")),
        catchError(this.handleError<string>("adminCreateCar"))
      );
  }
  adminUpdateCar(car: Car): Observable<string> {
    return this.http.put<string>(this.adminUrl + "cars/update", car)
      .pipe(
        tap(_ => this.messageService.set("Car updated successfully")),
        catchError(this.handleError<string>("adminUpdateCar"))
      );
  }
  adminDeleteCar(id: number): Observable<string> {
    return this.http.delete<string>(this.adminUrl + "cars/delete/" + id)
      .pipe(
        tap(_ => this.messageService.set("Car deleted successfully")),
        catchError(this.handleError<string>("adminDeleteCar"))
      );
  }


  //User data access methods
  userGetAllCars(): Observable<Car[]> {
    return this.http.get<Car[]>(this.userUrl + "cars/read")
      .pipe(
        catchError(this.handleError<Car[]>("userGetAllCars"))
      );
  }
  userGetFilteredCars(column: string, searchValue: string): Observable<Car[]> {
    return this.http.get<Car[]>(this.userUrl + "cars/read/" + column + "/" + searchValue)
      .pipe(
        catchError(this.handleError<Car[]>("userGetFilteredCars"))
      );
  }


  //error handling
  private handleError<T>(method = "method", result?: T) {
    return (error: any): Observable<T> => {

      //default error message to print if no other cases are true
      var errorMessage = "Error: " + error.status;

      switch(method) {
        case "adminGetAllCars":
          if(error.status === 204)
            errorMessage = "Error: No cars exist";
          break;
        case "adminGetFilteredCars":
          if(error.status === 204)
            errorMessage = "Error: No cars exist";
          else if(error.status === 406)
            errorMessage = "Error: Wrong column name";
          break;
        case "adminCreateCar":
          if(error.status === 406) 
            errorMessage = "Error: Car contains an empty field";
          else if(error.status === 409)
            errorMessage = "Error: Car already exists";
          break;
        case "adminUpdateCar":
          if(error.status === 204)
            errorMessage = "Error: Car does not exist";
          else if(error.status === 406)
            errorMessage = "Error: Car contains an empty field";
          break;
        case "adminDeleteCar":
          if(error.status === 204)
            errorMessage = "Error: Car does not exist";
          break;
        case "userGetAllCars":
          if(error.status === 204)
            errorMessage = "Error: No cars exist";
          break;
        case "userGetFilteredCars":
          if(error.status === 204)
            errorMessage = "Error: No cars exist";
          else if(error.status === 406)
            errorMessage = "Error: Wrong column name";
          break;
      }
      this.messageService.set(errorMessage);

      return of(result as T);
    }
  }


  //order methods
  order(cars: Car[], orderByOption: string) {
    if(cars !== null){
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
  }
  reverseOrder(cars: Car[], orderByOption: string) {
    if(cars !== null){
      switch(orderByOption){
        case "Year":
          cars.sort(this.reverseCompareByYear);
          break;
        case "Make":
          cars.sort(this.reverseCompareByMake);
          break;
        case "Model":
          cars.sort(this.reverseCompareByModel);
          break;
        case "Type":
          cars.sort(this.reverseCompareByType);
          break;
        case "Color":
          cars.sort(this.reverseCompareByColor);
          break;
      }
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
  //compares year least to greatest
  reverseCompareByYear(a, b) {
    let comparison = 0;

    if (a.year > b.year) {
      comparison = 1;
    } else if (a.year < b.year) {
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
  //compares make in reverse alphabetic order
  reverseCompareByMake(a, b) {
    let comparison = 0;

    if (a.make < b.make) {
      comparison = 1;
    } else if (a.make > b.make) {
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
  //compares model in reverse alphabetic order
  reverseCompareByModel(a, b) {
    let comparison = 0;

    if (a.model < b.model) {
      comparison = 1;
    } else if (a.model > b.model) {
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
  //compares type in reverse alphabetic order
  reverseCompareByType(a, b) {
    let comparison = 0;

    if (a.type < b.type) {
      comparison = 1;
    } else if (a.type > b.type) {
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
  //compares color in reverse alphabetic order
  reverseCompareByColor(a, b) {
    let comparison = 0;

    if (a.color < b.color) {
      comparison = 1;
    } else if (a.color > b.color) {
      comparison = -1;
    }

    return comparison;
  }

}