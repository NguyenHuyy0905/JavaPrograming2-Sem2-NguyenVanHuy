package model;

import dao.DBConnection;
import entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDaoImpl {
    private static final String SQL_CREATE = "INSERT INTO empolyees (name) VALUES (?);";
    private static final String SQL_DELETE = "DELETE FROM empolyees WHERE id = ?;";
    private static final String SQL_GET_BY_ID = "SELECT * FROM empolyees WHERE id = ?;";
    private static final String SQL_GET_ALL = "SELECT * FROM empolyees;";
    public void create(Book book) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            // pstm.getString(1, "aaa");
            pstm.executeUpdate();
            try (ResultSet generateId = pstm.getGeneratedKeys()) {
                while (generateId.next()) {
                    System.out.println("Generated id: " + generateId.getInt(1));
                }
            }
            conn.commit();
            System.out.println("Create successfully !");
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(int id) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_DELETE)) {
            conn.setAutoCommit(false);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            conn.commit();
            System.out.println("Delete successfully !");
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Book getById(int id) {
        Book book = new Book();
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_GET_BY_ID)) {
            conn.setAutoCommit(false);
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    //
                }
            }
            conn.commit();
            System.out.println(book);
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return book;
    }
    public List<Book> getAll() {
        List<Book> bookList = new ArrayList<>();
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_GET_ALL)) {
            conn.setAutoCommit(false);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    //
                    bookList.add(book);
                }
            }
            conn.commit();
            System.out.println(bookList);
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookList;
    }
}
