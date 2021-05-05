package library.persistence;

import java.sql.SQLException;

public interface Service {
    class ServiceException extends SQLException {
        public ServiceException(String message){
            super(message);
        }
        public  ServiceException(String message, Throwable cause){
            super(message, cause);
        }
    }

    //List<T> getEntities() throws ServiceException;
    //void postEntities(List<T> entities) throws ServiceException;
}
