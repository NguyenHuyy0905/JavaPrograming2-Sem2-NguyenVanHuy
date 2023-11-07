package model;

import dao.DBConnection;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDaoImpl implements ProductDao{
    private static final Connection conn = DBConnection.createConnection();
    private static final String SQL_CREATE_PRODUCT = "INSERT INTO products (name, price, quantity, sales) VALUES (?, ?, ?, ?);";
    private static final String SQL_ADD_PRODUCT = "UPDATE products SET quantity = quantity + ? WHERE id = ?;";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM products WHERE id = ?;";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM products WHERE id = ?;";
    private static final String SQL_GET_ALL_PRODUCTS = "SELECT * FROM products;";
    private static final String SQL_GET_SOLD_OUT_PRODUCTS = "SELECT * FROM products WHERE quantity = 0;";
    private static final String SQL_GET_BEST_SELLER_PRODUCTS = "SELECT * FROM products ORDER BY sales DESC LIMIT 1;";
    private static final String SQL_SELL_PRODUCT = "UPDATE products SET sales = sales + ?, quantity = quantity - ? WHERE id = ?;";
    @Override
    public void createProduct(Product product) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, product.getName());
            pstm.setDouble(2, product.getPrice());
            pstm.setInt(3, product.getQuantity());
            pstm.setInt(4, product.getSales());
            pstm.executeUpdate();
            System.out.println("Tạo sản phẩm thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addProduct(int id, int quantity) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_ADD_PRODUCT)) {
            pstm.setInt(1, quantity);
            pstm.setInt(2, id);
            pstm.executeUpdate();
            System.out.println("Thêm số lượng thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Product findById(int id) {
        Product product = new Product();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_FIND_BY_ID)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setSales(rs.getInt("sales"));
                    System.out.println(product);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    @Override
    public void deleteById(int id) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_DELETE_BY_ID)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
            System.out.println("Xóa thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_ALL_PRODUCTS)) {
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setSales(rs.getInt("sales"));
                    productList.add(product);
                    System.out.println(product);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }

    @Override
    public List<Product> getSoldOutProducts() {
        List<Product> productList = new ArrayList<>();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_SOLD_OUT_PRODUCTS)) {
                try (ResultSet rs = pstm.executeQuery()) {
                    while (rs.next()) {
                        Product product = new Product();
                        product.setId(rs.getInt("id"));
                        product.setName(rs.getString("name"));
                        product.setPrice(rs.getDouble("price"));
                        product.setQuantity(rs.getInt("quantity"));
                        product.setSales(rs.getInt("sales"));
                        productList.add(product);
                        System.out.println(product);
                    }
                }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }

    @Override
    public List<Product> getBestSellerProducts() {
        List<Product> productList = new ArrayList<>();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_BEST_SELLER_PRODUCTS)) {
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setSales(rs.getInt("sales"));
                    productList.add(product);
                    System.out.println(product);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }

    @Override
    public void sellProduct(int id, int quantity) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_SELL_PRODUCT)) {
            pstm.setInt(1, quantity);
            pstm.setInt(2, quantity);
            pstm.setInt(3, id);
            pstm.executeUpdate();
            System.out.println("Bán thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
