package library.presentation;

import library.model.implementation.*;
import library.persistence.implementation.DatabaseFactory;
import library.persistence.implementation.DatabaseService;

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


    ImageIcon saveicon;
    ImageIcon editicon;

    Container c;

    CardLayout cl = new CardLayout();

    JPanel jpResidentRoom, jpFilterTextAll, jpFilter, jpTextResident, jpResident, jpRoom, jpSpecific, jpEditResident, jpTextResidentAndEdit, cards;

    JScrollPane spBaseData, spMedication, spDiagnosisSheet, spClosestRelative, spVisits, spOther;
    JTextPane tpBaseData, tpMedication, tpDiagnosisSheet, tpClosestRelative, tpVisits, tpOther;
    Document docBaseData, docMedication, docDiagnosisSheet, docClosestRelative, docVisits, docOther;

    SimpleAttributeSet attrHeader;
    SimpleAttributeSet attrSubHeader;
    SimpleAttributeSet attrText;


    JButton[] btnResident;
    JButton[] btnEditResident;
    JButton btnAll;
    JLabel[] lblRoom;
    String[] shifts;
    String[] time;
    JComboBox<String> jcbShift;
    JComboBox<String> jcbTime;


    JTextArea[] taResident;
    JScrollPane[] spTextResident;
    JTextArea taAll;


    GridBagConstraints gbc = new GridBagConstraints();

    Color lightgrey = new Color(245, 245, 245);
    Color lightyellow = new Color(255, 255, 202);

    private final ResourceBundle resourceBundle;
    private static final String RESOURCE_BUNDLE = "i18n/gui/gui"; //NON-NLS
    DatabaseFactory factory = new DatabaseFactory();
    public GUI() {

        this.resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);
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


        saveicon = new ImageIcon("Saveicon.png");
        editicon = new ImageIcon("Editicon.png");

        c = getContentPane();


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

        //todo: internationalization
        //getting pressed button:


        taResident = new JTextArea[factory.residents.size()];


        spBaseData = new JScrollPane(tpBaseData);
        spMedication = new JScrollPane(tpMedication);
        spDiagnosisSheet = new JScrollPane(tpDiagnosisSheet);
        spClosestRelative = new JScrollPane(tpClosestRelative);
        spVisits = new JScrollPane(tpVisits);
        spOther = new JScrollPane(tpOther);
        spTextResident = new JScrollPane[factory.residents.size()];


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

        c.add(jpResidentRoom, BorderLayout.WEST);
        c.add(jpFilterTextAll, BorderLayout.NORTH);
        c.add(cards, BorderLayout.CENTER);
        jpFilterTextAll.add(jpFilter, BorderLayout.WEST);
        cards.add(jpTextResidentAndEdit, "Bewohner");
        cards.add(jpSpecific, "Spezifisch");
        cl.show(cards, "Bewohner");


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


        btnResident = new JButton[10];
        btnEditResident = new JButton[10];
        lblRoom = new JLabel[10];

        shifts = new String[]{resourceBundle.getString("morning_shift"), resourceBundle.getString("late_shift"), resourceBundle.getString("night_shift")};
        time = new String[(factory.shiftSchedules.size() / 3)];
        int n = 0;
        String previous = null;
        for (int i = 0; i < factory.shiftSchedules.size(); i++) {
            Date date = factory.shiftSchedules.get(i).getDate();
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

        jcbShift = new JComboBox<>(shifts);
        jpFilter.add(jcbShift);
        jcbTime = new JComboBox<>(time);
        jpFilter.add(jcbTime);
        if (Locale.getDefault() == Locale.GERMAN) {
            jcbTime.setBorder(BorderFactory.createMatteBorder(5, 30, 17, 300, lightyellow));
            jcbShift.setBorder(BorderFactory.createMatteBorder(17, 30, 5, 300, lightyellow));
        } else {
            if (Locale.getDefault() == Locale.ENGLISH) {
                jcbTime.setBorder(BorderFactory.createMatteBorder(5, 30, 17, 312, lightyellow));
                jcbShift.setBorder(BorderFactory.createMatteBorder(17, 30, 5, 312, lightyellow));
            }
        }
        jpFilter.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GUI.ButtonListener1 bL1 = new GUI.ButtonListener1();
        GUI.Buttonlistener2 bl2 = new GUI.Buttonlistener2();

        for (int i = 0; i < factory.residents.size(); i++) {
            btnResident[i] = new JButton(factory.residents.get(i).getName() + " " + factory.residents.get(i).getSurname());
            btnResident[i].setBackground(lightgrey);
            btnResident[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            btnResident[i].setPreferredSize(new Dimension(302, 51));
            btnResident[i].setFont(new Font("TimesNewRoman", Font.BOLD, 18));
            jpResident.add(btnResident[i]);
            btnResident[i].addActionListener(bL1);
        }

        for (int i = 0; i < factory.residents.size(); i++) {
            lblRoom[i] = new JLabel(MessageFormat.format(resourceBundle.getString("room.0"), factory.residents.get(i).getRoom()), SwingConstants.CENTER);
            lblRoom[i].setBackground(lightgrey);
            lblRoom[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            lblRoom[i].setOpaque(true);
            lblRoom[i].setPreferredSize(new Dimension(132, 51));
            lblRoom[i].setFont(new Font("TimesNewRoman", Font.BOLD, 18));
            jpRoom.add(lblRoom[i]);
        }

        for (int i = 0; i < factory.residents.size(); i++) {
            int resID = factory.residents.get(i).getResID();
            taResident[i] = new JTextArea(MessageFormat.format(resourceBundle.getString("incidents.0"), Objects.requireNonNull(Incident.get(resID)).getDescription()));
            taResident[i].setLineWrap(true);
            taResident[i].setWrapStyleWord(true);
            taResident[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            taResident[i].setFont(new Font("TimesNewRoman", Font.PLAIN, 15));
            taResident[i].setEditable(false);
            spTextResident[i] = new JScrollPane(taResident[i]);
            jpTextResident.add(spTextResident[i]);
        }

        for (int i = 0; i < factory.residents.size(); i++) {
            btnEditResident[i] = new JButton();
            btnEditResident[i].setBackground(lightgrey);
            btnEditResident[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            btnEditResident[i].setPreferredSize(new Dimension(30, 51));
            jpEditResident.add(btnEditResident[i]);
            btnEditResident[i].setIcon(editicon);
            btnEditResident[i].addActionListener(bl2);
        }


        taAll = new JTextArea();
        setShiftIncidentText();
        taAll.setLineWrap(true);
        taAll.setWrapStyleWord(true);
        jpFilterTextAll.add(taAll, BorderLayout.CENTER);
        taAll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taAll.setBackground(lightyellow);
        taAll.setEditable(false);
        taAll.setFont(new Font("TimesNewRoman", Font.BOLD, 15));

        jcbShift.addItemListener(new ComboBoxItemListener());
        jcbTime.addItemListener(new ComboBoxItemListener());

        btnAll = new JButton();
        btnAll.setBackground(lightgrey);
        btnAll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnAll.setPreferredSize(new Dimension(30, 51));
        jpFilterTextAll.add(btnAll, BorderLayout.EAST);
        btnAll.setIcon(editicon);
        btnAll.addActionListener(bl2);
    }

    class ComboBoxItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            setShiftIncidentText();
            setResidentIncidentText();
        }
    }

    private void setResidentIncidentText() {
        for (int i = 0; i < factory.residents.size(); i++) {
            int resID = factory.residents.get(i).getResID();
            String dateString = (String) jcbTime.getSelectedItem(); //String format
            Date date = null;
            try {
                date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            taResident[i].setText(MessageFormat.format(resourceBundle.getString("incidents.0"), Objects.requireNonNull(Incident.get(resID, date)).getDescription()));
        }
    }

    private void setShiftIncidentText() {
        int shiftCategory;
        shiftCategory = (jcbShift.getSelectedIndex()) + 1;

        String dateString = (String) jcbTime.getSelectedItem(); //String format
        Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String shiftIncident = Objects.requireNonNull(factory.getSingleShiftSchedule(shiftCategory, date)).getShiftIncidents();
        taAll.setText(shiftIncident);
    }

    class ButtonListener1 implements ActionListener {
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

    public void setResidentSpecificData(int index) {
        Resident selectedResident = factory.getSingleResident(index);
        MedPlan medPlan = MedPlan.get(selectedResident.getResID());
        ICE ice = ICE.get(selectedResident.getResID());

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
            docMedication.insertString(docMedication.getLength(), Medication.get(medPlan.getMedID()), attrText);

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
            docVisits.insertString(docVisits.getLength(), "\n \n " + factory.getSingleVisitDescription(selectedResident.getResID()), attrText);

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


    class Buttonlistener2 implements ActionListener {
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
                            saveChanges(index);
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
                            //Sachen abspeichern Mehode
                            isSaved = true;
                            beingEdited = false;

                        }
                    }
                }
            }
        }
    }

    private void saveChanges(int index) {
        String newText = taResident[index].getText();
        int resID = factory.residents.get(index).getResID();
        DatabaseService.updateIncidentsDatabase(newText, factory.incidents.get(index));

    }

}
