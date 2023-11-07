package model;

import dao.DBConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketDaoImpl implements TicketDao{
    private static final String SQL_LET_STUDENT_BORROW_BOOK = "INSERT INTO ticketBook (student_id, book_id, isOpen) VALUES (?, ?, 1);";
    private static final String SQL_LET_STUDENT_RETURN_BOOK = "UPDATE ticketBook SET isOpen = 0 WHERE book_id = ?;";
    @Override
    public void letStudentBorrowBook(int student_id, int book_id) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_LET_STUDENT_BORROW_BOOK, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            pstm.setInt(1, student_id);
            pstm.setInt(2, book_id);
            pstm.executeUpdate();
            try (ResultSet generatedId = pstm.getGeneratedKeys()) {
                if (generatedId.next()) {
                    System.out.println("Id: " + generatedId.getInt(1));
                }
            }
            conn.commit();
            System.out.println("Cho sinh viên mượn thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(TicketDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void letStudentReturnBook(int book_id) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_LET_STUDENT_RETURN_BOOK)) {
            conn.setAutoCommit(false);
            pstm.setInt(1, book_id);
            pstm.executeUpdate();
            conn.commit();
            System.out.println("Cho sinh viên trả sách thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(TicketDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
