package com.kgisl.multipleconn2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kgisl.multipleconn2.model.Employee;
import com.kgisl.multipleconn2.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String getAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "index";
    }

    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee";
    }

    @GetMapping("/new")
    public String showCreateEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "createEmployee";
    }

    @PostMapping
    public String createEmployee(@ModelAttribute Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "edit";
    }

    @PostMapping("/{id}/edit") // Corrected the mapping to POST
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee updatedEmployee) {
        employeeService.updateEmployee(id, updatedEmployee);
        return "redirect:/employees";
    }

    @GetMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable Long id) {
        System.out.println("Delete called");
        // Delete the employee with the given ID
        employeeService.deleteEmployee(id);
        System.out.println("Delete finished");
        // Return a response indicating the success or failure of the deletion
        return "redirect:/employees";
    }
}
