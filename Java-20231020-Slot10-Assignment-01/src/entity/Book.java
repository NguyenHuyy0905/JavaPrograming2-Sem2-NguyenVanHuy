package entity;

import java.sql.Date;

public class Book {
    private int id;
    private String name;
    private String author;
    private double price;
    private Date importDate;
    private Status status;

    public Book() {
    }

    public Book(String name, String author, double price, Date importDate, Status status) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.importDate = importDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id: " + id +
                ", name: '" + name + '\'' +
                ", author: '" + author + '\'' +
                ", price: " + price +
                ", importDate: " + importDate +
                ", status: " + status +
                '}';
    }
}
