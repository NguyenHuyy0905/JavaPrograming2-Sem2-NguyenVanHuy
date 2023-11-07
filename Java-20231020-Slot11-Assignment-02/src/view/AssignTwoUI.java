package view;

import controller.ProductService;
import entity.Product;

import java.util.List;
import java.util.Scanner;

public class AssignTwoUI {
    private ProductService productService = new ProductService();
    private final Scanner sc;
    public AssignTwoUI() {
        this.sc = new Scanner(System.in);
    }
    private int mainMenu() {
        System.out.println("---MAIN MENU---");
        System.out.println("1. Tạo sản phẩm mới");
        System.out.println("2. Thêm số lượng");
        System.out.println("3. Tìm sản phẩm theo Id");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Hiển thị tất cả sản phẩm");
        System.out.println("6. Hiển thị sản phẩm đã bán hết");
        System.out.println("7. Hiển thị sản phẩm bán chạy nhất");
        System.out.println("8. Bán sản phẩm");
        System.out.println("0. Thoát");
        int choice = readInt(0, 8);
        return choice;
    }
    public void start() {
        int choice;
        while (true) {
            choice = mainMenu();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    createProduct();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    findById();
                    break;
                case 4:
                    deleteById();
                    break;
                case 5:
                    getAllProducts();
                    break;
                case 6:
                    getSoldOutProducts();
                    break;
                case 7:
                    getBestSellerProducts();
                    break;
                case 8:
                    sellProduct();
                    break;
                default:
                    throw new AssertionError();
            }
        }

    }
    private void createProduct() {
        System.out.println("Nhập tên sản phẩm: ");
        String name = sc.nextLine();
        System.out.println("Nhập giá: ");
        double price = readDouble(0, Double.MAX_VALUE);
        System.out.println("Nhập số lượng: ");
        int quantity = Integer.parseInt(sc.nextLine());
        Product product = new Product(name, price, quantity);
        productService.createProduct(product);
    }
    private void addProduct() {
        System.out.println("Nhập id sản phẩm: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập số lượng sản phẩm: ");
        int quantity = Integer.parseInt(sc.nextLine());
        productService.addProduct(id, quantity);
    }
    private Product findById() {
        System.out.println("Nhập id sản phẩm: ");
        int id = Integer.parseInt(sc.nextLine());
        return productService.findById(id);
    }
    private void deleteById() {
        System.out.println("Nhập id sản phẩm: ");
        int id = Integer.parseInt(sc.nextLine());
        productService.deleteById(id);
    }
    private List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    private List<Product> getSoldOutProducts() {
        return productService.getSoldOutProducts();
    }
    private List<Product> getBestSellerProducts() {
        return productService.getBestSellerProducts();
    }
    private void sellProduct() {
        System.out.println("Nhập id sản phẩm: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập số lượng sản phẩm: ");
        int quantity = Integer.parseInt(sc.nextLine());
        productService.sellProduct(id, quantity);
    }
    private int readInt(int min, int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= min && choice <= max) {
                    break;
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }

        }
        return choice;
    }
    private double readDouble(double min, double max) {
        double price;
        while (true) {
            try {
                price = Double.parseDouble(sc.nextLine());
                if (price >= min && price <= max) {
                    break;
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        return price;
    }
}
