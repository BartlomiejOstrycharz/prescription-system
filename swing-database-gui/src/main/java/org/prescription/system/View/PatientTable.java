package org.prescription.system.View;

import org.prescription.system.Model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PatientTable extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public PatientTable(List<Patient> patients) {
        String[] columnNames = {"id", "First Name", "Last Name", "Date of birth", "Gender", "Address", "Phone", "Email"};

        Object[][] data = patients.stream()
                .map(patient -> new Object[]{patient.getPatient_id(), patient.getFirstName(), patient.getLastName(), patient.getDateOfBirth(), patient.getGender(), patient.getAddress(), patient.getPhoneNumber(), patient.getEmail()})
                .toArray(Object[][]::new);

        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateTable(List<Patient> patients) {
        // Remove all rows from the table
        tableModel.setRowCount(0);

        // Add new rows based on the updated data
        for (Patient patient : patients) {
            tableModel.addRow(new Object[]{
                    patient.getPatient_id(), patient.getFirstName(), patient.getLastName(),
                    patient.getDateOfBirth(), patient.getGender(), patient.getAddress(),
                    patient.getPhoneNumber(), patient.getEmail()
            });
        }
    }
}
