package entity;

public class Student {
    private int id;
    private String name;
    private String studentCode;
    public Student() {}
    public Student(String name, String studentCode) {
        this.name = name;
        this.studentCode = studentCode;
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

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id: " + id +
                ", name: '" + name + '\'' +
                ", student code: '" + studentCode + '\'' +
                '}';
    }
}
