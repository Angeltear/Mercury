package com.technical.mercury.Controllers;

import com.technical.mercury.model.Employee;
import com.technical.mercury.model.PathToPage;
import com.technical.mercury.model.Users.MercuryUserDetails;
import com.technical.mercury.model.Vacation;
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
public class EmployeeVacationsController {

    @Autowired
    private EmployeeService employeeservice;

    @GetMapping("/vacations")
    public String getVacations(Model model) {
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Vacations");
        List<Vacation> vacationList = employeeservice.getAllVacations();
        model.addAttribute("vacations", vacationList);
        model.addAttribute("pageTitle", "Vacations");
        return "employees/employeeVacations/vacationList";
    }

    @GetMapping("/vacations/{id}")
    public String getEmployeeVacation(@PathVariable Long id, Model model) {

        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Vacations", "/vacations/" + id));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Vacations");
        List<Vacation> employeeVacationList = employeeservice.getVacationByRequestorId(id);
        List<Vacation> pendingRequests = employeeservice.getVacationByStatusAndRequestorId("Pending", id);
        List<Vacation> pendingApproves = employeeservice.getVacationByStatusAndApproverId("Pending", id);
        Employee emp = employeeservice.getById(id);
        model.addAttribute("allVacations", employeeVacationList);
        model.addAttribute("pendingRequests", pendingRequests);
        model.addAttribute("pendingApproves", pendingApproves);
        model.addAttribute("employee", emp);
        model.addAttribute("pageTitle", "Vacations");
        return "employees/employeeVacations/vacationListByEmployee";
    }

    @GetMapping("/vacations/add/{id}")
    public String addEmployeeVacation(@PathVariable Long id, Model model) {
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Vacations", "/vacations/" + id));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "New Vacation Request");

        model.addAttribute("pageTitle", "New Vacation Request");

        Employee employeeRequestor = employeeservice.getById(id);
        Vacation vacation = new Vacation();
        vacation.setEmployeeRequestor(employeeRequestor);
        model.addAttribute("vacation", vacation);

        return "employees/employeeVacations/vacationRequest";
    }

    @PostMapping("/vacations/add")
    public String addEmployeeVacation(@ModelAttribute Vacation vacation) {
        vacation.setStatus("Pending");
        employeeservice.saveEmpVacation(vacation);
        return "redirect:/vacations/" + vacation.getEmployeeRequestor().getId();
    }

    @GetMapping("/vacations/edit/{id}/{empId}")
    public String editEmployeeVacation(@PathVariable Long id, @PathVariable Long empId, Model model) {
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Employees", "/employees"));
        breadcrumbs.add(new PathToPage("Employee Vacations", "/vacations/" + empId));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Edit Employee Vacation");

        model.addAttribute("pageTitle", "Edit Employee Vacation");

        Vacation vacation = employeeservice.getVacationById(id);

        model.addAttribute("vacation", vacation);

        return "employees/employeeVacations/vacationRequest";
    }

    @GetMapping("/vacations/{confirmation}/{id}/{empId}")
    public String confirmEmployeeVacation(@PathVariable String confirmation, @PathVariable Long id, @PathVariable Long empId, Model model) {

        employeeservice.confirmVacation(confirmation, id);
        MercuryUserDetails currentEmp = (MercuryUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return "redirect:/vacations/" + currentEmp.getUser().getEmployee().getId();
    }
}
