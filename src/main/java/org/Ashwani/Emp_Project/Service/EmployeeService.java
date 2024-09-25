package org.Ashwani.Emp_Project.Service;

import org.Ashwani.Emp_Project.Model.Employee;

import java.util.List;

public interface EmployeeService {
    String createEmployee(Employee employee);
    Employee readEmployee(Long id);
    List<Employee> readEmployees();
    boolean deleteEmployee(Long id);
    String updateEmployee(Long id, Employee employee);
}
