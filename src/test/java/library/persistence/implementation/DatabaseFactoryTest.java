package library.persistence.implementation;

import library.persistence.Factory;
import library.persistence.Service;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseFactoryTest {

    @Test
    void createServiceShouldNotReturnNull() {
        Factory factory = new DatabaseFactory();
        Service service = factory.createService();
        assertTrue(service!=null);
    }
}