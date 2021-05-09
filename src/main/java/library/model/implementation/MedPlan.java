package library.model.implementation;

import library.model.Edit;
import library.persistence.implementation.DatabaseService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedPlan implements Edit<MedPlan> {
    private int medID;
    private int resID;
    private double intakeFrequency;
    private double concentration;
    private int medicID;

    public int getResID() {
        return this.resID;
    }

    public int getMedID() {
        return this.medID;
    }

    public double getConcentration() {
        return this.concentration;
    }

    public double getIntakeFrequency() {
        return this.intakeFrequency;
    }

    public int getMedicID() {
        return medicID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public void setMedID(int medID) {
        this.medID = medID;
    }

    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }

    public void setIntakeFrequency(double intakeFrequency) {
        this.intakeFrequency = intakeFrequency;
    }

    public void setMedicID(int medicID) {
        this.medicID = medicID;
    }

    @Override
    public String toString() {
        return "MedPlan{" +
                "medID=" + medID +
                ", resID=" + resID +
                ", intakeFrequency=" + intakeFrequency +
                ", concentration=" + concentration +
                ", medicID=" + medicID +
                '}';
    }

    public MedPlan(int medID, int resID, double concentration, double intakeFrequency, int medicID){
        this.concentration=concentration;
        this.intakeFrequency=intakeFrequency;
        this.medID=medID;
        this.resID=resID;
        this.medicID = medicID;
    }

    @Override
    public void add(MedPlan object) {
        //TODO implement add logic
    }

    public static MedPlan get(int resID) {
        try{
            String sql = "Select * from medplan where resID = ?";
            ResultSet rs = DatabaseService.createPreparedStatement(sql, String.valueOf(resID));
            System.out.println(rs);
            MedPlan medPlan = new MedPlan(
                    rs.getInt("medID"),
                    rs.getInt("resID"),
                    rs.getDouble("intake_frequency"),
                    rs.getDouble("concentration"),
                    rs.getInt("medicID"));
            rs.getStatement().close();
            rs.close();
            return medPlan;
        }catch (SQLException e){
            if(e.getMessage().equals("ResultSet closed")) { //result set is closed if there are no entries in db
                return new MedPlan(0, 0,0,0,0);
            }
            e.printStackTrace();
            return null;
        }// return initial values if ResultSet ist closed : überprüfe e.detailMessage.equals("ResultSet closed")
    }

    @Override
    public void remove(MedPlan object) {
        //TODO implement remove logic
    }

    @Override
    public MedPlan get() {
        return null;
        //TODO implement get logic
    }
}
