package com.carrentalsystem.model;

public class Car {
    private int carId;
    private String make;
    private String model;
    private int year;
    private double pricePerDay;
    private boolean availableForRent;

    // Constructors
    public Car() {}

    public Car(int carId, String make, String model, int year, double pricePerDay, boolean availableForRent) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.pricePerDay = pricePerDay;
        this.availableForRent = availableForRent;
    }

    // Getters and Setters
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvailableForRent() {
        return availableForRent;
    }

    public void setAvailableForRent(boolean availableForRent) {
        this.availableForRent = availableForRent;
    }
}
