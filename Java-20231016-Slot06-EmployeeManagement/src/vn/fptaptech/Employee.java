package vn.fptaptech;

public class Employee {
    private String id;
    private String name;
    private String salary;
    public Employee() {}
    public Employee(String id, String name, String salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSalary() {
        return salary;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
