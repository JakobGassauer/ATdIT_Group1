package library.persistence;

public class EmployeeData {
    public final String name;
    public final String surname;
    public final int age;
    public final int employeeID;
    public final int stationID;

    public EmployeeData(int employeeID, String name, String surname, int age, int stationID) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.employeeID = employeeID;
        this.stationID = stationID;
    }
}
