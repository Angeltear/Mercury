package com.technical.mercury.Controllers;

import com.technical.mercury.model.Employee;
import com.technical.mercury.model.EmployeeSalary;
import com.technical.mercury.model.PathToPage;
import com.technical.mercury.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeSalaryController {

    @Autowired
    private EmployeeService employeeservice;

    @GetMapping("/employeeSalary/{id}")
    public String getEmployeeSalary(@PathVariable Long id, Model model) {

        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Employees", "/employees"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Employees Salary");
        //   if (!departmentList.isEmpty()){
        List<EmployeeSalary> employeeSalaryList = employeeservice.getSalariesByEmpId(id);
        model.addAttribute("employeeSalary", employeeSalaryList);
        model.addAttribute("employeeId", id);
        model.addAttribute("pageTitle", "Employees");
        //     }
        return "employees/employeeSalary/employeeSalaryList";
    }

    @GetMapping("/employeeSalary/add/{id}")
    public String addEmployeeSalary(@PathVariable Long id, Model model) {
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Employees", "/employees"));
        breadcrumbs.add(new PathToPage("Employee Salary", "/employeeSalary/" + id));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "New Employee Salary Entry");

        model.addAttribute("pageTitle", "Add Employee Salary");

        Employee employee = employeeservice.getById(id);
        EmployeeSalary employeeSalary = new EmployeeSalary();
        employeeSalary.setEmployee(employee);
        model.addAttribute("employeeSalary", employeeSalary);

        return "employees/employeeSalary/employeeSalaryAdd";
    }

    @PostMapping("/employeeSalary/add")
    public String addEmployeeSalary(@ModelAttribute EmployeeSalary employeeSalary) {
        employeeservice.saveEmpSalary(employeeSalary);
        return "redirect:/employeeSalary/" + employeeSalary.getEmployee().getId();
    }

    @GetMapping("/employeeSalary/edit/{id}/{empId}")
    public String editEmployeeSalary(@PathVariable Long id, @PathVariable Long empId, Model model) {
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Employees", "/employees"));
        breadcrumbs.add(new PathToPage("Employee Salary", "/employeeSalary/" + empId));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Edit Employee Salary");

        model.addAttribute("pageTitle", "Edit Employee Salary");

        EmployeeSalary employeeSalary = employeeservice.getSalaryById(id);

        model.addAttribute("employeeSalary", employeeSalary);

        return "employees/employeeSalary/employeeSalaryEdit";
    }
}
