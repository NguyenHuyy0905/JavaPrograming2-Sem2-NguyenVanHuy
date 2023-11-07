package controller;

import entity.Student;
import model.StudentDao;
import model.StudentDaoImpl;

public class StudentService {
    public StudentDao studentDao = new StudentDaoImpl();
    public void createStudent(Student student) {
        studentDao.createStudent(student);
    }
    public void deleteStudentById(int id) {
        studentDao.deleteStudentById(id);
    }
}
