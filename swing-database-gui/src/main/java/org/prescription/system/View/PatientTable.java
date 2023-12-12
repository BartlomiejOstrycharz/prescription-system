package org.prescription.system.View;

import org.prescription.system.Model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PatientTable extends JPanel {
    private JTable table;

    public PatientTable(List<Patient> patients) {
        String[] columnNames = {"id", "First Name", "Last Name", "Date of birth", "Gender", "Address", "Phone", "Email"};

        // Convert the list of patients to a 2D array
        Object[][] data = patients.stream()
                .map(patient -> new Object[]{patient.getPatient_id(), patient.getFirst_name(), patient.getLast_name(), patient.getDate_of_birth(), patient.getGender(), patient.getAddress(), patient.getPhone_number(), patient.getEmail()})
                .toArray(Object[][]::new);

        // Create a DefaultTableModel with non-editable cells
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Create a main container with padding
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Add table to the panel
        add(scrollPane, BorderLayout.CENTER);
    }
}
