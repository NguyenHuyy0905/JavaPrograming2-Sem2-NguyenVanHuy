package model;

import entity.Book;
import entity.Status;

import java.util.List;

public interface BookDao {
    public void createBook(Book book);
    public void deleteBook(int code);
    public void updateStatusBook(int id, Status status);
    public List<Book> viewAllBooks();
    public Book searchBookByCode(int code);
    public List<Book> searchBookByName(String name);
    public void viewBookDetails(int id);
}
