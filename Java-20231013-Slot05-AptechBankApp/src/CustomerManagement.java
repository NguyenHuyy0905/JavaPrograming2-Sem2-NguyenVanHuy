import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerManagement {
    CustomerController customerController = new CustomerController();
    Customer customer = new Customer();
    Scanner scanner = new Scanner(System.in);
    public void getAllCustomer() throws SQLException {
        String name = scanner.nextLine();
        List<Customer> customerList = customerController.getAllCustomers(name);
        customerList.forEach(customer -> {
            System.out.println(customer.getId() + "\t" + customer.getUsername());
        });
    }

    public void menu() throws SQLException {
        int action = 0;
        do {
            System.out.println("1: ");
            System.out.println("2: ");
            System.out.println("3: ");
            System.out.println("4: ");
            System.out.println("5: ");
            System.out.println("6: ");
            action = scanner.nextInt();
            switch (action) {
                case 1: break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: break;
                case 6: break;
                default:
                    System.out.println("Enter a validate !!");
                    break;
            }
        }
    }
}
