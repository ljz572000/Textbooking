package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.History;
import com.ljz.textbook_manager_service.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoryRepo extends JpaRepository<History,Integer> {
    @Query(
            value = "select * from textbook_manager.history ",
            countQuery = "select count(*) from textbook_manager.history",
            nativeQuery = true
    )
    Page<History> findHistories(Pageable pageable);
}
