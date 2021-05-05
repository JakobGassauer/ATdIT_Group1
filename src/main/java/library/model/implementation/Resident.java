package library.model.implementation;

import library.model.People;
import library.persistence.implementation.DatabaseService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Resident extends People {
    private int resID;
    private int room;
    private int stationID;

    public int getResID() { return resID; }

    public int getRoom() { return room; }

    public int getStationID() { return stationID; }

    public void setResID(int resID) { this.resID = resID; }

    public void setRoom(int room) { this.room = room; }

    public void setStationID(int stationID) { this.stationID = stationID; }

    public Resident(int resID, String name, String surname, int age, int stationID, int room){
        this.resID = resID;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.stationID = stationID;
        this.room = room;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", resID=" + resID +
                ", room=" + room +
                ", stationID=" + stationID +
                '}';
    }

    public static Resident get(int index) {
        List<Resident> residentsArrayList =new ArrayList<>();
        residentsArrayList = DatabaseService.getResidents();
        Resident resident = residentsArrayList.get(index);
        return resident;
        //TODO implement get logic
    }

    public static Resident get(String name) {
        try{
            String sql = "Select * from senior_resident where name = ?";
            ResultSet result = DatabaseService.createPreparedStatement(sql, name);
            Resident resident = new Resident(result.getInt("resID"),
                    result.getString("name"),
                    result.getString("surname"),
                    result.getInt("age"),
                    result.getInt("stationID"),
                    result.getInt("room"));
            return resident;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
}
