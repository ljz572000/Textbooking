package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUserId(String userId);
    @Transactional
    @Modifying
    @Query(
            value = " insert into\n" +
                    "    `user`\n" +
                    "    (`user_id`,\n" +
                    "    `is_admin`,\n" +
                    "    `user_password`,\n" +
                    "    `user_icon_path`,\n" +
                    "    `user_name`,\n" +
                    "    `money`,\n" +
                    "    `major`,\n" +
                    "    `address`,\n" +
                    "    `mail`,\n" +
                    "    `birth`,\n" +
                    "    `is_female`\n" +
                    "    )values(?1,?2,?3,?4," +
                    "?5,?6,?7,?8,?9,?10,?11);",
            nativeQuery = true
    )
    void insertNewUser(String user_id, Boolean isAdmin, String pwd, String user_icon,
                       String user_name, Double money, String major, String address,
                       String mail, String birth,Boolean is_female);
    @Transactional
    @Modifying
    @Query(
            value = "update `user` set `user_password` = ?1 where `user_no` = ?2",
            nativeQuery = true
    )
    void repairPwd(String user_pwd,Integer user_no);
    //update `user` set `user_password` = '5678' where `user_no` = 1;
}
