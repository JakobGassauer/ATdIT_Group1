package library;

import java.sql.*;


public class DBConnect {
    public static final String location="jdbc:sqlite:src/database/db.sqlite";

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

    public void selectAll(){
        String sql = "SELECT * FROM medication";

        try (Connection conn = this.connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(
                        rs.getInt("medicID") +  "\t" +
                        rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection conn = connect();


    }

}
