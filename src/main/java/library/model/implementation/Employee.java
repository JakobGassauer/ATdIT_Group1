package library.model.implementation;

import library.model.People;

public class Employee extends People {
    private int employeeID;
    private int stationID;

    @Override
    public String toString() {
        return "People{" +
                "EmployeeID =" + this.employeeID + '\'' +
                ", stationID=" + this.stationID + '\'' +
                ", name='" + this.name + '\'' +
                ", surname='" + this.surname + '\'' +
                ", age=" + this.age +
                '}';
    }

    public int getEmployeeID() {
        return this.employeeID;
    }

    public int getStationID() {
        return this.stationID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public Employee(int employeeID, String name, String surname, int age, int stationID){
        this.employeeID = employeeID;
        this.stationID = stationID;
        this.name = name;
        this.age = age;
        this.surname = surname;
    }

}
