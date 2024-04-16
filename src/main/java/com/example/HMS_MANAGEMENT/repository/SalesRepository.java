package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalesRepository extends JpaRepository<SalesEntity, Long> {
}