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


public class GUI extends JFrame {

    static boolean beingEdited = false;
    static boolean isSaved = true;
    static boolean hasSwitched = false;
    static int buttonIdentification = -1;
    static int indexComparison = -1;
    static int lastButton = -1;
    static int selectedLanguage = 0;

    ImageIcon saveicon;
    ImageIcon editicon;

    Container c;

    CardLayout cl = new CardLayout();

    JPanel jpResidentRoom, jpFilterTextAll, jpFilter, jpTextResident, jpResident, jpRoom, jpSpecific, jpEditResident, jpTextResidentAndEdit, cards;

    JScrollPane spBaseData, spMedication, spDiagnosisSheet, spClosestRelative, spVisits, spOther;
    JTextPane tpBaseData, tpMedication, tpDiagnosisSheet, tpClosestRelative, tpVisits, tpOther;
    Document docBaseData, docMedication, docDiagnosisSheet, docClosestRelative, docVisits, docOther;
    JLabel lblspace;

    SimpleAttributeSet attrHeader;
    SimpleAttributeSet attrSubHeader;
    SimpleAttributeSet attrText;

    JButton[] btnResident;
    JButton[] btnEditResident;
    JButton btnAll;
    JLabel[] lblRoom;
    String[] shifts;
    String[] time;
    String[] language;
    JComboBox<String> jcbShift;
    JComboBox<String> jcbTime;
    JComboBox<String> jcbLanguage;

    JTextArea[] taResident;
    JScrollPane[] spTextResident;
    JTextArea taAll;

    GridBagConstraints gbc = new GridBagConstraints();

    Color lightgrey = new Color(245, 245, 245);
    Color lightyellow = new Color(255, 255, 202);

    ButtonListenerChangeCardsForResidentSpecificData listenerChangeCardsForResidentSpecificData;
    ButtonListenerEnableEditing listenerEnableEditing;

    private final ResourceBundle resourceBundle;
    private static final String RESOURCE_BUNDLE = "i18n/gui/gui"; //NON-NLS
  // gui does not use db types from persistence but uses methods from the adapter
    private Adapter adapter = new DatabaseAdapter();

    public GUI() {

        this.resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);
        saveicon = new ImageIcon("Saveicon.png");
        editicon = new ImageIcon("Editicon.png");
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

        roomLabelInitalization();

        residentTextAreaInitialization();

        editButtonInitialization();

        unspecificButtonAndTextareaInitialization();

    }

    private void residentTextAreaGridBagLayoutInitialization() {
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

    private void contentPaneInitialisation(){
        c = getContentPane();
        c.add(jpResidentRoom, BorderLayout.WEST);
        c.add(jpFilterTextAll, BorderLayout.NORTH);
        c.add(cards, BorderLayout.CENTER);
        jpFilterTextAll.add(jpFilter, BorderLayout.WEST);
        cards.add(jpTextResidentAndEdit, "Bewohner");
        cards.add(jpSpecific, "Spezifisch");
        cl.show(cards, "Bewohner");
    }

    private void residentOverviewPanelInitialization() {
        spBaseData = new JScrollPane(tpBaseData);
        spMedication = new JScrollPane(tpMedication);
        spDiagnosisSheet = new JScrollPane(tpDiagnosisSheet);
        spClosestRelative = new JScrollPane(tpClosestRelative);
        spVisits = new JScrollPane(tpVisits);
        spOther = new JScrollPane(tpOther);
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

    private void filterAndJComboboxInitialization() {

        Object [] items = {
                new ImageIcon("Germanyicon.png"),
                new ImageIcon("UnitedKingdomicon.png")
        };

        language = new String[]{"German","English"};
        jcbShift = new JComboBox<>(shifts);
        jpFilter.add(jcbShift);
        jcbTime = new JComboBox<>(time);
        lblspace = new JLabel("");
        lblspace.setBackground(lightyellow);
        lblspace.setOpaque(true);
        jcbLanguage = new JComboBox(items);
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

    private void roomLabelInitalization() {
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
        }
    }

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
        }
    }

    private void setDiagnosisSheet() {
        try {
            tpDiagnosisSheet.setText("");
            docDiagnosisSheet.insertString(docDiagnosisSheet.getLength(), resourceBundle.getString("diagnosis"), attrHeader);
            docDiagnosisSheet.insertString(docDiagnosisSheet.getLength(), resourceBundle.getString("currently.no.information.given"), attrText);

        } catch (BadLocationException be) {
        }

        // todo was soll hier drauf?
    }

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
        }
    }

    private void setVisits(Resident selectedResident) {
        try {
            tpVisits.setText(" ");
            docVisits.insertString(docVisits.getLength(), resourceBundle.getString("visits"), attrHeader);
            docVisits.insertString(docVisits.getLength(), "\n \n " + adapter.getSingleVisitDescription(selectedResident.getResID()), attrText);

        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (BadLocationException be) {
        }
    }

    private void setOther() {
        try {
            tpOther.setText(" ");
            docOther.insertString(docOther.getLength(), resourceBundle.getString("other"), attrHeader);

            //todo was soll hier drauf?
        } catch (BadLocationException be) {
        }
    }

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

    //todo datumsabhängigkeit

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


    class ButtonListenerChangeCardsForResidentSpecificData implements ActionListener {
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

    class ButtonListenerEnableEditing implements ActionListener {
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
                            //Sachen abspeichern Mehode
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
