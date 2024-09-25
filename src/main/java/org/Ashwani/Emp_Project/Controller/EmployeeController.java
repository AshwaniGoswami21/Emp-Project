package org.Ashwani.Emp_Project.Controller;

import org.Ashwani.Emp_Project.Model.Employee;
import org.Ashwani.Emp_Project.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ModelAndView getAllEmployees(){
        List<Employee> employees = employeeService.readEmployees();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("employeeList",employees);
        return modelAndView;
    }

    @GetMapping("employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.readEmployee(id);

    }

    @PostMapping("employees")
    public String createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable Long id){
        if(employeeService.deleteEmployee(id)){
            return "Deleted";
        }else {
            return "Not found";
        }
    }

    @PutMapping("employees/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id,employee);
    }
}
