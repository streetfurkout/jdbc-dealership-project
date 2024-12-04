package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO vehicles (vin, make, model, year, sold, color, vehicleType, odometer, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            // Set the values for the prepared statement using the vehicle object properties
            statement.setString(1, vehicle.getVin());
            statement.setString(2, vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setInt(4, vehicle.getYear());
            statement.setBoolean(5, vehicle.isSold());
            statement.setString(6, vehicle.getColor());
            statement.setString(7, vehicle.getVehicleType());
            statement.setInt(8, vehicle.getOdometer());
            statement.setDouble(9, vehicle.getPrice());

            // Execute the statement to insert the vehicle into the database
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM vehicles WHERE vin = ?")) {

            statement.setString(1, VIN);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicles WHERE price BETWEEN ? AND ?")) {

            // Set the minPrice and maxPrice parameters for the query
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    // Create a Vehicle object from the result set and add it to the list
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicles WHERE make = ? AND model = ?")) {

            // Set the make and model parameters for the query
            statement.setString(1, make);
            statement.setString(2, model);

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    // Create a Vehicle object from the result set and add it to the list
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range
        List<Vehicle> vehicleList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicles WHERE year BETWEEN ? AND ?")) {

            // Set the parameters for the query
            statement.setInt(1, minYear);
            statement.setInt(2, maxYear);

            try (ResultSet resultSet = statement.executeQuery()) {
                // Loop through the results and populate the vehicleList
                while (resultSet.next()) {
                    // Create a Vehicle object from the result set
                    Vehicle vehicle = createVehicleFromResultSet(resultSet);
                    vehicleList.add(vehicle);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Return the list of vehicles
        return vehicleList;
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicles WHERE color = ?")) {

            // Set the color parameter for the query
            statement.setString(1, color);

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    // Create a Vehicle object from the result set and add it to the list
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?")) {

            // Set the mileage range parameters for the query
            statement.setInt(1, minMileage);
            statement.setInt(2, maxMileage);

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    // Create a Vehicle object from the result set and add it to the list
                    Vehicle vehicle = createVehicleFromResultSet(results);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
            List<Vehicle> vehicles = new ArrayList<>();

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicles WHERE vehicleType = ?")) {

                // Set the vehicle type parameter for the query
                statement.setString(1, type);

                try (ResultSet results = statement.executeQuery()) {
                    while (results.next()) {
                        // Create a Vehicle object from the result set and add it to the list
                        Vehicle vehicle = createVehicleFromResultSet(results);
                        vehicles.add(vehicle);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return vehicles;
        }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
