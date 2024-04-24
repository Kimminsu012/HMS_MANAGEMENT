package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.constent.InvenStatus;
import com.example.HMS_MANAGEMENT.entity.InvenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvenRepo extends JpaRepository<InvenEntity,Long> {

    List<InvenEntity> findAllByInvenStatusOrderByTimeDesc(InvenStatus invenStatus);

    void deleteAllByIdCode(Integer idCode);
}
