package library;//http://tutorials.jenkov.com/java-date-time/java-util-calendar.html

import library.presentation.GUI;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The Main class launches the GUI.
 * Error hanlding:
 * Error Messages are written directly into the console, thus the user does not need to interact with errors.
 */
public class Main {
    static GUI frame;
    public static void main(String[] args) {
        Locale.setDefault(Locale.GERMAN);
        launch();
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
