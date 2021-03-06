package library.model.implementation;

import library.model.People;

/**
 * Model specific type ICE which inherits from the abstract class People
 */
public class ICE extends People {
    private int iceID;
    private int resID;
    private String telnumber;
    private String adress;

    public ICE(int iceID, int resID, String telnumber, String adress){
        this.iceID=iceID;
        this.resID=resID;
        this.telnumber=telnumber;
        this.adress=adress;
    }

    public ICE(int iceID, int resID, String name, String surname, String telnumber, String adress) {
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

    public String getTelnumber() {
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

    public void setTelnumber(String telnumber) {
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

}
