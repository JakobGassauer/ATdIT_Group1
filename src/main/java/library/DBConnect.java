package library;

import java.sql.*;


public class DBConnect {
    public static final String location="jdbc:sqlite:src/database/group1_database_final.db";

    public static Connection connect() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(location);
            System.out.println("Connection to Database established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection conn = connect();


    }

}
