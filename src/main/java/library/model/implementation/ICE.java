package library.model.implementation;

import library.model.People;
import library.persistence.implementation.DatabaseService;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Model specific type ICE which inherits from the abstract class People
 */
public class ICE extends People {
    private int iceID;
    private int resID;
    private int telnumber;
    private String adress;

    public ICE(int iceID, int resID, int telnumber, String adress){
        this.iceID=iceID;
        this.resID=resID;
        this.telnumber=telnumber;
        this.adress=adress;
    }

    public ICE(int iceID, int resID, String name, String surname, int telnumber, String adress) {
        //super();
        this.iceID=iceID;
        this.resID=resID;
        this.telnumber=telnumber;
        this.adress=adress;
        this.name = name;
        this.surname = surname;
    }

    public int getIceID() {
        return iceID;
    }

    public int getResID() {
        return resID;
    }

    public int getTelnumber() {
        return telnumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setIceID(int iceID) {
        this.iceID = iceID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public void setTelnumber(int telnumber) {
        this.telnumber = telnumber;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "ICE{" +
                "iceID=" + iceID +
                ", resID=" + resID +
                ", telnumber=" + telnumber +
                ", adress='" + adress + '\'' +
                '}';
    }

   // public static ICE get(int resID) {
    //    return DatabaseService.getSingleICE(resID);
    //}
}
