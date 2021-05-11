package library.persistence.implementation;

import library.model.implementation.Incident;
import library.model.implementation.Resident;
import library.model.implementation.ShiftSchedule;

import java.util.ArrayList;

public class DatabaseFactory {
    public static ArrayList<Resident> residents = DatabaseService.getResidents();
    public static ArrayList<Incident> incidents = DatabaseService.getIncidents();
    public static ArrayList<ShiftSchedule> shiftSchedules = DatabaseService.getShiftSchedule();
}
