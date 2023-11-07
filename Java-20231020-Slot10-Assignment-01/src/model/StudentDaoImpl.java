package model;

import dao.DBConnection;
import entity.Student;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDaoImpl implements StudentDao{
    private static final Connection conn = DBConnection.createConnection();
    private static final String SQL_CREATE_STUDENT = "INSERT INTO students (name, studentCode) VALUES (?, ?);";
    private static final String SQL_DELETE_STUDENT = "DELETE FROM students WHERE id = ?;";
    @Override
    public void createStudent(Student student) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_CREATE_STUDENT, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, student.getName());
            pstm.setString(2, student.getStudentCode());
            pstm.executeUpdate();
            try (ResultSet generatedId = pstm.getGeneratedKeys()) {
                while (generatedId.next()) {
                    student.setId(generatedId.getInt(1));
                }
            }
            System.out.println("Thêm sinh viên thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteStudentById(int id) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_DELETE_STUDENT)) {
            pstm.setInt(1, id);
            pstm.executeUpdate();
            System.out.println("Xóa sinh viên thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
