package org.prescription.system.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeletePatientService {

    private static final String BASE_URL = "http://localhost:8080/";

    public static void deletePatient(Long patientId) {
        String deleteEndpoint = "patients/" + patientId;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + deleteEndpoint))
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HTTP Status Code: " + response.statusCode());
            System.out.println("HTTP Response Body: " + response.body());
        } catch (Exception ex) {
            ex.printStackTrace();
            // Handle exceptions or log errors as needed
        }
    }
}
