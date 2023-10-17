package dao;

import config.MySQLConnection;
import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRepository {
    private static Connection connection = null;
    private static PreparedStatement pstm = null;
    public static void add(Customer customer) throws SQLException {
        connection = MySQLConnection.getConnection();
        String query = "INSERT INTO _user(id, username, email, password, address, enable, role_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
        pstm = connection.prepareStatement(query);
        pstm.setInt(1, customer.getId());
        pstm.setString(2, customer.getUsername());
        pstm.setString(3, customer.getEmail());
        pstm.setString(4, customer.getPassword());
        pstm.setString(5, customer.getAddress());
        pstm.setBoolean(6, false);
        pstm.setInt(7, 1);
        int count = pstm.executeUpdate();
        System.out.println(count > 0 ? "Added successfully !" : "Failed to add :(");
        pstm.close();
        connection.close();
    }
}
