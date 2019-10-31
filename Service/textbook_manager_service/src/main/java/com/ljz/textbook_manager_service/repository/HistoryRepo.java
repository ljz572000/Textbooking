package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.History;
import com.ljz.textbook_manager_service.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface HistoryRepo extends JpaRepository<History,Integer> {
    @Query(
            value = "select * from textbook_manager.history",
            countQuery = "select count(*) from textbook_manager.history",
            nativeQuery = true
    )
    Page<History> findHistories(Pageable pageable);
    @Transactional
    @Modifying
    @Query(value = " insert into `history`(`order_no`) values\n" +
            "(?1);",nativeQuery = true)
    void addNewHistories(Integer order_no);
}
