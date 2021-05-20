package library.persistence;

import library.model.implementation.*;

import java.util.Date;
import java.util.List;

/**
 * The interface Adapter defines the methods needed to convert the database types into the model types
 */
public interface Adapter {
    void convertToModelObjects();

    List<Employee> getEmployees();
    List<Resident> getResidents();
    List<ICE> getIces();
    List<Incident> getIncidents();
    List<Medication> getMedications();
    List<MedPlan> getMedPlans();
    List<ShiftSchedule> getShiftSchedules();
    List<Station> getStations();
    List<Visits> getVisits();

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
