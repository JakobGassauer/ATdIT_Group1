package library.model.implementation;

public class Station {
    private int stationID;
    private String name;

    public String getName() { return this.name; }

    public int getStationID() { return this.stationID; }

    public void setName(String name) { this.name = name; }

    public void setStationID(int stationID) { this.stationID = stationID; }

    public Station(int stationID, String name){
        this.stationID = stationID;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationID=" + stationID +
                ", name='" + name + '\'' +
                '}';
    }
}
