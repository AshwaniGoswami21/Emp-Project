package org.Ashwani.Emp_Project.Service;

import org.Ashwani.Emp_Project.Model.Employee;
import org.Ashwani.Emp_Project.Entity.EmployeeEntity;
import org.Ashwani.Emp_Project.Repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee,employeeEntity);

        employeeRepository.save(employeeEntity);
        return "Saved successfully";
    }

    @Override
    public Employee readEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee emp = new Employee();
        BeanUtils.copyProperties(employeeEntity,emp);

        return emp;
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> employeesList = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for(EmployeeEntity employeeEntity : employeesList){
            Employee emp = new Employee();
            emp.setId(employeeEntity.getId());
            emp.setName(employeeEntity.getName());
            emp.setEmail(employeeEntity.getEmail());
            emp.setPhone(employeeEntity.getPhone());
            employees.add(emp);
        }

        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
//        return employees.removeIf(employee -> employee.getId().equals(id));
        EmployeeEntity emp = employeeRepository.findById(id).get();
        employeeRepository.delete(emp);
        return true;
    }

    @Override
    public String updateEmployee(Employee employee) {
        EmployeeEntity existingEmployee = employeeRepository.findById(employee.getId()).get();

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());

        employeeRepository.save(existingEmployee);
        return "Update successfully";
    }


}
