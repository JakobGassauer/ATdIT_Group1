package library.model.implementation;

import library.model.People;

public class ICE extends People {
    private int iceID;
    private int resID;
    private float telnumber;
    private String adress;

    public ICE(int iceID, int resID, float telnumber, String adress){
        this.iceID=iceID;
        this.resID=resID;
        this.telnumber=telnumber;
        this.adress=adress;
    }

    public int getIceID() {
        return iceID;
    }

    public int getResID() {
        return resID;
    }

    public float getTelnumber() {
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

    public void setTelnumber(float telnumber) {
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
