package controller;

import entity.Product;
import model.ProductDao;
import model.ProductDaoImpl;

import java.util.List;

public class ProductService {
    ProductDao productDao = new ProductDaoImpl();
    public void createProduct(Product product) {
        productDao.createProduct(product);
    }
    public void addProduct(int id, int quantity) {
        productDao.addProduct(id, quantity);
    }
    public Product findById(int id) {
        return productDao.findById(id);
    }
    public void deleteById(int id) {
        productDao.deleteById(id);
    }
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
    public List<Product> getSoldOutProducts() {
        return productDao.getSoldOutProducts();
    }
    public List<Product> getBestSellerProducts() {
        return productDao.getBestSellerProducts();
    }
    public void sellProduct(int id, int quantity) {
        productDao.sellProduct(id, quantity);
    }
}
