/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Nurse;

public class NurseDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/user";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "";

    private static final String INSERT_NURSE = "INSERT INTO nurses (first_name, last_name, email, phone_number, address, license_number) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_NURSE_BY_ID = "SELECT id, first_name, last_name, email, phone_number, address, license_number FROM nurses WHERE id = ?";
    private static final String SELECT_ALL_NURSES = "SELECT id, first_name, last_name, email, phone_number, address, license_number FROM nurses";
    private static final String DELETE_NURSE = "DELETE FROM nurses WHERE id = ?";
    private static final String UPDATE_NURSE = "UPDATE nurses SET first_name = ?, last_name = ?, email = ?, phone_number = ?, address = ?, license_number = ? WHERE id = ?";

    protected Connection connectToDatabase() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            // Handle the ClassNotFoundException appropriately (e.g., log the error)
            e.printStackTrace();
        } catch (SQLException e) {
            // Handle the SQLException appropriately (e.g., log the error)
            e.printStackTrace();
        }
        return connection;
    }

    public void insertNurse(Nurse nurse) throws SQLException {
        System.out.println(INSERT_NURSE);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = connectToDatabase(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NURSE)) {
            preparedStatement.setString(1, nurse.getFirstName());
            preparedStatement.setString(2, nurse.getLastName());
            preparedStatement.setString(3, nurse.getEmail());
            preparedStatement.setString(4, nurse.getPhoneNumber());
            preparedStatement.setString(5, nurse.getAddress());
            preparedStatement.setString(6, nurse.getLicenseNumber());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Nurse selectNurse(Long id) {
        Nurse nurse = null;
        // Step 1: Establishing a Connection
        try (Connection connection = connectToDatabase(); // Step 2: Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NURSE_BY_ID);) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String address = rs.getString("address");
                String licenseNumber = rs.getString("license_number");

                nurse = new Nurse(id, firstName, lastName, email, phoneNumber, address, licenseNumber);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return nurse;
    }

    public List<Nurse> selectAllNurses() {
        // using try-with-resources to avoid closing resources (boilerplate code)
        List<Nurse> nurses = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = connectToDatabase(); // Step 2: Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NURSES);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String address = rs.getString("address");
                String licenseNumber = rs.getString("license_number");

                nurses.add(new Nurse(id, firstName, lastName, email, phoneNumber, address, licenseNumber));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return nurses;
    }

    public boolean deleteNurse(Long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = connectToDatabase(); PreparedStatement statement = connection.prepareStatement(DELETE_NURSE);) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateNurse(Nurse nurse) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = connectToDatabase(); PreparedStatement statement = connection.prepareStatement(UPDATE_NURSE);) {
            statement.setString(1, nurse.getFirstName());
            statement.setString(2, nurse.getLastName());
            statement.setString(3, nurse.getEmail());
            statement.setString(4, nurse.getPhoneNumber());
            statement.setString(5, nurse.getAddress());
            statement.setString(6, nurse.getLicenseNumber());
            statement.setLong(7, nurse.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
