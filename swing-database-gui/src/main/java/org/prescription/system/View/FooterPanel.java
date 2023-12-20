package org.prescription.system.View;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FooterPanel extends JPanel {

    //obiekt klasy JLabel wyświetlająca aktualną datę i godzinę
    private final JLabel dateTimeLabel;

    //Inicjalizacja układu FlowLayout oraz tworzenie etykiety
    public FooterPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        dateTimeLabel = new JLabel();
        add(dateTimeLabel);
    }
    //Metoda aktualizująca etykietę z aktualną datą i godziną.  Wywoływana w określonych odstępach czasowych przez Timer.
    public void updateDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String formattedDateTime = dateFormat.format(new Date());
        dateTimeLabel.setText("Aktualna data i godzina: " + formattedDateTime);
    }
}
