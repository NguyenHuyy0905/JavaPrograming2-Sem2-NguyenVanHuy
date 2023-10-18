package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    public static Connection createConnection() {
        String dbUrl = "jdbc:mysql://localhost:3307/myDBSlotNineJavaTwo";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
