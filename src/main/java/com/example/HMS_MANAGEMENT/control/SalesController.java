package com.example.HMS_MANAGEMENT.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SalesController {

    @GetMapping("/sales")
    public String cusList(){

        return "sales/salesPage";
    }

}
