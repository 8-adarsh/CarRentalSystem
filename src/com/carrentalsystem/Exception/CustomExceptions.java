package com.carrentalsystem.Exception;

public class CustomExceptions {
    public static class CarNotFoundException extends Exception {
        public CarNotFoundException(String message) {
            super(message);
        }
    }

    public static class RentalNotFoundException extends Exception {
        public RentalNotFoundException(String message) {
            super(message);
        }
    }
}
