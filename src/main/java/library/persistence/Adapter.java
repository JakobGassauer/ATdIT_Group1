package library.persistence;

import library.model.implementation.*;

import java.util.Date;

public interface Adapter {
    Resident getSingleResident(int index);
    Resident getSingleResident(String name);
    ShiftSchedule getSingleShiftSchedule(Object category, Date date);
    String getSingleVisitDescription(int resID);
    ICE getSingleICE(int resID);
    Incident getSingleIncident(int resID, Date date);
    Incident getSingleIncident(int resID);
    String getSingleMedication(int medicID);
    MedPlan getSingleMedPlan(int resID);
    void saveResidentIncidentsDatabase(String newText, Incident incident, int resID, Date date);
    void saveShiftIncidentsDatabase(String newText, int shiftID);

}
