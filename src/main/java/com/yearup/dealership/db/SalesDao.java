package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        // TODO: Implement the logic to add a sales contract
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO sales_contracts (vin, sale_date, price) VALUES (?, ?, ?)")) {
                statement.setString(1, salesContract.getVin());
                statement.setObject(2, salesContract.getSaleDate());
                statement.setDouble(3, salesContract.getPrice());
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

