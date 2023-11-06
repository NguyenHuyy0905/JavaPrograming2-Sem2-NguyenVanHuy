package model;

import dao.DBConnection;
import entity.Student;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDaoImpl implements StudentDao{
    private static final String SQL_CREATE_STUDENT = "INSERT INTO students (name) VALUES (?);";
    private static final String SQL_DELETE_STUDENT = "DELETE FROM students WHERE id = ?;";
    @Override
    public void createStudent(Student student) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_CREATE_STUDENT, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            pstm.setString(1, student.getName());
            pstm.executeUpdate();
            try (ResultSet generatedId = pstm.getGeneratedKeys()) {
                if (generatedId.next()) {
                    System.out.println("Id: " + generatedId.getInt(1));
                }
            }
            conn.commit();
            System.out.println("Tạo sinh viên thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteStudent(int id) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_DELETE_STUDENT)) {
            conn.setAutoCommit(false);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            conn.commit();
            System.out.println("Xóa sinh viên thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
