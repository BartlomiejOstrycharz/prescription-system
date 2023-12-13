package org.prescription.system.View;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.prescription.system.Model.Patient;
import org.prescription.system.View.PatientTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TableScreen extends JFrame {

    public TableScreen() {
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem aboutItem = new JMenuItem("About");

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(TableScreen.this, "About", Dialog.ModalityType.DOCUMENT_MODAL);
                dialog.setSize(200, 200);
                dialog.setLocationRelativeTo(null);

                JLabel label = new JLabel("Info about app", SwingConstants.CENTER);

                dialog.add(label);

                dialog.setVisible(true);
            }
        });

        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);


        List<Patient> patients = fetchDataFromBackend();

        PatientTable patientTable = new PatientTable(patients);
        getContentPane().add(patientTable, BorderLayout.CENTER);
    }

    private List<Patient> fetchDataFromBackend() {
        // Make HTTP request to the backend to fetch patient data
        // Use Apache HttpClient to make the request
        // Example assumes the backend URL is "http://localhost:8080/patients"
        try {
            HttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet("http://localhost:8080/patients");

            // Execute the request and obtain the response
            HttpResponse response = client.execute(request);

            // Parse the JSON response into a list of Patient objects
            ObjectMapper objectMapper = new ObjectMapper();
            List<Patient> patients = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<List<Patient>>() {});

            return patients;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
