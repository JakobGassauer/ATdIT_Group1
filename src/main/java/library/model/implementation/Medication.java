package library.model.implementation;

/**
 * Model specific type Medication
 */
public class Medication {
    private int medicID;
    private String name;

    public String getName() {
        return this.name;
    }

    public int getMedicID() {
        return this.medicID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMedicID(int medicID) {
        this.medicID = medicID;
    }
    public Medication(int medID, String name){
        this.medicID = medID;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "medID=" + medicID +
                ", name='" + name + '\'' +
                '}';
    }
}
