package library.presentation;

import library.model.implementation.*;
import library.persistence.implementation.DatabaseService;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;


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
    Document docBaseData,docMedication, docDiagnosisSheet, docClosestRelative, docVisits, docOther;

    SimpleAttributeSet attrHeader;
    SimpleAttributeSet attrSubHeader;
    SimpleAttributeSet attrText;


    JButton[] btnResident;
    JButton[] btnEditResident;
    JButton btnAll;
    JLabel[] lblRoom;
    String[] shifts;
    String[] time;
    JComboBox jcbShift;
    JComboBox jcbTime;


    JTextArea[] taResident;
    JScrollPane[] spTextResident;
    JTextArea taAll;


    GridBagConstraints gbc = new GridBagConstraints();

    Color lightgrey = new Color(245, 245, 245);
    Color lightyellow = new Color(255, 255, 202);


    public GUI() {

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
        StyleConstants.setFontSize(attrHeader,26);
        StyleConstants.setBold(attrHeader,true);

        attrSubHeader = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrSubHeader, 20);
        StyleConstants.setBold(attrSubHeader,true);

        attrText = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrText, 20);

        tpBaseData.setEditable(false);
        tpMedication.setEditable(false);
        tpDiagnosisSheet.setEditable(false);
        tpClosestRelative.setEditable(false);
        tpVisits.setEditable(false);
        tpOther.setEditable(false);


        //todo in Methode auslagern: getData
        ArrayList<Resident> residents;
        residents = DatabaseService.getResidents();
        ArrayList<Incident> incidents;
        incidents = DatabaseService.getIncidents();

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
        jpSpecific = new JPanel(new GridBagLayout());
        jpTextResidentAndEdit = new JPanel(new GridBagLayout());
        cards = new JPanel(cl);

        //todo: internationalization
        //getting pressed button:



        taResident = new JTextArea[residents.size()];




        spBaseData = new JScrollPane(tpBaseData);
        spMedication = new JScrollPane(tpMedication);
        spDiagnosisSheet = new JScrollPane(tpDiagnosisSheet);
        spClosestRelative = new JScrollPane(tpClosestRelative);
        spVisits = new JScrollPane(tpVisits);
        spOther = new JScrollPane(tpOther);
        spTextResident = new JScrollPane[residents.size()];


        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weighty = 1;
        gbc.weightx = 1;
        jpSpecific.add(spBaseData, gbc);
        gbc.gridx = 1;
        jpSpecific.add(spMedication, gbc);
        gbc.gridx = 2;
        jpSpecific.add(spDiagnosisSheet, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        jpSpecific.add(spClosestRelative, gbc);
        gbc.gridx = 1;
        jpSpecific.add(spVisits, gbc);
        gbc.gridx = 2;
        jpSpecific.add(spOther, gbc);


        c.add(jpResidentRoom, BorderLayout.WEST);
        c.add(jpFilterTextAll, BorderLayout.NORTH);
        c.add(cards, BorderLayout.CENTER);
        jpFilterTextAll.add(jpFilter, BorderLayout.WEST);
        cards.add(jpTextResidentAndEdit, "Bewohner");
        cards.add(jpSpecific, "Spezifisch");
        cl.show(cards, "Bewohner");


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
        shifts = new String[]{"Frühschicht", "Spätschicht", "Nachtschicht"};
        LocalDate today = LocalDate.now();
        time = new String[6]; //todo currentdate
        for (int i = 0; i < time.length; i++){
            time[i]=today.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
            today.plusDays(1);
        }

        GUI.ButtonListener1 bL1 = new GUI.ButtonListener1();
        GUI.Buttonlistener2 bl2 = new GUI.Buttonlistener2();

        for (int i = 0; i < residents.size(); i++) {
            btnResident[i] = new JButton(residents.get(i).getName()+ " " + residents.get(i).getSurname());
            btnResident[i].setBackground(lightgrey);
            btnResident[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            btnResident[i].setPreferredSize(new Dimension(302, 51));
            btnResident[i].setFont(new Font("TimesNewRoman", Font.BOLD, 18));
            jpResident.add(btnResident[i]);
            btnResident[i].addActionListener(bL1);
        }

        for (int i = 0; i < residents.size(); i++) {
            lblRoom[i] = new JLabel("Raum " + (residents.get(i).getRoom()), SwingConstants.CENTER);
            lblRoom[i].setBackground(lightgrey);
            lblRoom[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            lblRoom[i].setOpaque(true);
            lblRoom[i].setPreferredSize(new Dimension(132, 51));
            lblRoom[i].setFont(new Font("TimesNewRoman", Font.BOLD, 18));
            jpRoom.add(lblRoom[i]);
        }

        for (int i = 0; i < residents.size(); i++) {
            int resID = residents.get(i).getResID();
           //todo
            taResident[i] = new JTextArea("Vorfälle: " + Incident.get(resID).getDescription());
            taResident[i].setLineWrap(true);
            taResident[i].setWrapStyleWord(true);
            taResident[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            taResident[i].setFont(new Font("TimesNewRoman", Font.PLAIN, 15));
            taResident[i].setEditable(false);
            spTextResident[i] = new JScrollPane(taResident[i]);
            jpTextResident.add(spTextResident[i]);
        }

        for (int i = 0; i < residents.size(); i++) {
            btnEditResident[i] = new JButton();
            btnEditResident[i].setBackground(lightgrey);
            btnEditResident[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            btnEditResident[i].setPreferredSize(new Dimension(30, 51));
            jpEditResident.add(btnEditResident[i]);
            btnEditResident[i].setIcon(editicon);
            btnEditResident[i].addActionListener(bl2);
        }


        jcbShift = new JComboBox(shifts);
        jpFilter.add(jcbShift);
        jcbTime = new JComboBox(time);
        jpFilter.add(jcbTime);
        jcbTime.setBorder(BorderFactory.createMatteBorder(5, 30, 17, 300, lightyellow));
        jcbShift.setBorder(BorderFactory.createMatteBorder(17, 30, 5, 300, lightyellow));
        jpFilter.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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
        jcbShift.addItemListener(new ComboBoxItemListener());

        btnAll = new JButton();
        btnAll.setBackground(lightgrey);
        btnAll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnAll.setPreferredSize(new Dimension(30, 51));
        jpFilterTextAll.add(btnAll, BorderLayout.EAST);
        btnAll.setIcon(editicon);
        btnAll.addActionListener(bl2);
    }

    class ComboBoxItemListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            setShiftIncidentText();
        }
    }

    private void setShiftIncidentText() {
        int shiftCategory;
        Object date;
        shiftCategory= (jcbShift.getSelectedIndex())+1;
        date = jcbTime.getSelectedItem();
        taAll.setText(ShiftSchedule.get(shiftCategory, date).getShiftIncidents());
    }

    class ButtonListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int index = Arrays.asList(btnResident).indexOf(e.getSource());

            setResidentSpecificData(index);

            if (isSaved == true) {
                if (buttonIdentification == index) {

                    cl.show(cards, "Bewohner");
                    buttonIdentification = -1;
                    hasSwitched = false;
                    btnResident[index].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    lblRoom[index].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                } else {
                    if (hasSwitched == false) {

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
        Resident selectedResident = Resident.get(index);
        MedPlan medPlan = MedPlan.get(selectedResident.getResID());
        ICE ice = ICE.get(selectedResident.getResID());

        setBaseData(selectedResident);
        setMedication(selectedResident,medPlan);
        setDiagnosisSheet();
        setClosestRelative(ice);
        setVisits(selectedResident);
        setOther();

    }

    private void setBaseData(Resident selectedResident) {
        try {
            tpBaseData.setText("");
            docBaseData.insertString(docBaseData.getLength(), " Base Data", attrHeader);
            docBaseData.insertString(docBaseData.getLength(), "\n \n Surname: ", attrSubHeader);
            docBaseData.insertString(docBaseData.getLength(), selectedResident.getSurname(), attrText);
            docBaseData.insertString(docBaseData.getLength(), "\n \n Name: ", attrSubHeader);
            docBaseData.insertString(docBaseData.getLength(), selectedResident.getName(), attrText);
            docBaseData.insertString(docBaseData.getLength(), "\n \n ID: ", attrSubHeader);
            docBaseData.insertString(docBaseData.getLength(), String.valueOf(selectedResident.getResID()), attrText);
            docBaseData.insertString(docBaseData.getLength(), "\n \n Age: ", attrSubHeader);
            docBaseData.insertString(docBaseData.getLength(), String.valueOf(selectedResident.getAge()), attrText);
            docBaseData.insertString(docBaseData.getLength(), "\n \n Room: ", attrSubHeader);
            docBaseData.insertString(docBaseData.getLength(), String.valueOf(selectedResident.getRoom()), attrText);
            docBaseData.insertString(docBaseData.getLength(), "\n \n Station: ", attrSubHeader);
            docBaseData.insertString(docBaseData.getLength(), String.valueOf(selectedResident.getStationID()), attrText);

        }catch(BadLocationException e){}
    }


    private void setMedication(Resident selectedResident, MedPlan medPlan) {
        try{
            tpMedication.setText("");
            docMedication.insertString(docMedication.getLength(), " Medication", attrHeader);
            docMedication.insertString(docMedication.getLength(), "\n \n MedPlan ID: ", attrSubHeader);
            docMedication.insertString(docMedication.getLength(), String.valueOf(medPlan.getMedID()), attrText);
            docMedication.insertString(docMedication.getLength(), "\n \n Resident ID: ", attrSubHeader);
            docMedication.insertString(docMedication.getLength(), String.valueOf(selectedResident.getResID()), attrText);
            docMedication.insertString(docMedication.getLength(), "\n \n Intake frequency: ", attrSubHeader);
            docMedication.insertString(docMedication.getLength(), String.valueOf(medPlan.getIntakeFrequency()), attrText);
            docMedication.insertString(docMedication.getLength(), "\n \n Concentration: ", attrSubHeader);
            docMedication.insertString(docMedication.getLength(), String.valueOf(medPlan.getConcentration()), attrText);
            docMedication.insertString(docMedication.getLength(), "\n \n Medication ID: ", attrSubHeader);
            docMedication.insertString(docMedication.getLength(), String.valueOf(medPlan.getMedicID()), attrText);
            docMedication.insertString(docMedication.getLength(), "\n \n Medication name: ", attrSubHeader);
            docMedication.insertString(docMedication.getLength(), Medication.get(medPlan.getMedID()), attrText);

        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (BadLocationException be){ }
    }

    private void setDiagnosisSheet() {
       try{
           tpDiagnosisSheet.setText("");
           docDiagnosisSheet.insertString(docDiagnosisSheet.getLength()," Diagnosis", attrHeader);
       } catch (BadLocationException be){ }

       // was soll hier drauf?
    }

    private void setClosestRelative(ICE ice) {
        try{
            tpClosestRelative.setText(" ");
            docClosestRelative.insertString(docClosestRelative.getLength(), " Closest Relative", attrHeader);
            docClosestRelative.insertString(docClosestRelative.getLength(), "\n \n Surname: ", attrSubHeader);
            docClosestRelative.insertString(docClosestRelative.getLength(), ice.getSurname(), attrText);
            docClosestRelative.insertString(docClosestRelative.getLength(), "\n \n Name: ", attrSubHeader);
            docClosestRelative.insertString(docClosestRelative.getLength(), ice.getName(), attrText);
            docClosestRelative.insertString(docClosestRelative.getLength(), "\n \n Ice ID: ", attrSubHeader);
            docClosestRelative.insertString(docClosestRelative.getLength(), String.valueOf(ice.getIceID()), attrText);
            docClosestRelative.insertString(docClosestRelative.getLength(), "\n \n Address: ", attrSubHeader);
            docClosestRelative.insertString(docClosestRelative.getLength(), ice.getAdress(), attrText);
            docClosestRelative.insertString(docClosestRelative.getLength(), "\n \n Phone number: ", attrSubHeader);
            docClosestRelative.insertString(docClosestRelative.getLength(), "MUSS EINGEFÜGT WERDEN", attrText);

//                + "\n" + "Telefonnummer: " + MessageFormat.format("{0,number,#}", ice.getTelnumber())); //todo format

        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (BadLocationException be){ }
    }

    private void setVisits(Resident selectedResident) {
        try{
        tpVisits.setText(" ");
        docVisits.insertString(docVisits.getLength(), " Visits", attrHeader);
        docVisits.insertString(docVisits.getLength(), "\n \n " + Visits.get(selectedResident.getResID()), attrText);

        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (BadLocationException be){ }
    }

    private void setOther() {
        try{
        tpOther.setText(" ");
        docOther.insertString(docOther.getLength()," Other", attrHeader);

        //was soll hier drauf?
        } catch (BadLocationException be){ }
    }


    class Buttonlistener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {


            if (e.getSource() != btnAll) {

                int index = Arrays.asList(btnEditResident).indexOf(e.getSource());

                if (beingEdited == false) {

                    if (isSaved == true) {

                        beingEdited = true;
                        taResident[index].setEditable(true);
                        btnEditResident[index].setIcon(saveicon);
                        isSaved = false;
                        indexComparison = index;

                    }
                } else {
                    if (isSaved == false && btnAll.getIcon()==editicon) {
                        if (indexComparison == index && e.getSource() != btnAll) {

                            taResident[index].setEditable(false);
                            btnEditResident[index].setIcon(editicon);
                            //Sachen abspeichern Mehode
                            isSaved = true;
                            beingEdited = false;

                        }
                    }
                }
            } else {
                if (beingEdited == false) {

                    if (isSaved == true) {

                        beingEdited = true;
                        taAll.setEditable(true);
                        btnAll.setIcon(saveicon);
                        isSaved = false;

                    }
                } else {
                    if (isSaved == false && btnAll.getIcon()==saveicon) {
                        if (btnAll ==e.getSource()) {

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

}
