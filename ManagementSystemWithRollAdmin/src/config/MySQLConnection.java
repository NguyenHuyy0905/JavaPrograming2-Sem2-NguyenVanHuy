package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String hostname = "localhost";
    private static final String dbname = "managementsystemwithrolladmin";
    private static final String username = "root";
    private static final String password = "";
    private static final String connectionURL = "jdbc:mysql://" + hostname + ":3307/" + dbname;
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(connectionURL, username, password);
        return connection;
    }
}
