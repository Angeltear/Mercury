package com.technical.mercury.Controllers;

import com.technical.mercury.model.Employee;
import com.technical.mercury.model.PathToPage;
import com.technical.mercury.model.PayslipInit;
import com.technical.mercury.model.PayslipSelectedObject;
import com.technical.mercury.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PayslipController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/payslip/init")
    public String initiatePayslip(Model model){

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
    public String calculatePayslip(PayslipSelectedObject selection, Model model){
        employeeService.processForMonth(selection.getMonth(), selection.getYear());
        return "redirect:/";
    }


}
