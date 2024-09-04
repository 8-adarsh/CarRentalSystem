package com.carrentalsystem.dao;

import com.carrentalsystem.model.Car;
import com.carrentalsystem.util.DatabaseUtil;
import com.carrentalsystem.Exception.CustomExceptions.CarNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    // Method to add a new car
    public void addCar(Car car) throws SQLException {
        String sql = "INSERT INTO Car (make, model, year, price_per_day, available_for_rent) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getMake());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setDouble(4, car.getPricePerDay());
            stmt.setBoolean(5, car.isAvailableForRent());
            stmt.executeUpdate();
        }
    }

    // Method to get a car by ID
    public static Car getCarById(int carId) throws SQLException, CarNotFoundException {
        String sql = "SELECT * FROM Car WHERE car_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Car(
                            rs.getInt("car_id"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("year"),
                            rs.getDouble("price_per_day"),
                            rs.getBoolean("available_for_rent")
                    );
                } else {
                    throw new CarNotFoundException("Car with ID " + carId + " not found.");
                }
            }
        }
    }

    // Method to get all cars
    public List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM Car";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("car_id"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price_per_day"),
                        rs.getBoolean("available_for_rent")
                ));
            }
        }
        return cars;
    }

    // Method to update a car
    public void updateCar(Car car) throws SQLException {
        String sql = "UPDATE Car SET make = ?, model = ?, year = ?, price_per_day = ?, available_for_rent = ? WHERE car_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, car.getMake());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setDouble(4, car.getPricePerDay());
            stmt.setBoolean(5, car.isAvailableForRent());
            stmt.setInt(6, car.getCarId());
            stmt.executeUpdate();
        }
    }

    // Method to delete a car
    public void deleteCar(int carId) throws SQLException {
        String sql = "DELETE FROM Car WHERE car_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carId);
            stmt.executeUpdate();
        }
    }
}
