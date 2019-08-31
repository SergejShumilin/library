package by.javatr.library.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private final static String URL = "jdbc:mysql://localhost:3306/library?serverTimezone=Europe/Moscow&useSSL=false";
    private final static String NAME = "root";
    private final static String PASS = "12345";

    public Connection makeConnection() throws SQLException {
                return DriverManager.getConnection(URL, NAME, PASS);
    }

}
