package com.technical.mercury.Controllers;

import com.technical.mercury.model.Location;
import com.technical.mercury.model.PathToPage;
import com.technical.mercury.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;


    @GetMapping("/locations")
    public String getLocations(Model model) {
        List<Location> locationList = locationService.getAll();
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Locations");
        //   if (!departmentList.isEmpty()){
        model.addAttribute("locations", locationList);
        model.addAttribute("pageTitle", "Location");
        //     }
        return "locations/locationList";
    }

    @GetMapping("/locations/add")
    public String addLocation(Model model) {
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Locations", "/locations"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Add Location");

        model.addAttribute("pageTitle", "Add Location");

        model.addAttribute("location", new Location());

        return "locations/locationAdd";
    }

    @PostMapping("/locations/add")
    public String addLocation(@ModelAttribute Location location) {
        locationService.save(location);
        return "redirect:/locations";
    }

    @GetMapping("/locations/edit/{id}")
    public String editLocation(@PathVariable Long id, Model model) {
        List<PathToPage> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new PathToPage("Home", "/index"));
        breadcrumbs.add(new PathToPage("Locations", "/locations"));
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("currentPage", "Edit Location");

        model.addAttribute("pageTitle", "Edit Location");

        Location location = locationService.getById(id);
        model.addAttribute("location", location);

        return "locations/locationEdit";
    }

    @RequestMapping(value = "/locations/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteLocation(@PathVariable Long id) {
        locationService.delete(id);
        return "redirect:/locations";
    }
}
