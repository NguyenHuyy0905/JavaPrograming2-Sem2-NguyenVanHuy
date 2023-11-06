package model;

import entity.Student;

public interface StudentDao {
    public void createStudent(Student student);
    public void deleteStudent(int id);
}
