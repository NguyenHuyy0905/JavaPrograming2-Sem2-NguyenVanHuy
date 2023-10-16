package vn.fptaptech;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeImpl implements DAO<Employee>{
    Scanner sc = new Scanner(System.in);
    Connection connection = null;
    PreparedStatement pstm = null;
    @Override
    public Employee getById(String id) throws SQLException {
        Employee employee = null;
        connection = MySQLConnection.getMyConnection();
        String query = "SELECT * FROM employee WHERE id = ?";
        pstm = connection.prepareStatement(query);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String _id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String salary = resultSet.getString("salary");
            employee = new Employee(_id, name, salary);
            System.out.println("Employee data:");
            System.out.println("Id: " + _id);
            System.out.println("Name: " + name);
            System.out.println("Salary: " + salary);
        }
        pstm.close();
        connection.close();
        return employee;
    }

    @Override
    public void getAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        connection = MySQLConnection.getMyConnection();
        String query = "SELECT * FROM employee";
        pstm = connection.prepareStatement(query);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String _id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String salary = resultSet.getString("salary");
            employees.add(new Employee(_id, name, salary));
        }
        System.out.println(employees);
        pstm.close();
        connection.close();
    }

    @Override
    public Employee add(Employee employee) throws SQLException {
        connection = MySQLConnection.getMyConnection();
        String query = "INSERT INTO employee(id, name, salary) VALUE(?, ?, ?)";
        pstm = connection.prepareStatement(query);
        pstm.setString(1, employee.getId());
        pstm.setString(2, employee.getName());
        pstm.setString(3, employee.getSalary());
        int count = pstm.executeUpdate();
        if (count > 0) {
            System.out.println("Added successfully !");
        } else {
            System.out.println("Failed to add !");
        }
        pstm.close();
        connection.close();
        return employee;
    }

    @Override
    public Employee delete(String id) throws SQLException {
        connection = MySQLConnection.getMyConnection();
        EmployeeImpl employeeImpl = new EmployeeImpl();
        Employee employee = employeeImpl.getById(id);
        if (employee != null) {
            String query = "DELETE FROM employee WHERE id = ?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, id);
            int count = pstm.executeUpdate();
            if (count > 0) {
                System.out.println("Delete successfully !");
            } else {
                System.out.println("Failed to delete !");
            }
            pstm.close();
            connection.close();
            return employee;
        } else {
            System.out.println("Id doesn't exist !");
            connection.close();
            return null;
        }
    }

    @Override
    public Employee update(String id, String name) throws SQLException {
        connection = MySQLConnection.getMyConnection();
        EmployeeImpl employeeImpl = new EmployeeImpl();
        Employee _employee = employeeImpl.getById(id);
        if (_employee != null) {
            String query = "UPDATE employee SET name = ? WHERE id = ?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, id);
            int count = pstm.executeUpdate();
            if (count > 0) {
                System.out.println("Updated successfully !");
            } else {
                System.out.println("Failed to update !");
            }
            pstm.close();
            connection.close();
            return null;
        } else {
            System.out.println("Id doesn't exist !");
            connection.close();
            return null;
        }
    }
}
