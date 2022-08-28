package com.technical.mercury.Controllers;

import com.technical.mercury.model.*;
import com.technical.mercury.services.DepartmentService;
import com.technical.mercury.services.EmployeeService;
import com.technical.mercury.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LocationService locationService;



    @GetMapping({"/index", "/"})
    public String getIndex(Model model) {
        Employee employee = new Employee();
        employee.setEmployeeLastName("SomeRandomEmp");
        model.addAttribute("employee", employee);
        model.addAttribute("pageTitle", "Home");
        return "test_index";
    }

    @GetMapping("/requests")
    public String getRequests(Model model){
        String var = "testVal";
        List<Vacation> listOfRequests = new ArrayList<>();
      //  listOfRequests.add(new Vacation(15, "Pesho", "Paid leave", 3, "Gosho"));
      //  listOfRequests.add(new Vacation(15, "Pesho2", "Paid leave", 3, "Gosho"));
      //  listOfRequests.add(new Vacation(15, "Pesho3", "Paid leave", 3, "Gosho"));
      //  listOfRequests.add(new Vacation(15, "Pesho6", "Paid leave", 3, "Gosho"));
        model.addAttribute("listOfRequests", listOfRequests);
        model.addAttribute("pageTitle", "Requests");
        return "requests";
    }

    @GetMapping("/employee")
    public String getEmployees(){
        employeeService.getAll();
        return "redirect:/";
    }


}
