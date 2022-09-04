package com.technical.mercury.Controllers;

import com.technical.mercury.model.Department;
import com.technical.mercury.model.Location;
import com.technical.mercury.model.PathToPage;
import com.technical.mercury.services.DepartmentService;
import com.technical.mercury.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LocationService locationService;


    @GetMapping("/departments")
    public String getDepartments(Model model) {
        List<Department> departmentList = departmentService.getAll();
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        ///  breadcrumbs.add(new PathToPage("Departments", "/departments"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Departments");
        //   if (!departmentList.isEmpty()){
        model.addAttribute("departments", departmentList);
        model.addAttribute("pageTitle", "Departments");
        //     }
        return "departments/departmentList";
    }

    @GetMapping("/departments/add")
    public String addDepartment(Model model) {
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Departments", "/departments"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Add Department");

        model.addAttribute("pageTitle", "Add Department");

        List<Location> locationList = locationService.getAll();

        model.addAttribute("locationList", locationList);
        model.addAttribute("department", new Department());

        return "departments/departmentAdd";
    }

    @PostMapping("/departments/add")
    public String addDepartment(@ModelAttribute Department department) {
        departmentService.save(department);
        return "redirect:/departments";
    }

    @GetMapping("/departments/edit/{id}")
    public String editDepartment(@PathVariable Long id, Model model) {
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Departments", "/departments"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Edit Department");

        model.addAttribute("pageTitle", "Edit Department");
        List<Location> locationList = locationService.getAll();

        model.addAttribute("locationList", locationList);
        Department department = departmentService.getById(id);
        model.addAttribute("department", department);

        return "departments/departmentEdit";
    }

    @RequestMapping(value = "/departments/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
        return "redirect:/departments";
    }
}
