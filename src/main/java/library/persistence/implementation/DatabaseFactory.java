package library.persistence.implementation;

import library.model.implementation.*;
import library.persistence.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseFactory {
    DatabaseService service;

    public final ArrayList<ResidentData> residentsData;
    public final ArrayList<IncidentData> incidentsData;
    public final ArrayList<ShiftScheduleData> shiftSchedulesData;
    public final ArrayList<EmployeeData> employeesData;
    public final ArrayList<ICEData> icesData;
    public final ArrayList<MedicationData> medicationsData;
    public final ArrayList<MedPlanData> medPlansData;
    public final ArrayList<StationData> stationsData;
    public final ArrayList<VisitsData> visitsData;

//Namenskonventionen!
    public final ArrayList<Resident> residents = new ArrayList<>();
    public final ArrayList<Incident> incidents = new ArrayList<>();
    public final ArrayList<ShiftSchedule> shiftSchedules = new ArrayList<>();
    public final ArrayList<Employee> employees = new ArrayList<>();
    public final ArrayList<ICE> ices = new ArrayList<>();
    public final ArrayList<Medication> medications = new ArrayList<>();
    public final ArrayList<MedPlan> medPlans = new ArrayList<>();
    public final ArrayList<Station> stations = new ArrayList<>();
    public final ArrayList<Visits> visits = new ArrayList<>();




    public DatabaseFactory(){
        service = new DatabaseService();

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
    }

    private void convertToModelObjects() {
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


    public Resident getSingleResident(int index) {
        return residents.get(index);
    }

    public Resident getSingleResident(String name) {
        try{
            String sql = "Select * from senior_resident where name = ?";
            ResultSet result = DatabaseService.createPreparedStatement(sql, name);
            Resident resident = new Resident(result.getInt("resID"),
                    result.getString("name"),
                    result.getString("surname"),
                    result.getInt("age"),
                    result.getInt("stationID"),
                    result.getInt("room"));
            result.getStatement().close();
            result.close();
            return resident;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public ShiftSchedule getSingleShiftSchedule(Object category, Date date) {
        try{
            String sql = "Select * from shift_schedule where date = ? and category  = ?";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = formatter.format(date);
            ResultSet rs = DatabaseService.createPreparedStatement(sql, dateInString, String.valueOf(category));
            System.out.println(rs);
            Date date1 =null;
            try{
                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date"));
            }catch (ParseException pe) {
                pe.printStackTrace();
                //  return null;
            }
            ShiftSchedule shiftSchedule = new ShiftSchedule(
                    rs.getInt("shiftID"),
                    rs.getInt("employeeID"),
                    rs.getInt("category"),
                    date1,
                    rs.getString("shift_incidents"));
            rs.getStatement().close();
            rs.close();
            return shiftSchedule;
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return new ShiftSchedule(0,0,0,null,"no shift incidents");
            }
            e.printStackTrace();
            return null;
        }
    }

    public String getSingleVisitDescription(int resID) {
        try{
            String sql = "Select description from visits where resID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID));
            String description = rs.getString("description");
            rs.getStatement().close();
            rs.close();
            return description; //todo testen ob das richtige zurückgeben wird
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return "no visits";
            }
            e.printStackTrace();
            return null;
        }
    }

}
