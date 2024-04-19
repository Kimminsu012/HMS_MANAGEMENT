package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.DayChartDto;
import com.example.HMS_MANAGEMENT.entity.DayChartEntity;
import com.example.HMS_MANAGEMENT.repository.CustomerRepository;
import com.example.HMS_MANAGEMENT.repository.DayChartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class DayChartService {

    @Autowired
    private DayChartRepo dayChartRepo;
    @Autowired
    private CustomerRepository customerRepository;

    public int ServiceIncome(LocalDate date){
        DayChartEntity dayChartEntity = new DayChartEntity();

        Integer totalCost = dayChartRepo.totalCustomCost(date);
        if (totalCost == null) {
            totalCost = 0;
        }
        dayChartEntity.setDate(date);
        dayChartEntity.setServiceIncome(totalCost);



        int totalInCome = dayChartEntity.getServiceIncome()+dayChartEntity.getProductSales();
        dayChartEntity.setTotalIncome(totalInCome);

        dayChartRepo.save(dayChartEntity);
        return totalCost;
    }


}