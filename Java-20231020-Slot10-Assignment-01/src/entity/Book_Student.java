package entity;

public class Book_Student {
    private int id;
    private int student_id;
    private int book_id;
    public Book_Student() {}
    public Book_Student(int student_id, int book_id) {
        this.student_id = student_id;
        this.book_id = book_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    @Override
    public String toString() {
        return "Book_Student{" +
                "id: " + id +
                ", student_id: " + student_id +
                ", book_id: " + book_id +
                '}';
    }
}
