package library;//http://tutorials.jenkov.com/java-date-time/java-util-calendar.html

import library.model.implementation.Incident;
import library.model.implementation.Resident;
import library.presentation.GUI;

import javax.swing.*;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        //System.out.println(Resident.get(2));
        //System.out.println(Incident.get(Resident.get("Inge").getResID()).getDescription());
        //System.out.println(Resident.get("Robert").getRoom());
        //todo Junit Tests & internationalization
        GUI frame = new GUI();
        frame.setTitle("Schichtplan");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
