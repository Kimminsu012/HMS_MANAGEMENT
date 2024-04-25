package com.example.HMS_MANAGEMENT.control;

import com.azure.core.annotation.Post;
import com.example.HMS_MANAGEMENT.dto.*;
import com.example.HMS_MANAGEMENT.entity.CustomerDetailEntity;
import com.example.HMS_MANAGEMENT.entity.CustomerEntity;
import com.example.HMS_MANAGEMENT.entity.DesignerCalendarEntity;
import com.example.HMS_MANAGEMENT.repository.CustomerDetailRepo;
import com.example.HMS_MANAGEMENT.repository.DesignerCalendarRepo;
import com.example.HMS_MANAGEMENT.service.CommuteListService;
import com.example.HMS_MANAGEMENT.service.CustomerService;
import com.example.HMS_MANAGEMENT.service.DesignerCalendarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final CommuteListService commuteListService;
    private final CustomerDetailRepo customerDetailRepo;

    private final DesignerCalendarService calendarService;

    private final DesignerCalendarRepo calendarRepo;

    public MainController(CommuteListService commuteListService, CustomerDetailRepo customerDetailRepo, DesignerCalendarService calendarService, DesignerCalendarRepo calendarRepo) {
        this.commuteListService = commuteListService;
        this.customerDetailRepo = customerDetailRepo;
        this.calendarService = calendarService;
        this.calendarRepo = calendarRepo;
    }


    @GetMapping("/")
    public String main(Model model){

        List<CustomerDetailEntity> cus = customerDetailRepo.findAllByOrderByVisitDesc();

        List<DesignerCalendarEntity> calendarDto = calendarRepo.findAllByOrderByEndDesc();

        model.addAttribute("customer", cus);
        model.addAttribute("calendar", calendarDto);

        return "main";
    }


}
