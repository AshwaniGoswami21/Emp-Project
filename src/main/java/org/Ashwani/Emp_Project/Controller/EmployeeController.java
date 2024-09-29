package org.Ashwani.Emp_Project.Controller;

import org.Ashwani.Emp_Project.Model.Employee;
import org.Ashwani.Emp_Project.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Employees list
    @GetMapping("/")
    public ModelAndView getAllEmployees(){
        List<Employee> employees = employeeService.readEmployees();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("employeeList",employees);
        return modelAndView;
    }

    //Add employee
    @GetMapping("/addEmployee")
    public ModelAndView showAddEmployeeForm() {
        return new ModelAndView("addEmployee");
    }
    @PostMapping("/addEmployee")
    public ModelAndView createEmployee(@ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        return new ModelAndView("redirect:/");
    }

    // Edit Employee
    @GetMapping("/editEmployee/{id}")
    public ModelAndView showEditEmployeeForm(@PathVariable Long id) {
        Employee employee = employeeService.readEmployee(id);
        ModelAndView modelAndView = new ModelAndView("updateEmployee");
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }
    @PostMapping("/updateEmployee")
    public ModelAndView updateEmployee(@ModelAttribute Employee employee) {
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee ID cannot be null for update");
        }
        employeeService.updateEmployee(employee);
        return new ModelAndView("redirect:/");
    }


    @PostMapping("/deleteEmployee")
    public ModelAndView deleteEmployee(@RequestParam Long id) {
        System.out.println("Id:" + id);
        employeeService.deleteEmployee(id);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.readEmployee(id);

    }


}
