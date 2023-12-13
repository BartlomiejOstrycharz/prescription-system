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

        Object[][] data = patients.stream()
                .map(patient -> new Object[]{patient.getPatient_id(), patient.getFirst_name(), patient.getLast_name(), patient.getDate_of_birth(), patient.getGender(), patient.getAddress(), patient.getPhone_number(), patient.getEmail()})
                .toArray(Object[][]::new);
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
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
}
