package org.prescription.system.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                dialog.setSize(200,200);
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

        PatientTable patientTable = new PatientTable();
        add(patientTable, BorderLayout.CENTER);
    }
}
