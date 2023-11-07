package controller;

import entity.Admin;
import entity.Employee;
import model.AdminDao;
import model.AdminDaoImpl;
import model.EmployeeDao;
import model.EmployeeDaoImpl;

import java.util.List;

public class AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    public void register(Admin admin) {
        adminDao.register(admin);
    }
    public void login(Admin admin) {
        adminDao.login(admin);
    }
    public boolean isExistUsername(String username) {
        return adminDao.isExistUsername(username);
    }
    public void createEmployee(Employee employee) {
        employeeDao.createEmployee(employee);
    }
    public void deleteEmployee(int id) {
        employeeDao.deleteEmployee(id);
    }
    public void modifyInformation(Employee employee) {
        employeeDao.modifyInformation(employee);
    }
    public Employee findEmployeeById(int id) {
        return employeeDao.findEmployeeById(id);
    }
    public List<Employee> findEmployeeByName(String name) {
        return employeeDao.findEmployeeByName(name);
    }
}
