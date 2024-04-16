package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.CustomerDto;
import com.example.HMS_MANAGEMENT.entity.CustomerEntity;
import com.example.HMS_MANAGEMENT.entity.SalesEntity;
import com.example.HMS_MANAGEMENT.repository.CustomerRepository;
import com.example.HMS_MANAGEMENT.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SalesRepository salesRepository;

    private SalesEntity salesEntity;

    public void saveCustomer(CustomerDto customerDto) {
        String formatPhoneNumber = formatPhoneNumber(customerDto.getTel());

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerDto.getName());
        customerEntity.setFirstVisit(customerDto.getFirstVisit());
        customerEntity.setTel(formatPhoneNumber);
        customerEntity.setFrequentDesigner(customerDto.getFrequentDesigner());

        SalesEntity sales = salesRepository.findById(salesEntity.getId())
                .orElseThrow(()-> new NullPointerException("해당 salesId가없음"));
        customerEntity.setSales(salesEntity);
        customerEntity.setRecord(salesEntity.getCut());
        customerEntity.setCusCost(salesEntity.getCost());


        customerRepository.save(customerEntity);
    }

    //고객정보 페이징
    public Page<CustomerEntity> getAllCustomer(Pageable pageable){
        return customerRepository.findAll(pageable);
    }


    //휴대폰 번호 하이푼 코드
    public String formatPhoneNumber(String phoneNumber) {
        String digits = phoneNumber.replaceAll("\\D", "");
        if (digits.length() < 10) {
            throw new IllegalArgumentException("유효한 전화번호가 아닙니다.");
        }
        String areaCode = digits.substring(0, 3);
        String firstPart = digits.substring(3, 7);
        String secondPart = digits.substring(7, 11);
        return String.format("%s-%s-%s", areaCode, firstPart, secondPart);
    }

    //고객정보에서 정보 보여주기
    public List<CustomerDto> getCustomer(){
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        List<CustomerDto> customer = new ArrayList<>();

        for(CustomerEntity entity : customerEntities){
            CustomerDto dto = new CustomerDto();
            dto.setName(entity.getName());
            dto.setFrequentDesigner(entity.getFrequentDesigner());
            dto.setRecord(entity.getRecord());
        }
        return customer;
    }


    public List<CustomerEntity> getAllCustomer(){
        return customerRepository.findAll();
    }


    //고객정보 아이디
    public CustomerEntity getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("id" + customerId));
    }

    // 서비스 아이디
    public SalesEntity getSalesId(Long salesId, String cut){
        return salesRepository.findById(salesId)
                .orElseThrow(() -> new NoSuchElementException("id" + salesId));
    }
}