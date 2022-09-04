package com.technical.mercury.Controllers;

import com.technical.mercury.model.PathToPage;
import com.technical.mercury.model.PayrollParams;
import com.technical.mercury.services.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PayrollParamsController {

    @Autowired
    private PayrollService payrollService;


    @GetMapping("/params")
    public String getParameters(Model model) {
        List<PayrollParams> paramsList = payrollService.getAll();
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Payroll Parameters");
        //   if (!departmentList.isEmpty()){
        model.addAttribute("params", paramsList);
        model.addAttribute("pageTitle", "Payroll Parameters");
        //     }
        return "payroll/payrollParamsList";
    }

    @GetMapping("/params/add")
    public String addParameter(Model model) {
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Parameters", "/params"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Add Payroll Parameter");

        model.addAttribute("pageTitle", "Add Payroll Parameter");

        model.addAttribute("parameters", new PayrollParams());

        return "payroll/payrollParamsAdd";
    }

    @PostMapping("/params/add")
    public String addParameter(@ModelAttribute PayrollParams parameters) {
        payrollService.save(parameters);
        return "redirect:/params";
    }

    @GetMapping("/params/edit/{id}")
    public String editParameter(@PathVariable Long id, Model model) {
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Parameters", "/params"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Edit Payroll Parameter");

        model.addAttribute("pageTitle", "Edit Payroll Parameter");

        PayrollParams payrollParam = payrollService.getById(id);
        model.addAttribute("parameters", payrollParam);

        return "payroll/payrollParamsEdit";
    }

    @RequestMapping(value = "/params/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteParameter(@PathVariable Long id) {
        payrollService.delete(id);
        return "redirect:/params";
    }
}
