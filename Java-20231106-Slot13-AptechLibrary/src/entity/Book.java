package entity;

public class Book {
    private int code;
    private String name;
    private String author;
    private Status status;
    public Book() {}
    public Book(String name, String author, Status status) {
        this.name = name;
        this.author = author;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    @Override
    public String toString() {
        return "Book{" +
                "code: " + code +
                ", name: '" + name + '\'' +
                ", author: '" + author + '\'' +
                ", status: " + status +
                '}';
    }
}
