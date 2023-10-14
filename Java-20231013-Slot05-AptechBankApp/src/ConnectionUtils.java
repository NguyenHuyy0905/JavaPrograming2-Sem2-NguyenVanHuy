import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionUtils {
    private static final String hostName = "localhost"; // 127.0.0.1
    private static final String dbName = "aptechbankdb";
    private static final String userName = "root";
    private static final String password = "";
    // Chuỗi kết nối database
    // "jdbc:mysql://localhost:3307/MyProjectJava, userName, password"
    public static Connection connection() throws SQLException {
        String connectionURL = "jdbc:mysql://" + hostName + ":3307/" + dbName;
        Connection connection = DriverManager.getConnection(connectionURL, userName, password);
        return connection;
    }

    public static void close(Connection connection) throws SQLException {
        if(connection != null) {
            connection.close();
        }
    }

    public static void close(PreparedStatement pstm) throws SQLException {
        if (pstm != null) {
            pstm.close();
        }
    }
}
