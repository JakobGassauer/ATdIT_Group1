package library.persistence.implementation;


import library.DBConnect;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

class DatabaseServiceTest {
    static DatabaseService service;
    static Connection connect;
    static String oldLocation;

    @BeforeAll
    static void beforeAll() {
        oldLocation = DBConnect.getLocation();
        DBConnect.setLocation("jdbc:sqlite:src/test/mockDatabase.sqlite");
        connect = DBConnect.connect();
        service = new DatabaseService();
    }

    @AfterAll
    static void afterAll(){
        DBConnect.setLocation(oldLocation);
        try {
            connect.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void getResidentDataTest() {
        ArrayList<ResidentData> residentDataArrayList = service.getResidentData();
        assertTrue(residentDataArrayList.size()==10);
        ResidentData resident1 = residentDataArrayList.get(0);
        assertEquals(resident1.resID, 1);
        assertEquals(resident1.room, 10);
        assertEquals(resident1.age, 85);
        assertEquals(resident1.name, "Robert");
        assertEquals(resident1.surname, "Ibe");
        assertEquals(resident1.stationID, 1);
    }

    @Test
    void createPreparedStatementTestOneParameter() {
        ResultSet result = DatabaseService.createPreparedStatement(
                "select name from senior_resident where resID = ?", "1");
        Statement statement = null;
        try {
            statement = result.getStatement();
            assertTrue(!statement.isClosed());
            statement.close();
            result.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void createPreparedStatementTestTwoParameters() {
        ResultSet result = DatabaseService.createPreparedStatement(
                "select name from senior_resident where resID = ? and stationID = ?", "1", "1");
        Statement statement = null;
        try {
            statement = result.getStatement();
            assertTrue(!statement.isClosed());
            statement.close();
            result.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void updateResidentIncidentsDataDatabase() {
        service.updateResidentIncidentsDataDatabase("Vorfälle: test", 1);
        String newText = null;
        try {
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery("Select description from incidents where incidentID = 1");
            newText = result.getString("description");
            statement.close();
            result.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assertTrue(newText.equals("test"));
    }

    @Test
    void updateShiftIncidentsDataDatabase() {
        service.updateShiftIncidentsDataDatabase("test", 1);
        String newText = null;
        try {
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery("Select shift_incidents from shift_schedule where shiftID = 1");
            newText = result.getString("shift_incidents");
            statement.close();
            result.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assertTrue(newText.equals("test"));
    }

    @Test
    void getSingleResidentData() {
        ResidentData residentData = service.getSingleResidentData("Robert");
        assertTrue(residentData.resID==1);
        assertEquals(residentData.room, 10);
        assertEquals(residentData.age, 85);
        assertEquals(residentData.name, "Robert");
        assertEquals(residentData.surname, "Ibe");
        assertEquals(residentData.stationID, 1);
    }

    @Test
    void createNewResidentIncidentDatabaseTest() {
        IncidentData incident = new IncidentData(50,1,1,"Vorfälle: test",new Date());
        service.createNewResidentIncidentDatabase(incident);
        try{
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery("Select * from incidents where incidentID = 50");
            IncidentData newIncident = new IncidentData(result.getInt("incidentID"),
                    result.getInt("resID"),
                    result.getInt("shiftID"),
                    result.getString("description"),
                    new Date());
            statement.close();
            result.close();
            Statement statement1  = connect.createStatement();
            statement1.executeUpdate("Delete from incidents where incidentID = 50");
            statement.close();
            assertEquals(incident.incidentID, newIncident.incidentID);
            assertEquals(incident.resID, newIncident.resID);
            assertEquals(incident.shiftID, newIncident.shiftID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}