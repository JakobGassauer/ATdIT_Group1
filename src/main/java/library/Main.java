package library;//http://tutorials.jenkov.com/java-date-time/java-util-calendar.html

import library.presentation.GUI;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    static GUI frame;
    public static void main(String[] args) {
     launch();
        //System.out.println(Resident.get(2));
        //System.out.println(Incident.get(Resident.get("Inge").getResID()).getDescription());
        //System.out.println(Resident.get("Robert").getRoom());
        //todo Junit Tests & internationalization

   //     Locale.setDefault(Locale.GERMAN); // in Abfrage/Button ändern??


    }

    public static void launch() {
        frame = new GUI();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/gui/gui"); //NON-NLS
        frame.setTitle(resourceBundle.getString("shift_schedule"));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void closeFrame(){
        frame.dispose();
    }

}
