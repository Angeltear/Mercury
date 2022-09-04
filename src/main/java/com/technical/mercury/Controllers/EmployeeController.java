package com.technical.mercury.Controllers;

import com.technical.mercury.model.Department;
import com.technical.mercury.model.Employee;
import com.technical.mercury.model.PathToPage;
import com.technical.mercury.model.Users.MercuryUserDetails;
import com.technical.mercury.services.DepartmentService;
import com.technical.mercury.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeservice;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/employeeManagement")
    public String getAllEmployees(Model model) {
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

    @GetMapping("/employees")
    public String getEmployees(Model model) {
        MercuryUserDetails currentEmp = (MercuryUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Employee> employeeList = employeeservice.getAllActive(currentEmp.getUser().getEmployee().getId());
        Employee emp = employeeservice.getById(currentEmp.getUser().getEmployee().getId());
        employeeList.add(emp);
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
    public String addEmployee(Model model) {
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
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeservice.save(employee);
        employeeservice.addUser(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
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

}
