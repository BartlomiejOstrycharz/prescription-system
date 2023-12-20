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
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        exitItem.addActionListener(e -> dispose());
        JMenuItem aboutItem = new JMenuItem("About");

        aboutItem.addActionListener(e -> {
            JDialog dialog = new JDialog(this, "About", Dialog.ModalityType.DOCUMENT_MODAL);
            dialog.setSize(200, 200);
            dialog.setLocationRelativeTo(null);

            JLabel label = new JLabel("Info about app", SwingConstants.CENTER);

            dialog.add(label);

            dialog.setVisible(true);
        });

        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // Create search panel
        searchPanel = new SearchPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform search based on the content of the search panel
                String searchTerm = searchPanel.getSearchTerm();
                 List<Patient> searchResults = patientService.searchPatients(searchTerm);
                 //Update the table with the search results
                 patientTable.updateTable(searchResults);
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

    }
}
