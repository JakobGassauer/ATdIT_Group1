//http://tutorials.jenkov.com/java-date-time/java-util-calendar.html

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        try {
            String host = "jdbc:mysql://localhost:3306/group1_database";
            String uName = "root";
            String uPass = "root";
            Connection con = DriverManager.getConnection(host, uName, uPass);
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

    }

}
