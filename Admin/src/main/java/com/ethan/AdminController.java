package com.ethan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/cars")
@RestController
public class AdminController {
    @Autowired
    AdminService service;

    //Create
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ResponseEntity<?> createCar(@RequestBody Car car){
        return service.createCar(car);
    }

    //Read
    @RequestMapping(value="/read", method=RequestMethod.GET)
    public ResponseEntity<?> readAllCars(){
        return service.readAllCars();
    }
    @RequestMapping(value="/read/{column}/{filter}", method=RequestMethod.GET)
    public ResponseEntity<?> readFilteredCars(@PathVariable("column") String column, @PathVariable("filter") String filter){
        return service.readFilteredCars(column, filter);
    }

    //Update
    @RequestMapping(value="/update", method=RequestMethod.PUT)
    public ResponseEntity<?> updateCar(@RequestBody Car car){
        return service.updateCar(car);
    }
    
    //Delete
    @RequestMapping(value="/delete", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllCars(){
        return service.deleteAllCars();
    }
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteCar(@PathVariable("id") int id){
        return service.deleteCar(id);
    }
}