package library.persistence.implementation;

public class ResidentData {
    public final String name;
    public final  String surname;
    public final  int age;
    public final  int resID;
    public final  int room;
    public final  int stationID;

    public ResidentData(int resID, String name, String surname, int age, int stationID, int room) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.resID = resID;
        this.room = room;
        this.stationID = stationID;
    }
}
