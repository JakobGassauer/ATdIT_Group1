package library.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Service {

    class ServiceException extends SQLException {
        public ServiceException(String message){
            super(message);
        }
        public  ServiceException(String message, Throwable cause){
            super(message, cause);
        }
    }

    ArrayList<EmployeeData> getEmployeeData();
    ArrayList<ICEData> getICEData();
    ArrayList<IncidentData> getIncidentData();
    ArrayList<MedicationData> getMedicationData();
    ArrayList<MedPlanData> getMedPlanData();
    ArrayList<ResidentData> getResidentData();
    ArrayList<ShiftScheduleData> getShiftScheduleData();
    ArrayList<StationData> getStationData();
    ArrayList<VisitsData> getVisitData();

    //List<T> getEntities() throws ServiceException;
    //void postEntities(List<T> entities) throws ServiceException;
}
