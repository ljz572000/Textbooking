package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.ResetCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ResetCodeRepo extends JpaRepository<ResetCode, Integer> {
    ResetCode findByUserNo(Integer no);

    @Transactional
    @Modifying
    @Query(value = "insert into `reset_code` (`user_no`,`code`) values(?1,?2);", nativeQuery = true)
    void insertNewCode(Integer user_no, String code);

    @Transactional
    @Modifying
    @Query(value = "delete from reset_code where user_no = ?1", nativeQuery = true)
    void deleteNewCode(Integer user_no);
}
