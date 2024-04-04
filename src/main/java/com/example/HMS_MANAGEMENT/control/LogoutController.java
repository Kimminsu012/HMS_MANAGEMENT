package com.example.HMS_MANAGEMENT.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/login")
    public String loginPage(){

        return "login/loginPage";
    }
}
