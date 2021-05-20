package library.persistence.implementation;

import library.model.implementation.*;
import library.persistence.Adapter;
import library.persistence.Factory;
import library.persistence.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseAdapter implements Adapter {
    private Factory factory = new DatabaseFactory(); //konkrete Factory muss hier gewählt werden
    private Service service;
    private static int entriesAddedInSession;


    //maps types from db service to types used in the model (?)
    // no direct database access/queries

    private final List<ResidentData> residentsData;
    private final List<IncidentData> incidentsData;
    private final List<ShiftScheduleData> shiftSchedulesData;
    private final List<EmployeeData> employeesData;
    private final List<ICEData> icesData;
    private final List<MedicationData> medicationsData;
    private final List<MedPlanData> medPlansData;
    private final List<StationData> stationsData;
    private final List<VisitsData> visitsData;

    //Namenskonventionen! Lists to be used in gui
    private final ArrayList<Resident> residents = new ArrayList<>();
    private final ArrayList<Incident> incidents = new ArrayList<>();
    private final ArrayList<ShiftSchedule> shiftSchedules = new ArrayList<>();
    private final ArrayList<Employee> employees = new ArrayList<>();
    private final ArrayList<ICE> ices = new ArrayList<>();
    private final ArrayList<Medication> medications = new ArrayList<>();
    private final ArrayList<MedPlan> medPlans = new ArrayList<>();
    private final ArrayList<Station> stations = new ArrayList<>();
    private final ArrayList<Visits> visits = new ArrayList<>();


    public DatabaseAdapter(){
        service = factory.createService();

        residentsData = service.getResidentData();
        incidentsData = service.getIncidentData();
        shiftSchedulesData = service.getShiftScheduleData();
        employeesData = service.getEmployeeData();
        icesData = service.getICEData();
        medicationsData = service.getMedicationData();
        medPlansData = service.getMedPlanData();
        stationsData = service.getStationData();
        visitsData = service.getVisitData();

        convertToModelObjects();
        entriesAddedInSession=0;
    }

    public void convertToModelObjects() {
        for(ResidentData entities : residentsData){
            Resident entity = new Resident(entities.resID,
                    entities.name,
                    entities.surname,
                    entities.age,
                    entities.stationID,
                    entities.room) ;
            residents.add(entity);
        }

        for(IncidentData entities : incidentsData){
            Incident entity = new Incident(entities.incidentID,
                    entities.resID,
                    entities.shiftID,
                    entities.description,
                    entities.incidentsDate) ;
            incidents.add(entity);
        }

        for(ShiftScheduleData entities : shiftSchedulesData){
            ShiftSchedule entity = new ShiftSchedule(entities.shiftID,
                    entities.employeeID,
                    entities.category,
                    entities.date,
                    entities.shiftIncidents) ;
            shiftSchedules.add(entity);
        }

        for(EmployeeData entities : employeesData){
            Employee entity = new Employee(entities.employeeID,
                    entities.name,
                    entities.surname,
                    entities.age,
                    entities.stationID) ;
            employees.add(entity);
        }

        for(ICEData entities : icesData){
            ICE entity = new ICE(entities.iceID,
                    entities.resID,
                    entities.name,
                    entities.surname,
                    entities.telnumber,
                    entities.adress) ;
            ices.add(entity);
        }

        for(MedicationData entities : medicationsData){
            Medication entity = new Medication(entities.medicID,
                    entities.name) ;
            medications.add(entity);
        }

        for(MedPlanData entities : medPlansData){
            MedPlan entity = new MedPlan(entities.medID,
                    entities.resID,
                    entities.concentration,
                    entities.intakeFrequency,
                    entities.medicID) ;
            medPlans.add(entity);
        }

        for(StationData entities : stationsData){
            Station entity = new Station(entities.stationID,
                    entities.name) ;
            stations.add(entity);
        }

        for(VisitsData entities : visitsData){
            Visits entity = new Visits(entities.visitID,
                    entities.description,
                    entities.resID) ;
            visits.add(entity);
        }

    }
//get all entries of one entity stored in lists

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<ICE> getIces() {
        return ices;
    }

    public List<Incident> getIncidents() {
        return incidents;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public List<MedPlan> getMedPlans() {
        return medPlans;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public List<ShiftSchedule> getShiftSchedules() {
        return shiftSchedules;
    }

    public List<Station> getStations() {
        return stations;
    }

    public List<Visits> getVisits() {
        return visits;
    }


//get single entity (stored in model object)

    public Resident getSingleResident(int index) {
        return getResidents().get(index);
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
            int newIncidentID = getIncidents().size()+(entriesAddedInSession+2);
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
