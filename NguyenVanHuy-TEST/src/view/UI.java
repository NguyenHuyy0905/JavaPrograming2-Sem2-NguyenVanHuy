package view;

import controller.AddressBookController;
import entity.AddressBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private AddressBookController controller = new AddressBookController();
    private final Scanner sc;
    public UI() {
        this.sc = new Scanner(System.in);
    }
    private int mainMenu() {
        System.out.println("---Main Menu---");
        System.out.println("01. Add new contact");
        System.out.println("02. Find a contact by name");
        System.out.println("03. Display contacts");
        System.out.println("04. Exit");
        int choice = readInt(0, 4);
        return choice;
    }
    public void start() {
        int choice;
        while (true) {
            choice = mainMenu();
            switch (choice) {
                case 1:
                    controller.addNewContact();
                    break;
                case 2:
                    controller.findContactByName();
                    break;
                case 3:
                    controller.displayAllContacts();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    throw new AssertionError();
            }
        }
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
