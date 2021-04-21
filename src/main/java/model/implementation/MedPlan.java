package model.implementation;

import model.Edit;

public class MedPlan implements Edit<MedPlan> {
    private int medID;
    private int resID;
    private double intakeFrequency;
    private double concentration;

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

    @Override
    public String toString() {
        return "MedPlan{" +
                "medID=" + medID +
                ", resID=" + resID +
                ", intakeFrequency=" + intakeFrequency +
                ", concentration=" + concentration +
                '}';
    }

    public MedPlan(int medID, int resID, double concentration, double intakeFrequency){
        this.concentration=concentration;
        this.intakeFrequency=intakeFrequency;
        this.medID=medID;
        this.resID=resID;
    }

    @Override
    public void add(MedPlan object) {
        //TODO implement add logic
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
