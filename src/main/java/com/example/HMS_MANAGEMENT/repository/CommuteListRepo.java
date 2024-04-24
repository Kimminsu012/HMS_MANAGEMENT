package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.CommuteListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuteListRepo extends JpaRepository<CommuteListEntity,Long> {

}
