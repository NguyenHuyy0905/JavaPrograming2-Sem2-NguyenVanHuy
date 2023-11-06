package model;

public interface TicketDao {
    public void letStudentBorrowBook(int student_id, int book_id);
    public void letStudentReturnBook(int Ticket_id);
}
