package com.carrentalsystem.model;

import java.util.Date;

public class Rental {
    private int rentalId;
    private int carId;
    private String customerName;
    private String customerPhone;
    private Date rentalDate;
    private Date returnDate;

    // Constructors
    public Rental() {}

    public Rental(int rentalId, int carId, String customerName, String customerPhone, Date rentalDate, Date returnDate) {
        this.rentalId = rentalId;
        this.carId = carId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    // Getters and Setters
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
