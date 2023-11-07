package model;

import entity.Student;

public interface StudentDao {
    void createStudent(Student student);
    void deleteStudentById(int id);
}
