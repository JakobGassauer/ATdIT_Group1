package library;//http://tutorials.jenkov.com/java-date-time/java-util-calendar.html

import library.presentation.GUI;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

    public static void main(String[] args) {
        //System.out.println(Resident.get(2));
        //System.out.println(Incident.get(Resident.get("Inge").getResID()).getDescription());
        //System.out.println(Resident.get("Robert").getRoom());
        //todo Junit Tests & internationalization

        Locale.setDefault(Locale.ENGLISH); // in Abfrage/Button ändern??

        GUI frame = new GUI();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/gui/gui"); //NON-NLS
        frame.setTitle(resourceBundle.getString("shift_schedule"));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
