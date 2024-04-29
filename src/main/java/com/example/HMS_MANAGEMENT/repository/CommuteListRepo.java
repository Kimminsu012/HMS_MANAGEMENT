package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.dto.CommuteListDto;
import com.example.HMS_MANAGEMENT.entity.CommuteListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommuteListRepo extends JpaRepository<CommuteListEntity,Long> {

    List<CommuteListDto> findByDate(LocalDate date);
}