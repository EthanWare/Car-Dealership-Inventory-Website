package com.ethan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService service;

    //returns 0 for user, 1 for admin, and -1 if neither of those
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/authenticate", method=RequestMethod.POST)
    public int authenticate(@RequestBody User user) {
        try{
            return service.authenticate(user);
        }
        catch(Exception e){
            System.out.print(e.getMessage());
            return -1;
        }
    }
    /*  Notes:

        -Change all angular http calls to call this microservice with the specific endpoint
        -Redired all endpoints to other microservices, except for login and signup

        -Login should return data in header (either 0/1 or true/false if using isAdmin instead of numbers)
        -Clean up directories into (Controller, Service, Repository, Model, Security)
    */

    // Authenticate:

    // @RequestMapping(value="/login", method=RequestMethod.POST)

    // @RequestMapping(value="/signin", method=RequestMethod.POST)




    // Admin:

    // @RequestMapping(value="/create", method=RequestMethod.POST)
    // public ResponseEntity<?> createCar(@RequestBody Car car){
    //     return service.createCar(car);
    // }

    // //Read
    // @RequestMapping(value="/read", method=RequestMethod.GET)
    // public ResponseEntity<?> readAllCars(){
    //     return service.readAllCars();
    // }
    // @RequestMapping(value="/read/{column}/{filter}", method=RequestMethod.GET)
    // public ResponseEntity<?> readFilteredCars(@PathVariable("column") String column, @PathVariable("filter") String filter){
    //     return service.readFilteredCars(column, filter);
    // }

    // //Update
    // @RequestMapping(value="/update", method=RequestMethod.PUT)
    // public ResponseEntity<?> updateCar(@RequestBody Car car){
    //     return service.updateCar(car);
    // }
    
    // //Delete
    // @RequestMapping(value="/delete", method=RequestMethod.DELETE)
    // public ResponseEntity<?> deleteAllCars(){
    //     return service.deleteAllCars();
    // }
    // @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    // public ResponseEntity<?> deleteCar(@PathVariable("id") int id){
    //     return service.deleteCar(id);
    // }



    
    // User

    // //Read
    // @RequestMapping(value="/read", method=RequestMethod.GET)
    // public ResponseEntity<?> readAllCars(){
    //     return service.readAllCars();
    // }
    // @RequestMapping(value="/read/{column}/{filter}", method=RequestMethod.GET)
    // public ResponseEntity<?> readFilteredCars(@PathVariable("column") String column, @PathVariable("filter") String filter){
    //     return service.readFilteredCars(column, filter);
    // }
}