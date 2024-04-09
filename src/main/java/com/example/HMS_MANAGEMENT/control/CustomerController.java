package com.example.HMS_MANAGEMENT.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/customer/cusList")
    public String cusList(){

        return "customer/cusList";
    }

    @GetMapping("/customer/useList")
    public String useList(){

        return "customer/useList";
    }

    @GetMapping("/customer/cusReg")
    public String cusReg(){
        return "customer/cusReg";
    }
}
