package view;

import controller.UserService;
import entity.User;

import java.util.Scanner;

public class UI {
    private UserService userService = new UserService();
    private final Scanner sc;
    public UI() {
        this.sc = new Scanner(System.in);
    }
    private int loginMenu() {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");
        int choice = readInt(0, 2);
        return choice;
    }
    private int mainMenu() {
        System.out.println("---MAIN MENU---");
        System.out.println("1. ///");
        System.out.println("2. ///");
        System.out.println("3. ///");
        System.out.println("0. Exit");
        int choice = readInt(0, 3);
        return choice;
    }
    private int subMenu() {
        System.out.println("---SUB MENU---");
        System.out.println("1. ///");
        System.out.println("2. ///");
        System.out.println("3. ///");
        System.out.println("0. Exit");
        int choice = readInt(0, 3);
        return choice;
    }
    public void start() {
        int choice;
        boolean checkRegister = false;
        boolean checkLogin = false;
        while (true) {
            choice = loginMenu();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    do {
                        checkRegister = register();
                    } while (!checkRegister);
                case 2:
                    do {
                        checkLogin = login();
                    } while (!checkLogin);

                    int choiceMenu = mainMenu();
                    switch (choiceMenu) {
                        case 0:
                            break;
                        case 1:
                            //
                            break;
                        case 2:
                            //
                            break;
                        case 3:
                            //
                            break;
                        case 4:
                            //
                            break;
                        case 5:
                            //
                            break;
                        default:
                            throw new AssertionError();
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    public boolean register() {
        System.out.println("--REGISTER--");
        String username;
        boolean isExistedUser;
        do {
            System.out.println("Enter your username: ");
            username = sc.nextLine();
            isExistedUser = userService.checkExistUser(username);
        } while (isExistedUser);
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        User user = new User(username, password);
        return userService.register(user);
    }
    public boolean login() {
        System.out.println("--LOGIN--");
        System.out.println("Enter your username: ");
        String username = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();
        User user = new User(username, password);
        return userService.checkLogin(user);
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
        double choice;
        while (true) {
            try {
                choice = Double.parseDouble(sc.nextLine());
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
