package library.persistence;

public interface Service {
    class ServiceException extends Exception{
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
