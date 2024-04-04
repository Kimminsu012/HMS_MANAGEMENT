package com.example.HMS_MANAGEMENT.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DesignerController {

    @GetMapping("/designer")
    public String designerMain(){

        return "designer/designerMain";
    }

    @GetMapping("/designer/scheduleList")
    public String scheduleList(){

        return "designer/scheduleList";
    }

    @GetMapping("/designer/salaryList")
    public String salaryList(){

        return "designer/salaryList";
    }

    @GetMapping("/designer/commuteList")
    public String commuteList(){

        return "designer/commuteList";
    }
}
