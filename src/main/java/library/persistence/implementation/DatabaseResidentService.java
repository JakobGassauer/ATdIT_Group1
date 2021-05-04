package library.persistence.implementation;

import library.model.implementation.Resident;
import library.persistence.Service;
import library.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseResidentService implements Service {

    public static List<Resident> getResidents() throws ServiceException{
        ArrayList<Resident> residentArrayList = new ArrayList<Resident>();
        String sql = "SELECT resID,name,surname,age,room,stationID,visitID,iceID FROM senior_resident";
        try /*(Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql))*/ {

            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            System.out.println(result.getInt("resID"));
            System.out.println(result.getString("name"));
            System.out.println(result.getString("surname"));
            System.out.println(result.getInt("age"));
            System.out.println(result.getInt("room"));
            System.out.println(result.getInt("stationID"));
            System.out.println(result.getInt("visitID"));
            System.out.println(result.getInt("iceID"));

/*
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            while(result.next()){
                Resident resident = new Resident(
                        result.getInt("resID"),result.getString("name"),
                        result.getString("surname"),result.getInt("age"),
                        result.getInt("stationID"),result.getInt("room"));
                residentArrayList.add(resident);
                result.next();
            }
            */

        }catch (SQLException e){
            System.out.println(e);
        }

        return residentArrayList;
    }


    public static void main(String[] args){
        List<Resident> residents = new ArrayList<Resident>();
        try {
          residents = DatabaseResidentService.getResidents();
        } catch (ServiceException e){
            System.out.println(e);
        }
        if(!residents.isEmpty()){
            for(Resident resident: residents){
                System.out.println(resident.toString());
            }
        }else
            System.out.println("fail");

    }
}