package org.example.databaseOperations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class InsertService {

    public static void addPatient(Connection connection, String first_name, String last_name, String gender, String address, String phone_number, String email, String date_of_birth) throws SQLException {
        String query = String.format(
                "INSERT INTO patients (first_name, last_name, date_of_birth, gender, address, phone_number, email) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                first_name, last_name, date_of_birth, gender, address, phone_number, email);

        try (Statement stmt = connection.createStatement()) {
            int rowsAffected = stmt.executeUpdate(query);
            if (rowsAffected == 0) {
                System.out.println("No matching data!");
            } else {
                System.out.println("Inserted successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


