package com.example.HMS_MANAGEMENT.repository;

import com.example.HMS_MANAGEMENT.constent.InvenStatus;
import com.example.HMS_MANAGEMENT.entity.InvenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvenRepo extends JpaRepository<InvenEntity,Long> {

    List<InvenEntity> findAllByInvenStatusOrderByTimeDesc(InvenStatus invenStatus);

    List<InvenEntity> findByItemNmOrIdCodeOrIdClassOrItemL(String itemNm, Integer idCode, String idClass, Integer itemL);
}
