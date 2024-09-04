# Car Rental System

## Overview
The Car Rental System is a console-based application developed in Java, utilizing MySQL for database management. The system allows users to manage a fleet of cars, handle rental transactions, and maintain rental records. This project demonstrates the integration of Java with MySQL through JDBC for database operations.

## Features
- **Add Car**: Add new cars to the rental fleet, specifying details such as make, model, year, and price per day.
- **View All Cars**: View a list of all available cars with their details.
- **Update Car**: Update the information of an existing car in the system.
- **Delete Car**: Remove a car from the rental fleet.
- **Rent Car**: Rent a car to a customer, recording the transaction details.
- **Return Car**: Process the return of a rented car and update the rental records.

## Technologies Used
- **Java**: Core Java for the main application logic.
- **MySQL**: For managing the database, which stores car and rental records.
- **JDBC**: Java Database Connectivity for interacting with the MySQL database.
- **IntelliJ IDEA**: IDE used for development.

## Database Structure
The system uses a MySQL database with the following key tables:

### `car`
- `car_id` (INT, Primary Key)
- `make` (VARCHAR)
- `model` (VARCHAR)
- `year` (INT)
- `price_per_day` (DOUBLE)
- `available_for_rent` (BOOLEAN)

### `rental`
- `rental_id` (INT, Primary Key)
- `car_id` (INT, Foreign Key)
- `customer_name` (VARCHAR)
- `customer_phone` (VARCHAR)
- `rental_date` (DATE)
- `return_date` (DATE, nullable)

## Exception Handling
The project includes custom exceptions to handle specific error scenarios:

- **CarNotFoundException**: Thrown when a requested car is not found in the database.
- **RentalNotFoundException**: Thrown when a rental record cannot be found.

## How to Run
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/8-adarsh/CarRentalSystem.git
