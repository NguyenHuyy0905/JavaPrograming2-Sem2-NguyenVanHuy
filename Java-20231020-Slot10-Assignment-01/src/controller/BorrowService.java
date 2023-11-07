package controller;

import entity.Book;
import entity.Book_Student;
import model.BorrowDao;
import model.BorrowDaoImpl;

import java.util.List;

public class BorrowService {
    private BorrowDao borrowDao = new BorrowDaoImpl();
    public void letStudentBorrowBook(Book_Student book_student) {
        borrowDao.letStudentBorrowBook(book_student);
    }
    public void letStudentReturnBook(int id) {
        borrowDao.letStudentReturnBook(id);
    }
    public List<Book> showBorrowedBookByStudentCode(String studentcode) {
        return borrowDao.showBorrowedBookByStudentCode(studentcode);
    }
}
