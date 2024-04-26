package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.DesignerCalendarEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DesignerCalendarRepo extends JpaRepository<DesignerCalendarEntity,Long> {

    List<DesignerCalendarEntity> findAllByOrderByIdDesc();

    List<DesignerCalendarEntity> findByStartBetween(LocalDateTime startDate, LocalDateTime endDate, Sort start);
}
