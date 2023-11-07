package view;

import controller.BookService;
import controller.BorrowService;
import controller.StudentService;
import entity.Book;
import entity.Book_Student;
import entity.Status;
import entity.Student;

import java.sql.Date;
import java.util.Scanner;

public class AssignOneUI {
    private BookService bookService = new BookService();
    private StudentService studentService = new StudentService();
    private BorrowService borrowService = new BorrowService();
    private final Scanner sc;
    public AssignOneUI() {
        this.sc = new Scanner(System.in);
    }
    public int mainMenu() {
        System.out.println("---MAIN MENU---");
        System.out.println("01. Tạo sách");
        System.out.println("02. Xóa sách");
        System.out.println("03. Tìm sách theo Id");
        System.out.println("04. Tìm sách theo Tên");
        System.out.println("05. Tìm sách theo Khoảng giá");
        System.out.println("06. Tạo sinh viên");
        System.out.println("07. Xóa sinh viên");
        System.out.println("08. Cho sinh viên mượn sách");
        System.out.println("09. Cho sinh viên trả sách");
        System.out.println("10. Hiển thị sách mượn theo mã sinh viên");
        System.out.println("00. Thoát");
        int choice = readInt(0, 10);
        return choice;
    }
    public void start() {
        while (true) {
            int choice = mainMenu();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    createNewBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    findBookById();
                    break;
                case 4:
                    findBookByName();
                    break;
                case 5:
                    findBooksByPriceRange();
                    break;
                case 6:
                    createStudent();
                    break;
                case 7:
                    deleteStudentById();
                    break;
                case 8:
                    letStudentBorrowBook();
                    break;
                case 9:
                    letStudentReturnBook();
                    break;
                case 10:
                    showBooksByStudentCode();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    public void createNewBook() {
        System.out.println("Nhập tên sách: ");
        String name = sc.nextLine();
        System.out.println("Nhập tên tác giả: ");
        String author = sc.nextLine();
        System.out.println("Nhập giá: ");
        double price = readDouble(0, Double.MAX_VALUE);
        Date importDate = new Date(System.currentTimeMillis());
        Status status = Status.AVAILABLE;
        Book book = new Book(name, author, price, importDate, status);
        bookService.createNewBook(book);
    }
    public void deleteBook() {
        System.out.println("Nhập id: ");
        int id = Integer.parseInt(sc.nextLine());
        bookService.deleteBookById(id);
    }
    public void findBookById() {
        System.out.println("Nhập id: ");
        int id = Integer.parseInt(sc.nextLine());
        bookService.findBookById(id);
    }
    public void findBookByName() {
        System.out.println("Nhập tên sách: ");
        String name = sc.nextLine();
        bookService.findBookByName(name);
    }
    public void findBooksByPriceRange() {
        System.out.println("Nhập giá nhỏ: ");
        double minPrice = Double.parseDouble(sc.nextLine());
        System.out.println("Nhập giá lớn: ");
        double maxPrice = Double.parseDouble(sc.nextLine());
        bookService.findBooksByPriceRange(minPrice, maxPrice);
    }
    public void createStudent() {
        System.out.println("Nhập tên sinh viên: ");
        String name = sc.nextLine();
        System.out.println("Nhập mã sinh viên: ");
        String studentCode = sc.nextLine();
        Student student = new Student(name, studentCode);
        studentService.createStudent(student);
    }
    public void deleteStudentById() {
        System.out.println("Nhập id: ");
        int id = Integer.parseInt(sc.nextLine());
        studentService.deleteStudentById(id);
    }
    public void letStudentBorrowBook() {
        System.out.println("Nhập id sách: ");
        int book_id = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập id sinh viên: ");
        int student_id = Integer.parseInt(sc.nextLine());
        Book_Student book_student = new Book_Student(book_id, student_id);
        borrowService.letStudentBorrowBook(book_student);
    }
    public void letStudentReturnBook() {
        System.out.println("Nhập id lần mượn sách: ");
        int id = Integer.parseInt(sc.nextLine());
        borrowService.letStudentReturnBook(id);
    }
    public void showBooksByStudentCode() {
        System.out.println("Nhập mã sinh viên: ");
        String studentCode = sc.nextLine();
        borrowService.showBorrowedBookByStudentCode(studentCode);
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
                System.out.printf("Số nhập vào không hợp lệ !");
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
                System.out.println("Giá nhập vào không hợp lệ !");
            }
        }
        return price;
    }
}
