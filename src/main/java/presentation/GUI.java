package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


public class GUI extends JFrame {

    static boolean wirdbearbeitet = false;
    static boolean istgespeichert = true;
    static boolean hatgeswitcht = false;


    ImageIcon saveicon;
    ImageIcon editicon;


    Container c;

    CardLayout cl = new CardLayout();

    JPanel jpBewohnerRaum, jpFilterTextAlle, jpFilter, jpTextBewohner, jpBewohner, jpRaum, jpSpezifisch, jpBearbeitenBewohner, jpTextBewohnerUndBearbeiten, cards;

    JButton btnBewohner[];
    JButton btnBearbeitenBewohner[];
    JLabel lblRaum[];
    String[] schichten;
    String[] zeiten;
    JComboBox jcbSchicht;
    JComboBox jcbZeit;

    JTextArea taBewohner[] = new JTextArea[10];
    JScrollPane spBewohner[] = new JScrollPane[10];

    GridBagConstraints gbc = new GridBagConstraints();

    Color lightgrey = new Color(245, 245, 245);
    Color lightyellow = new Color(255, 255, 202);


    public GUI() {

        saveicon = new ImageIcon("Saveicon.png");
        editicon = new ImageIcon("Editicon.png");

        c = getContentPane();

        jpBewohnerRaum = new JPanel(new GridBagLayout());
        jpFilterTextAlle = new JPanel(new BorderLayout());
        jpFilter = new JPanel(new GridLayout(2, 1));
        jpTextBewohner = new JPanel(new GridLayout(10, 1));
        jpBewohner = new JPanel(new GridLayout(10, 1));
        jpRaum = new JPanel(new GridLayout(10, 1));
        jpBearbeitenBewohner = new JPanel(new GridLayout(10, 1));
        jpSpezifisch = new JPanel(new GridLayout());
        jpTextBewohnerUndBearbeiten = new JPanel(new GridBagLayout());
        cards = new JPanel(cl);

        //Platzhalter bis sich über das Design der zweiten Seite beraten wurde :
        JLabel platzhalter = new JLabel();
        platzhalter.setOpaque(true);
        platzhalter.setBackground(Color.DARK_GRAY);
        jpSpezifisch.add(platzhalter);


        c.add(jpBewohnerRaum, BorderLayout.WEST);
        c.add(jpFilterTextAlle, BorderLayout.NORTH);
        c.add(cards, BorderLayout.CENTER);
        jpFilterTextAlle.add(jpFilter, BorderLayout.WEST);
        cards.add(jpTextBewohnerUndBearbeiten, "Bewohner");
        cards.add(jpSpezifisch, "Spezifisch");
        cl.show(cards, "Bewohner");


        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weighty = 1;
        gbc.weightx = 1;
        jpBewohnerRaum.add(jpBewohner, gbc);
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        jpBewohnerRaum.add(jpRaum, gbc);


        gbc.gridx = 0;
        jpTextBewohnerUndBearbeiten.add(jpTextBewohner, gbc);
        gbc.weightx = 0;
        gbc.gridx = 1;
        jpTextBewohnerUndBearbeiten.add(jpBearbeitenBewohner, gbc);


        btnBewohner = new JButton[10];
        btnBearbeitenBewohner = new JButton[10];
        lblRaum = new JLabel[10];
        schichten = new String[]{"Frühschicht", "Spätschicht", "Nachtschicht"};
        zeiten = new String[]{"25.04.2021", "26.04.2021", "27.04.2021", "28.04.2021", "29.04.2021", "30.04.2021"};


        GUI.ButtonListener1 bL1 = new GUI.ButtonListener1();
        GUI.Buttonlistener2 bl2 = new GUI.Buttonlistener2();


        for (int i = 0; i < 10; i++) {
            btnBewohner[i] = new JButton("Bewohner " + (i + 1));
            btnBewohner[i].setBackground(lightgrey);
            btnBewohner[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            btnBewohner[i].setPreferredSize(new Dimension(302, 51));
            jpBewohner.add(btnBewohner[i]);
            btnBewohner[i].addActionListener(bL1);
        }

        for (int i = 0; i < 10; i++) {
            lblRaum[i] = new JLabel("Raum " + (i + 1), SwingConstants.CENTER);
            lblRaum[i].setBackground(lightgrey);
            lblRaum[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            lblRaum[i].setOpaque(true);
            lblRaum[i].setPreferredSize(new Dimension(132, 51));
            jpRaum.add(lblRaum[i]);
        }

        for (int i = 0; i < 10; i++) {
            taBewohner[i] = new JTextArea("TEST " + (i + 1));
            taBewohner[i].setLineWrap(true);
            taBewohner[i].setWrapStyleWord(true);
            taBewohner[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            taBewohner[i].setEditable(false);
            spBewohner[i] = new JScrollPane(taBewohner[i]);
            jpTextBewohner.add(spBewohner[i]);
        }

        for (int i = 0; i < 10; i++) {
            btnBearbeitenBewohner[i] = new JButton();
            btnBearbeitenBewohner[i].setBackground(lightgrey);
            btnBearbeitenBewohner[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            btnBearbeitenBewohner[i].setPreferredSize(new Dimension(30, 51));
            jpBearbeitenBewohner.add(btnBearbeitenBewohner[i]);
            btnBearbeitenBewohner[i].setIcon(editicon);
            btnBearbeitenBewohner[i].addActionListener(bl2);
        }


        jcbSchicht = new JComboBox(schichten);
        jpFilter.add(jcbSchicht);
        jcbZeit = new JComboBox(zeiten);
        jpFilter.add(jcbZeit);
        jcbZeit.setBorder(BorderFactory.createMatteBorder(5, 30, 17, 300, lightyellow));
        jcbSchicht.setBorder(BorderFactory.createMatteBorder(17, 30, 5, 300, lightyellow));
        jpFilter.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        JTextArea taAlle = new JTextArea(" Hier steht ein text, der über bestimmte Ereiginisse berichtet, die Bewohnerunspezifisch sind.");
        taAlle.setLineWrap(true);
        taAlle.setWrapStyleWord(true);
        jpFilterTextAlle.add(taAlle, BorderLayout.CENTER);
        taAlle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taAlle.setBackground(lightyellow);


    }


    class Buttonlistener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int index = Arrays.asList(btnBearbeitenBewohner).indexOf(e.getSource());

            if (wirdbearbeitet == false) {

                if (istgespeichert == true) {

                    wirdbearbeitet = true;
                    taBewohner[index].setEditable(true);
                    btnBearbeitenBewohner[index].setIcon(saveicon);
                    istgespeichert = false;

                } else {

                    //TODO Fehlermeldung, die den weiteren Programvorgang blockiert(Hauptfenster)
                }
            } else {
                if (istgespeichert == false) {

                    taBewohner[index].setEditable(false);
                    btnBearbeitenBewohner[index].setIcon(editicon);
                    //TODO Sachen abspeichern Mehode
                    istgespeichert = true;
                    wirdbearbeitet = false;
                }
            }
        }
    }


    class ButtonListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            cl.show(cards, "Spezifisch");


        }
    }

    public static void main(String[] args) {
        GUI frame = new GUI();
        frame.setTitle("Schichtplan");
        frame.setSize(1600, 950);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
