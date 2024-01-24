package org.prescription.system.Service;

import org.prescription.system.Model.Patient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AddPatient {
    public void sendPatientDataToBackend(Patient patient) {
        PatientService patientService;
        try {
            URL url = new URL("http://localhost:8080/add");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String patientJson = "{\"firstName\":\"" + patient.getFirstName() +
                    "\",\"lastName\":\"" + patient.getLastName() +
                    "\",\"dateOfBirth\":\"" + patient.getDateOfBirth() +
                    "\",\"gender\":\"" + patient.getGender() +
                    "\",\"address\":\"" + patient.getAddress() +
                    "\",\"phoneNumber\":\"" + patient.getPhoneNumber() +
                    "\",\"email\":\"" + patient.getEmail() + "\"}";

            connection.getOutputStream().write(patientJson.getBytes());

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Patient added successfully");

            } else {
                System.out.println("Error adding patient. HTTP Status Code: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
