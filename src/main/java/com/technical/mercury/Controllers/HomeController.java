package com.technical.mercury.Controllers;

import com.technical.mercury.model.*;
import com.technical.mercury.model.Users.User;
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
        model.addAttribute("pageTitle", "Home");
        return "test_index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/supersecret")
    public String initializeUser(){

        if (employeeService.getAllUsers().isEmpty()){
            Employee emp = new Employee();
            Department dept = new Department();
            Location location = new Location();
            location.setCity("initial");
            location.setStreet_address("initial");
            locationService.save(location);
            dept.setDepartmentName("initial");
            dept.setLocation(location);
            dept.setDepartmentOffice("initial");
            departmentService.save(dept);
            emp.setActive(true);
            emp.setEmployeeEmail("gigaadmin@mcy.bg");
            emp.setHrRole(true);
            emp.setPassword("password");
            emp.setDepartment(dept);
            employeeService.save(emp);
            employeeService.addUser(emp);
        }
        return "redirect:/index";
    }



}
