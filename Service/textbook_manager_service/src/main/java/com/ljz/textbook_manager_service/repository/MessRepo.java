package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessRepo extends JpaRepository<Message,Integer> {
    @Query(
            value = "select * from textbook_manager.message ",
            countQuery = "select count(*) from textbook_manager.message",
            nativeQuery = true
    )
    Page<Message> findMessages(Pageable pageable);
}
