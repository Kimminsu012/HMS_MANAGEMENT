package com.example.HMS_MANAGEMENT.repository;


import com.example.HMS_MANAGEMENT.entity.DayChartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DayChartRepo extends JpaRepository<DayChartEntity,Long> {

    @Query("select sum(c.cusCost) from CustomerEntity c where c.firstVisit = :d")
    Integer totalCustomCost(LocalDate d);


}