package library.model.implementation;

import library.model.Edit;
import library.persistence.implementation.DatabaseService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Incident implements Edit<Incident> {
    private int incidentID;
    private String description;
    private int resID;
    private int shiftID;
    private Date incidentsDate;

    public int getIncidentID() {
        return this.incidentID;
    }

    public int getResID() {
        return this.resID;
    }

    public int getShiftID() {
        return this.shiftID;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getIncidentsDate() {
        return incidentsDate;
    }

    public void setIncidentsDate(Date incidentsDate) {
        this.incidentsDate = incidentsDate;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Incidents{" +
                "incidentID=" + incidentID +
                ", description='" + description + '\'' +
                ", resID=" + resID +
                ", shiftID=" + shiftID +
                '}';
    }

    public Incident(int incidentID, int resID, int shiftID, String description, Date incidentsDate){
        this.description=description;
        this.incidentID=incidentID;
        this.resID=resID;
        this.shiftID=shiftID;
        this.incidentsDate = incidentsDate;
    }

    @Override
    public void add(Incident incident) {
        //TODO implement add logic
    }

    @Override
    public void remove(Incident incident) {
        //TODO implement remove logic
    }


    public static Incident get(int resID) {
        try{
            String sql = "Select * from incidents where resID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID));
            Date incidentsDate = null;
            try{
                incidentsDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("incidents_date"));
            }catch (ParseException e) {
                e.printStackTrace();
            }
            Incident incident = new Incident(
                    rs.getInt("incidentID"),
                    rs.getInt("resID"),
                    rs.getInt("shiftID"),
                    rs.getString("description"), incidentsDate);
            rs.getStatement().close();
            rs.close();
            return incident;
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return new Incident(0,0,0,"no incident", null);
            }
            e.printStackTrace();
            return null;
        }
    }
    public static Incident get(int resID, Date date) {
        try{
            String sql = "Select * from incidents where resID = ? and incidents_date = ?";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = formatter.format(date);
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID), dateInString);
            Date incidentsDate = null;
            try{
                incidentsDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("incidents_date"));
            }catch (ParseException e) {
                e.printStackTrace();
            }
            Incident incident = new Incident(
                    rs.getInt("incidentID"),
                    rs.getInt("resID"),
                    rs.getInt("shiftID"),
                    rs.getString("description"), incidentsDate);
            rs.getStatement().close();
            rs.close();
            return incident;
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return new Incident(0,0,0,"no incident", null);
            }
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public  Incident get() {
        return null;
    }

    /*public static Incident get(int index) {
        List<Incident> incidentsArrayList =new ArrayList<>();
        incidentsArrayList = DatabaseService.getIncidents();
        Incident incident = incidentsArrayList.get(index);
        return incident;
        //TODO implement get logic
    }*/
}
