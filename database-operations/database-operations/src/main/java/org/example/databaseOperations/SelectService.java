package org.example.databaseOperations;

import java.sql.*;

public class SelectService {
    public static void viewPatients(Connection conn) throws SQLException {

        Statement stmt;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *  FROM patients");
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            int columnWidth = 25; //COLUMN WIDTH

            for (int i = 1; i <= columnCount; i++) {
                String colName = metaData.getColumnName(i);
                System.out.print(String.format("%-" + columnWidth + "s", colName));
            }

            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue;
                    columnValue = rs.getString(i);
                    System.out.print(String.format("%-" + columnWidth + "s", columnValue));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("viewTable error");
        }
    }
}
