package library.model.implementation;

import library.model.Edit;
import library.persistence.implementation.DatabaseService;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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


    public Date getDate() {
        return date;
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

    public static ShiftSchedule get(Object category, Date date) {
        return null;// DatabaseService.getSingleShiftSchedule(category, date);
    }
    /*
     date can be asked through GregorianCalendar() in main,
     calculations for timeschedule can be done in here
     //http://tutorials.jenkov.com/java-date-time/java-util-calendar.html
    */
}
