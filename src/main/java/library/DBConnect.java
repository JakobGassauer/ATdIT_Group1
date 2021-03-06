package library;


import java.sql.*;
import java.util.ResourceBundle;

/**
 * DBConnect establishes a connection to the local sqlite database
 */
public class DBConnect {
    private static String location="jdbc:sqlite:src/database/database.sqlite"; //NON-NLS
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/gui/gui"); //NON-NLS

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(location);
            System.out.println(resourceBundle.getString("connection.to.database.established"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public static void setLocation(String newLocation){
        location  = newLocation;
    }

    public static String getLocation() {
        return location;
    }
}
