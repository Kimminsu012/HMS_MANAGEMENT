package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.InvenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvenRepo extends JpaRepository<InvenEntity,Long> {


}
