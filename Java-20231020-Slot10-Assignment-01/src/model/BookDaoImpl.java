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
    private static final Connection conn = DBConnection.createConnection();
    private static final String SQL_CREATE_BOOK = "INSERT INTO books (name, author, price, importDate, status) VALUES (?, ?, ?, ?, ?);";
    private static final String SQL_DELETE_BOOK = "DELETE FROM books WHERE id = ?;";
    private static final String SQL_FIND_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?;";
    private static final String SQL_FIND_BOOK_BY_NAME = "SELECT * FROM books WHERE name = ?;";
    private static final String SQL_FIND_BOOK_BY_PRICE_RANGE = "SELECT * FROM books WHERE price >= ? AND price <= ?;";
    @Override
    public void createNewBook(Book book) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_CREATE_BOOK, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, book.getName());
            pstm.setString(2, book.getAuthor());
            pstm.setDouble(3, book.getPrice());
            pstm.setDate(4, book.getImportDate());
            pstm.setString(5, book.getStatus().name());
            pstm.executeUpdate();
            try (ResultSet generatedId = pstm.getGeneratedKeys()) {
                if (generatedId.next()) {
                    book.setId(generatedId.getInt(1));
                }
            }
            System.out.println("Thêm sách thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteBookById(int id) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_DELETE_BOOK)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
            System.out.println("Xóa sách thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Book findBookById(int id) {
        Book book = new Book();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_FIND_BOOK_BY_ID)) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    book.setId(rs.getInt("id"));
                    book.setName(rs.getString("name"));
                    book.setAuthor(rs.getString("author"));
                    book.setPrice(rs.getDouble("price"));
                    book.setImportDate(rs.getDate("importDate"));
                    book.setStatus(rs.getString("status"));
                    System.out.println(book);
                    return book;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Book findBookByName(String name) {
        Book book = new Book();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_FIND_BOOK_BY_NAME)) {
            pstm.setString(1, name);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    book.setId(rs.getInt("id"));
                    book.setName(rs.getString("name"));
                    book.setAuthor(rs.getString("author"));
                    book.setPrice(rs.getDouble("price"));
                    book.setImportDate(rs.getDate("importDate"));
                    book.setStatus(rs.getString("status"));
                    System.out.println(book);
                    return book;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Book> findBooksByPriceRange(double min, double max) {
        List<Book> bookList = new ArrayList<>();
        try (PreparedStatement pstm = conn.prepareStatement(SQL_FIND_BOOK_BY_PRICE_RANGE)) {
            pstm.setDouble(1, min);
            pstm.setDouble(2, max);
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
            Logger.getLogger(BookDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(bookList);
        return bookList;
    }
}
