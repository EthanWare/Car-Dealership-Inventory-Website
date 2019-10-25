import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';
import { FormGroup, FormControl } from '@angular/forms';

import { Car } from '../Car';
import { DataService } from '../data.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  cars: Car[];

  //values for adding a car
  addCar: boolean;
  addCarForm = new FormGroup({
    newCarYear: new FormControl(''),
    newCarMake: new FormControl(''),
    newCarModel: new FormControl(''),
    newCarType: new FormControl(''),
    newCarColor: new FormControl('')
  });

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

  //value for updating car
  showUpdateCarId: number;
  updateCarForm = new FormGroup({
    updateYear: new FormControl(''),
    updateMake: new FormControl(''),
    updateModel: new FormControl(''),
    updateType: new FormControl(''),
    updateColor: new FormControl('')
  });



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
    this.dataService.adminGetAllCars().subscribe(result => {
      this.cars = result;
      this.order();
    });
  }
  getFilteredCars() {
    if(this.searchValue !== "") {
      this.dataService.adminGetFilteredCars(this.searchColumn, this.searchValue).subscribe(result => {
        this.cars = result;
        this.order();
      });
    }
    else {
      this.getAllCars();
    }
  }
  addNewCarFormSubmit() {
    var newCar = {
      id: 1,
      year: this.addCarForm.controls.newCarYear.value,
      make: this.addCarForm.controls.newCarMake.value,
      model: this.addCarForm.controls.newCarModel.value,
      type: this.addCarForm.controls.newCarType.value,
      color: this.addCarForm.controls.newCarColor.value
    };

    this.dataService.adminCreateCar(newCar).subscribe(result => this.getAllCars());
    this.closeAddCar();
  }
  deleteCar(id: number) {
    this.dataService.adminDeleteCar(id).subscribe(result => this.getAllCars());
  }


  //add car functions
  showAddCar() {
    this.addCar = true;
  }
  closeAddCar() {
    this.addCar = false;
  }



  //update car functions
  showUpdateCar(id: number) {
    this.showUpdateCarId = id;

    //set default values
    var carToUpdate = this.cars.find(function(element) {
      return element.id == id;
    });
    this.updateCarForm.controls.updateYear.setValue(carToUpdate.year);
    this.updateCarForm.controls.updateMake.setValue(carToUpdate.make);
    this.updateCarForm.controls.updateModel.setValue(carToUpdate.model);
    this.updateCarForm.controls.updateType.setValue(carToUpdate.type);
    this.updateCarForm.controls.updateColor.setValue(carToUpdate.color);
  }
  updateCar(id) {
    var updateCar = {
      id: id,
      year: this.updateCarForm.controls.updateYear.value,
      make: this.updateCarForm.controls.updateMake.value,
      model: this.updateCarForm.controls.updateModel.value,
      type: this.updateCarForm.controls.updateType.value,
      color: this.updateCarForm.controls.updateColor.value
    };

    this.dataService.adminUpdateCar(updateCar).subscribe(result => this.getAllCars());
    this.closeUpdateCar();
  }
  closeUpdateCar() {
    this.showUpdateCarId = -1;
  }
}