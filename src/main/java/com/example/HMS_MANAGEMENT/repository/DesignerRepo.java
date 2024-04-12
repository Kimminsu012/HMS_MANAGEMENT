package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.DesignerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface DesignerRepo extends JpaRepository<DesignerEntity,Long> {
    DesignerEntity findById(long id);
}
