package com.ethan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@RequestMapping(value="/cars")
@RestController
public class AdminController {
    @Autowired
    AdminService service;

    //Create
    @RequestMapping(value="/add", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody Car car){
        service.addCar(car);
    }

    //Read
    @RequestMapping(value="/read", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Car> readAllCars(){
        return service.readAllCars();
    }
    @RequestMapping(value="/read/{filter}", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Car> readFilteredCars(@PathVariable("filter") String filter){
        return service.readFilteredCars(filter);
    }

    //Update
    @RequestMapping(value="/update/{id}", method=RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public List<Car> updateCar(@PathVariable("id") int id){
        return service.updateCar(id);
    }
    
    //Delete
    @RequestMapping(value="/delete", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public List<Car> deleteAllCars(){
        return service.deleteAllCars();
    }
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public List<Car> deleteCar(@PathVariable("id") int id){
        return service.deleteCar(id);
    }
}