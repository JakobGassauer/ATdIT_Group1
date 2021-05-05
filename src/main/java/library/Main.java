package library;//http://tutorials.jenkov.com/java-date-time/java-util-calendar.html

import library.model.implementation.Incident;
import library.model.implementation.Resident;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(Resident.get(2));
        System.out.println(Resident.get("Robert").getRoom());


    }

}
