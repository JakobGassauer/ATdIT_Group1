package library.model.implementation;

import library.model.People;
import library.persistence.implementation.DatabaseService;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static ICE get(int resID) {
        try{
            String sql = "Select * from ice where resID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID));
            ICE ice = new ICE(
                    rs.getInt("iceID"),
                    rs.getInt("resID"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getInt("tel_number"),
                    rs.getString("adress"));
            return ice;
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return new ICE(0,0,null,null,0,null);
            }
            e.printStackTrace();
            return null;
        }
    }
}
