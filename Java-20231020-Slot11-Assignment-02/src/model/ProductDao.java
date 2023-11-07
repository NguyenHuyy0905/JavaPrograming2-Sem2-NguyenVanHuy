package model;

import entity.Product;

import java.util.List;

public interface ProductDao {
    void createProduct(Product product);
    void addProduct(int id, int quantity);
    Product findById(int id);
    void deleteById(int id);
    List<Product> getAllProducts();
    List<Product> getSoldOutProducts();
    List<Product> getBestSellerProducts();
    void sellProduct(int id, int quantity);
}
