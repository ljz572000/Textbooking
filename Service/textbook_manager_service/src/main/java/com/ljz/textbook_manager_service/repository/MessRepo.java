package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface MessRepo extends JpaRepository<Message,Integer> {
    @Query(
            value = "select * from textbook_manager.message ",
            countQuery = "select count(*) from textbook_manager.message",
            nativeQuery = true
    )
    Page<Message> findMessages(Pageable pageable);
    @Transactional
    @Modifying
    @Query(
            value = "insert into `message`(`content`,`user_no`) values (?1,?2); ",nativeQuery = true
    )
    void newMessage(String content,Integer user_no);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `message` WHERE `mess_no`=?1",nativeQuery = true)
    void deleteMessage(Integer mess_no);


    Message findByMessNo(Integer mess_no);
}
