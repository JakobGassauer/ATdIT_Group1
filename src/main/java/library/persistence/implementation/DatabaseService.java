package library.persistence.implementation;

import library.model.implementation.*;
import library.persistence.Service;
import library.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService implements Service {
    public DatabaseService(){
    }

    //Residents
    public static ArrayList<Resident> getResidents() {
        ArrayList<Resident> residentArrayList = new ArrayList<Resident>();
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
            System.out.println(e);
        }
        return residentArrayList;
    }

    // Employees
    public static ArrayList<Employee> getEmployees() {
        ArrayList<Employee> residentArrayList = new ArrayList<Employee>();
        String sql = "SELECT * FROM employee";
        try {
            ResultSet result = createNewStatement(sql);
            while(result.next()){
                Employee employee = new Employee(
                        result.getInt("employeeID"),result.getString("name"),
                        result.getString("surname"),result.getInt("age"),
                        result.getInt("stationID"));
                residentArrayList.add(employee);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return residentArrayList;
    }

    // ICE
    public static ArrayList<ICE> getICE() {
        ArrayList<ICE> arrayList = new ArrayList<ICE>();
        String sql = "SELECT * FROM ICE";
        try {
            ResultSet result = createNewStatement(sql);
            while(result.next()){
                ICE ice = new ICE(
                        result.getInt("iceID"),result.getInt("resID"),
                        result.getString("name"),result.getString("surname"),
                        result.getInt("tel_number"),result.getString("adress"));
                arrayList.add(ice);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return arrayList;
    }

    // Incidents
    public static ArrayList<Incident> getIncidents() {
        ArrayList<Incident> arrayList = new ArrayList<Incident>();
        String sql = "SELECT * FROM Incidents";
        try {
            ResultSet result = createNewStatement(sql);
            while(result.next()){
                Incident incident = new Incident(
                        result.getInt("incidentID"),result.getInt("resID"),
                        result.getInt("shiftID"),result.getString("description"));
                arrayList.add(incident);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return arrayList;
    }

    // Medication
    public static ArrayList<Medication> getMedication() {
        ArrayList<Medication> arrayList = new ArrayList<Medication>();
        String sql = "SELECT * FROM Medication";
        try {
            ResultSet result = createNewStatement(sql);
            while(result.next()){
                Medication medication = new Medication(
                        result.getInt("medID"),result.getString("name"));
                arrayList.add(medication);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return arrayList;
    }

    // MedPlan
    public static ArrayList<MedPlan> getMedPlan() {
        ArrayList<MedPlan> arrayList = new ArrayList<MedPlan>();
        String sql = "SELECT * FROM MedPlan";
        try {
            ResultSet result = createNewStatement(sql);
            while(result.next()){
                MedPlan medPlan = new MedPlan(
                        result.getInt("medID"),result.getInt("resID"),
                        result.getDouble("concentration"),result.getDouble("intakeFrequency")
                        , result.getInt("medicID"));
                arrayList.add(medPlan);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return arrayList;
    }

    //ShiftSchedule
    public static ArrayList<ShiftSchedule> getShiftSchedule() {
        ArrayList<ShiftSchedule> arrayList = new ArrayList<ShiftSchedule>();
        String sql = "SELECT * FROM ShiftSchedule";
        try {
            ResultSet result = createNewStatement(sql);
            while(result.next()){
                ShiftSchedule shiftSchedule = new ShiftSchedule(
                        result.getInt("shiftID"),result.getInt("employeeID"), result.getInt("category"), result.getDate("date"), result.getString("shiftIncidents"));
                arrayList.add(shiftSchedule);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return arrayList;
    }


    //Station
    public static ArrayList<Station> getStation() {
        ArrayList<Station> arrayList = new ArrayList<Station>();
        String sql = "SELECT * FROM Station";
        try {
            ResultSet result = createNewStatement(sql);
            while(result.next()){
                Station station = new Station(
                        result.getInt("stationID"),result.getString("name"));
                arrayList.add(station);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return arrayList;
    }

    // Visits
    public static ArrayList<Visits> getVisits() {
        ArrayList<Visits> arrayList = new ArrayList<Visits>();
        String sql = "SELECT * FROM Visits";
        try {
            ResultSet result = createNewStatement(sql);
            while(result.next()){
                Visits visits = new Visits(
                        result.getInt("visitID"),result.getString("description"),
                        result.getInt("resID"));
                arrayList.add(visits);
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return arrayList;
    }

    public static ResultSet createNewStatement(String sql){
        try {
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result;
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
            ResultSet result = statement.executeQuery();
            return result;
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
            ResultSet result = statement.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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