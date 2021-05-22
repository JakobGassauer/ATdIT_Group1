package library.model.implementation;

/**
 * Model specific type MedPlan which implements the interface Edit
 */
public class MedPlan {
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


}
