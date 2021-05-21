package library.persistence.implementation;

/**
 * Database specific type StationData
 */
public class StationData {
    public final int stationID;
    public final String name;

    public StationData(int stationID, String name) {
        this.stationID = stationID;
        this.name = name;
    }
}
