package library.persistence.implementation;

import library.model.implementation.*;
import library.persistence.Adapter;
import library.persistence.Factory;
import library.persistence.Service;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseAdapterTest {


    @Test
    void convertToModelObjects() {

        DatabaseAdapter adapter = new DatabaseAdapter();
        Factory factory = new DatabaseFactory();
        DatabaseService service = new DatabaseService();

        ArrayList<ResidentData> residentDataList = service.getResidentData();
        ArrayList<Resident> residentList = adapter.getResidents();
        ArrayList<EmployeeData> employeeDataList = service.getEmployeeData();
        ArrayList<Employee> employeeList = adapter.getEmployees();
        ArrayList<ICEData> iceDataList = service.getICEData();
        ArrayList<ICE> iceList = adapter.getIces();
        ArrayList<IncidentData> incidentDataList = service.getIncidentData();
        ArrayList<Incident> incidentList = adapter.getIncidents();
        ArrayList<MedicationData> medicationDataList = service.getMedicationData();
        ArrayList<Medication> medicationList = adapter.getMedications();
        ArrayList<MedPlanData> medPlanDataList = service.getMedPlanData();
        ArrayList<MedPlan> medPlanList = adapter.getMedPlans();
        ArrayList<ShiftScheduleData> shiftScheduleDataList = service.getShiftScheduleData();
        ArrayList<ShiftSchedule> shiftScheduleList = adapter.getShiftSchedules();
        ArrayList<StationData> stationDataList = service.getStationData();
        ArrayList<Station> stationList = adapter.getStations();
        ArrayList<VisitsData> visitsDataList = service.getVisitData();
        ArrayList<Visits> visitList = adapter.getVisits();

        //residents
        for (int i = 0; i<residentDataList.size();i++){
            assertEquals(residentDataList.get(i).resID, residentList.get(i).getResID());
            assertEquals(residentDataList.get(i).surname, residentList.get(i).getSurname());
            assertEquals(residentDataList.get(i).name, residentList.get(i).getName());
            assertEquals(residentDataList.get(i).room, residentList.get(i).getRoom());
            assertEquals(residentDataList.get(i).stationID, residentList.get(i).getStationID());
            assertEquals(residentDataList.get(i).age, residentList.get(i).getAge());
        }

        //employees
        for (int i = 0; i<employeeDataList.size();i++){
            assertEquals(employeeDataList.get(i).employeeID, employeeList.get(i).getEmployeeID());
            assertEquals(employeeDataList.get(i).surname, employeeList.get(i).getSurname());
            assertEquals(employeeDataList.get(i).name, employeeList.get(i).getName());
            assertEquals(employeeDataList.get(i).stationID, employeeList.get(i).getStationID());
            assertEquals(employeeDataList.get(i).age, employeeList.get(i).getAge());
        }

        //ice
        for (int i = 0; i<iceDataList.size();i++){
            assertEquals(iceDataList.get(i).iceID, iceList.get(i).getIceID());
            assertEquals(iceDataList.get(i).surname, iceList.get(i).getSurname());
            assertEquals(iceDataList.get(i).name, iceList.get(i).getName());
            assertEquals(iceDataList.get(i).age, iceList.get(i).getAge());
            assertEquals(iceDataList.get(i).resID, iceList.get(i).getResID());
            assertEquals(iceDataList.get(i).telnumber, iceList.get(i).getTelnumber());
            assertEquals(iceDataList.get(i).adress, iceList.get(i).getAdress());
        }

        //incidents
        for (int i = 0; i<incidentDataList.size();i++){
            assertEquals(incidentDataList.get(i).resID, incidentList.get(i).getResID());
            assertEquals(incidentDataList.get(i).incidentsDate, incidentList.get(i).getIncidentsDate());
            assertEquals(incidentDataList.get(i).incidentID, incidentList.get(i).getIncidentID());
            assertEquals(incidentDataList.get(i).description, incidentList.get(i).getDescription());
            assertEquals(incidentDataList.get(i).shiftID, incidentList.get(i).getShiftID());
        }

        //medication
        for (int i = 0; i<medicationDataList.size();i++){
            assertEquals(medicationDataList.get(i).medicID, medicationList.get(i).getMedicID());
            assertEquals(medicationDataList.get(i).name, medicationList.get(i).getName());
        }

        //medPlan
        for (int i = 0; i<medPlanDataList.size();i++){
            assertEquals(medPlanDataList.get(i).resID, medPlanList.get(i).getResID());
            assertEquals(medPlanDataList.get(i).concentration, medPlanList.get(i).getConcentration());
            assertEquals(medPlanDataList.get(i).medID, medPlanList.get(i).getMedID());
            assertEquals(medPlanDataList.get(i).medicID, medPlanList.get(i).getMedicID());
            assertEquals(medPlanDataList.get(i).intakeFrequency, medPlanList.get(i).getIntakeFrequency());
        }

        //ShiftSchedule
        for (int i = 0; i<shiftScheduleDataList.size();i++){
            assertEquals(shiftScheduleDataList.get(i).shiftID, shiftScheduleList.get(i).getShiftID());
            assertEquals(shiftScheduleDataList.get(i).shiftIncidents, shiftScheduleList.get(i).getShiftIncidents());
            assertEquals(shiftScheduleDataList.get(i).date, shiftScheduleList.get(i).getDate());
            assertEquals(shiftScheduleDataList.get(i).category, shiftScheduleList.get(i).getCategory());
            assertEquals(shiftScheduleDataList.get(i).employeeID, shiftScheduleList.get(i).getEmployeeID());
        }

        //Station
        for (int i = 0; i<stationDataList.size();i++){
            assertEquals(stationDataList.get(i).name, stationList.get(i).getName());
            assertEquals(stationDataList.get(i).stationID, stationList.get(i).getStationID());
        }

        //visits
        for (int i = 0; i<visitsDataList.size();i++){
            assertEquals(visitsDataList.get(i).resID, visitList.get(i).getResID());
            assertEquals(visitsDataList.get(i).visitID, visitList.get(i).getVisitID());
            assertEquals(visitsDataList.get(i).description, visitList.get(i).getDescription());
        }
    }


    @Test
    void saveResidentIncidentsDatabase() {
    }

}