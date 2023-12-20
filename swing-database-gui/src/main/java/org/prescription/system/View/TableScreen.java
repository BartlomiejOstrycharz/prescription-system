package org.prescription.system.View;

import org.prescription.system.Model.Patient;
import org.prescription.system.Service.PatientService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class TableScreen extends JFrame {

    private final PatientTable patientTable;
    private final SearchPanel searchPanel;

    public TableScreen(PatientService patientService) {
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        JMenu editMenu = new JMenu("Edit");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem deletePatient = new JMenuItem("Delete selected patient");
        JMenuItem unselectItem = new JMenuItem("Unselect Patient");
        JMenuItem addDoctor = new JMenuItem("Add Doctor");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        exitItem.addActionListener(e -> dispose());
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        aboutItem.addActionListener(e -> {
            JDialog dialog = new JDialog(this, "About", Dialog.ModalityType.DOCUMENT_MODAL);
            dialog.setLayout(new GridLayout(3, 1));
            dialog.setSize(250, 250);
            dialog.setLocationRelativeTo(null);

            JLabel label = new JLabel("Info about authors", SwingConstants.CENTER);
            JLabel author_number_one = new JLabel("Bartłomiej Ostrycharz -> sigma", SwingConstants.CENTER);
            JLabel author_number_two = new JLabel("Adam Walkowiak", SwingConstants.CENTER);

            dialog.add(label);
            dialog.add(author_number_one);
            dialog.add(author_number_two);
            dialog.setVisible(true);
        });

        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        menuBar.add(editMenu);
        editMenu.add(deletePatient);
        editMenu.add(unselectItem);
        editMenu.add(addDoctor);
        addDoctor.setEnabled(false);

        // Create search panel
        searchPanel = new SearchPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform search based on the content of the search panel
                String searchTerm = searchPanel.getSearchTerm();
                List<Patient> searchResults = patientService.searchPatients(searchTerm);
                // Update the table with the search results
                patientTable.updateTable(searchResults);

                // Unselect the patient when performing a new search
                patientTable.clearSelection();
            }
        });

        // Create a panel for the search bar and add it to the top of the frame
        JPanel searchBarPanel = new JPanel(new BorderLayout());
        searchBarPanel.add(searchPanel, BorderLayout.CENTER);
        searchBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Fetch initial data from the backend
        List<Patient> patients = patientService.fetchDataFromBackend();

        // Create the patient table
        patientTable = new PatientTable(patients);

        // Add the search bar panel and patient table to the main content pane
        getContentPane().add(searchBarPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(patientTable), BorderLayout.CENTER);

        // Create and add the footer panel
        FooterPanel footerPanel = new FooterPanel();
        getContentPane().add(footerPanel, BorderLayout.SOUTH);

        // Set up a timer to update the date and time every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                footerPanel.updateDateTime();
            }
        });
        timer.start();
        footerPanel.updateDateTime(); // Initial update

        // ActionListener for Delete selected patient
        deletePatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = patientTable.getSelectedRow();
                // Handle deletion logic based on the selected row
                // You can use selectedRow to identify the patient to delete
                // For now, let's just print the selected row index
                System.out.println("Selected row index: " + selectedRow);
            }
        });

        // ActionListener for Unselect Patient
        unselectItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the unselectPatient method in PatientTable
                patientTable.clearSelection();
            }
        });

        // Disable the menu items initially
        deletePatient.setEnabled(false);
        unselectItem.setEnabled(false);

        // Add a ListSelectionListener to enable/disable the menu items based on selection
        patientTable.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = patientTable.getSelectedRow();
                deletePatient.setEnabled(selectedRow != -1);
                unselectItem.setEnabled(selectedRow != -1);
            }
        });
    }
}
