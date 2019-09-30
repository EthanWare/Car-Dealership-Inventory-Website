package com.ethan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository repo;

    public ResponseEntity<?> readAllCars(){
        //check for an empty database
        if(repo.count() == 0)
            return new ResponseEntity<String>("No cars exist", HttpStatus.NO_CONTENT);
        else{
            ArrayList<Car> cars = new ArrayList<>();
            repo.findAll().forEach(cars::add);
            return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
        }
    }
    public ResponseEntity<?> readFilteredCars(String column, String filter){
        //check for an empty database
        if(repo.count() == 0)
            return new ResponseEntity<String>("No cars exist", HttpStatus.NO_CONTENT);
        else{
            ArrayList<Car> cars = new ArrayList<>();
            switch(column){
                case "year": repo.findByYear(filter).forEach(cars::add); break;
                case "make": repo.findByMake(filter).forEach(cars::add); break;
                case "model": repo.findByModel(filter).forEach(cars::add); break;
                case "type": repo.findByType(filter).forEach(cars::add); break;
                case "color": repo.findByColor(filter).forEach(cars::add); break;
                default: return new ResponseEntity<String>("Wrong column name", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
        }
    }
}