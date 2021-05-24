package library.presentation;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * ItemListener for the two combo boxes Shift and Time on the top left corner of the GUI.
 */
public class ComboBoxItemListener implements ItemListener {
    GUI gui;

    /**
     * Creates a connection to the class GUI.
     * @param gui
     */
    public ComboBoxItemListener(GUI gui){
        this.gui = gui;
    }

    /**
     * invokes the methods setShiftIncidentText and setResidentIncidentText to update the data shown on the GUI according to the selected time and shift.
     * @param e
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        gui.setShiftIncidentText();
        gui.setResidentIncidentText();
    }
}