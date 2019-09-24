package com.ethan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepository repo;

    //Create
    public ResponseEntity<String> createCar(Car car){
        ArrayList<Car> cars = new ArrayList<>();
        repo.findAll().forEach(cars::add);
        //check if any fields are null
        if(car == null || car.getId() == 0 || car.getYear() == 0 || car.getMake() == "" || car.getModel() == "" || car.getType() == "" || car.getColor() == "")
            return new ResponseEntity<String>("Car creation unsuccessful: Car contains an empty field", HttpStatus.NOT_ACCEPTABLE);
        
        for(Car c: cars){
            //check if the car already exist in the database
            if(c.getYear() == car.getYear() && c.getMake().equals(car.getMake()) && c.getModel().equals(car.getModel()) && c.getType().equals(car.getType()) && c.getColor().equals(car.getColor()) )
                return new ResponseEntity<String>("Car creation unsuccessful: Car already exists", HttpStatus.CONFLICT);
            //auto-increment id based on database
            if(c.getId() == car.getId())
            car.setId(c.getId() + 1);
        }

        repo.save(car);
        return new ResponseEntity<String>("Car creation successful", HttpStatus.CREATED);
    }

    //Read
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
            repo.findByColumnFilter(column, filter).forEach(cars::add);
            return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
        }
    }

    //Update
    public void updateCar(Car car){
        repo.save(car);
    }
    
    //Delete
    public void deleteAllCars(){
        repo.deleteAll();
    }
    public void deleteCar(int id){
        repo.deleteById(id);
    }
}