import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';
import { FormGroup, FormControl } from '@angular/forms';

import { Car } from '../Car';
import { DataService } from '../data.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

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
  reverseOrder: boolean = false;


  constructor(private messageService: MessageService, private dataService: DataService) { }
  ngOnInit() {
    this.messageService.clear();

    //set default search column and order by drop down selectors
    this.orderByOption = this.columnOptions[0].name;
    this.searchColumn = this.columnOptions[0].name;

    //get cars and order they default by year
    this.getAllCars();
  }



  //data functions
  order() {
    if(this.reverseOrder === true)
      this.dataService.reverseOrder(this.cars, this.orderByOption);
    else
      this.dataService.order(this.cars, this.orderByOption);
  }
  reverseOrderCheckbox() {
    if(this.reverseOrder === false)
      this.reverseOrder = true;
    else
      this.reverseOrder = false;

    this.order();
  }
  getAllCars() {
    this.dataService.userGetAllCars().subscribe(result => {
      this.cars = result;
      this.order();
    });
  }
  getFilteredCars() {
    if(this.searchValue !== "") {
      this.dataService.userGetFilteredCars(this.searchColumn, this.searchValue).subscribe(result => {
        this.cars = result;
        this.order();
      });
    }
    else {
      this.getAllCars();
    }
  }
  deleteCar(id: number) {
    this.dataService.userDeleteCar(id).subscribe(result => this.getAllCars());
  }
}