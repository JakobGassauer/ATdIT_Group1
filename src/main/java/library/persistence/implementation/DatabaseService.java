package library.persistence.implementation;

import library.model.implementation.*;
import library.persistence.*;
import library.DBConnect;
//import org.graalvm.compiler.core.common.calc.FloatConvertCategory;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("HardCodedStringLiteral")
public class DatabaseService implements Service {
    public DatabaseService(){

    }

    //Residents
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

    // Employees
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

    // ICE
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

    // Incidents
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

    // Medication
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
                            result.getInt("medID"),result.getString("name"));
                    arrayList.add(medication);
                }
            }
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    // MedPlan
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
                            result.getDouble("concentration"),result.getDouble("intakeFrequency")
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

    //ShiftSchedule
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


    //Station
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

    // Visits
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

    public static ResultSet createNewStatement(String sql){
        try {
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        }catch(SQLException e){
            //todo error handling
            return null;
        }
    }

    public static ResultSet createPreparedStatement(String sql, String name){
        try{
            Connection connection = DBConnect.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

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

    public static void updateIncidentsDatabase(String newText, Incident incident){
        try{
            String newTextEdited = newText.substring(10);
            Connection connection = DBConnect.connect();
            String sql = "UPDATE incidents set description = ? WHERE resID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newTextEdited);
            statement.setString(2, String.valueOf(incident.getResID()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResidentData getSingleResidentData(String name) {
        try{
            String sql = "Select * from senior_resident where name = ?";
            ResultSet result = DatabaseService.createPreparedStatement(sql, name);
            ResidentData resident = new ResidentData(result.getInt("resID"),
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

    public String getSingleVisitDataDescription(int resID) {
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

    public ShiftScheduleData getSingleShiftScheduleData(Object category, Date date) {
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
            ShiftScheduleData shiftSchedule = new ShiftScheduleData(
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
                return new ShiftScheduleData(0,0,0,null,"no shift incidents");
            }
            e.printStackTrace();
            return null;
        }
    }


    public ICEData getSingleICEData(int resID) {
        try{
            String sql = "Select * from ice where resID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID));
            if(rs != null) {
                return new ICEData(
                        rs.getInt("iceID"),
                        rs.getInt("resID"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("tel_number"),
                        rs.getString("adress"));
            }
            rs.getStatement().close();
            rs.close();
            return new ICEData(0,0,null,null,0,null);
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return new ICEData(0,0,null,null,0,null);
            }
            e.printStackTrace();
            return null;
        }
    }

    public IncidentData getSingleIncidentData(int resID, Date date) {
        try{
            String sql = "Select * from incidents where resID = ? and incidents_date = ?";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = formatter.format(date);
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID), dateInString);
            Date incidentsDate = null;
            try{
                incidentsDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("incidents_date"));
            }catch (ParseException e) {
                e.printStackTrace();
            }
            IncidentData incident = new IncidentData(
                    rs.getInt("incidentID"),
                    rs.getInt("resID"),
                    rs.getInt("shiftID"),
                    rs.getString("description"), incidentsDate);
            rs.getStatement().close();
            rs.close();
            return incident;
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return new IncidentData(0,0,0,"no incident", null);
            }
            e.printStackTrace();
            return null;
        }
    }

    public IncidentData getSingleIncidentData(int resID) {
        try{
            String sql = "Select * from incidents where resID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID));
            Date incidentsDate = null;
            try{
                incidentsDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("incidents_date"));
            }catch (ParseException e) {
                e.printStackTrace();
            }
            IncidentData incident = new IncidentData(
                    rs.getInt("incidentID"),
                    rs.getInt("resID"),
                    rs.getInt("shiftID"),
                    rs.getString("description"), incidentsDate);
            rs.getStatement().close();
            rs.close();
            return incident;
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return new IncidentData(0,0,0,"no incident", null);
            }
            e.printStackTrace();
            return null;
        }
    }

    public String getSingleMedicationData(int medicID) {
        try{
            String sql = "Select name from medication where medicID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(medicID));
            String name = rs.getString("name");
            rs.getStatement().close();
            rs.close();
            return name; //todo testen ob das richtige zurückgeben wird
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return "no medication";
            }
            e.printStackTrace();
            return null;
        }
    }

    public MedPlanData getSingleMedPlanData(int resID) {
        try{
            String sql = "Select * from medplan where resID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID));
            System.out.println(rs);
            MedPlanData medPlan = new MedPlanData(
                    rs.getInt("medID"),
                    rs.getInt("resID"),
                    rs.getDouble("intake_frequency"),
                    rs.getDouble("concentration"),
                    rs.getInt("medicID"));
            rs.getStatement().close();
            rs.close();
            return medPlan;
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return new MedPlanData(0, 0,0,0,0);
            }
            e.printStackTrace();
            return null;
        }
    }
}