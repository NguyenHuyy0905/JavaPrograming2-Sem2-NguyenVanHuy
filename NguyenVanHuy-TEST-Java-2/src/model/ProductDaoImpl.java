package model;

import dao.DBConnection;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDaoImpl {
    private static final String SQL_CREATE = "INSERT INTO products (name) VALUES (?);";
    private static final String SQL_DELETE = "DELETE FROM products WHERE id = ?;";
    private static final String SQL_GET_BY_ID = "SELECT * FROM products WHERE id = ?;";
    private static final String SQL_GET_ALL = "SELECT * FROM products;";
    public void create(Product product) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            // pstm.getString(1, "aaa");
            pstm.executeUpdate();
            try (ResultSet generateId = pstm.getGeneratedKeys()) {
                while (generateId.next()) {
                    System.out.println("Generated id: " + generateId.getInt(1));
                }
            }
            conn.commit();
            System.out.println("Create successfully !");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(int id) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_DELETE)) {
            conn.setAutoCommit(false);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            conn.commit();
            System.out.println("Delete successfully !");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Product getById(int id) {
        Product product = new Product();
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_GET_BY_ID)) {
            conn.setAutoCommit(false);
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    //
                }
            }
            conn.commit();
            System.out.println(product);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_GET_ALL)) {
            conn.setAutoCommit(false);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    //
                    productList.add(product);
                }
            }
            conn.commit();
            System.out.println(productList);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }
}
