package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.TextBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface TextBookRepo extends JpaRepository<TextBook,Integer> {
    @Query(
            value = "select * from textbook_manager.text_book ",
            countQuery = "select count(*) from textbook_manager.text_book",
            nativeQuery = true
    )
    Page<TextBook> findTenTextBooks(Pageable pageable);

    TextBook findByBookNo(Integer bookNo);

    @Transactional
    @Modifying
    @Query(value = "update `text_book` set `text_book`.`totalnum` = ?1 where `text_book`.`book_no`=?2",
            nativeQuery = true)
    void UpdateTextBookNum(Integer updateNum,Integer bookNo);
}
