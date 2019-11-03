package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUserId(String userId);
    @Transactional
    @Modifying
    @Query(
            value = "insert into `user`(`user_id`,`is_admin`,`user_password`,`user_icon_path`,`user_name`)" +
                    "values(?1,?2,?3,?4,?5);",
            nativeQuery = true
    )
    void insertNewUser(String user_id,Boolean isAdmin,String pwd,String user_icon,String user_name);
    @Transactional
    @Modifying
    @Query(
            value = "update `user` set `user_password` = ?1 where `user_no` = ?2",
            nativeQuery = true
    )
    void repairPwd(String user_pwd,Integer user_no);
    //update `user` set `user_password` = '5678' where `user_no` = 1;
}
