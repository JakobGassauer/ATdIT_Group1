package library.persistence.implementation;

/**
 * Database specific type MedPlanData
 */
public class MedPlanData {
    public final int medID;
    public final int resID;
    public final double intakeFrequency;
    public final double concentration;
    public final int medicID;

    public MedPlanData(int medID, int resID, double concentration,double intakeFrequency,  int medicID) {
        this.medID = medID;
        this.resID = resID;
        this.intakeFrequency = intakeFrequency;
        this.concentration = concentration;
        this.medicID = medicID;
    }
}
