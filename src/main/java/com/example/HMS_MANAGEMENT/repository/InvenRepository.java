package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.entity.InvenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvenRepository extends JpaRepository<InvenEntity,Long> {

//    List<InvenEntity> findByItemNmOrIdClass(String itemNm, String idClass );
}
