package com.technical.mercury.Controllers;

import com.technical.mercury.model.*;
import com.technical.mercury.services.DepartmentService;
import com.technical.mercury.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeservice;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/employees")
    public String getEmployees(Model model){
        List<Employee> employeeList = employeeservice.getAll();
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        ///  breadcrumbs.add(new PathToPage("employees", "/employees"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Employees");
        //   if (!departmentList.isEmpty()){
        model.addAttribute("employees", employeeList);
        model.addAttribute("pageTitle", "Employees");
        //     }
        return "employees/employeeList";
    }

    @GetMapping("/employees/add")
    public String addEmployee(Model model){
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Employees", "/employees"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Add Employee");

        model.addAttribute("pageTitle", "Add Employee");

        List<Department> departmentList = departmentService.getAll();
        List<Employee> employeeList = employeeservice.getAll();

        model.addAttribute("departmentList", departmentList);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("employee", new Employee());

        return "employees/employeeAdd";
    }
    @PostMapping("/employees/add")
    public String addEmployee(@ModelAttribute Employee employee){
        employeeservice.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model){
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Employees", "/employees"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Edit Employee");

        model.addAttribute("pageTitle", "Edit Employee");
        List<Department> departmentList = departmentService.getAll();
        List<Employee> employeeList = employeeservice.getAll();

        model.addAttribute("departmentList", departmentList);
        model.addAttribute("employeeList", employeeList);
        Employee employee = employeeservice.getById(id);
        model.addAttribute("employee", employee);

        return "employees/employeeEdit";
    }

    //TODO - NOT NEEDED
   // @RequestMapping(value = "/employees/delete/{id}", method = {RequestMethod.GET,RequestMethod.DELETE})
   // public String deleteEmployee(@PathVariable Long id){
   //     employeeservice.delete(id);
   //     return "redirect:/employees";
   // }

    @GetMapping("/employeeSalary/{id}")
    public String getEmployeeSalary(@PathVariable Long id, Model model){

        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Employees", "/employees"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Employees Salary");
        //   if (!departmentList.isEmpty()){
        List<EmployeeSalary> employeeSalaryList = employeeservice.getSalariesByEmpId(id);
        model.addAttribute("employeeSalary", employeeSalaryList);
        model.addAttribute("pageTitle", "Employees");
        //     }
        return "employees/employeeSalary/employeeSalaryList";
    }

    @GetMapping("/employeeSalary/add/{id}")
    public String addEmployeeSalary(@PathVariable Long id, Model model){
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
    public String addEmployeeSalary(@ModelAttribute EmployeeSalary employeeSalary){
        employeeservice.saveEmpSalary(employeeSalary);
        return "redirect:/employeeSalary/" + employeeSalary.getEmployee().getId();
    }

    @GetMapping("/employeeSalary/edit/{id}/{empId}")
    public String editEmployeeSalary(@PathVariable Long id, @PathVariable Long empId, Model model){
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
