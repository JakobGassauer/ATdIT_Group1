package library.persistence.implementation;

/**
 * Database specific type ICEData
 */
public class ICEData {
    public String name;
    public String surname;
    public int age;
    public final int iceID;
    public final int resID;
    public final int telnumber;
    public final String adress;

    public ICEData( int iceID,int resID, String name, String surname,  int telnumber, String adress) {
        this.name = name;
        this.surname = surname;
        this.iceID = iceID;
        this.resID = resID;
        this.telnumber = telnumber;
        this.adress = adress;
    }
}
