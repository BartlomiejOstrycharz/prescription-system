package org.example.databaseOperations;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateService {

    public static void changePatientAddress(Connection connection, String first_name, String last_name, String address, String new_address) throws SQLException{
        String query = String.format(
                "UPDATE patients " +
                "SET address = '%s' " +
                "WHERE first_name = '%s' AND last_name = '%s' AND address = '%s'",
                new_address, first_name, last_name, address);

        try (Statement stmt = connection.createStatement()) {
            int rowsAffected = stmt.executeUpdate(query);
            if (rowsAffected == 0) {
                System.out.println("No matching data!");
            } else {
                System.out.println("Address updated successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
