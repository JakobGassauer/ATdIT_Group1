package library.persistence.implementation;

import library.model.implementation.*;
import library.persistence.Adapter;
import library.persistence.Factory;
import library.persistence.Service;

import java.util.Date;

public class DatabaseAdapter implements Adapter {
    private Factory factory;
    private Service service;
    private static int entriesAddedInSession;

    public DatabaseAdapter(){
        //factory = new DatabaseFactory();
        //service = new DatabaseService();
        entriesAddedInSession=0;
    }


    public Resident getSingleResident(int index) {
        return factory.getResidents().get(index);
    }

    public Resident getSingleResident(String name) {
        ResidentData residentData = service.getSingleResidentData(name);
        return new Resident(residentData.resID,residentData.name,
                residentData.surname,residentData.age,
                residentData.stationID,residentData.room);
    }

    public ShiftSchedule getSingleShiftSchedule(Object category, Date date) {
        ShiftScheduleData shiftScheduleData = service.getSingleShiftScheduleData(category,date);
        return new ShiftSchedule(shiftScheduleData.shiftID,shiftScheduleData.employeeID,
                shiftScheduleData.category,shiftScheduleData.date,
                shiftScheduleData.shiftIncidents);
    }

    public String getSingleVisitDescription(int resID) {
        return service.getSingleVisitDataDescription(resID);
    }


    public ICE getSingleICE(int resID){
        ICEData iceData = service.getSingleICEData(resID);
        return new ICE(iceData.iceID, iceData.resID,iceData.name,iceData.surname,iceData.telnumber,iceData.adress);
    }

    public Incident getSingleIncident(int resID, Date date){
        IncidentData incidentData = service.getSingleIncidentData(resID,date);
        return new Incident(incidentData.incidentID,incidentData.resID,
                incidentData.shiftID,incidentData.description,
                incidentData.incidentsDate);
    }

    public Incident getSingleIncident(int resID){
        IncidentData incidentData = service.getSingleIncidentData(resID);
        return new Incident(incidentData.incidentID,incidentData.resID,
                incidentData.shiftID,incidentData.description,
                incidentData.incidentsDate);
    }

    public String getSingleMedication(int medicID){
        return service.getSingleMedicationData(medicID);
    }

    public MedPlan getSingleMedPlan(int resID){
        MedPlanData medPlanData = service.getSingleMedPlanData(resID);
        return new MedPlan(medPlanData.medID,medPlanData.resID,
                medPlanData.concentration,medPlanData.intakeFrequency,
                medPlanData.medicID);
    }


    public void saveResidentIncidentsDatabase(String newText, Incident incident, int resID, Date date){
        if (incident.getIncidentID()==0){
            int newIncidentID = factory.getIncidents().size()+(entriesAddedInSession+2);
            int shiftID = 0;
            String description = newText;
            IncidentData incidentData = new IncidentData(newIncidentID, resID, shiftID, description, date);
            service.createNewResidentIncidentDatabase(incidentData);
            entriesAddedInSession++;
        }
        service.updateResidentIncidentsDataDatabase(newText, incident.getIncidentID());
    }

    public void saveShiftIncidentsDatabase(String newText, int shiftID){
        service.updateShiftIncidentsDataDatabase(newText, shiftID);
    }
}
