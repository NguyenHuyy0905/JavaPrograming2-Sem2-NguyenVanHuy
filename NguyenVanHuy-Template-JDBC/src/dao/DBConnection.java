package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final String hostname = "localhost";
    private static final String dbname = "TestJavaTwoDataBase";
    private static final String username = "root";
    private static final String password = "";
    private static final String connectionURL = "jdbc:mysql://" + hostname + ":3307/" + dbname;
    public static Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionURL, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
