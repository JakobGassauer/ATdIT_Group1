package library.persistence.implementation;

import library.model.implementation.*;
import library.persistence.Adapter;
import library.persistence.Factory;
import library.persistence.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The DatabaseAdapter implements the interface Adapter. The Adapter is accessed from the GUI in order to
 * retrieve data from the database. The Adapter creates an instance of the chosen implemented Factory class,
 * which returns the specific service. With the help of the service, data from the database is retrieved
 * and converted into the model types which are used in the GUI.
 */
public class DatabaseAdapter implements Adapter {
    private final Service service;
    private static int entriesAddedInSession;


    private final List<ResidentData> residentsData;
    private final List<IncidentData> incidentsData;
    private final List<ShiftScheduleData> shiftSchedulesData;
    private final List<EmployeeData> employeesData;
    private final List<ICEData> icesData;
    private final List<MedicationData> medicationsData;
    private final List<MedPlanData> medPlansData;
    private final List<StationData> stationsData;
    private final List<VisitsData> visitsData;

    private final ArrayList<Resident> residents = new ArrayList<>();
    private final ArrayList<Incident> incidents = new ArrayList<>();
    private final ArrayList<ShiftSchedule> shiftSchedules = new ArrayList<>();
    private final ArrayList<Employee> employees = new ArrayList<>();
    private final ArrayList<ICE> ices = new ArrayList<>();
    private final ArrayList<Medication> medications = new ArrayList<>();
    private final ArrayList<MedPlan> medPlans = new ArrayList<>();
    private final ArrayList<Station> stations = new ArrayList<>();
    private final ArrayList<Visits> visits = new ArrayList<>();


    /**
     * The constructor initiates the service with the help of the factory. Database lists are generated and filled
     * with current database entries. Then the database objects are converted into the model objects.
     */
    public DatabaseAdapter(){
        Factory factory = new DatabaseFactory();
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

    /**
     *  Converts database types into the model types, which are used in the GUI.
     */
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


    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<ICE> getIces() {
        return ices;
    }

    public ArrayList<Incident> getIncidents() {
        return incidents;
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    public ArrayList<MedPlan> getMedPlans() {
        return medPlans;
    }

    public ArrayList<Resident> getResidents() {
        return residents;
    }

    public ArrayList<ShiftSchedule> getShiftSchedules() {
        return shiftSchedules;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public ArrayList<Visits> getVisits() {
        return visits;
    }




    /**
     * @param index position
     * @return Resident object that is stored in the List at the index.
     */
    public Resident getSingleResident(int index) {
        return getResidents().get(index);
    }

    /**
     * Passes the request to the service and converts the returned database type
     * into the model type, so it can be used in the GUI.
     * @param name for select statement
     * @return Resident object with the provided name.
     */
    public Resident getSingleResident(String name) {
        ResidentData residentData = service.getSingleResidentData(name);
        return new Resident(residentData.resID,residentData.name,
                residentData.surname,residentData.age,
                residentData.stationID,residentData.room);
    }


    /**
     * Passes the request to the service and converts the returned database type
     * into the model type, so it can be used in the GUI.
     * @param category for select statement
     * @param date for select statement
     * @return Shift Schedule with the provided shift category and date.
     */
    public ShiftSchedule getSingleShiftSchedule(Object category, Date date) {
        ShiftScheduleData shiftScheduleData = service.getSingleShiftScheduleData(category,date);
        return new ShiftSchedule(shiftScheduleData.shiftID,shiftScheduleData.employeeID,
                shiftScheduleData.category,shiftScheduleData.date,
                shiftScheduleData.shiftIncidents);
    }

    /**
     * Passes the request to the service.
     * @param resID for select statement
     * @return Description of the Visit of the provided resident.
     */
    public String getSingleVisitDescription(int resID) {
        return service.getSingleVisitDataDescription(resID);
    }


    /**
     * Passes the request to the service and converts the returned database type
     * into the model type, so it can be used in the GUI.
     * @param resID for select statement
     * @return ICE of provided Resident
     */
    public ICE getSingleICE(int resID){
        ICEData iceData = service.getSingleICEData(resID);
        return new ICE(iceData.iceID, iceData.resID,iceData.name,iceData.surname,iceData.telnumber,iceData.adress);
    }

    /**
     * Passes the request to the service and converts the returned database type
     * into the model type, so it can be used in the GUI.
     * @param resID for select statement
     * @param date for select statement
     * @return Incident of provided resident on the date
     */
    public Incident getSingleIncident(int resID, Date date){
        IncidentData incidentData = service.getSingleIncidentData(resID,date);
        return new Incident(incidentData.incidentID,incidentData.resID,
                incidentData.shiftID,incidentData.description,
                incidentData.incidentsDate);
    }

    /**
     * Passes the request to the service and converts the returned database type
     * into the model type, so it can be used in the GUI.
     * @param resID for select statement
     * @return Incident of the provided Resident
     */
    public Incident getSingleIncident(int resID){
        IncidentData incidentData = service.getSingleIncidentData(resID);
        return new Incident(incidentData.incidentID,incidentData.resID,
                incidentData.shiftID,incidentData.description,
                incidentData.incidentsDate);
    }

    /**
     * Passes the request to the service so it can be used in the GUI.
     * @param medicID for select statement
     * @return Name of the Medication with the provided medicID
     */
    public String getSingleMedication(int medicID){
        return service.getSingleMedicationData(medicID);
    }

    /**
     * Passes the request to the service so it can be used in the GUI.
     * The method is not used yet and solely for expansion purposes.
     * @param stationID for select statement
     * @return Name of the station with the provided stationID
     */
    @SuppressWarnings("unused")
    public String getSingleStation(int stationID){
        return service.getSingleStationData(stationID);
    }

    /**
     * Passes the request to the service and converts the returned database type
     * into the model type, so it can be used in the GUI.
     * The method is not used yet and solely for expansion purposes.
     * @param employeeID for select statement
     * @return EmployeeData of the provided employeeID
     */
    @SuppressWarnings("unused")
    public Employee getSingleEmployee(int employeeID){
        EmployeeData employeeData = service.getSingleEmployeeData(employeeID);
        return new Employee(employeeData.employeeID,
                employeeData.name,
                employeeData.surname,
                employeeData.age,
                employeeData.stationID);
    }

    /**
     * Passes the request to the service and converts the returned database type
     * into the model type, so it can be used in the GUI.
     * @param resID for select statement
     * @return MedPlan of the provided resident
     */
    public MedPlan getSingleMedPlan(int resID){
        MedPlanData medPlanData = service.getSingleMedPlanData(resID);
        return new MedPlan(medPlanData.medID,medPlanData.resID,
                medPlanData.concentration,medPlanData.intakeFrequency,
                medPlanData.medicID);
    }


    /**
     * Saves the new or changed incident description of resident specific incidents.
     * @param newText for update statement
     * @param incident for update statement
     * @param resID for update statement
     * @param date for update statement
     */
    public void saveResidentIncidentsDatabase(String newText, Incident incident, int resID, Date date){
        if (incident.getIncidentID()==0){
            int newIncidentID = getIncidents().size()+(entriesAddedInSession+2);
            int shiftID = 0;
            IncidentData incidentData = new IncidentData(newIncidentID, resID, shiftID, newText, date);
            service.createNewResidentIncidentDatabase(incidentData);
            entriesAddedInSession++;
        }
        service.updateResidentIncidentsDataDatabase(newText, incident.getIncidentID());
    }

    /**
     * Saves the new or changed incident description of shift specific incidents.
     * @param newText for update statement
     * @param shiftID for update statement
     */
    public void saveShiftIncidentsDatabase(String newText, int shiftID){
        service.updateShiftIncidentsDataDatabase(newText, shiftID);
    }
}
