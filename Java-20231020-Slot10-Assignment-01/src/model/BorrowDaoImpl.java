package model;

import dao.DBConnection;
import entity.Book;
import entity.Book_Student;
import entity.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BorrowDaoImpl implements BorrowDao{
    private static final Connection conn = DBConnection.createConnection();
    private static final String SQL_LET_STUDENT_BORROW_BOOK = "INSERT INTO book_students (book_id, student_id) VALUES (?, ?);";
    private static final String SQL_BORROW_STATUS = "UPDATE books SET status = 'UNAVAILABLE' WHERE id = ?;";
    private static final String SQL_RETURN_STATUS = "UPDATE books SET status = 'AVAILABLE' WHERE id = (SELECT book_id FROM book_students WHERE id = ?);";
    private static final String SQL_LET_STUDENT_RETURN_BOOK = "DELETE FROM book_students WHERE id = ?";
    private static final String SQL_FIND_BORROWED_BOOK = "SELECT * FROM books b WHERE b.id in (SELECT bs.book_id FROM book_students bs WHERE bs.student_id = (SELECT s.id FROM students s WHERE s.studentCode = ?));";
    @Override
    public void letStudentBorrowBook(Book_Student book_student) {
        try (PreparedStatement pstm_1 = conn.prepareStatement(SQL_LET_STUDENT_BORROW_BOOK, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement pstm_2 = conn.prepareStatement(SQL_BORROW_STATUS)) {
            pstm_1.setInt(1, book_student.getBook_id());
            pstm_1.setInt(2, book_student.getStudent_id());
            pstm_1.executeUpdate();
            try (ResultSet generatedId = pstm_1.getGeneratedKeys()) {
                while (generatedId.next()) {
                    book_student.setId(generatedId.getInt(1));
                }
            }
            pstm_2.setInt(1, book_student.getBook_id());
            pstm_2.executeUpdate();
            System.out.println("Cho sinh viên mượn sách thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void letStudentReturnBook(int id) {
        try (PreparedStatement pstm_1 = conn.prepareStatement(SQL_LET_STUDENT_RETURN_BOOK);
             PreparedStatement pstm_2 = conn.prepareStatement(SQL_RETURN_STATUS)) {
            pstm_1.setInt(1, id);
            pstm_1.executeUpdate();
            pstm_2.setInt(1, id);
            pstm_2.executeUpdate();
            System.out.println("Cho sinh viên trả sách thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Book> showBorrowedBookByStudentCode(String studentcode) {
        List<Book> bookList = new ArrayList<>();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_FIND_BORROWED_BOOK)) {
                pstm.setString(1, studentcode);
                try (ResultSet rs = pstm.executeQuery()) {
                    while (rs.next()) {
                        Book book = new Book();
                        book.setId(rs.getInt("id"));
                        book.setName(rs.getString("name"));
                        book.setAuthor(rs.getString("author"));
                        book.setPrice(rs.getDouble("price"));
                        book.setImportDate(rs.getDate("importDate"));
                        book.setStatus(rs.getString("status"));
                        bookList.add(book);
                    }
                }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(bookList);
        return bookList;
    }
}
