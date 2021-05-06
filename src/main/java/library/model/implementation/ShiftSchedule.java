package library.model.implementation;

import library.model.Edit;
import library.persistence.implementation.DatabaseService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ShiftSchedule implements Edit<ShiftSchedule> {
    private int shiftID;
    private int employeeID;
    private int category;
    private Date date;
    private String shiftIncidents;



    public int getShiftID() { return this.shiftID; }

    public int getEmployeeID() { return this.employeeID; }

    public int getCategory() { return this.category; }

    public String getShiftIncidents() {
        return shiftIncidents;
    }

    public void setShiftID(int shiftID) { this.shiftID = shiftID; }

    public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }

    public void setCategory(int category) { this.category = category; }

    public ShiftSchedule(int shiftID, int employeeID, int category, Date date, String shiftIncidents) {
        this.shiftID = shiftID;
        this.employeeID = employeeID;
        this.category = category;
        this.date = date;
        this.shiftIncidents = shiftIncidents;
    }

    @Override
    public void add(ShiftSchedule object) {
        // todo implement add logic
    }

    @Override
    public void remove(ShiftSchedule object) {
        // todo implement remove logic
    }

    @Override
    public ShiftSchedule get() {
        // todo implement get logic
        return null;
    }

    @Override
    public String toString() {
        return "ShiftSchedule{" +
                "shiftID=" + shiftID +
                ", employeeID=" + employeeID +
                ", category=" + category +
                '}';
    }

    public static ShiftSchedule get(Object category, Object date) {
        try{
            String sql = "Select * from shift_shedule where date = ? and category  = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(date), String.valueOf(category));
            System.out.println(rs);
            ShiftSchedule shiftSchedule = new ShiftSchedule(
                    rs.getInt("shiftID"),
                    rs.getInt("employeeID"),
                    rs.getInt("category"),
                    rs.getDate("date"),
                    rs.getString("shiftIncidents"));
            return shiftSchedule;
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return new ShiftSchedule(0,0,0,null,"no shift incidents");
            }
            e.printStackTrace();
            return null;
        }
    }
    /*
     date can be asked through GregorianCalendar() in main,
     calculations for timeschedule can be done in here
     //http://tutorials.jenkov.com/java-date-time/java-util-calendar.html
    */
}
