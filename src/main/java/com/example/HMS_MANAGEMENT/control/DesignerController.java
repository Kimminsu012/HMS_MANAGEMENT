package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.dto.DesignerCalendarDto;
import com.example.HMS_MANAGEMENT.entity.DesignerCalendarEntity;
import com.example.HMS_MANAGEMENT.repository.DesignerCalendarRepo;
import com.example.HMS_MANAGEMENT.service.DesignerCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/designer/detailPage")
    public String detailPage(){

        return "designer/detailPage";
    }

    @GetMapping("/designer/regPage")
    public String regPage(){

        return "designer/designerReg";
    }



}
