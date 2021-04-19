package model.implementation;

import model.Edit;

public class ShiftSchedule implements Edit<ShiftSchedule> {
    private int shiftID;
    private int employeeID;
    private int category;
    /*
     date can be asked through GregorianCalendar() in main,
     calculations for timeschedule can be done in here
     //http://tutorials.jenkov.com/java-date-time/java-util-calendar.html
    */

    public int getShiftID() { return this.shiftID; }

    public int getEmployeeID() { return this.employeeID; }

    public int getCategory() { return this.category; }

    public void setShiftID(int shiftID) { this.shiftID = shiftID; }

    public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }

    public void setCategory(int category) { this.category = category }

    public ShiftSchedule(int shiftID, int employeeID) {
        this.shiftID = shiftID;
        this.employeeID = employeeID;
    }

    //Override








}
