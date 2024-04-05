package com.example.HMS_MANAGEMENT.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvenController {

    @GetMapping("/inven")
    public String invenMain(){

        return "inven/invenMain";
    }
    @GetMapping("/inven/invenList")
    public String invenListPage(){

        return "inven/invenList";
    }
    @GetMapping("/inven/buyList")
    public String buyListPage(){

        return "inven/buyList";
    }
    @GetMapping("/inven/sortList")
    public String sortListPage(){

        return "inven/sortList";
    }
}
