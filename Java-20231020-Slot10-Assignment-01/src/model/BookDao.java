package model;

import entity.Book;

import java.util.List;

public interface BookDao {
    void createNewBook(Book book);
    void deleteBookById(int id);
    Book findBookById(int id);
    Book findBookByName(String name);
    List<Book> findBooksByPriceRange(double min, double max);
}
