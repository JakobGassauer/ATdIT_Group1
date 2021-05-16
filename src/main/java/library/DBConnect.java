package library;

import java.sql.*;
import java.util.ResourceBundle;


public class DBConnect {
    public static final String location="jdbc:sqlite:src/database/dieNeue.sqlite"; //NON-NLS
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

}
