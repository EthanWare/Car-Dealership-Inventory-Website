import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';

import { Car } from '../Car';
import { DataService } from '../data.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  cars: Car[];

  //values for filtering results
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

  //values for adding a car
  addCar: boolean;
  newCarYear: string;
  newCarMake: string;
  newCarModel: string;
  newCarType: string;
  newCarColor: string;

  constructor(private messageService: MessageService, private dataService: DataService) { }

  ngOnInit() {
    this.messageService.clear();

    //set default search column and order by drop down selectors
    this.orderByOption = this.columnOptions[0].name;
    this.searchColumn = this.columnOptions[0].name;

    //get cars and order they default by year
    this.getAllCars();
  }


  getAllCars() {
    this.dataService.getAllCars().subscribe(result => {
      this.cars = result;
      this.order();
    });
  }
  
  getFilteredCars() {
    if(this.searchValue !== "") {
      this.dataService.getFilteredCars(this.searchColumn, this.searchValue).subscribe(result => {
        this.cars = result;
        this.order();
      });
    }
    else {
      this.getAllCars();
    }
  }

  // createCar() {

  // }


  order() {
    this.dataService.order(this.cars, this.orderByOption);
  }

  showAddCar() {
    this.addCar = true;
  }

  addNewCarFormSubmit() {
    var newCar = {
      id: 1,
      year: this.newCarYear,
      make: this.newCarMake,
      model: this.newCarModel,
      type: this.newCarType,
      color: this.newCarColor
    }

    this.dataService.createCar(newCar).subscribe(result => {
      this.getAllCars();
    });
    this.closeAddCar();
  }

  closeAddCar() {
    this.addCar = false;
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