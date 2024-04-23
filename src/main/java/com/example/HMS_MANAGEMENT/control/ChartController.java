package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.dto.DayChartDto;
import com.example.HMS_MANAGEMENT.entity.DayChartEntity;
import com.example.HMS_MANAGEMENT.repository.DayChartRepo;
import com.example.HMS_MANAGEMENT.service.DayChartService;
import org.codehaus.groovy.control.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;


@Controller
public class ChartController {
    @Autowired
    private DayChartService dayChartService;
    @Autowired
    private DayChartRepo dayChartRepo;


    @GetMapping("/sales")
    public String cusList(Model model) {

        return "sales/salesPage";
    }


    @GetMapping("/sales/monPage")
    public String monPage() {
        return "sales/monPage";
    }

    @GetMapping("/sales/dayPage")
    public String dayPage(@RequestParam(value = "date", required = false)String dateString, Model model) {
        LocalDate date;
        try {
            date = (dateString != null) ? LocalDate.parse(dateString) : LocalDate.now();
        } catch (DateTimeParseException e) {
            date = LocalDate.now();
        }
        int totalIncome = dayChartService.ServiceIncome(date);

        model.addAttribute("TotalIncome", totalIncome);
        model.addAttribute("serviceIncome", totalIncome);
        model.addAttribute("date", date);
        model.addAttribute("prevDate", date.minusDays(1).toString());
        model.addAttribute("nextDate", date.plusDays(1).toString());

        return "sales/dayPage";
    }
}