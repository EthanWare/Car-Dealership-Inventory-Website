package com.ethan;

import javax.persistence.*;

@Entity
@Table(name = "car_table")
public class Car{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int year;
    private String make;
    private String model;
    private String type;
    private String color;

    public Car(int id, int year, String make, String model, String type, String color) {
        this.id = id;
        this.year = year;
        this.make = make;
        this.model = model;
        this.type = type;
        this.color = color;
    }

    public Car() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Car id(int id) {
        this.id = id;
        return this;
    }

    public Car year(int year) {
        this.year = year;
        return this;
    }

    public Car make(String make) {
        this.make = make;
        return this;
    }

    public Car model(String model) {
        this.model = model;
        return this;
    }

    public Car type(String type) {
        this.type = type;
        return this;
    }

    public Car color(String color) {
        this.color = color;
        return this;
    }
}