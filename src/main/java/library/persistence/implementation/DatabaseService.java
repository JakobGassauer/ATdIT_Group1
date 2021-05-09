package library.persistence.implementation;

import library.model.implementation.*;
import library.persistence.Service;
import library.DBConnect;
//import org.graalvm.compiler.core.common.calc.FloatConvertCategory;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseService implements Service {
    public DatabaseService(){
    }

    //Residents
    public static ArrayList<Resident> getResidents() {
        ArrayList<Resident> residentArrayList = new ArrayList<>();
        String sql = "SELECT * FROM senior_resident";
        try {
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                Resident resident = new Resident(
                        result.getInt("resID"),result.getString("name"),
                        result.getString("surname"),result.getInt("age"),
                        result.getInt("stationID"),result.getInt("room"));
                residentArrayList.add(resident);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return residentArrayList;
    }

    // Employees
    public static ArrayList<Employee> getEmployees() {
        ArrayList<Employee> residentArrayList = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try {
            ResultSet result = createNewStatement(sql);
            if (result != null) {
                while(result.next()){
                    Employee employee = new Employee(
                            result.getInt("employeeID"),result.getString("name"),
                            result.getString("surname"),result.getInt("age"),
                            result.getInt("stationID"));
                    residentArrayList.add(employee);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return residentArrayList;
    }

    // ICE
    public static ArrayList<ICE> getICE() {
        ArrayList<ICE> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM ICE";
        try {
            ResultSet result = createNewStatement(sql);
            if (result != null) {
                while(result.next()){
                    ICE ice = new ICE(
                            result.getInt("iceID"),result.getInt("resID"),
                            result.getString("name"),result.getString("surname"),
                            result.getInt("tel_number"),result.getString("adress"));
                    arrayList.add(ice);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    // Incidents
    public static ArrayList<Incident> getIncidents() {
        ArrayList<Incident> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM Incidents";
        try {
            ResultSet result = createNewStatement(sql);
            Date incidentsDate = null;
            if (result!=null){
                try{
                    incidentsDate = new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("incidents_date"));
                }catch (ParseException e) {
                    e.printStackTrace();
                }
                while(result.next()){
                    Incident incident = new Incident(
                            result.getInt("incidentID"),result.getInt("resID"),
                            result.getInt("shiftID"),result.getString("description"), incidentsDate);
                    arrayList.add(incident);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    // Medication
    public static ArrayList<Medication> getMedication() {
        ArrayList<Medication> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM Medication";
        try {
            ResultSet result = createNewStatement(sql);
            if (result != null) {
                while(result.next()){
                    Medication medication = new Medication(
                            result.getInt("medID"),result.getString("name"));
                    arrayList.add(medication);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    // MedPlan
    public static ArrayList<MedPlan> getMedPlan() {
        ArrayList<MedPlan> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM MedPlan";
        try {
            ResultSet result = createNewStatement(sql);
            if (result != null) {
                while(result.next()){
                    MedPlan medPlan = new MedPlan(
                            result.getInt("medID"),result.getInt("resID"),
                            result.getDouble("concentration"),result.getDouble("intakeFrequency")
                            , result.getInt("medicID"));
                    arrayList.add(medPlan);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    //ShiftSchedule
    public static ArrayList<ShiftSchedule> getShiftSchedule() {
        ArrayList<ShiftSchedule> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM shift_schedule";
        try {
            ResultSet result = createNewStatement(sql);
            if (result != null) {
                while(result.next()){
                    Date date1 = null;
                    try{
                       date1 = new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("date"));
                    }catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ShiftSchedule shiftSchedule = new ShiftSchedule(
                            result.getInt("shiftID"),result.getInt("employeeID"),
                            result.getInt("category"),
                            date1, result.getString("shift_incidents"));
                    arrayList.add(shiftSchedule);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }


    //Station
    public static ArrayList<Station> getStation() {
        ArrayList<Station> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM Station";
        try {
            ResultSet result = createNewStatement(sql);
            if (result != null) {
                while(result.next()){
                    Station station = new Station(
                            result.getInt("stationID"),result.getString("name"));
                    arrayList.add(station);
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    // Visits
    public static ArrayList<Visits> getVisits() {
        ArrayList<Visits> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM Visits";
        try {
            ResultSet result = createNewStatement(sql);
            if (result != null) {
                while(result.next()){
                    Visits visits = new Visits(
                            result.getInt("visitID"),result.getString("description"),
                            result.getInt("resID"));
                    arrayList.add(visits);
                }
            }
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
            String sql = "UPDATE incidents set incidentID = ?, description = ?, resID = ?, shiftID = ?, incidents_date = ? WHERE resID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(incident.getIncidentID()));
            statement.setString(2, newTextEdited);
            statement.setString(3, String.valueOf(incident.getResID()));
            statement.setString(4, String.valueOf(incident.getShiftID()));
            statement.setString(5, String.valueOf(incident.getIncidentsDate()));
            statement.setString(6, String.valueOf(incident.getResID()));
            statement.execute();

            //"REPLACE into incidents (description) values (" + newTextEdited + ") WHERE resID = " + incident.getResID();

                    /*"REPLACE into incidents (incidentID, description, resID, shiftID, incidentsDate) " +
                    "values (" + incident.getIncidentID() + ", " +
                    newTextEdited + ", " +
                    incident.getResID() + ", " +
                    incident.getShiftID() + ", " +
                    incident.getIncidentsDate() + ") ";*/
            //WHERE resID = " + resID;
            //Statement statement = connection.createStatement();
            //  statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


   /* public List<entity> getTable() throws ServiceException{
        ArrayList<entity> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM ?";
        try{            Connection connection = DBConnect.connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(0,(String) entity);
            ResultSet result = statement.executeQuery(sql);
           while(result.next()){
                Resident resident = new Resident(
                        result.getInt("resID"),result.getString("name"),
                        result.getString("surname"),result.getInt("age"),
                        result.getInt("stationID"),result.getInt("room"));
                arrayList.add(resident);
                result.next();
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return arrayList;
    }*/


    /*public static void main(String[] args){
        List<Resident> residents = new ArrayList<Resident>();
        //try {
          residents = DatabaseService.getResidents();
        //} catch (ServiceException e){
        //    System.out.println(e);
        //}
        if(!residents.isEmpty()){
            for(Resident resident: residents){
                System.out.println(resident.toString());
            }
        }else
            System.out.println("fail");

    }*/
}