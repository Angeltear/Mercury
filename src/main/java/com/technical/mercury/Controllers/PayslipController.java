package com.technical.mercury.Controllers;

import com.technical.mercury.model.*;
import com.technical.mercury.services.EmployeeService;
import com.technical.mercury.services.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PayslipController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PayrollService payrollService;

    @GetMapping("/payslip/init")
    public String initiatePayslip(Model model) {

        PayslipInit payslipParams = new PayslipInit();

        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Payslip Selection");
        model.addAttribute("months", payslipParams.getMonths());
        model.addAttribute("years", payslipParams.getYears());
        model.addAttribute("payslipSelectedObject", new PayslipSelectedObject());
        model.addAttribute("pageTitle", "Payslip Selection");

        return "/payslip/payslipInit";
    }

    @PostMapping("/payslip/generate")
    public String calculatePayslip(PayslipSelectedObject selection, Model model) {
        employeeService.processForMonth(selection.getMonth(), selection.getYear());
        return "redirect:/";
    }

    @GetMapping("/payslip/view")
    public String initiatePersonalPayslip(Model model) {

        PayslipInit payslipParams = new PayslipInit();

        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Payslip Selection");
        model.addAttribute("months", payslipParams.getMonths());
        model.addAttribute("years", payslipParams.getYears());
        model.addAttribute("payslipSelectedObject", new PayslipSelectedObject());
        model.addAttribute("pageTitle", "Payslip Selection");

        return "/payslip/payslipView";
    }

    @PostMapping("/payslip/viewEmp/")
    public String viewEmployeePayslip(Long empId, PayslipSelectedObject selection, Model model) {
        Employee emp;
        emp = employeeService.getById(empId);
        Payslip payslip = employeeService.findPayslip(empId, selection.getMonth(), selection.getYear());
        List<PayrollParams> paramsDeduct = payrollService.getDeducts();
        List<PayrollParams> paramsAccrual = payrollService.getAccruals();

        PayslipView payslipView = new PayslipView();

        payslipView.setEmployee(emp);
        payslipView.setPayrollParamsAccruals(paramsAccrual);
        payslipView.setPayrollParamsDeducts(paramsDeduct);
        payslipView.setPayslip(payslip);


        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Payslip Selection", "/payslip/view"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Payslip View");
        Period periodCareer = Period.between(emp.getCareerStartDate(), LocalDate.now());
        Period periodCompany = Period.between(emp.getEmploymentStartDate(), LocalDate.now());

        int yearsEmployment = periodCareer.getYears();
        int monthsEmployment = periodCareer.getMonths();
        int daysEmployment = periodCareer.getDays();

        int yearsCompany = periodCompany.getYears();
        int monthsCompany = periodCompany.getMonths();
        int daysCompany = periodCompany.getDays();

        model.addAttribute("daysEmployment", daysEmployment);
        model.addAttribute("monthsEmployment", monthsEmployment);
        model.addAttribute("yearsEmployment", yearsEmployment);
        model.addAttribute("daysCompany", daysCompany);
        model.addAttribute("monthsCompany", monthsCompany);
        model.addAttribute("yearsCompany", yearsCompany);
        model.addAttribute("payslipView", payslipView);
        model.addAttribute("pageTitle", "Payslip Viewer");
        return "/payslip/payslipViewEmployee";
    }


}
