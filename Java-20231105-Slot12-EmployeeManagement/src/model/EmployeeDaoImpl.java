package model;

import dao.DBConnection;
import entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDaoImpl implements EmployeeDao{
    private static final String SQL_CREATE_EMPLOYEE = "INSERT INTO employees (firstName, lastName, salary) VALUES (?, ?, ?);";
    private static final String SQL_DELETE_EMPLOYEE = "DELETE FROM employees WHERE id = ?;";
    private static final String SQL_MODIFY_INFORMATION = "UPDATE employees SET firstName = ?, lastName = ?, salary = ? WHERE id = ?;";
    private static final String SQL_FIND_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE id = ?;";
    private static final String SQL_FIND_EMPLOYEE_BY_NAME = "SELECT * FROM employees WHERE lastName = ?;";
    @Override
    public void createEmployee(Employee employee) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_CREATE_EMPLOYEE, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            pstm.setString(1, employee.getFirstName());
            pstm.setString(2, employee.getLastName());
            pstm.setInt(3, employee.getSalary());
            pstm.executeUpdate();
            try (ResultSet generatedId = pstm.getGeneratedKeys()) {
                while (generatedId.next()) {
                    System.out.println("Generated id: " + generatedId.getInt(1));
                }
            }
            conn.commit();
            System.out.println("Tạo nhân viên thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_DELETE_EMPLOYEE)) {
            conn.setAutoCommit(false);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            conn.commit();
            System.out.println("Xóa nhân viên thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifyInformation(Employee employee) {
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_MODIFY_INFORMATION)) {
            conn.setAutoCommit(false);
            pstm.setString(1, employee.getFirstName());
            pstm.setString(2, employee.getLastName());
            pstm.setInt(3, employee.getSalary());
            pstm.executeUpdate();
            conn.commit();
            System.out.println("Sửa thông tin thành công !");
        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Employee findEmployeeById(int id) {
        Employee employee = new Employee();
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_FIND_EMPLOYEE_BY_ID)) {
            conn.setAutoCommit(false);
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    employee.setId(rs.getInt(1));
                    employee.setFirstName(rs.getString(2));
                    employee.setLastName(rs.getString(3));
                    employee.setSalary(rs.getInt(4));
                }
            }
            conn.commit();
            System.out.println(employee);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }

    @Override
    public List<Employee> findEmployeeByName(String name) {
        List<Employee> employeeList = new ArrayList<>();
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstm = conn.prepareStatement(SQL_FIND_EMPLOYEE_BY_NAME)) {
            conn.setAutoCommit(false);
            pstm.setString(1, name);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setId(rs.getInt(1));
                    employee.setFirstName(rs.getString(2));
                    employee.setLastName(rs.getString(3));
                    employee.setSalary(rs.getInt(4));
                    employeeList.add(employee);
                }
            }
            conn.commit();
            System.out.println(employeeList);
        } catch (SQLException ex) {
            Logger.getLogger(AdminDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employeeList;
    }
}
