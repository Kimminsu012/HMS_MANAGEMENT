package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.CustomerDetailEntity;
import com.example.HMS_MANAGEMENT.entity.CustomerEntity;
import org.apache.el.stream.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    //페이징
    Page<CustomerEntity> findAll(Pageable pageable);

}