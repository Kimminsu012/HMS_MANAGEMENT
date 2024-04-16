package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.dto.CustomerDto;
import com.example.HMS_MANAGEMENT.dto.SalesDto;
import com.example.HMS_MANAGEMENT.entity.CustomerEntity;
import com.example.HMS_MANAGEMENT.entity.SalesEntity;
import com.example.HMS_MANAGEMENT.repository.SalesRepository;
import com.example.HMS_MANAGEMENT.service.CustomerService;
import com.example.HMS_MANAGEMENT.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    SalesRepository salesRepository;

    //페이징수
    @GetMapping("/customer/cusList")
    public String cusList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerEntity> customerPage = customerService.getAllCustomer(pageable);

        model.addAttribute("customers", customerPage.getContent());
        model.addAttribute("totalPages", customerPage.getTotalPages());
        model.addAttribute("currentPage", page);

        return "customer/cusList";

    }

    @GetMapping("/customer/cusReg")
    public String cusReg(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "customer/cusReg";
    }

    // 등록 후 고객정보로 이동 컨트롤러
    @PostMapping("/customer/cusReg")
    public String saveCustomer(@Valid @ModelAttribute("customerDto") CustomerDto customerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customerDto", customerDto);
            return "customer/cusReg";
        }
        customerService.saveCustomer(customerDto);
        return "redirect:/customer/cusList";
    }

    //고객정보에서 이름 클릭했을때 id값에 맞는 내용 출력하는 컨트롤
    @GetMapping("/customer/useList")
    public String useList(@RequestParam("id") Long customerId, Model model) {

        CustomerEntity customer = customerService.getCustomerById(customerId);
        List<CustomerEntity> customers = customerService.getAllCustomer();

        List<CustomerEntity> customerList = new ArrayList<>();
        customerList.add(customer);

        Map<String, List<CustomerEntity>> groupCustomer = new HashMap<>();
        groupCustomer.put(customer.getName(), customerList);


//        SalesEntity sales = salesRepository.findById(customer.getSales().getId())
//                        .orElseThrow(()-> new NoSuchElementException("id" + customer.getSales().getId()));
//        customer.setCusCost(sales.getCost());
//        customer.setRecord(sales.getCut());

        model.addAttribute("customers", groupCustomer);

        return "customer/useList";
    }

}