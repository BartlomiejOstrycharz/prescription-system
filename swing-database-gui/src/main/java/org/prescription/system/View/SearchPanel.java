package org.prescription.system.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {

    private final JTextField searchField;
    private final JButton searchButton;

    public SearchPanel(ActionListener searchButtonListener) {
        setLayout(new FlowLayout());

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300, 30));

        searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.addActionListener(searchButtonListener);

        add(searchField);
        add(searchButton);
    }

    public String getSearchTerm() {
        return searchField.getText();
    }

    public void setSearchTerm(String searchTerm) {
        searchField.setText(searchTerm);
    }
}
