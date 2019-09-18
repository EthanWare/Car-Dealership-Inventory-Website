package com.ethan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepository repo;

    //Create
    public void createCar(Car car){
        repo.save(car);
    }

    //Read
    public List<Car> readAllCars(){
        ArrayList<Car> cars = new ArrayList<>();
        repo.findAll().forEach(cars::add);
        return cars;
    }
    public List<Car> readFilteredCars(String column, String filter){
        ArrayList<Car> cars = new ArrayList<>();
        repo.findByColumnFilter(column, filter).forEach(cars::add);
        return cars;
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