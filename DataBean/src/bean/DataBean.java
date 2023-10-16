package bean;
/**
 *
 * @author thayTuan
 */
import javax.management.DescriptorRead;
import java.util.*;
import java.sql.*;
public class DataBean {
    private Connection connection = null;
    private ResultSet resultSet = null;

    // Khai báo về các kiểu câu truy vấn có thể sử dụng
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private CallableStatement storeProc = null;

    // Các thông số về kết quả trả về
    private int resultsettype = 0;
    private int resultsetconcurrency = 0;

    // Các thông số cần để kết nối cơ sở dữ liệu để truy vấn
    private String driver = null;
    private String url = null;
    private String username = null;
    private String password = null;
    private Properties properties = null;

    private String database;        // Tên cơ sở dữ liệu làm việc
    private String table;           // Tên bảng cần thực hiện truy vấn
    private Vector<String> fields;  // Tên các trường trong bảng

    // Số hiệu lỗi và câu thông báo lỗi
    private int error = 0;
    private String errmsg = null;
    private boolean preparestatement = false;

    /**
     * Phương thức xem lỗi khi thực hiện truy vấn
     */
    public int getError() {
        return error;
    }

    /**
     * Phương thức thiết lập tên người dùng
     *
     * @param usr Tên người dùng mới cần thay đổi
     */
    public void setUsername(String usr) {
        username = usr;
    }

    /**
     * Phương thức lấy tên của người dùng
     */
    public String getUsername() {
        return username;
    }

    /**
     * Phương thức thiết lập mật khẩu truy cập CSDL
     *
     * @param psw Mật khẩu truy cập
     */
    public void setPassword(String psw) {
        password = psw;
    }

    /**
     * Phương thức lấy mật khẩu truy cập
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Phương thức thiết lập driver để truy cập CSDL
     *
     * @param drv Tên driver truy cập CSDL
     */
    public void setDriver(String drv) {
        driver = drv;
    }

    /**
     * Phương thức trả về tên của driver truy cập CSDL
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Phương thức thiết lập đường dẫn tới CSDL
     *
     * @param Url Đường dẫn tới bảng CSDL cần truy vấn
     */
    public void setUrl(String Url) {
        url = Url;
    }

    /**
     * Phương thức trả về đường dẫn tới bảng CSDL cần truy vấn
     */
    public String getUrl() {
        return url;
    }

    /**
     * Phương thức thiết lập các thuộc tính trong tìm kiếm
     */
    public void setProperties(Properties pr) {
        properties = pr;
    }

    public Properties getProperties() {
        return properties;
    }

    /**
     * Phương thức thiết lập kết nối tới CSDL
     *
     * @param con Đối tượng kết nối tới CSDL
     */
    public void setConnection(Connection con) {
        // Nếu chưa có driver thì gán lỗi số 1
        if (driver == null) {
            error = 1;
            return;
        }

        // Nếu chưa có đường dẫn tới CSDL thì gán lỗi số 2
        if (url == null) {
            error = 2;
            return;
        }

        try {
            Class.forName(driver); // Nạp driver
            // Nếu biến thuộc tính chưa có thì truy cập theo tên và mật khẩu
            if ((username == null) && (password == null)) {
                con = DriverManager.getConnection(url);
            } else {
                con = DriverManager.getConnection(url, username, password);
            }
            connection = con;
        } catch (Exception e) {
            errmsg = e.getMessage();
            error = -1;
            System.out.println("Có lỗi khi tạo kết nối");
            return;
        }
        error = 0;
    }
    /**
     * Phương thức lấy kết nối đến CSDL
     */
    public Connection getConnection() {
        if (connection == null) {
            error = 3;
        }
        return connection;
    }

    /**
     * Thiết lập kiểu của kết quả trả về
     *
     * @param type Kiểu của kết quả trả về
     */
    public void setResultsettype(int type) {
        resultsettype = type;
    }

    /**
     * Phương thức lấy kết quả truy vấn CSDL
     */
    public int getResultsettype() {
        return resultsettype;
    }

    /**
     * Phương thức thiết lập bộ kết quả đồng bộ
     *
     * @param type Kiểu kết quả đồng bộ
     */
    public void setResultsetconcurrency(int type) {
        resultsetconcurrency = type;
    }

    public int getResultsetconcurrency() {
        return resultsetconcurrency;
    }

    /**
     * Phương thức lấy về đối tượng quản lý các câu truy vấn thông thường
     */
    public Statement getStatement() {
        try {
            if ((resultsettype == 0) && (resultsetconcurrency == 0)) {
                statement = connection.createStatement(); // Tạo đối tượng truy vấn thông thường
            } else {
                statement = connection.createStatement(resultsettype, resultsetconcurrency);
            }
        } catch (SQLException e) {
            errmsg = "Error : " + e.getSQLState() + "--" + e.getMessage();
            error = -1;
        }
        return statement;
    }

    /**
     * Phương thức trả về đối tượng quản lý câu truy vấn có biến
     */
    public PreparedStatement getPreparedStatement(String sql) {
        try {
            if ((resultsettype == 0) && (resultsetconcurrency == 0)) {
                preparedStatement = connection.prepareStatement(sql);
            } else {
                preparedStatement = connection.prepareStatement(sql, resultsettype, resultsetconcurrency);
            }
        } catch (SQLException e) {
            errmsg = "Error : " + e.getSQLState() + "--" + e.getMessage();
            error = -1;
        }
        return preparedStatement;
    }

    /**
     * Phương thức trả về câu truy vấn tạo các hàm lưu
     */
    public  CallableStatement getStoreProc(String sql) {
        try {
            if ((resultsettype == 0) && (resultsetconcurrency == 0)) {
                storeProc = connection.prepareCall(sql);
            } else {
                storeProc = connection.prepareCall(sql, resultsettype, resultsetconcurrency);
            }
        } catch (SQLException e) {
            errmsg = "Error : " + e.getSQLState() + "--" + e.getMessage();
            error = -1;
        }
        return storeProc;
    }

    public void setResultSet(ResultSet rs) {
        resultSet = rs;
    }

    // Phương thức trả về đối tượng kết quả truy vấn
    public ResultSet getResultSet() {
        return resultSet;
    }

    // Phương thức trả về các thông báo lỗi
    public String getErrmsg() {
        switch (error) {
            case 0:
                errmsg = "No Error !!!";
                break;
            case 1:
                errmsg = "Driver invalid !!!";
                break;
            case 2:
                errmsg = "URL invalid !!!";
                break;
            case 3:
                errmsg = "Connection is not currently established !!!";
                break;
        }
        return errmsg;
    }

    /**
     * Phương thức hủy kết nối và các kết quả truy vấn
     */
    public void destroy() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            errmsg = "Error : " + e.getSQLState() + "--" + e.getMessage();
            error = -1;
        }
    }

    /**
     * Phương thức thiết lập CSDL để làm việc
     *
     * @param dtb Tên cơ sở dữ liệu hoạt động
     */
    public void setDatabase(String dtb) {
        database = dtb;
    }

    /**
     * Phương thức trả về tên CSDL
     *
     * @return
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Phương thức thiết lập bảng dữ liệu làm việc
     *
     * @param tb Tên bảng làm việc
     */
    public void setTable(String tb) {
        table = tb;
    }

    /**
     * Trả về tên của bảng đang làm việc
     */
    public String getTable() {
        return table;
    }

    /**
     * Thiết lập các trường trong bảng cần xử lý
     *
     * @param fs Danh sách tên các trường trong bảng.
     *           Mặc định tên trường đầu tiên là khóa chính.
     */
    public void setFields(Vector<String> fs) {
        fields = fs;
    }

    /**
     * Phương thức chèn thêm 1 khối dữ liệu mới vào bảng
     *
     * @param pField
     */
    public void insert(Properties pField) {
        String queryString = "INSERT INTO " + table + "(";
        int t = 0;
        // Liệt kê tên các cột theo thứ tự giá trị thêm vào
        for (int i = 0; i< fields.size(); i++) {
            String s = pField.getProperty(fields.get(i));
            if (s != null) {
                if (t != 0) {
                    queryString += ",";
                }
                queryString += fields.get(i);
                t++;
            }
        }
        queryString += ") VALUES (";
        t = 0;
        // Điền các giá trị cần thêm vào đúng vị trí tương ứng
        for (int i = 0; i < fields.size(); i++) {
            String s = pField.getProperty(fields.get(i));
            if (s != null) {
                if (t != 0) {
                    queryString += ",";
                }
                queryString += "'" + pField.getProperty(fields.get(i)) + "'";
                t++;
            }
        }
        queryString += ");";

        try {
            System.out.println("queryString");
            if (statement == null) {
                System.out.println("Statement == NULL");
            }
            statement.executeUpdate(queryString);
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }

    /**
     * Phương thức thay đổi 1 khối dữ liệu mới trong bảng
     *
     * @param pField
     */
    public void update(Properties pField) {
        String queryString = "UPDATE " + table + " SET";
        int t = 0;
        // Liệt kê tên các cột theo thứ tự giá trị cần cập nhật
        for (int i = 0; i < fields.size(); i++) {
            String s = pField.getProperty(fields.get(i));
            if (s != null) {
                if (t != 0) {
                    queryString += ",";
                }
                queryString += fields.get(i) + "=" + pField.getProperty(fields.get(i));
                t++;
            }
        }
        // Cập nhật dựa vào trường khóa chính
        queryString += " WHERE " + fields.get(0) + "='" + pField.getProperty(fields.get(0)) + "'";

        try {
            statement.executeUpdate(queryString);
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }

    /**
     * Phương thức xóa 1 khối dữ liệu ra khỏi bảng
     */
    public void delete(Properties pField) {
        String queryString = "DELETE FROM " + table + " WHERE ";
        int t = 0;
        // Liệt kê tên các cột theo thứ tự giá trị cần cập nhật
        for (int i = 0; i < fields.size(); i++) {
            String s = pField.getProperty(fields.get(i));
            System.out.println(s);

            if (s != null) {
                if (t != 0) {
                    queryString += " AND ";
                }
                queryString += fields.get(i) + "='" + pField.getProperty(fields.get(i));
                t++;
            }
        }

        try {
            System.out.println(queryString);
            statement.executeUpdate(queryString);
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }
}
