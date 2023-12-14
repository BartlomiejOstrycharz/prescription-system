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
}
