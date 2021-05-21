package library.persistence.implementation;

/**
 * Database specific type MedicationData
 */
public class MedicationData {
    public final int medicID;
    public final String name;

    public MedicationData(int medicID, String name) {
        this.medicID = medicID;
        this.name = name;
    }
}
