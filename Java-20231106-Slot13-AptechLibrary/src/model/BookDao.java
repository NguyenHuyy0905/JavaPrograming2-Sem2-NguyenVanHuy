package model;

import entity.Book;

import java.util.List;

public interface BookDao {
    public void createBook(Book book);
    public void deleteBook(int code);
    public List<Book> viewAllBooks();
    public Book searchBookByCode(int code);
    public List<Book> searchBookByName(String name);
}
