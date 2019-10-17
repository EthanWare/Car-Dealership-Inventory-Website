import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';

import { Car } from '../Car';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  cars: Car[];

  searchColumn: string;
  columnOptions = [
    {name: "Year", value: "year"},
    {name: "Make", value: "name"},
    {name: "Model", value: "model"},
    {name: "Type", value: "type"},
    {name: "Color", value: "color"}
  ]
  searchValue: string;
  orderByOption: string;

  constructor(private messageService: MessageService, private adminService: AdminService) { }

  ngOnInit() {
    this.messageService.clear;

    //set default search column and order by drop down selectors
    this.orderByOption = this.columnOptions[0].name;
    this.searchColumn = this.columnOptions[0].name;

    //get cars and order they default by year
    this.getAllCars();
  }

  getAllCars() {
    this.adminService.readAllCars().subscribe(result => {
      this.cars = result;
      this.order();
    });
  }

  
  searchColumnByValue() {
    if(this.searchValue !== "") {
      this.adminService.readFilteredCars(this.searchColumn, this.searchValue).subscribe(result => {
        this.cars = result;
        this.order();
      });
    }
    else {
      this.getAllCars();
    }
  }

  order() {
    switch(this.orderByOption){
      case "Year":
        this.cars.sort(this.compareByYear);
        break;
      case "Make":
        this.cars.sort(this.compareByMake);
        break;
      case "Model":
        this.cars.sort(this.compareByModel);
        break;
      case "Type":
        this.cars.sort(this.compareByType);
        break;
      case "Color":
        this.cars.sort(this.compareByColor);
        break;
    }
  }

  //compares year greatest to least
  compareByYear(a: Car, b: Car) {
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





// searchColumnByValue() {
//   var tempCars: Car[];
//   switch(this.searchColumn) {
//     case "Year":
//       this.cars.forEach(car => {
//         if(car.year.toString().search(this.searchColumn) != -1){
//           tempCars.push(car);
//         }
//       });
//       break;
//     case "Make":
//         this.cars.forEach(car => {
//           if(car.make.search(this.searchColumn) != -1){
//             tempCars.push(car);
//           }
//         });
//       break;
//     case "Model":
//         this.cars.forEach(car => {
//           if(car.model.search(this.searchColumn) != -1){
//             tempCars.push(car);
//           }
//         });
//       break;
//     case "Type":
//         this.cars.forEach(car => {
//           if(car.type.search(this.searchColumn) != -1){
//             tempCars.push(car);
//           }
//         });
//       break;
//     case "Color":
//         this.cars.forEach(car => {
//           if(car.color.search(this.searchColumn) != -1){
//             tempCars.push(car);
//           }
//         });
//       break;
//   }
//   this.cars = tempCars;
// }