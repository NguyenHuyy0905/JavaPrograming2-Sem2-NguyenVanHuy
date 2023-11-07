package controller;

import entity.Book;
import entity.Status;
import entity.Student;
import model.*;

import java.util.List;

public class StaffService {
    private BookDao bookDao = new BookDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();
    private TicketDao ticketDao = new TicketDaoImpl();
    public void createBook(Book book) {
        bookDao.createBook(book);
    }
    public void deleteBook(int code) {
        bookDao.deleteBook(code);
    }
    public void updateStatusBook(int id, Status status) {
        bookDao.updateStatusBook(id, status);
    }
    public List<Book> viewAllBooks() {
        return bookDao.viewAllBooks();
    }
    public Book searchBookByCode(int code) {
        return bookDao.searchBookByCode(code);
    }
    public List<Book> searchBookByName(String name) {
        return bookDao.searchBookByName(name);
    }
    public void viewBookDetails(int id) {
        bookDao.viewBookDetails(id);
    }
    public void createStudent(Student student) {
        studentDao.createStudent(student);
    }
    public void deleteStudent(int id) {
        studentDao.deleteStudent(id);
    }
    public void letStudentBorrowBook(int student_id, int book_id) {
        ticketDao.letStudentBorrowBook(student_id, book_id);
    }
    public void letStudentReturnBook(int book_id) {
        ticketDao.letStudentReturnBook(book_id);
    }
}
