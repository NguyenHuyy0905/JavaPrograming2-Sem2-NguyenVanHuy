package model;

import dao.DBConnection;
import entity.Book;
import entity.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDaoImpl implements BookDao{
    private static final String SQL_CREATE_BOOK = "INSERT INTO books (name, author, status) VALUES (?, ?, ?);";
    private static final String SQL_DELETE_BOOK = "DELETE FROM books WHERE id = ?;";
    private static final String SQL_UPDATE_STATUS_BOOK = "UPDATE books SET status = ? WHERE id = ?;";
    private static final String SQL_VIEW_ALL_BOOKS = "SELECT * FROM books;";
    private static final String SQL_SEARCH_BOOK_BY_CODE = "SELECT * FROM books WHERE code = ?;";
    private static final String SQL_SEARCH_BOOK_BY_NAME = "SELECT * FROM books WHERE name = ?;";
    private static final String SQL_VIEW_BOOK_DETAILS = "SELECT b.code, b.name, b.author, b.status, t.borrow_date, t.return_date FROM books b LEFT JOIN ticketBook t ON b.id = t.book_id WHERE b.id = ?;";
    @Override
    public void createBook(Book book) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_CREATE_BOOK, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            pstm.setString(1, book.getName());
            pstm.setString(2, book.getAuthor());
            pstm.setString(3, book.getStatus().name());
            pstm.executeUpdate();
            try (ResultSet generatedId = pstm.getGeneratedKeys()) {
                if (generatedId.next()) {
                    System.out.println("Code: " + generatedId.getInt(1));
                }
            }
            conn.commit();
            System.out.println("Tạo sách thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteBook(int id) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_DELETE_BOOK)) {
            conn.setAutoCommit(false);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            conn.commit();
            System.out.println("Xóa thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateStatusBook(int id, Status status) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_UPDATE_STATUS_BOOK)) {
            conn.setAutoCommit(false);
            pstm.setString(1, status.name());
            pstm.setInt(2, id);
            pstm.executeUpdate();
            conn.commit();
            System.out.println("Cấp nhật trạng thái sách thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Book> viewAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_VIEW_ALL_BOOKS)) {
            conn.setAutoCommit(false);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setCode(rs.getInt(1));
                    book.setName(rs.getString(2));
                    book.setAuthor(rs.getString(3));
                    book.setStatus(rs.getString(4));
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

    @Override
    public Book searchBookByCode(int code) {
        Book book = new Book();
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_SEARCH_BOOK_BY_CODE)) {
            conn.setAutoCommit(false);
            pstm.setInt(1, code);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    book.setCode(rs.getInt(1));
                    book.setName(rs.getString(2));
                    book.setAuthor(rs.getString(3));
                    book.setStatus(rs.getString(4));
                }
            }
            conn.commit();
            System.out.println(book);
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return book;
    }

    @Override
    public List<Book> searchBookByName(String name) {
        List<Book> bookList = new ArrayList<>();
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_SEARCH_BOOK_BY_NAME)) {
            conn.setAutoCommit(false);
            pstm.setString(1, name);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setCode(rs.getInt(1));
                    book.setName(rs.getString(2));
                    book.setAuthor(rs.getString(3));
                    book.setStatus(rs.getString(4));
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

    @Override
    public void viewBookDetails(int id) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_VIEW_BOOK_DETAILS)) {
            conn.setAutoCommit(false);
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    System.out.print("\nCode: " + rs.getString(1));
                    System.out.print(", Name: " + rs.getString(2));
                    System.out.print(", Author: " + rs.getString(3));
                    System.out.print(", Status: " + rs.getString(4));
                    System.out.print(", Borrow date: " + rs.getDate(5));
                    System.out.print(", Return date: " + rs.getDate(6));
                }
            }
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
