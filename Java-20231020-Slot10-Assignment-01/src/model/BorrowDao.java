package model;

import entity.Book;
import entity.Book_Student;

import java.util.List;

public interface BorrowDao {
    void letStudentBorrowBook(Book_Student book_student);
    void letStudentReturnBook(int id);
    List<Book> showBorrowedBookByStudentCode(String studentcode);
}
