package library.persistence.implementation;

import library.persistence.*;
import library.DBConnect;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * DatabaseService implements the interface Service. It retrieves data from the Database and returns them either in Lists
 * of the database types or directly in objects of the database types.
 */
@SuppressWarnings("HardCodedStringLiteral")
public class DatabaseService implements Service {
    public DatabaseService(){

    }

    /**
     * @return ArrayList with all resident entries in the database.
     */
    public ArrayList<ResidentData> getResidentData() {
        ArrayList<ResidentData> residentArrayList = new ArrayList<>();
        String sql = "SELECT * FROM senior_resident";
        try {
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                ResidentData resident = new ResidentData(
                        result.getInt("resID"),result.getString("name"),
                        result.getString("surname"),result.getInt("age"),
                        result.getInt("stationID"),result.getInt("room"));
                residentArrayList.add(resident);
            }
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return residentArrayList;
    }

    /**
     * @return ArrayList with all employee entries in the database.
     */
    public ArrayList<EmployeeData> getEmployeeData() {
        ArrayList<EmployeeData> employeeArrayList = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try {
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result != null) {
                while(result.next()){
                    EmployeeData employee = new EmployeeData(
                            result.getInt("employeeID"),result.getString("name"),
                            result.getString("surname"),result.getInt("age"),
                            result.getInt("stationID"));
                    employeeArrayList.add(employee);
                }
            }
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employeeArrayList;
    }


    /**
     * @return ArrayList with all ice entries in the database.
     */
    public ArrayList<ICEData> getICEData() {
        ArrayList<ICEData> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM ICE";
        try {
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result != null) {
                while(result.next()){
                    ICEData ice = new ICEData(
                            result.getInt("iceID"),result.getInt("resID"),
                            result.getString("name"),result.getString("surname"),
                            result.getInt("tel_number"),result.getString("adress"));
                    arrayList.add(ice);
                }
            }
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    /**
     * @return ArrayList with all incident entries in the database.
     */
    public ArrayList<IncidentData> getIncidentData() {
        ArrayList<IncidentData> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM Incidents";
        try {
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            Date incidentsDate = null;
            if (result!=null){
                try{
                    incidentsDate = new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("incidents_date"));
                }catch (ParseException e) {
                    e.printStackTrace();
                }
                while(result.next()){
                    IncidentData incident = new IncidentData(
                            result.getInt("incidentID"),result.getInt("resID"),
                            result.getInt("shiftID"),result.getString("description"), incidentsDate);
                    arrayList.add(incident);
                }
            }
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }


    /**
     * @return ArrayList with all medication entries in the database.
     */
    public ArrayList<MedicationData> getMedicationData() {
        ArrayList<MedicationData> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM Medication";
        try {
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result != null) {
                while(result.next()){
                    MedicationData medication = new MedicationData(
                            result.getInt("medicID"),result.getString("name"));
                    arrayList.add(medication);
                }
            }
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    /**
     * @return ArrayList with all medPlan entries in the database.
     */
    public ArrayList<MedPlanData> getMedPlanData() {
        ArrayList<MedPlanData> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM MedPlan";
        try {
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result != null) {
                while(result.next()){
                    MedPlanData medPlan = new MedPlanData(
                            result.getInt("medID"),result.getInt("resID"),
                            result.getDouble("concentration"),result.getDouble("intake_frequency")
                            , result.getInt("medicID"));
                    arrayList.add(medPlan);
                }
            }
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    /**
     * @return ArrayList with all shiftSchedule entries in the database.
     */
    public ArrayList<ShiftScheduleData> getShiftScheduleData() {
        ArrayList<ShiftScheduleData> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM shift_schedule";
        try {
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result != null) {
                while(result.next()){
                    Date date1 = null;
                    try{
                       date1 = new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("date"));
                    }catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ShiftScheduleData shiftSchedule = new ShiftScheduleData(
                            result.getInt("shiftID"),result.getInt("employeeID"),
                            result.getInt("category"),
                            date1, result.getString("shift_incidents"));
                    arrayList.add(shiftSchedule);
                }
            }
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }


    /**
     * @return ArrayList with all station entries in the database.
     */
    public ArrayList<StationData> getStationData() {
        ArrayList<StationData> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM Station";
        try {
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result != null) {
                while(result.next()){
                    StationData station = new StationData(
                            result.getInt("stationID"),result.getString("name"));
                    arrayList.add(station);
                }
            }
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    /**
     * @return ArrayList with all visit entries in the database.
     */
    public ArrayList<VisitsData> getVisitData() {
        ArrayList<VisitsData> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM Visits";
        try {
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result != null) {
                while(result.next()){
                    VisitsData visits = new VisitsData(
                            result.getInt("visitID"),result.getString("description"),
                            result.getInt("resID"));
                    arrayList.add(visits);
                }
            }
            statement.close();
        }catch (SQLException | NullPointerException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    /**
     * Creates a prepared sql-statement with the given String and one parameter. The statement
     * is executed and the ResultSet is returned.
     * @param sql for select statement
     * @param value for select statement
     * @return ResultSet with selected data from the database.
     */
    public static ResultSet createPreparedStatement(String sql, String value){
        try{
            Connection connection = DBConnect.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,value);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a prepared sql-statement with the given String and two parameters. The statement
     * is executed and the ResultSet is returned.
     * @param sql for select statement
     * @param value1 for select statement
     * @param value2 for select statement
     * @return ResultSet
     */
    public static ResultSet createPreparedStatement(String sql, String value1, String value2) {
        try{
            Connection connection = DBConnect.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,value1);
            statement.setString(2,value2);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates the incident with the given ID and sets its description to the
     * changed text provided in newText.
     * @param newText for update statement
     * @param incidentID for update statement
     */
    public void updateResidentIncidentsDataDatabase(String newText, int incidentID){
        try{
            String newTextEdited = newText.substring(10);
            Connection connection = DBConnect.connect();
            String sql = "UPDATE incidents set description = ? WHERE incidentID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newTextEdited);
            statement.setString(2, String.valueOf(incidentID));
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the shift incident with the given shiftID and sets its description to the
     * changed text provided in newText.
     * @param newText for update statement
     * @param shiftID for update statement
     */
    public void updateShiftIncidentsDataDatabase(String newText, int shiftID) {
        try{
            Connection connection = DBConnect.connect();
            String sql = "UPDATE shift_schedule set shift_incidents = ? WHERE shiftID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newText);
            statement.setString(2, String.valueOf(shiftID));
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Selects one Resident with the provided name.
     * @param name for select statement
     * @return ResidentData for select statement
     */
    public ResidentData getSingleResidentData(String name) {
        try{
            String sql = "Select * from senior_resident where name = ?";
            ResultSet result = DatabaseService.createPreparedStatement(sql, name);
            ResidentData resident = null;
            if (result != null) {
                result.getStatement().close();
                resident = new ResidentData(result.getInt("resID"),
                        result.getString("name"),
                        result.getString("surname"),
                        result.getInt("age"),
                        result.getInt("stationID"),
                        result.getInt("room"));
                result.close();
            }

            return resident;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Selects the Visit of the given resident.
     * @param resID for select statement
     * @return Description of the Visit of the provided resident.
     */
    public String getSingleVisitDataDescription(int resID) {
        try{
            String sql = "Select description from visits where resID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID));
            String description = null;
            if (rs != null) {
                description = rs.getString("description");
                rs.getStatement().close();
                rs.close();
            }


            return description;
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return "no visits";
            }
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Selects the ShiftSchedule with the provided shift category and date.
     * @param category for select statement
     * @param date for select statement
     * @return ShiftScheduleData
     */
    public ShiftScheduleData getSingleShiftScheduleData(Object category, Date date) {
        try{
            String sql = "Select * from shift_schedule where date = ? and category  = ?";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = formatter.format(date);
            ResultSet rs = DatabaseService.createPreparedStatement(sql, dateInString, String.valueOf(category));
            System.out.println(rs);
            Date date1 =null;
            try{
                if (rs != null) {
                    date1 = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date"));
                }
            }catch (ParseException pe) {
                pe.printStackTrace();
                //  return null;
            }

            ShiftScheduleData shiftSchedule;
            if (rs != null) {
                shiftSchedule = new ShiftScheduleData(
                        rs.getInt("shiftID"),
                        rs.getInt("employeeID"),
                        rs.getInt("category"),
                        date1,
                        rs.getString("shift_incidents"));
                rs.getStatement().close();
                rs.close();
                return shiftSchedule;
            }
            return new ShiftScheduleData(0,0,0,null,"no incidents");
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) {
                return new ShiftScheduleData(0,0,0,null,"no incidents");
            }
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Selects the ICE of the provided resident.
     * @param resID for select statement
     * @return ICEData
     */
    public ICEData getSingleICEData(int resID) {
        try{
            String sql = "Select * from ice where resID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID));
            if(rs != null) {
                rs.getStatement().close();
                ICEData iceData =  new ICEData(
                        rs.getInt("iceID"),
                        rs.getInt("resID"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("tel_number"),
                        rs.getString("adress"));
                rs.close();
                return iceData;
            }
            return new ICEData(0,0,null,null,0,null);
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) {
                return new ICEData(0,0,null,null,0,null);
            }
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Selects the incident of a resident on the given date.
     * @param resID for select statement
     * @param date for select statement
     * @return IncidentData
     */
    public IncidentData getSingleIncidentData(int resID, Date date) {
        try{
            String sql = "Select * from incidents where resID = ? and incidents_date = ?";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = formatter.format(date);
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID), dateInString);
            Date incidentsDate = null;
            try{
                if (rs != null) {
                    incidentsDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("incidents_date"));
                }
            }catch (ParseException e) {
                e.printStackTrace();
            }
            IncidentData incident;
            if (rs != null) {
                incident = new IncidentData(
                        rs.getInt("incidentID"),
                        rs.getInt("resID"),
                        rs.getInt("shiftID"),
                        rs.getString("description"), incidentsDate);
                rs.getStatement().close();
                rs.close();
                return incident;
            }
            return new IncidentData(0,0,0,"no incident", null);
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) {
                return new IncidentData(0,0,0,"no incident", null);
            }
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Selects the incident of a resident.
     * @param resID for select statement
     * @return IncidentData
     */
    public IncidentData getSingleIncidentData(int resID) {
        try{
            String sql = "Select * from incidents where resID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID));
            Date incidentsDate = null;
            try{
                if (rs != null) {
                    incidentsDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("incidents_date"));
                }
            }catch (ParseException e) {
                e.printStackTrace();
            }
            if (rs != null) {
                IncidentData incident = new IncidentData(
                        rs.getInt("incidentID"),
                        rs.getInt("resID"),
                        rs.getInt("shiftID"),
                        rs.getString("description"), incidentsDate);
                rs.getStatement().close();
                rs.close();
                return incident;
            }
            return new IncidentData(0,0,0,"no incident", null);
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) {
                return new IncidentData(0,0,0,"no incident", null);
            }
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Selects the name of the medication with the provided medicID.
     * @param medicID for select statement
     * @return Name of the Medication.
     */
    public String getSingleMedicationData(int medicID) {
        try{
            String sql = "Select name from medication where medicID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(medicID));
            String name = null;
            if (rs != null) {
                name = rs.getString("name");
                rs.getStatement().close();
                rs.close();
            }
            return name;
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) {
                return "no medication";
            }
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Selects the employee with the provided employeeID.
     * @param employeeID for select statement
     * @return EmployeeData.
     */
    public EmployeeData getSingleEmployeeData(int employeeID) {
        try{
            String sql = "Select * from employee where employeeID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(employeeID));
            if(rs != null) {
                EmployeeData employeeData = new EmployeeData(
                        rs.getInt("employeeID"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age"),
                        rs.getInt("stationID"));
                rs.getStatement().close();
                rs.close();
                return  employeeData;
            }
            return new EmployeeData(0,null,null,0,0);
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) {
                return new EmployeeData(0,null,null,0,0);
            }
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Selects the station with the provided stationID.
     * @param stationID for select statement
     * @return Name of the Station.
     */
    public String getSingleStationData(int stationID) {
        try{
            String sql = "Select name from station where stationID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(stationID));
            if(rs != null) {
                String name =  rs.getString("name");
                rs.getStatement().close();
                rs.close();
                return  name;
            }
            return null;
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) {
                return null;
            }
            e.printStackTrace();
            return null;
        }
    }



    /**
     * Selects the medPlan of a resident.
     * @param resID for select statement
     * @return MedPlanData
     */
    public MedPlanData getSingleMedPlanData(int resID) {
        try{
            String sql = "Select * from medplan where resID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID));
            System.out.println(rs);
            if(rs != null){
                MedPlanData medPlan = new MedPlanData(
                        rs.getInt("medID"),
                        rs.getInt("resID"),
                        rs.getDouble("intake_frequency"),
                        rs.getDouble("concentration"),
                        rs.getInt("medicID"));
                rs.getStatement().close();
                rs.close();
                return medPlan;
            }
            return new MedPlanData(0,0,0,0,0);
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) {
                return new MedPlanData(0, 0,0,0,0);
            }
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Saves the new incident description of a resident specific incident to the database.
     *
     * @param incidentData for insert statement
     */
    public void createNewResidentIncidentDatabase(IncidentData incidentData) {
        try{
            String newTextEdited = incidentData.description.substring(10);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = formatter.format(incidentData.incidentsDate);
            Connection connection = DBConnect.connect();
            String sql = "INSERT into incidents (incidentID, description, resID, shiftID, incidents_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(incidentData.incidentID));
            statement.setString(2, newTextEdited);
            statement.setString(3, String.valueOf(incidentData.resID));
            statement.setString(4, String.valueOf(incidentData.shiftID));
            statement.setString(5, dateInString);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}