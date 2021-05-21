package library.persistence;

import library.persistence.implementation.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
/**
 * The interface Service defines the methods needed to update the database and
 * retrieve data from the database types into the database types.
 *
 */
public interface Service {

    class ServiceException extends SQLException {
        public ServiceException(String message){
            super(message);
        }
        public  ServiceException(String message, Throwable cause){
            super(message, cause);
        }
    }

    List<EmployeeData> getEmployeeData();
    List<ICEData> getICEData();
    List<IncidentData> getIncidentData();
    List<MedicationData> getMedicationData();
    List<MedPlanData> getMedPlanData();
    List<ResidentData> getResidentData();
    List<ShiftScheduleData> getShiftScheduleData();
    List<StationData> getStationData();
    List<VisitsData> getVisitData();


    void updateShiftIncidentsDataDatabase(String newText, int shiftID);
    void updateResidentIncidentsDataDatabase(String newText, int incidentID);
    void createNewResidentIncidentDatabase(IncidentData incidentData);

    MedPlanData getSingleMedPlanData(int resID) ;
    String getSingleMedicationData(int medicID);
    IncidentData getSingleIncidentData(int resID) ;
    IncidentData getSingleIncidentData(int resID, Date date) ;
    ICEData getSingleICEData(int resID) ;
    ShiftScheduleData getSingleShiftScheduleData(Object category, Date date) ;
    String getSingleVisitDataDescription(int resID);
    ResidentData getSingleResidentData(String name);

}
