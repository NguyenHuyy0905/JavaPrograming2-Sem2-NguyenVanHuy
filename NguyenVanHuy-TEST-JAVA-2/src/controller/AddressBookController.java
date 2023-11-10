package controller;

import entity.AddressBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBookController {
    private List<AddressBook> addressBookList = new ArrayList<>();
    private final Scanner sc;
    public AddressBookController() {
        this.sc = new Scanner(System.in);
    }
    public void addNewContact() {
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
    public void findContactByName() {
        System.out.println("Enter name: ");
        String name = sc.nextLine();
        for (AddressBook addressBook : addressBookList) {
            if (addressBook.getName().equals(name)) {
                System.out.println("Number phone: " + addressBook.getPhoneNumber());
            } else {
                System.out.println("Not found !");
            }
        }
    }
    public void displayAllContacts() {
        System.out.printf("%-20s %-20s %-20s %-20s \n", "Contact Name", "Company", "Email", "Phone number");
//        System.out.println(addressBookList);
        for (AddressBook addressBook : addressBookList) {
            System.out.printf("%-20s %-20s %-20s %-20s \n", addressBook.getName(), addressBook.getCompany(), addressBook.getEmail(), addressBook.getPhoneNumber());
        }
    }
}
