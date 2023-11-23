package org.example.userInterface;

import org.example.databaseConnection.DatabaseConnection;
import org.example.databaseOperations.DeleteService;
import org.example.databaseOperations.InsertService;
import org.example.databaseOperations.SelectService;
import org.example.databaseOperations.UpdateService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DatabaseConnection.connect()) {
            System.out.println("Connected");
            while(true) {
                System.out.println();

                System.out.println("Operation to perform:");
                System.out.println("S -> select all patients");
                System.out.println("I -> insert new patient");
                System.out.println("U -> update patient's address");
                System.out.println("D -> delete existing patient");
                System.out.println("Q -> exit");
                String operation = scanner.next().toUpperCase();

                System.out.println();

                switch(operation){
                    case "S":
                        SelectService.viewPatients(connection); //Select Method
                        break;
                    case "I":
                        System.out.println("ENTER DATA TO INSERT");
                        scanner.nextLine();
                        System.out.print("First name: ");
                        String first_name = scanner.nextLine();
                        System.out.print("Last name: ");
                        String last_name = scanner.nextLine();
                        System.out.print("Gender (Male, Female, Other): ");
                        String gender = scanner.nextLine();
                        System.out.print("Address: ");
                        String address = scanner.nextLine();
                        System.out.print("Phone number (ddd-ddd-ddd): ");
                        String phone_number = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Date of birth (YYYY-MM-DD): ");
                        String date_of_birth = scanner.nextLine();

                        InsertService.addPatient(connection, first_name, last_name, gender, address, phone_number, email, date_of_birth); //Insert Method
                        break;
                    case "U":
                        System.out.println("ENTER DATA TO CHANGE ADDRESS");
                        scanner.nextLine();
                        System.out.print("First name: ");
                        first_name = scanner.nextLine();
                        System.out.print("Last name: ");
                        last_name = scanner.nextLine();
                        System.out.print("Address to change: ");
                        address = scanner.nextLine();
                        System.out.print("New address: ");
                        String new_address = scanner.nextLine();

                        UpdateService.changePatientAddress(connection, first_name, last_name, address, new_address); //Update Method
                        break;
                    case "D":
                        System.out.println("ENTER DATA TO DELETE PATIENT");
                        System.out.print("First name: ");
                        first_name = scanner.next();
                        System.out.print("Last name: ");
                        last_name = scanner.next();
                        System.out.print("date_of_birth(YYYY-MM-DD): ");
                        date_of_birth = scanner.next();

                        DeleteService.deletePatient(connection, first_name, last_name, date_of_birth); //Delete Method
                        break;
                    case "Q":
                        System.out.println("Connection closed");
                        connection.close();
                        return;
                    default:
                        System.out.println("Incorrect operation");
                        break;
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}