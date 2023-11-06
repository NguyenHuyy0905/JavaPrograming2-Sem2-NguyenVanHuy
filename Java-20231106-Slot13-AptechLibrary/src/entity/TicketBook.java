package entity;

import java.sql.Date;

public class TicketBook {
    private int id;
    private Date borrow_date;
    private Date return_date;
    private int book_id;
    private int student_id;
    private int isOpen;
    public TicketBook() {}
    public TicketBook(int book_id, int student_id) {
        this.book_id = book_id;
        this.student_id = student_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getOpen() {
        return isOpen;
    }

    public void setOpen(int open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return "TicketBook{" +
                "id: " + id +
                ", borrow_date: " + borrow_date +
                ", return_date: " + return_date +
                ", book_id: " + book_id +
                ", student_id: " + student_id +
                ", isOpen: " + isOpen +
                '}';
    }
}
