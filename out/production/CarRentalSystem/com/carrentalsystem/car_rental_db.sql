CREATE DATABASE car_rental_db;

USE car_rental_db;

CREATE TABLE Car (
    car_id INT AUTO_INCREMENT PRIMARY KEY,
    make VARCHAR(50),
    model VARCHAR(50),
    year INT,
    price_per_day DECIMAL(10,2),
    available_for_rent BOOLEAN DEFAULT TRUE
);

CREATE TABLE Rental (
    rental_id INT AUTO_INCREMENT PRIMARY KEY,
    car_id INT,
    customer_name VARCHAR(100),
    customer_phone VARCHAR(15),
    rental_date DATE,
    return_date DATE,
    FOREIGN KEY (car_id) REFERENCES Car(car_id)
);
