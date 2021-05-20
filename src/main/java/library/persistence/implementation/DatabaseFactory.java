package library.persistence.implementation;

import library.persistence.Factory;
import library.persistence.Service;



public class DatabaseFactory implements Factory {
    Service service;

    public DatabaseFactory() {
        createService();
    }

    @Override
    public Service createService() {

        return service = new DatabaseService();  //konkreter Service muss hier gesetzt werden
    }
}