package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.Doctor;

public class DoctorDAO {

    private final String jdbcURL = "jdbc:mysql://localhost:3306/user";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "";

    private static final String INSERT_DOCTOR_SQL = "INSERT INTO doctors (first_name, last_name, specialization, email, phone_number, address, license_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_DOCTOR_BY_ID = "SELECT id, first_name, last_name, specialization, email, phone_number, address, license_number FROM doctors WHERE id = ?";
    private static final String SELECT_ALL_DOCTORS = "SELECT id, first_name, last_name, specialization, email, phone_number, address, license_number FROM doctors";
    private static final String DELETE_DOCTOR_SQL = "DELETE FROM doctors WHERE id = ?";
    private static final String UPDATE_DOCTOR_SQL = "UPDATE doctors SET first_name = ?, last_name = ?, specialization = ?, email = ?, phone_number = ?, address = ?, license_number = ? WHERE id = ?";

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

    public void insertDoctor(Doctor doctor) throws SQLException {
        System.out.println(INSERT_DOCTOR_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = connectToDatabase(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DOCTOR_SQL)) {
            preparedStatement.setString(1, doctor.getFirstName());
            preparedStatement.setString(2, doctor.getLastName());
            preparedStatement.setString(3, doctor.getSpecialization());
            preparedStatement.setString(4, doctor.getEmail());
            preparedStatement.setString(5, doctor.getPhoneNumber());
            preparedStatement.setString(6, doctor.getAddress());
            preparedStatement.setString(7, doctor.getLicenseNumber());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Doctor selectDoctor(Long id) {
        Doctor doctor = null;
        // Step 1: Establishing a Connection
        try (Connection connection = connectToDatabase(); // Step 2: Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTOR_BY_ID);) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String specialization = rs.getString("specialization");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String address = rs.getString("address");
                String licenseNumber = rs.getString("license_number");

                doctor = new Doctor(id, firstName, lastName, specialization, email, phoneNumber, address, licenseNumber);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return doctor;
    }

    public List<Doctor> selectAllDoctors() {
        // using try-with-resources to avoid closing resources (boilerplate code)
        List<Doctor> doctors = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = connectToDatabase(); // Step 2: Create a statement using connection object
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DOCTORS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String specialization = rs.getString("specialization");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String address = rs.getString("address");
                String licenseNumber = rs.getString("license_number");

                doctors.add(new Doctor(id, firstName, lastName, specialization, email, phoneNumber, address, licenseNumber));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return doctors;
    }

    public boolean deleteDoctor(Long id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = connectToDatabase(); PreparedStatement statement = connection.prepareStatement(DELETE_DOCTOR_SQL);) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateDoctor(Doctor doctor) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = connectToDatabase(); PreparedStatement statement = connection.prepareStatement(UPDATE_DOCTOR_SQL);) {
            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3, doctor.getSpecialization());
            statement.setString(4, doctor.getEmail());
            statement.setString(5, doctor.getPhoneNumber());
            statement.setString(6, doctor.getAddress());
            statement.setString(7, doctor.getLicenseNumber());
            statement.setLong(8, doctor.getId());

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
