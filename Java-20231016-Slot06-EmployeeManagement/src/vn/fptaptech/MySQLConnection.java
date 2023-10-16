package vn.fptaptech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    private static final String hostname = "localhost";
    private static final String dbname = "employeemanagement";
    private static final String username = "root";
    private static final String password = "";
    public static Connection getMyConnection() throws SQLException {
        String connectionURL = "jdbc:mysql://" + hostname + ":3307/" + dbname;
        Connection connection = DriverManager.getConnection(connectionURL, username, password);
        return connection;
    }
    public static void createEmployeeTable() throws SQLException {
        // 1. Tạo connection
        Connection connection = getMyConnection();
        Statement statement = connection.createStatement();
        // 2. Thực thi các chỉ thị với Database
        // CREATE TABLE
        statement.execute("DROP TABLE IF EXISTS employee");
        statement.execute("CREATE TABLE employee(id VARCHAR(10) PRIMARY KEY, name VARCHAR(20), salary VARCHAR(20))");
    }
}
