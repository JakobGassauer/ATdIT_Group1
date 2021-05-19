package library.presentation;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxItemListener implements ItemListener {
    GUI gui;

    public ComboBoxItemListener(GUI gui){
        this.gui = gui;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        gui.setShiftIncidentText();
        gui.setResidentIncidentText();
    }
}