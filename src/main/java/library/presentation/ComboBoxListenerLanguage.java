package library.presentation;

import library.Main;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Locale;

public class ComboBoxListenerLanguage implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        int index = cb.getSelectedIndex();
        switch (index) {
            case 0:
                Locale.setDefault(Locale.GERMAN);
                System.out.println("German");
                System.out.println(Locale.getDefault());
                break;
            case 1:
                Locale.setDefault(Locale.ENGLISH);
                System.out.println("English");
                System.out.println(Locale.getDefault());
                break;
        }
        Main.closeFrame();
        Main.launch();
    }
}
