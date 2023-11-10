package view;

import entity.AddressBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private List<AddressBook> addressBookList = new ArrayList<>();
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
                    addNewContact();
                    break;
                case 2:
                    findContactByName();
                    break;
                case 3:
                    displayAllContacts();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    private void addNewContact() {
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter company: ");
        String company = sc.nextLine();
        System.out.println("Enter email: ");
        String email = sc.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = sc.nextLine();
        AddressBook addressBook = new AddressBook(name, company, email, phoneNumber);
        addressBookList.add(addressBook);
        System.out.println("Added address book successfully !");
    }
    private void findContactByName() {
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        for (AddressBook addressBook : addressBookList) {
            if (addressBook.getName().equals(name)) {
                System.out.println(addressBook.getPhoneNumber());
            }
        }
    }
    private void displayAllContacts() {
        System.out.println(addressBookList);
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
