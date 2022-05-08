package com.technical.mercury.Controllers;

import com.technical.mercury.model.VacationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/requests")
    public String getRequests(Model model){
        String var = "testVal";
        List<VacationRequest> listOfRequests = new ArrayList<>();
        listOfRequests.add(new VacationRequest(15, "Pesho", "Paid leave", 3, "Gosho"));
        listOfRequests.add(new VacationRequest(15, "Pesho2", "Paid leave", 3, "Gosho"));
        listOfRequests.add(new VacationRequest(15, "Pesho3", "Paid leave", 3, "Gosho"));
        model.addAttribute("listOfRequests", listOfRequests);
        return "requests";
    }


}
