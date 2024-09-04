package com.carrentalsystem.dao;

import com.carrentalsystem.Exception.CustomExceptions.CarNotFoundException;
import com.carrentalsystem.Exception.CustomExceptions.RentalNotFoundException;
import com.carrentalsystem.model.Rental;
import com.carrentalsystem.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {

    // Method to add a new rental (rent a car)
    public void addRental(Rental rental) throws SQLException {
        String sql = "INSERT INTO Rental (car_id, customer_name, customer_phone, rental_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rental.getCarId());
            stmt.setString(2, rental.getCustomerName());
            stmt.setString(3, rental.getCustomerPhone());
            stmt.setDate(4, new java.sql.Date(rental.getRentalDate().getTime()));
            stmt.executeUpdate();

            // Update car availability
            String updateCarSql = "UPDATE Car SET available_for_rent = FALSE WHERE car_id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateCarSql)) {
                updateStmt.setInt(1, rental.getCarId());
                updateStmt.executeUpdate();
            }
        }
    }

    // Method to return a rented car
    public void updateRental(Rental rental) throws SQLException {
        String sql = "UPDATE Rental SET return_date = ? WHERE rental_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(rental.getReturnDate().getTime()));
            stmt.setInt(2, rental.getRentalId());
            stmt.executeUpdate();
        }
    }

    // Method to get a rental by ID
    public Rental getRentalById(int rentalId) throws SQLException, RentalNotFoundException {
        Connection connection = DatabaseUtil.getConnection();
        String query = "SELECT * FROM rentals WHERE rental_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rentalId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Create and return Rental object from ResultSet
                return new Rental(rs.getInt("rental_id"), rs.getInt("car_id"), rs.getString("customer_name"),
                        rs.getString("customer_phone"), rs.getDate("rental_date"), rs.getDate("return_date"));
            } else {
                throw new RentalNotFoundException("Rental with ID " + rentalId + " not found.");
            }
        }
    }

    // Method to get all rentals
    public List<Rental> getAllRentals() throws SQLException {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM Rental";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rentals.add(new Rental(
                        rs.getInt("rental_id"),
                        rs.getInt("car_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_phone"),
                        rs.getDate("rental_date"),
                        rs.getDate("return_date")
                ));
            }
        }
        return rentals;
    }

    public void rentCar(Rental rental) throws SQLException {
        String sql = "INSERT INTO Rental (car_id, customer_name, customer_phone, rental_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rental.getCarId());
            stmt.setString(2, rental.getCustomerName());
            stmt.setString(3, rental.getCustomerPhone());
            stmt.setDate(4, new java.sql.Date(rental.getRentalDate().getTime()));
            stmt.executeUpdate();

            // Update car availability
            String updateCarSql = "UPDATE Car SET available_for_rent = FALSE WHERE car_id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateCarSql)) {
                updateStmt.setInt(1, rental.getCarId());
                updateStmt.executeUpdate();
            }
        }
    }


    public void returnCar(int rentalId) throws SQLException {
        String sql = "UPDATE Rental SET return_date = ? WHERE rental_id = ?";
        String getCarIdSql = "SELECT car_id FROM Rental WHERE rental_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement getCarIdStmt = conn.prepareStatement(getCarIdSql)) {

            // Set the return date
            stmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            stmt.setInt(2, rentalId);
            stmt.executeUpdate();

            // Get the car_id associated with this rental
            getCarIdStmt.setInt(1, rentalId);
            ResultSet rs = getCarIdStmt.executeQuery();
            if (rs.next()) {
                int carId = rs.getInt("car_id");

                // Update the car's availability status
                String updateCarSql = "UPDATE Car SET available_for_rent = TRUE WHERE car_id = ?";
                try (PreparedStatement updateCarStmt = conn.prepareStatement(updateCarSql)) {
                    updateCarStmt.setInt(1, carId);
                    updateCarStmt.executeUpdate();
                }
            }
        }
    }

}
