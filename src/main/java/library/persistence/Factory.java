package library.persistence;

import library.model.implementation.*;
import library.persistence.implementation.*;

import java.util.List;

public interface Factory {
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

}
