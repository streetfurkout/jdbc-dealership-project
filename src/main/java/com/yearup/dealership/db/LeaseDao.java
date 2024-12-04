package com.yearup.dealership.db;

import com.yearup.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        // TODO: Implement the logic to add a lease contract
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO lease_contracts (vin, lease_start, lease_end, monthly_payment) VALUES (?, ?, ?, ?)")) {
                statement.setString(1, leaseContract.getVin());
                statement.setObject(2, leaseContract.getLeaseStart());
                statement.setObject(3, leaseContract.getLeaseEnd());
                statement.setDouble(4, leaseContract.getMonthlyPayment());
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

