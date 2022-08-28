package com.technical.mercury.Controllers;

import com.technical.mercury.model.Employee;
import com.technical.mercury.model.EmployeeHistory;
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
public class EmployeeHistoryController {

    @Autowired
    private EmployeeService employeeservice;


    @GetMapping("/employeeHistory/{id}")
    public String getEmployeeHistory(@PathVariable Long id, Model model){

        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Employees", "/employees"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Employees History");
        //   if (!departmentList.isEmpty()){
        List<EmployeeHistory> employeeHistoryList = employeeservice.getHistoryByEmpId(id);
        model.addAttribute("employeeHistory", employeeHistoryList);
        model.addAttribute("pageTitle", "Employees");
        //     }
        return "employees/employeeHistory/employeeHistoryList";
    }

    @GetMapping("/employeeHistory/add/{id}")
    public String addEmployeeHistory(@PathVariable Long id, Model model){
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Employees", "/employees"));
        breadcrumbs.add(new PathToPage("Employee Salary", "/employeeHistory/" + id));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "New Employee History Entry");

        model.addAttribute("pageTitle", "Add Employee History");

        Employee employee = employeeservice.getById(id);
        EmployeeHistory employeeHistory = new EmployeeHistory();
        employeeHistory.setEmployee(employee);
        model.addAttribute("employeeHistory", employeeHistory);

        return "employees/employeeHistory/employeeHistoryAdd";
    }
    @PostMapping("/employeeHistory/add")
    public String addEmployeeHistory(@ModelAttribute EmployeeHistory employeeHistory){
        employeeservice.saveEmpHistory(employeeHistory);
        return "redirect:/employeeHistory/" + employeeHistory.getEmployee().getId();
    }

    @GetMapping("/employeeHistory/edit/{id}/{empId}")
    public String editEmployeeHistory(@PathVariable Long id, @PathVariable Long empId, Model model){
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Employees", "/employees"));
        breadcrumbs.add(new PathToPage("Employee History", "/employeeHistory/" + empId));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Edit Employee History");

        model.addAttribute("pageTitle", "Edit Employee History");

        EmployeeHistory employeeHistory = employeeservice.getHistoryById(id);

        model.addAttribute("employeeHistory", employeeHistory);

        return "employees/employeeHistory/employeeHistoryEdit";
    }
}
