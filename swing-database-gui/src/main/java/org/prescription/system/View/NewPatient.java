package org.prescription.system.View;

import org.prescription.system.Model.Gender;
import org.prescription.system.Model.Patient;
import org.prescription.system.Service.AddPatient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewPatient {
    public NewPatient(Component owner) {
        JDialog add_patient = new JDialog((Window) owner, "Add Patient", Dialog.ModalityType.DOCUMENT_MODAL);
            add_patient.setLayout(new GridLayout(0, 2));
            add_patient.setSize(400, 300);
            add_patient.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridLayout(0, 2));
            int borderSize = 10;
            panel.setBorder(new EmptyBorder(borderSize, borderSize, borderSize, borderSize));


            JTextField first_name = new JTextField();
            panel.add(new JLabel("First Name:"));
            panel.add(first_name);


            JTextField last_name = new JTextField();
            panel.add(new JLabel("Last Name:"));
            panel.add(last_name);

            JTextField date_of_birth = new JTextField();
            panel.add(new JLabel("Date of birth:"));
            panel.add(date_of_birth);

            JTextField human_gender = new JTextField();
            panel.add(new JLabel("Gender:"));
            panel.add(human_gender);

            JTextField address = new JTextField();
            panel.add(new JLabel("Address:"));
            panel.add(address);

            JTextField phone_number = new JTextField();
            panel.add(new JLabel("Phone Number:"));
            panel.add(phone_number);

            JTextField email = new JTextField();
            panel.add(new JLabel("Email:"));
            panel.add(email);

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(saveEvent -> {
                String firstName = first_name.getText();
                String lastName = last_name.getText();
                String dateOfBirth = date_of_birth.getText();
                String gender = human_gender.getText();
                String patientAddress = address.getText();
                String phoneNumber = phone_number.getText();
                String patientEmail = email.getText();

                Patient newPatient = new Patient();
                newPatient.setFirstName(firstName);
                newPatient.setLastName(lastName);
                newPatient.setDateOfBirth(dateOfBirth);
                newPatient.setGender(Gender.valueOf(gender));
                newPatient.setAddress(patientAddress);
                newPatient.setPhoneNumber(phoneNumber);
                newPatient.setEmail(patientEmail);

                AddPatient patientadd = new AddPatient();
                patientadd.sendPatientDataToBackend(newPatient);

                add_patient.dispose();

            });
            add_patient.add(panel, BorderLayout.CENTER);
            add_patient.add(saveButton, BorderLayout.SOUTH);

            add_patient.setVisible(true);
        }
    }

