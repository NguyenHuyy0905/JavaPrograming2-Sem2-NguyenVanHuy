package model;

import entity.Employee;

import java.util.List;

public interface EmployeeDao {
    void createEmployee(Employee employee);
    void deleteEmployee(int id);
    void modifyInformation(Employee employee);
    Employee findEmployeeById(int id);
    List<Employee> findEmployeeByName(String name);
}
