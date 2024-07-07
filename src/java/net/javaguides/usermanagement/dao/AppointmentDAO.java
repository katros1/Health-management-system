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

import net.javaguides.usermanagement.model.Appointment;

public class AppointmentDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/user";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "";

    private static final String INSERT_APPOINTMENT = "INSERT INTO appointments (time, date, patient_name, patient_age, doctor) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_APPOINTMENT_BY_ID = "SELECT id, time, date, patient_name, patient_age, doctor  FROM appointments WHERE id = ?";
    private static final String SELECT_ALL_APPOINTMENTS = "SELECT * FROM appointments";
    private static final String DELETE_APPOINTMENT = "DELETE FROM appointments WHERE id = ?";
    private static final String UPDATE_APPOINTMENT = "UPDATE appointments SET time = ?, date = ?, patient_name = ?, patient_age = ?, doctor = ? WHERE id = ?";

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

    public void insertAppointment(Appointment appointment) throws SQLException {
        System.out.println(INSERT_APPOINTMENT);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = connectToDatabase(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_APPOINTMENT)) {
            preparedStatement.setString(1, appointment.getTime());
            preparedStatement.setString(2, appointment.getDate());
            preparedStatement.setString(3, appointment.getPatientName());
            preparedStatement.setString(4, appointment.getPatientAge());
            preparedStatement.setString(5, appointment.getDoctor());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Appointment selectAppointment(Long id) {
        Appointment appointment = null;
        // Step 1: Establishing a Connection
        try (Connection connection = connectToDatabase(); // Step 2: Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_BY_ID);) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String time = rs.getString("time");
                String date = rs.getString("date");
                String patientName = rs.getString("patient_name");
                String patientAge = rs.getString("patient_age");
                String doctor = rs.getString("doctor");

                appointment = new Appointment(id, time, date, patientName, patientAge, doctor);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return appointment;
    }

    public List<Appointment> selectAllAppointments() {
        // using try-with-resources to avoid closing resources (boilerplate code)
        List<Appointment> appointments = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = connectToDatabase(); // Step 2: Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_APPOINTMENTS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Long id = rs.getLong("id");
                String time = rs.getString("time");
                String date = rs.getString("date");
                String patientName = rs.getString("patient_name");
                String patientAge = rs.getString("patient_age");
                String doctor = rs.getString("doctor");

                appointments.add(new Appointment(id, time, date, patientName, patientAge, doctor));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return appointments;
    }

    public boolean deleteAppointment(Long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = connectToDatabase(); PreparedStatement statement = connection.prepareStatement(DELETE_APPOINTMENT);) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateAppointment(Appointment appointment) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = connectToDatabase(); PreparedStatement statement = connection.prepareStatement(UPDATE_APPOINTMENT);) {
            statement.setString(1, appointment.getTime());
            statement.setString(2, appointment.getDate());
            statement.setString(3, appointment.getPatientName());
            statement.setString(4, appointment.getPatientAge());
            statement.setString(5, appointment.getDoctor());
            statement.setLong(8, appointment.getId());

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
