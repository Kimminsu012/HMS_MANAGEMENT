package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.dto.DesignerCalendarDto;
import com.example.HMS_MANAGEMENT.dto.DesignerDto;
import com.example.HMS_MANAGEMENT.entity.DesignerCalendarEntity;
import com.example.HMS_MANAGEMENT.entity.DesignerEntity;
import com.example.HMS_MANAGEMENT.repository.DesignerCalendarRepo;
import com.example.HMS_MANAGEMENT.repository.DesignerRepo;
import com.example.HMS_MANAGEMENT.service.DesignerCalendarService;
import com.example.HMS_MANAGEMENT.service.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class DesignerController {

    private final DesignerService designerService;

    @Autowired
    public DesignerController(DesignerService designerService){
        this.designerService = designerService;
    }

    @GetMapping("/designer")
    public String designerMain(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, Model model){

        List<DesignerDto> designerDtoList = designerService.getAllDesigners();
        Pageable pageable = PageRequest.of(page, 5);
        model.addAttribute("designerDtoList", designerService.getAllDesignersWithDayOff());
        model.addAttribute("designerDto", new DesignerDto());
        model.addAttribute("maxPage",5);


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

    @GetMapping("/designer/detailPage/{id}")
    public String detailPage(){

        return "designer/detailPage/{id}";
    }

    @GetMapping("/designer/regPage")
    public String regPage(Model model){

        model.addAttribute("designerDto", new DesignerDto());

        return "designer/designerReg";
    }

    @GetMapping("/designer/salaryPage")
    public String salaryPage(){

        return "designer/salaryPage";
    }

    @PostMapping("/designer/regPage")
    public String designerChk(@ModelAttribute("designerDto") @Valid DesignerDto designerDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return "designer/designerReg";
        }

        designerService.designerSave(designerDto);
        designerService.getAllDesignersWithDayOff();
        return "redirect:/designer";
    }



}
