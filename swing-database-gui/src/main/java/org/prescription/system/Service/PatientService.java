package org.prescription.system.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.prescription.system.Model.Patient;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PatientService {

    public List<Patient> fetchDataFromBackend() {
        try {
            HttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet("http://localhost:8080/patients");

            HttpResponse response = client.execute(request);

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.getEntity().getContent(), new TypeReference<List<Patient>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    private boolean patientMatchesSearch(Patient patient, String searchTerm) {
        // Replace this with your actual search logic
        // For simplicity, searching across all patient attributes
        String firstName = patient.getFirstName();
        String lastName = patient.getLastName();
        String dateOfBirth = patient.getDateOfBirth();
        String gender = patient.getGender().toString(); // Assuming Gender is an enum
        String address = patient.getAddress();
        String phone = patient.getPhoneNumber();
        String email = patient.getEmail();

        String patientInfo = firstName + " " + lastName + " " + dateOfBirth + " " + gender + " " + address + " " + phone + " " + email;

        return patientInfo.toLowerCase().contains(searchTerm.toLowerCase());
    }


    public List<Patient> searchPatients(String searchTerm) {
        // Replace this with your actual search logic
        // For simplicity, assuming you have a method to fetch all patients
        List<Patient> allPatients = fetchDataFromBackend();

        // Filter patients based on the search term
        List<Patient> searchResults = allPatients.stream()
                .filter(patient -> patientMatchesSearch(patient, searchTerm))
                .collect(Collectors.toList());

        return searchResults;
    }
}
