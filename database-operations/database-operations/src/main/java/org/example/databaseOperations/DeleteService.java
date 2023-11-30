package org.example.databaseOperations;

import java.sql.*;


public class DeleteService {
    public static void deletePatient(Connection connection, String first_name, String last_name, String date_of_birth) throws SQLException {
        Statement stmt;
        String query = String.format("DELETE FROM patients WHERE first_name = '%s' && last_name = '%s' && date_of_birth = '%s'", first_name, last_name, date_of_birth);
        try {
            stmt = connection.createStatement();
            int rowsAffected = stmt.executeUpdate(query);
            if(rowsAffected == 0){
                System.out.println("No matching data!");
            }else {
                System.out.println("Deleted successfully");
            }

        } catch (SQLException e ){
            System.out.println("DeleteErrror");
        }

    }
}
