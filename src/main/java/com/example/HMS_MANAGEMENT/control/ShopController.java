package com.example.HMS_MANAGEMENT.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    @GetMapping("/shop/service")
    public String servicePage(){

        return "shop/service";
    }
}
