package Part_03_Do_it_yourself.Ex_02;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student implements Serializable {
    private String name;
    private int age;
    private double marks;

    public Student(String name, int age, double marks) {
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return name + "\t" + age + "\t" + marks;
    }
}

public class StudentFileIO {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu");
            System.out.println("-------------------------------------------------");
            System.out.println("1. Add a list of Students and save to File");
            System.out.println("2. Loading list of Students from a File");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudents(studentList);
                    saveToFile(studentList);
                    break;
                case 2:
                    loadFromFile(studentList);
                    displayStudents(studentList);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }

    private static void addStudents(List<Student> studentList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student information (name, age, marks) or type 'done' to finish:");
        while (true) {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            if (name.equals("done")) {
                break;
            }

            System.out.print("Age: ");
            int age = scanner.nextInt();
            System.out.print("Marks: ");
            double marks = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            Student student = new Student(name, age, marks);
            studentList.add(student);
        }
    }

    private static void saveToFile(List<Student> studentList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            oos.writeObject(studentList);
            System.out.println("Data saved to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadFromFile(List<Student> studentList) {
        studentList.clear(); // Clear existing student data
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"))) {
            List<Student> loadedStudents = (List<Student>) ois.readObject();
            studentList.addAll(loadedStudents);
            System.out.println("Data loaded from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void displayStudents(List<Student> studentList) {
        if (studentList.isEmpty()) {
            System.out.println("No student data to display.");
            return;
        }
        System.out.println("Student List:");
        System.out.println("Name\tAge\tMarks");
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
