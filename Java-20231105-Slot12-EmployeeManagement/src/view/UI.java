package view;

import common.PasswordHashing;
import controller.AdminService;
import entity.Admin;
import entity.Employee;

import java.util.List;
import java.util.Scanner;

public class UI {
    private final Scanner sc;
    private AdminService adminService = new AdminService();
    public UI() {
        this.sc = new Scanner(System.in);
    }
    private int mainMenu() {
        System.out.println("---MAIN MENU---");
        System.out.println("1. Đăng nhập vào hệ thống quản lý");
        System.out.println("2. Quản lý tài khoản nhân viên");
        System.out.println("3. Tìm kiếm nhân viên");
        System.out.println("0. Thoát");
        int choice = readInt(0, 3);
        return choice;
    }
    private int menu1() {
        System.out.println("---Đăng ký & Đăng nhập---");
        System.out.println("1. Đăng ký");
        System.out.println("2. Đăng nhập");
        System.out.println("0. Thoát");
        int choice = readInt(0, 2);
        return choice;
    }
    private int menu2() {
        System.out.println("---Quản lý tài khoản nhân viên---");
        System.out.println("1. Tạo nhân viên");
        System.out.println("2. Xóa nhân viên");
        System.out.println("3. Sửa thông tin nhân viên");
        System.out.println("0. Thoát");
        int choice = readInt(0, 3);
        return choice;
    }
    private int menu3() {
        System.out.println("---Tìm kiếm nhân viên---");
        System.out.println("1. Tìm kiếm nhân viên theo tên");
        System.out.println("2. Tìm kiếm nhân viên theo mã nhân viên");
        System.out.println("0. Thoát");
        int choice = readInt(0, 2);
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
                    boolean flag1 = false;
                    while (!flag1) {
                        int choice1 = menu1();
                        switch (choice1) {
                            case 0:
                                flag1 = true;
                                break;
                            case 1:
                                register();
                                break;
                            case 2:
                                login();
                                break;
                            default:
                                throw new AssertionError();
                        }
                    }
                case 2:
                    boolean flag2 = false;
                    while (!flag2) {
                        int choice2 = menu2();
                        switch (choice2) {
                            case 0:
                                flag2 = true;
                                break;
                            case 1:
                                createEmployee();
                                break;
                            case 2:
                                deleteEmployee();
                                break;
                            case 3:
                                modifyInformation();
                                break;
                            default:
                                throw new AssertionError();
                        }
                    }
                case 3:
                    boolean flag3 = false;
                    while (!flag3) {
                        int choice3 = menu3();
                        switch (choice3) {
                            case 0:
                                flag3 = true;
                                break;
                            case 1:
                                findEmployeeByName();
                                break;
                            case 2:
                                findEmployeeById();
                                break;
                            default:
                                throw new AssertionError();
                        }
                    }
                default:
                    throw new AssertionError();
            }
        }
    };
    private void register() {
        boolean isExistUsername;
        String username;
        do {
            System.out.println("Nhập username: ");
            username = sc.nextLine();
            isExistUsername = adminService.isExistUsername(username);
        } while (isExistUsername);
        System.out.println("Nhập password: ");
        String password = sc.nextLine();
        String hashedPassword = PasswordHashing.hashingPassword(password);
        Admin admin = new Admin(username, hashedPassword);
        adminService.register(admin);
    }
    private void login() {
        System.out.println("Nhập username: ");
        String username = sc.nextLine();
        System.out.println("Nhập password: ");
        String password = sc.nextLine();
        Admin admin = new Admin(username, password);
        adminService.login(admin);
    }
    private void createEmployee() {
        System.out.println("Nhập first name: ");
        String firstName = sc.nextLine();
        System.out.println("Nhập last name: ");
        String lastName = sc.nextLine();
        System.out.println("Nhập lương: ");
        int salary = Integer.parseInt(sc.nextLine());
        Employee employee = new Employee(firstName, lastName, salary);
        adminService.createEmployee(employee);
    }
    private void deleteEmployee() {
        System.out.println("Nhập id: ");
        int id = Integer.parseInt(sc.nextLine());
        adminService.deleteEmployee(id);
    }
    private void modifyInformation() {
        System.out.println("Nhập first name mới: ");
        String firstName = sc.nextLine();
        System.out.println("Nhập last name mới: ");
        String lastName = sc.nextLine();
        System.out.println("Nhập lương mới: ");
        int salary = Integer.parseInt(sc.nextLine());
        Employee employee = new Employee(firstName, lastName, salary);
        adminService.modifyInformation(employee);
    }
    private Employee findEmployeeById() {
        System.out.println("Nhập id: ");
        int id = Integer.parseInt(sc.nextLine());
        return adminService.findEmployeeById(id);
    }
    private List<Employee> findEmployeeByName() {
        System.out.println("Nhập tên: ");
        String name = sc.nextLine();
        return adminService.findEmployeeByName(name);
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
