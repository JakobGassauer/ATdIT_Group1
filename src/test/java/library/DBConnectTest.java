package library;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectTest {

    @Test
    void connectionShouldNotBeNull() {
        Connection connect;
        connect = DBConnect.connect();
        assertTrue(connect != null);
    }
}