package org.prescription.system.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PatientTable extends JPanel {
    private JTable table;
    public PatientTable(){
        String[][] data = {{"1", "John", "Doe"}, {"2", "Jane", "Smith"}, {"3", "Bob", "Johnson"}}; // examples data
        String[] columnNames = {"id", "First Name", "Last Name","Date of birth", "Gender", "Address", "Phone", "Email" };

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
