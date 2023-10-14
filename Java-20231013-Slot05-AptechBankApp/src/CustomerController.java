import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    public static void createTable() throws SQLException {
        Connection connection = ConnectionUtils.connection();
        Statement statement = connection.createStatement();
        String query = "DROP TABLE IF EXISTS users; CREATE TABLE users(id int primary key, username varchar(50), password varchar(100))";
        statement.execute(query);
    }
    public static void createNewCustomer(Customer customer) throws SQLException {
        Connection connection = ConnectionUtils.connection();
        String query = "INSERT INTO users VALUES (?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1, customer.getId());
        pstm.setString(2, customer.getUsername());
        pstm.setString(3, customer.getPassword());
        pstm.executeUpdate();
        pstm.close();
        connection.close();
    }

    public static void updatePerson(Customer customer) throws SQLException {
        Connection connection = ConnectionUtils.connection();
        String query = "UPDATE users SET username = ? WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setString(1, customer.getUsername());
        pstm.setInt(2, customer.getId());
        pstm.executeUpdate();
        pstm.close();
        connection.close();
    }

    public static void deletePerson(int id) throws SQLException {
        //
        Connection connection = ConnectionUtils.connection();
        String query = "DELETE FROM users WHERE id = ?";
        PreparedStatement pstm = connection.prepareStatement(query);
        pstm.setInt(1, id);
        pstm.executeQuery();
        pstm.close();
        connection.close();
    }

    public static Customer findById(int id) throws SQLException {
        // Đảm bảo id tồn tại
        // Đảm bảo id != null
        Connection connection = ConnectionUtils.connection();
        String query = "SELECT * FROM users";
        PreparedStatement pstm = connection.prepareStatement(query);
        ResultSet resultSet = pstm.executeQuery();
        String currenId = resultSet.getString("id");
        if (currenId.equals(id)) {
            if (resultSet.next()) {
                int customerId = resultSet.getInt("id");
                String customerName = resultSet.getString("name");
                Customer customer = new Customer(customerId, customerName);
                return customer;
            }
        } else {
            System.out.println("Ko");
            return null;
        }
        pstm.close();
        resultSet.close();
        connection.close();
        return null;
    }

    public List<Customer> getAllCustomers(String username) throws SQLException {
        Connection connection = ConnectionUtils.connection();
        List<Customer> reCustomerList = new ArrayList<>();
        String query = "SELECT * FROM users";
        if(username != null && !username.equals("")) {
            query += "WHERE username like '" + username + "'";
        }
        PreparedStatement pstm = connection.prepareStatement(query);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt(1));
            customer.setUsername(resultSet.getString("username"));
            reCustomerList.add(customer);
        }
        pstm.close();
        resultSet.close();
        connection.close();
        return reCustomerList;
    }


}
