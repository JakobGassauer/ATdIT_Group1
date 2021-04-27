package library.model.implementation;

public class Medication {
    private int medID;
    private String name;

    public String getName() {
        return this.name;
    }

    public int getMedID() {
        return this.medID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMedID(int medID) {
        this.medID = medID;
    }
    public Medication(int medID, String name){
        this.medID = medID;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "medID=" + medID +
                ", name='" + name + '\'' +
                '}';
    }
}
