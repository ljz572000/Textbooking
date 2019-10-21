package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.TextBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TextBookRepo extends JpaRepository<TextBook,Integer> {
    @Query(
            value = "select * from textbook_manager.text_book ",
            countQuery = "select count(*) from textbook_manager.text_book",
            nativeQuery = true
    )
    Page<TextBook> findTenTextBooks(Pageable pageable);
}
