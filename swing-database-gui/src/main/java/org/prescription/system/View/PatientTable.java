package org.prescription.system.View;

import org.prescription.system.Model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.List;

public class PatientTable extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    // Add popup menu items
    private JPopupMenu popupMenu;
    private JMenuItem deleteMenuItem;
    private JMenuItem unselectMenuItem;

    public PatientTable(List<Patient> patients) {
        String[] columnNames = {"Patient ID", "First Name", "Last Name", "Date of birth", "Gender", "Address", "Phone", "Email"};

        Object[][] data = patients.stream()
                .map(patient -> new Object[]{
                        patient.getPatient_id(), // Keep patient_id as Long
                        patient.getFirstName(), patient.getLastName(), patient.getDateOfBirth(), patient.getGender(),
                        patient.getAddress(), patient.getPhoneNumber(), patient.getEmail()})
                .toArray(Object[][]::new);

        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);

        // Set a custom comparator for the "Patient ID" column
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        sorter.setComparator(0, Comparator.comparingLong(value -> {
            try {
                return Long.parseLong(value.toString());
            } catch (NumberFormatException e) {
                return Long.MAX_VALUE; // Handle non-numeric values
            }
        }));
        table.setRowSorter(sorter);

        // Initialize the popup menu
        popupMenu = new JPopupMenu();
        deleteMenuItem = new JMenuItem("Delete Patient");
        unselectMenuItem = new JMenuItem("Unselect Patient");
        popupMenu.add(deleteMenuItem);
        popupMenu.add(unselectMenuItem);

        // Add a mouse listener to show the popup menu on right-click
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = table.rowAtPoint(e.getPoint());
                    table.getSelectionModel().setSelectionInterval(row, row);

                    popupMenu.show(table, e.getX(), e.getY());
                }
            }
        });

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

    // Add this method to get the JTable
    public JTable getTable() {
        return table;
    }

    // Add this method to get the selected row
    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    // Add this method to unselect the patient
    public void clearSelection() {
        table.clearSelection();
    }

    // Add this method to add a listener for the delete menu item
    public void addDeleteMenuItemListener(ActionListener listener) {
        deleteMenuItem.addActionListener(listener);
    }

    // Add this method to add a listener for the unselect menu item
    public void addUnselectMenuItemListener(ActionListener listener) {
        unselectMenuItem.addActionListener(listener);
    }
}
