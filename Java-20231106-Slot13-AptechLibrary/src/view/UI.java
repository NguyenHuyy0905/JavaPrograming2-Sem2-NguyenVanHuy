package view;

import common.PasswordHashing;
import controller.StaffService;
import entity.Book;
import entity.Staff;
import entity.Status;
import entity.Student;

import java.util.List;
import java.util.Scanner;

public class UI {
    private StaffService staffService = new StaffService();
    private final Scanner sc;
    public UI() {
        this.sc = new Scanner(System.in);
    }
    private int loginMenu() {
        System.out.println("---LOGIN & REGISTER---");
        System.out.println("01. Login");
        System.out.println("02. Register");
        System.out.println("00. Exit");
        int choice = readInt(0, 2);
        return choice;
    }
    private int mainMenu() {
        System.out.println("---Main Menu---");
        System.out.println("01. Create book");
        System.out.println("02. Delete book");
        System.out.println("03. View all books");
        System.out.println("04. Search books by code");
        System.out.println("05. Search books by name");
        System.out.println("06. View book details");
        System.out.println("07. Create student");
        System.out.println("08. Delete student");
        System.out.println("09. Let student borrow book");
        System.out.println("10. Let student return book");
        System.out.println("00. EXIT");
        int choice = readInt(0, 10);
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
                    createBook();
                    break;
                case 2:
                    deleteBook();
                    break;
                case 3:
                    viewAllBooks();
                    break;
                case 4:
                    searchBookByCode();
                    break;
                case 5:
                    searchBookByName();
                    break;
                case 6:
                    viewBookDetails();
                    break;
                case 7:
                    createStudent();
                    break;
                case 8:
                    deleteStudent();
                    break;
                case 9:
                    letStudentBorrowBook();
                    break;
                case 10:
                    letStudentReturnBook();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    private void register() {
        boolean isExistUsername;
        String username;
        do {
            System.out.println("Nhập username: ");
            username = sc.nextLine();
            isExistUsername = staffService.isExistUsername(username);
        } while (isExistUsername);
        System.out.println("Nhập password: ");
        String password = sc.nextLine();
        String hashedPassword = PasswordHashing.hashingPassword(password);
        Staff staff = new Staff(username, hashedPassword);
        staffService.register(staff);
    }
    private void login() {
        System.out.println("Nhập username: ");
        String username = sc.nextLine();
        System.out.println("Nhập password: ");
        String password = sc.nextLine();
        Staff staff = new Staff(username, password);
        staffService.login(staff);
    }
    private void createBook() {
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter author: ");
        String author = sc.nextLine();
        Status status = Status.AVAILABLE;
        Book book = new Book(name, author, status);
        staffService.createBook(book);
    }
    private void deleteBook() {
        System.out.println("Enter code: ");
        int code = Integer.parseInt(sc.nextLine());
        staffService.deleteBook(code);
    }

    private void viewAllBooks() {
        staffService.viewAllBooks();
    }
    private void searchBookByCode() {
        System.out.println("Enter code: ");
        int code = Integer.parseInt(sc.nextLine());
        staffService.searchBookByCode(code);
    }
    private void searchBookByName() {
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        staffService.searchBookByName(name);
    }
    private void viewBookDetails() {
        System.out.println("Enter code: ");
        int code = Integer.parseInt(sc.nextLine());
        staffService.viewBookDetails(code);
    }
    private void createStudent() {
        System.out.println("Enter student's name: ");
        String name = sc.nextLine();
        Student student = new Student(name);
        staffService.createStudent(student);
    }
    private void deleteStudent() {
        System.out.println("Enter id: ");
        int id = Integer.parseInt(sc.nextLine());
        staffService.deleteStudent(id);
    }
    private void letStudentBorrowBook() {
        System.out.println("Enter student's id: ");
        int student_id = Integer.parseInt(sc.nextLine());
        System.out.println("Enter book's id: ");
        int book_id = Integer.parseInt(sc.nextLine());
        Status status = Status.BORROWED;
        staffService.updateStatusBook(book_id, status);
        staffService.letStudentBorrowBook(student_id, book_id);
    }
    private void letStudentReturnBook() {
        System.out.println("Enter book's id: ");
        int book_id = Integer.parseInt(sc.nextLine());
        Status status = Status.AVAILABLE;
        staffService.updateStatusBook(book_id, status);
        staffService.letStudentReturnBook(book_id);
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
}
