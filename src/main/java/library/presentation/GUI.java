package library.presentation;

import library.model.implementation.*;
import library.persistence.Adapter;
import library.persistence.implementation.DatabaseAdapter;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * The Class creates the graphical user interface and uses the Adapter to fill itself with the data out of the database.
 */
public class GUI extends JFrame {

    private static boolean beingEdited = false;
    private static boolean isSaved = true;
    private static boolean hasSwitched = false;
    private static int buttonIdentification = -1;
    private static int indexComparison = -1;
    private static int lastButton = -1;
    private static int selectedLanguage = 0;

    private final ImageIcon saveicon;
    private final ImageIcon editicon;

    private final CardLayout cl = new CardLayout();

    private JPanel jpResidentRoom, jpFilterTextAll, jpFilter, jpTextResident, jpResident, jpRoom, jpSpecific, jpEditResident, jpTextResidentAndEdit, cards;

    private JTextPane tpBaseData, tpMedication, tpDiagnosisSheet, tpClosestRelative, tpVisits, tpOther;
    private Document docBaseData, docMedication, docDiagnosisSheet, docClosestRelative, docVisits, docOther;

    private SimpleAttributeSet attrHeader;
    private SimpleAttributeSet attrSubHeader;
    private SimpleAttributeSet attrText;

    private JButton[] btnResident;
    private JButton[] btnEditResident;
    private JButton btnAll;
    private JLabel[] lblRoom;
    private String[] shifts;
    private String[] time;
    private JComboBox<String> jcbShift;
    private JComboBox<String> jcbTime;

    private JTextArea[] taResident;
    private JScrollPane[] spTextResident;
    private JTextArea taAll;

    private final Color lightgrey = new Color(245, 245, 245);
    private final Color lightyellow = new Color(255, 255, 202);

    private final ButtonListenerChangeCardsForResidentSpecificData listenerChangeCardsForResidentSpecificData;
    private final ButtonListenerEnableEditing listenerEnableEditing;

    private final ResourceBundle resourceBundle;
    private static final String RESOURCE_BUNDLE = "i18n/gui/gui"; //NON-NLS
    private final Adapter adapter = new DatabaseAdapter();

    /**
     *The constructor invokes methods which initialize the different components of the GUI.
     * It also invokes methods which use the DatabaseAdapter in order to fill the components with the data of the database.
     */
    public GUI() {

        this.resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);
        saveicon = new ImageIcon("src/main/resources/icons/Saveicon.png");
        editicon = new ImageIcon("src/main/resources/icons/Editicon.png");
        listenerChangeCardsForResidentSpecificData = new ButtonListenerChangeCardsForResidentSpecificData();
        listenerEnableEditing = new ButtonListenerEnableEditing();

        residentOverviewInitialization();

        jpanelInitialization();

        residentOverviewPanelInitialization();

        contentPaneInitialisation();

        residentTextAreaGridBagLayoutInitialization();

        databaseConnectionForFilters();

        filterAndJComboboxInitialization();

        residentButtonInitialization();

        roomLabelInitialization();

        residentTextAreaInitialization();

        editButtonInitialization();

        unspecificButtonAndTextareaInitialization();

    }

    /**
     * Uses GridBagConstraints to add the JPanel jpResident and jpRoom to jpText and Room as well as adding jpTextResident and jpEditResident
     * to jpTextResidentandEdit.
     */
    private void residentTextAreaGridBagLayoutInitialization() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weighty = 1;
        gbc.weightx = 1;
        jpResidentRoom.add(jpResident, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        jpResidentRoom.add(jpRoom, gbc);

        gbc.gridx = 0;
        jpTextResidentAndEdit.add(jpTextResident, gbc);
        gbc.weightx = 0;
        gbc.gridx = 1;
        jpTextResidentAndEdit.add(jpEditResident, gbc);
    }

    /**
     * Initializes the content pane and adds the different Panels.
     */
    private void contentPaneInitialisation(){
        Container c;
        c = getContentPane();
        c.add(jpResidentRoom, BorderLayout.WEST);
        c.add(jpFilterTextAll, BorderLayout.NORTH);
        c.add(cards, BorderLayout.CENTER);
        jpFilterTextAll.add(jpFilter, BorderLayout.WEST);
        cards.add(jpTextResidentAndEdit, "Bewohner"); //todo warning
        cards.add(jpSpecific, "Spezifisch");
        cl.show(cards, "Bewohner");
    }

    /**
     * Initializes the scroll panels for the resident overview and adds them to the JPanel jpSpecific.
     */
    private void residentOverviewPanelInitialization() {
        JScrollPane spBaseData = new JScrollPane(tpBaseData);
        JScrollPane spMedication = new JScrollPane(tpMedication);
        JScrollPane spDiagnosisSheet = new JScrollPane(tpDiagnosisSheet);
        JScrollPane spClosestRelative = new JScrollPane(tpClosestRelative);
        JScrollPane spVisits = new JScrollPane(tpVisits);
        JScrollPane spOther = new JScrollPane(tpOther);
        spTextResident = new JScrollPane[adapter.getResidents().size()];

        jpSpecific.add(spBaseData);
        jpSpecific.add(spMedication);
        jpSpecific.add(spDiagnosisSheet);
        jpSpecific.add(spClosestRelative);
        jpSpecific.add(spVisits);
        jpSpecific.add(spOther);

        spBaseData.setBorder(BorderFactory.createMatteBorder(12, 12, 6, 6, lightgrey));
        spMedication.setBorder(BorderFactory.createMatteBorder(12, 6, 6, 6, lightgrey));
        spDiagnosisSheet.setBorder(BorderFactory.createMatteBorder(12, 6, 6, 12, lightgrey));
        spClosestRelative.setBorder(BorderFactory.createMatteBorder(6, 12, 12, 6, lightgrey));
        spVisits.setBorder(BorderFactory.createMatteBorder(6, 6, 12, 6, lightgrey));
        spOther.setBorder(BorderFactory.createMatteBorder(6, 6, 12, 12, lightgrey));
    }

    /**
     * Initializes the TextArea and editing button on the Top of the Gui.
     */
    private void unspecificButtonAndTextareaInitialization() {
        taAll = new JTextArea();
        setShiftIncidentText();
        taAll.setLineWrap(true);
        taAll.setWrapStyleWord(true);
        jpFilterTextAll.add(taAll, BorderLayout.CENTER);
        taAll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taAll.setBackground(lightyellow);
        taAll.setEditable(false);
        taAll.setFont(new Font("TimesNewRoman", Font.BOLD, 15));

        btnAll = new JButton();
        btnAll.setBackground(lightgrey);
        btnAll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnAll.setPreferredSize(new Dimension(30, 51));
        jpFilterTextAll.add(btnAll, BorderLayout.EAST);
        btnAll.setIcon(editicon);
        btnAll.addActionListener(listenerEnableEditing);
    }

    /**
     * Initializes the main JPanels of the Gui.
     */
    private void jpanelInitialization() {
        jpResidentRoom = new JPanel(new GridBagLayout());
        jpFilterTextAll = new JPanel(new BorderLayout());
        jpFilter = new JPanel(new GridLayout(2, 1));
        jpTextResident = new JPanel(new GridLayout(10, 1));
        jpResident = new JPanel(new GridLayout(10, 1));
        jpRoom = new JPanel(new GridLayout(10, 1));
        jpEditResident = new JPanel(new GridLayout(10, 1));
        jpSpecific = new JPanel(new GridLayout(2, 3));
        jpTextResidentAndEdit = new JPanel(new GridBagLayout());
        cards = new JPanel(cl);
    }

    /**
     * Connects the JComboBoxes for the filters of shift and time and connects them via the Adapter to the Database.
     */
    private void databaseConnectionForFilters() {
        shifts = new String[]{resourceBundle.getString("morning_shift"), resourceBundle.getString("late_shift"), resourceBundle.getString("night_shift")};
        time = new String[(adapter.getShiftSchedules().size() / 3)];
        int n = 0;
        String previous = null;
        for (int i = 0; i < adapter.getShiftSchedules().size(); i++) {
            Date date = adapter.getShiftSchedules().get(i).getDate();
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String dateInString = formatter.format(date);
            if (i != 0) {
                if (dateInString.equals(previous)) {
                    i++;
                    n += 2;
                    continue;
                }
            }
            time[i - n] = String.format(dateInString, "dd.MM.yyyy");
            previous = dateInString;
        }
    }

    /**
     * Initializes the TextPanes and Documents for the specific resident overview.
     * Initializes 3 SimpleAttributeSets in order to later on format the individual parts of the overview differently.
     */
    private void residentOverviewInitialization() {
        tpBaseData = new JTextPane();
        tpMedication = new JTextPane();
        tpDiagnosisSheet = new JTextPane();
        tpClosestRelative = new JTextPane();
        tpVisits = new JTextPane();
        tpOther = new JTextPane();

        docBaseData = tpBaseData.getStyledDocument();
        docMedication = tpMedication.getStyledDocument();
        docDiagnosisSheet = tpDiagnosisSheet.getStyledDocument();
        docClosestRelative = tpClosestRelative.getStyledDocument();
        docVisits = tpVisits.getStyledDocument();
        docOther = tpOther.getStyledDocument();

        attrHeader = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrHeader, 26);
        StyleConstants.setBold(attrHeader, true);

        attrSubHeader = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrSubHeader, 20);
        StyleConstants.setBold(attrSubHeader, true);

        attrText = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrText, 20);

        tpBaseData.setEditable(false);
        tpMedication.setEditable(false);
        tpDiagnosisSheet.setEditable(false);
        tpClosestRelative.setEditable(false);
        tpVisits.setEditable(false);
        tpOther.setEditable(false);
    }

    /**
     * Initializes all of the combo boxes and changes the appearance and size of them depending on the selected language.
     * Initializes Itemlisteners for the combo boxes.
     */
    private void filterAndJComboboxInitialization() {
        JLabel lblspace;
        Object [] items = {
                new ImageIcon("src/main/resources/icons/Germanyicon.png"),
                new ImageIcon("src/main/resources/icons/UnitedKingdomicon.png")
        };

        String[] language = new String[]{"German","English"}; // todo löschen?
        jcbShift = new JComboBox<>(shifts);
        jpFilter.add(jcbShift);
        jcbTime = new JComboBox<>(time);
        lblspace = new JLabel("");
        lblspace.setBackground(lightyellow);
        lblspace.setOpaque(true);
        JComboBox<String> jcbLanguage = new JComboBox(items);
        jpFilter.add(jcbLanguage);
        jpFilter.add(jcbTime);
        jpFilter.add(lblspace);

        jcbLanguage.setSelectedIndex(selectedLanguage);
        jcbShift.setBorder(BorderFactory.createMatteBorder(20, 40, 10, 73, lightyellow));
        jcbTime.setBorder(BorderFactory.createMatteBorder(10, 40, 20, 73, lightyellow));
        jcbLanguage.setBorder(BorderFactory.createMatteBorder(20, 38, 10, 90, lightyellow));

        if (Locale.getDefault() == Locale.GERMAN) {
            jcbShift.setBorder(BorderFactory.createMatteBorder(20, 40, 10, 73, lightyellow));
            jcbTime.setBorder(BorderFactory.createMatteBorder(10, 40, 20, 73, lightyellow));
            jcbLanguage.setBorder(BorderFactory.createMatteBorder(20, 38, 10, 90, lightyellow));
            selectedLanguage = 0;

        } else {
            if (Locale.getDefault() == Locale.ENGLISH) {
                jcbShift.setBorder(BorderFactory.createMatteBorder(20, 40, 10, 86, lightyellow));
                jcbTime.setBorder(BorderFactory.createMatteBorder(10, 40, 20, 86, lightyellow));
                jcbLanguage.setBorder(BorderFactory.createMatteBorder(20, 50, 10, 93, lightyellow));
                selectedLanguage = 1;
            }
        }

        jpFilter.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        jcbShift.addItemListener(new ComboBoxItemListener(this));
        jcbTime.addItemListener(new ComboBoxItemListener(this));
        jcbLanguage.addItemListener(new ComboBoxListenerLanguage());
    }

    /**
     * Initializes the edit buttons for the individual resident incidents.
     */
    private void editButtonInitialization() {
        btnEditResident = new JButton[adapter.getResidents().size()];

        for (int i = 0; i < adapter.getResidents().size(); i++) {
            btnEditResident[i] = new JButton();
            btnEditResident[i].setBackground(lightgrey);
            btnEditResident[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            btnEditResident[i].setPreferredSize(new Dimension(30, 51));
            jpEditResident.add(btnEditResident[i]);
            btnEditResident[i].setIcon(editicon);
            btnEditResident[i].addActionListener(listenerEnableEditing);
        }
    }

    /**
     * Initializes the text areas for the individual resident incidents.
     */
    private void residentTextAreaInitialization() {
        taResident = new JTextArea[adapter.getResidents().size()];

        for (int i = 0; i < adapter.getResidents().size(); i++) {
            int resID = adapter.getResidents().get(i).getResID();
            taResident[i] = new JTextArea(MessageFormat.format(resourceBundle.getString("incidents.0"),adapter.getSingleIncident(resID).getDescription())); //todo reicht es hier den ersten Incident zu nehmen oder sollte man eine Datumskontrolle machen ?
            taResident[i].setLineWrap(true);
            taResident[i].setWrapStyleWord(true);
            taResident[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            taResident[i].setFont(new Font("TimesNewRoman", Font.PLAIN, 15));
            taResident[i].setEditable(false);
            spTextResident[i] = new JScrollPane(taResident[i]);
            jpTextResident.add(spTextResident[i]);
    }
    }

    /**
     * Initializes the room labels for the individual residents and uses the adapter to set the number out of the database.
     */
    private void roomLabelInitialization() {
        lblRoom = new JLabel[adapter.getResidents().size()];

        for (int i = 0; i < adapter.getResidents().size(); i++) {
            lblRoom[i] = new JLabel(MessageFormat.format(resourceBundle.getString("room.0"), adapter.getResidents().get(i).getRoom()), SwingConstants.CENTER);
            lblRoom[i].setBackground(lightgrey);
            lblRoom[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            lblRoom[i].setOpaque(true);
            lblRoom[i].setPreferredSize(new Dimension(132, 51));
            lblRoom[i].setFont(new Font("TimesNewRoman", Font.BOLD, 18));
            jpRoom.add(lblRoom[i]);
        }
    }

    /**
     * Initializes the Buttons for the individual resident overview and uses the adapter to set the name out of the database.
     */
    private void residentButtonInitialization() {
        btnResident = new JButton[adapter.getResidents().size()];

        for (int i = 0; i < adapter.getResidents().size(); i++) {
            btnResident[i] = new JButton(adapter.getResidents().get(i).getName() + " " + adapter.getResidents().get(i).getSurname());
            btnResident[i].setBackground(lightgrey);
            btnResident[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            btnResident[i].setPreferredSize(new Dimension(302, 51));
            btnResident[i].setFont(new Font("TimesNewRoman", Font.BOLD, 18));
            jpResident.add(btnResident[i]);
            btnResident[i].addActionListener(listenerChangeCardsForResidentSpecificData);
        }
    }

    /**
     * Uses the adapter to set the text of the TextAres of the individual residents, depending on the selected item of the combo box jcbTime.
     */
    public void setResidentIncidentText() {
        for (int i = 0; i < adapter.getResidents().size(); i++) {
            int resID = adapter.getResidents().get(i).getResID();
            String dateString = (String) jcbTime.getSelectedItem(); //String format
            Date date = null;
            try {
                date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            taResident[i].setText(MessageFormat.format(resourceBundle.getString("incidents.0"),  adapter.getSingleIncident(resID, date).getDescription()));
        }
    }

    /**
     *  Uses the adapter to set the text of the TextArea for the resident unspecific incidents, depending on the selected item of the combo boxes jcbTime and jcbShift.
     */
    public void setShiftIncidentText() {
        int shiftCategory;
        shiftCategory = (jcbShift.getSelectedIndex()) + 1;

        String dateString = (String) jcbTime.getSelectedItem(); //String format
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String shiftIncident = adapter.getSingleShiftSchedule(shiftCategory, date).getShiftIncidents();
        taAll.setText(shiftIncident);
    }

    /**
     * Invokes methods which fill out the individual resident overview with the data of the database.
     * @param index
     */
    public void setResidentSpecificData(int index) {
        Resident selectedResident = adapter.getSingleResident(index);
        MedPlan medPlan =adapter.getSingleMedPlan(selectedResident.getResID());
        ICE ice = adapter.getSingleICE(selectedResident.getResID());

        setBaseData(selectedResident);
        setMedication(selectedResident, medPlan);
        setDiagnosisSheet();
        setClosestRelative(ice);
        setVisits(selectedResident);
        setOther();

    }

    /**
     * Fills the textpane BaseData of the individual resident overview with data out of the database.
     * @param selectedResident
     */
    private void setBaseData(Resident selectedResident) {
        try {
            tpBaseData.setText("");
            docBaseData.insertString(docBaseData.getLength(), resourceBundle.getString("base.data"), attrHeader);
            docBaseData.insertString(docBaseData.getLength(), resourceBundle.getString("surname"), attrSubHeader);
            docBaseData.insertString(docBaseData.getLength(), selectedResident.getSurname(), attrText);
            docBaseData.insertString(docBaseData.getLength(), resourceBundle.getString("name"), attrSubHeader);
            docBaseData.insertString(docBaseData.getLength(), selectedResident.getName(), attrText);
            docBaseData.insertString(docBaseData.getLength(), resourceBundle.getString("id"), attrSubHeader);
            docBaseData.insertString(docBaseData.getLength(), String.valueOf(selectedResident.getResID()), attrText);
            docBaseData.insertString(docBaseData.getLength(), resourceBundle.getString("age"), attrSubHeader);
            docBaseData.insertString(docBaseData.getLength(), String.valueOf(selectedResident.getAge()), attrText);
            docBaseData.insertString(docBaseData.getLength(), resourceBundle.getString("room"), attrSubHeader);
            docBaseData.insertString(docBaseData.getLength(), String.valueOf(selectedResident.getRoom()), attrText);
            docBaseData.insertString(docBaseData.getLength(), resourceBundle.getString("station"), attrSubHeader);
            docBaseData.insertString(docBaseData.getLength(), String.valueOf(selectedResident.getStationID()), attrText);

        } catch (BadLocationException e) {
            System.out.println("BadLocationException");
        }
    }


    /**
     * Fills the textpane Medication of the individual resident overview with data out of the database.
     * @param selectedResident
     * @param medPlan
     */
    private void setMedication(Resident selectedResident, MedPlan medPlan) {
        try {
            tpMedication.setText("");
            docMedication.insertString(docMedication.getLength(), resourceBundle.getString("medication"), attrHeader);
            docMedication.insertString(docMedication.getLength(), resourceBundle.getString("medplan.id"), attrSubHeader);
            docMedication.insertString(docMedication.getLength(), String.valueOf(medPlan.getMedID()), attrText);
            docMedication.insertString(docMedication.getLength(), resourceBundle.getString("resident.id"), attrSubHeader);
            docMedication.insertString(docMedication.getLength(), String.valueOf(selectedResident.getResID()), attrText);
            docMedication.insertString(docMedication.getLength(), resourceBundle.getString("intake.frequency"), attrSubHeader);
            docMedication.insertString(docMedication.getLength(), String.valueOf(medPlan.getIntakeFrequency()), attrText);
            docMedication.insertString(docMedication.getLength(), resourceBundle.getString("concentration"), attrSubHeader);
            docMedication.insertString(docMedication.getLength(), String.valueOf(medPlan.getConcentration()), attrText);
            docMedication.insertString(docMedication.getLength(), resourceBundle.getString("medication.id"), attrSubHeader);
            docMedication.insertString(docMedication.getLength(), String.valueOf(medPlan.getMedicID()), attrText);
            docMedication.insertString(docMedication.getLength(), resourceBundle.getString("medication.name"), attrSubHeader);
            docMedication.insertString(docMedication.getLength(),  adapter.getSingleMedication(medPlan.getMedID()), attrText);

        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (BadLocationException be) {
            System.out.println("BadLocationException");
        }
    }

    /**
     * Fills the textpane DiagnosisSheet of the individual resident overview with data out of the database.
     */
    private void setDiagnosisSheet() {
        try {
            tpDiagnosisSheet.setText("");
            docDiagnosisSheet.insertString(docDiagnosisSheet.getLength(), resourceBundle.getString("diagnosis"), attrHeader);
            docDiagnosisSheet.insertString(docDiagnosisSheet.getLength(), resourceBundle.getString("currently.no.information.given"), attrText);

        } catch (BadLocationException be) {
            System.out.println("BadLocationException");
        }

    }

    /**
     * Fills the textpane ClosestRelative of the individual resident overview with data out of the database.
     * @param ice
     */
    private void setClosestRelative(ICE ice) {
        try {
            tpClosestRelative.setText(" ");
            docClosestRelative.insertString(docClosestRelative.getLength(), resourceBundle.getString("closest.relative"), attrHeader);
            docClosestRelative.insertString(docClosestRelative.getLength(), resourceBundle.getString("surname"), attrSubHeader);
            docClosestRelative.insertString(docClosestRelative.getLength(), ice.getSurname(), attrText);
            docClosestRelative.insertString(docClosestRelative.getLength(), resourceBundle.getString("name"), attrSubHeader);
            docClosestRelative.insertString(docClosestRelative.getLength(), ice.getName(), attrText);
            docClosestRelative.insertString(docClosestRelative.getLength(), resourceBundle.getString("ice.id"), attrSubHeader);
            docClosestRelative.insertString(docClosestRelative.getLength(), String.valueOf(ice.getIceID()), attrText);
            docClosestRelative.insertString(docClosestRelative.getLength(), resourceBundle.getString("address"), attrSubHeader);
            docClosestRelative.insertString(docClosestRelative.getLength(), ice.getAdress(), attrText);
            docClosestRelative.insertString(docClosestRelative.getLength(), resourceBundle.getString("phone.number"), attrSubHeader);
            docClosestRelative.insertString(docClosestRelative.getLength(), String.valueOf(ice.getTelnumber()), attrText);

//                + "\n" + "Telefonnummer: " + MessageFormat.format("{0,number,#}", ice.getTelnumber()));
//                       integer wird falsch angezeigt ?!   todo format

        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (BadLocationException be) {
            System.out.println("BadLocationException");
        }
    }

    /**
     * Fills the textpane Visits of the individual resident overview with data out of the database.
     * @param selectedResident
     */
    private void setVisits(Resident selectedResident) {
        try {
            tpVisits.setText(" ");
            docVisits.insertString(docVisits.getLength(), resourceBundle.getString("visits"), attrHeader);
            docVisits.insertString(docVisits.getLength(), "\n \n " + adapter.getSingleVisitDescription(selectedResident.getResID()), attrText);

        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (BadLocationException be) {
            System.out.println("BadLocationException");
        }
    }

    /**
     * Fills the textpane Other of the individual resident overview with data out of the database.
     */
    private void setOther() {
        try {
            tpOther.setText(" ");
            docOther.insertString(docOther.getLength(), resourceBundle.getString("other"), attrHeader);

            //todo was soll hier drauf?
        } catch (BadLocationException be) {
            System.out.println("BadLocationException");
        }
    }

    /**
     * Uses the adapter to update the database after a change was made in the resident specific textarea for incidents, by using the edit and save buttons.
     * @param index
     */
    private void saveChangesResidentIncidentText(int index) {
        String newText = taResident[index].getText();  //Get text that has been changed
        int resID = adapter.getResidents().get(index).getResID(); //get resid of selected resident
        String dateString = (String) jcbTime.getSelectedItem(); //String format
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        adapter.saveResidentIncidentsDatabase(newText, adapter.getSingleIncident(resID,date), resID, date);
    }

    /**
     * Uses the adapter to update the database after a change was made in the resident unspecific textarea for incidents, by using the edit and save buttons.
     */
    public void saveChangesShiftIncidentText() {
        String newText = taAll.getText(); //get new text
        String dateString = (String) jcbTime.getSelectedItem(); //String format
        int shiftCategory = (jcbShift.getSelectedIndex()) + 1; //get selected shift category
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int shiftID = adapter.getSingleShiftSchedule(shiftCategory, date).getShiftID();
        adapter.saveShiftIncidentsDatabase(newText, shiftID);
        //todo datums und shiftabhängigkeit
    }

    /**
     * ButtonListener for the resident buttons on the left side of the GUI.
     *
     */
    class ButtonListenerChangeCardsForResidentSpecificData implements ActionListener {
        /**
         * Manages that only one button can be activated at the same time.
         * Changes the appearance of the selected button and opens the corresponding overview of the resident. Invokes the method setResidentSpecificData to fill the Overview with data.
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            int index = Arrays.asList(btnResident).indexOf(e.getSource());

            setResidentSpecificData(index);

            if (isSaved) {
                if (buttonIdentification == index) {

                    cl.show(cards, "Bewohner");
                    buttonIdentification = -1;
                    hasSwitched = false;
                    btnResident[index].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    lblRoom[index].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                } else {
                    if (!hasSwitched) {

                        buttonIdentification = index;
                        //Aufspielen der Daten auf die Bewohnerübersicht
                        cl.show(cards, "Spezifisch");
                        btnResident[index].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 0, Color.lightGray));
                        lblRoom[index].setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, Color.lightGray));
                        hasSwitched = true;
                        lastButton = index;

                    } else {
                        buttonIdentification = index;
                        //Aufspielen der Daten auf die Bewohnerübersicht
                        cl.show(cards, "Spezifisch");
                        hasSwitched = true;

                        btnResident[lastButton].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        lblRoom[lastButton].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                        btnResident[index].setBorder(BorderFactory.createMatteBorder(4, 4, 4, 0, Color.lightGray));
                        lblRoom[index].setBorder(BorderFactory.createMatteBorder(4, 0, 4, 0, Color.lightGray));

                        lastButton = index;
                    }
                }
            }
        }
    }

    /**
     * ButtonListener for the edit and save buttons on the right side of the GUI.
     */
    class ButtonListenerEnableEditing implements ActionListener {
        /**
         * Manages that only one edit button can be activated at the same time.
         * Sets the chosen textarea for the resident incidents on editable, so that new incidents can be added.
         * Invokes the method saveChangesResidentIncidentText.
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() != btnAll) {

                int index = Arrays.asList(btnEditResident).indexOf(e.getSource());

                if (!beingEdited) {

                    if (isSaved) {

                        beingEdited = true;
                        taResident[index].setEditable(true);
                        btnEditResident[index].setIcon(saveicon);
                        isSaved = false;
                        indexComparison = index;
                    }
                } else {
                    if (!isSaved && btnAll.getIcon() == editicon) {

                        if (indexComparison == index && e.getSource() != btnAll) {

                            taResident[index].setEditable(false);
                            btnEditResident[index].setIcon(editicon);
                            saveChangesResidentIncidentText(index);
                            isSaved = true;
                            beingEdited = false;
                        }
                    }
                }
            } else {
                if (!beingEdited) {

                    if (isSaved) {

                        beingEdited = true;
                        taAll.setEditable(true);
                        btnAll.setIcon(saveicon);
                        isSaved = false;
                    }
                } else {
                    if (!isSaved && btnAll.getIcon() == saveicon) {
                        if (btnAll == e.getSource()) {

                            taAll.setEditable(false);
                            btnAll.setIcon(editicon);
                            saveChangesShiftIncidentText();
                            isSaved = true;
                            beingEdited = false;
                        }
                    }
                }
            }
        }
    }
}
