package model;

import dao.DBConnection;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDaoImpl implements ProductDao{
    private final Connection conn = DBConnection.createConnection();
    private final String SQL_CREATE_PRODUCT = "INSERT INTO products (productName, productDesc, productPrice) VALUES (?, ?, ?)";
    private final String SQL_GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE Id = ?";
    private final String SQL_GET_ALL_PRODUCTS = "SELECT * FROM products";
    private final String SQL_UPDATE_PRODUCT = "UPDATE products SET productName = ?, productDesc = ?, productPrice = ? WHERE Id = ?";
    private final String SQL_DELETE_PRODUCT = "DELETE FROM products WHERE Id = ?";
    @Override
    public void createProduct(Product product) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, product.getProductName());
            pstm.setString(2, product.getProductDesc());
            pstm.setDouble(3, product.getProductPrice());
            pstm.executeUpdate();
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setProductId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Product getProductById(int productId) {
        Product product = new Product();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_PRODUCT_BY_ID)) {
            pstm.setInt(1, productId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    product.setProductId(rs.getInt(1));
                    product.setProductName(rs.getString(2));
                    product.setProductDesc(rs.getString(3));
                    product.setProductPrice(rs.getDouble(4));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> allProducts = new ArrayList<>();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_GET_ALL_PRODUCTS);
            ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt(1));
                product.setProductName(rs.getString(2));
                product.setProductDesc(rs.getString(3));
                product.setProductPrice(rs.getDouble(4));
                allProducts.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allProducts;
    }

    @Override
    public void updateProduct(Product product) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_UPDATE_PRODUCT)) {
            pstm.setString(1, product.getProductName());
            pstm.setString(2, product.getProductDesc());
            pstm.setDouble(3, product.getProductPrice());
            pstm.setInt(4, product.getProductId());
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean deleteProduct(int productId) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_DELETE_PRODUCT)) {
            pstm.setInt(1, productId);
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
