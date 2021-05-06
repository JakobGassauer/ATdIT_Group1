package library.model.implementation;

import library.model.Edit;
import library.model.People;
import library.persistence.implementation.DatabaseService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Visits implements Edit<Visits> {
    private int visitID;
    private String description;
    private int resID;

    public Visits(int visitID, String description, int resID){
        this.visitID=visitID;
        this.description=description;
        this.resID=resID;
    }

    public int getVisitID() {
        return visitID;
    }

    public String getDescription() {
        return description;
    }

    public int getResID() {
        return resID;
    }

    @Override
    public void add(Visits object) {

    }

    @Override
    public void remove(Visits object) {

    }

    @Override
    public Visits get() {
        return null;
    }


    public static String get(int resID) {
        try{
            String sql = "Select description from visits where resID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID));
            return rs.getString("description"); //todo testen ob das richtige zurückgeben wird
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return "no visits";
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Visits{" +
                "visitID=" + visitID +
                ", description='" + description + '\'' +
                ", resID=" + resID +
                '}';
    }
}
