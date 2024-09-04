package com.carrentalsystem.main;

import com.carrentalsystem.dao.CarDAO;
import com.carrentalsystem.dao.RentalDAO;
//import com.carrentalsystem.Exception.CarNotFoundException;
import com.carrentalsystem.Exception.CustomExceptions.CarNotFoundException;
import com.carrentalsystem.model.Car;
import com.carrentalsystem.model.Rental;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarDAO carDAO = new CarDAO();
        RentalDAO rentalDAO = new RentalDAO();

        while (true) {
            System.out.println("\n=== Car Rental System ===");
            System.out.println("1. Add Car");
            System.out.println("2. View All Cars");
            System.out.println("3. Update Car");
            System.out.println("4. Delete Car");
            System.out.println("5. Rent Car");
            System.out.println("6. Return Car");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            try {
                switch (choice) {
                    case 1:
                        addCar(scanner, carDAO);
                        break;
                    case 2:
                        viewAllCars(carDAO);
                        break;
                    case 3:
                        updateCar(scanner, carDAO);
                        break;
                    case 4:
                        deleteCar(scanner, carDAO);
                        break;
                    case 5:
                        rentCar(scanner, rentalDAO);
                        break;
                    case 6:
                        returnCar(scanner, rentalDAO);
                        break;
                    case 7:
                        System.out.println("Exiting system. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void addCar(Scanner scanner, CarDAO carDAO) throws Exception {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        System.out.print("Enter year: ");
        int year = scanner.nextInt();

        System.out.print("Enter price per day(In Rs): ");
        double pricePerDay = scanner.nextDouble();

        Car car = new Car(0, make, model, year, pricePerDay, true);
        carDAO.addCar(car);
        System.out.println("Car added successfully.");
    }

    private static void viewAllCars(CarDAO carDAO) throws Exception {
        List<Car> cars = carDAO.getAllCars();
        if (cars.isEmpty()) {
            System.out.println("No cars available.");
        } else {
            for (Car car : cars) {
                System.out.println(car.getCarId() + " - " + car.getMake() + " " + car.getModel() + " (" + car.getYear() + "), $" + car.getPricePerDay() + " per day, Available: " + car.isAvailableForRent());
            }
        }
    }

    private static void updateCar(Scanner scanner, CarDAO carDAO) {
        System.out.print("Enter Car ID to update: ");
        int carId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Car car = CarDAO.getCarById(carId);
            if (car == null) {
                throw new CarNotFoundException("Car with ID " + carId + " not found.");
            }

            System.out.print("Enter new make (current: " + car.getMake() + "): ");
            String make = scanner.nextLine();
            System.out.print("Enter new model (current: " + car.getModel() + "): ");
            String model = scanner.nextLine();
            System.out.print("Enter new year (current: " + car.getYear() + "): ");
            int year = scanner.nextInt();
            System.out.print("Enter new price per day (current: $" + car.getPricePerDay() + "): ");
            double pricePerDay = scanner.nextDouble();

            car.setMake(make);
            car.setModel(model);
            car.setYear(year);
            car.setPricePerDay(pricePerDay);

            carDAO.updateCar(car);
            System.out.println("Car updated successfully.");
        } catch (CarNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void deleteCar(Scanner scanner, CarDAO carDAO) {
        System.out.print("Enter Car ID to delete: ");
        int carId = scanner.nextInt();

        try {
            // Check if the car exists
            Car car = carDAO.getCarById(carId);
            if (car == null) {
                throw new CarNotFoundException("Car with ID " + carId + " not found.");
            }

            // Proceed to delete the car
            carDAO.deleteCar(carId);
            System.out.println("Car deleted successfully.");
        } catch (CarNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    private static void rentCar(Scanner scanner, RentalDAO rentalDAO) {
        System.out.print("Enter Car ID to rent: ");
        int carId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter customer phone: ");
        String customerPhone = scanner.nextLine();

        try {
            // Check if the car exists
            Car car = CarDAO.getCarById(carId);
            if (car == null) {
                throw new CarNotFoundException("Car with ID " + carId + " not found.");
            }

            // Proceed with the rental process
            Rental rental = new Rental(0, carId, customerName, customerPhone, new Date(), null);
            rentalDAO.rentCar(rental);

            System.out.println("Car rented successfully.");
        } catch (CarNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    private static void returnCar(Scanner scanner, RentalDAO rentalDAO) throws Exception {
        System.out.print("Enter Rental ID to return: ");
        int rentalId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        rentalDAO.returnCar(rentalId);
        System.out.println("Car returned successfully.");
    }
}
