package library.persistence.implementation;

import library.persistence.Factory;
import library.persistence.Service;


/**
 * The Factory is called to instantiate an implemented Service that is used in the Adapter
 */
public class DatabaseFactory implements Factory {
    Service service;

    public DatabaseFactory() {
        createService();
    }

    /**
     * @return Service of chosen Implementation
     */
    @Override
    public Service createService() {

        return service = new DatabaseService();  //konkreter Service muss hier gesetzt werden
    }
}