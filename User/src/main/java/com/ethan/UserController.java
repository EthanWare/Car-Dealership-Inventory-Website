package com.ethan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value="/cars")
@RestController
public class UserController {
    @Autowired
    UserService service;

    //Read
    @RequestMapping(value="/read", method=RequestMethod.GET)
    public ResponseEntity<?> readAllCars(){
        return service.readAllCars();
    }
    @RequestMapping(value="/read/{column}/{filter}", method=RequestMethod.GET)
    public ResponseEntity<?> readFilteredCars(@PathVariable("column") String column, @PathVariable("filter") String filter){
        return service.readFilteredCars(column, filter);
    }
}