package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.CustomerDetailEntity;
import com.example.HMS_MANAGEMENT.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerDetailRepo extends JpaRepository<CustomerDetailEntity , Long> {

    List<CustomerDetailEntity> findByCustomerId(Long customerId);

}