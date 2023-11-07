package controller;

import entity.Book;
import model.BookDao;
import model.BookDaoImpl;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDaoImpl();
    public void createNewBook(Book book) {
        bookDao.createNewBook(book);
    }
    public void deleteBookById(int id) {
        bookDao.deleteBookById(id);
    }
    public Book findBookById(int id) {
        return bookDao.findBookById(id);
    }
    public Book findBookByName(String name) {
        return bookDao.findBookByName(name);
    }
     public List<Book> findBooksByPriceRange(double min, double max) {
        return bookDao.findBooksByPriceRange(min, max);
     }
}
