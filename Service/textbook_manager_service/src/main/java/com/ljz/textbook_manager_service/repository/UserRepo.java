package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUserIdAndUserPassword(String userId,String userPassword);
    @Transactional
    @Modifying
    @Query(
            value = "insert into `textbook_manager`.`user`(`user_id`,`is_admin`,`user_password`,`user_icon_path`,`user_name`)"+
                    " values(?1,?2,?3,?4,?5);",
            nativeQuery = true
    )
    void insertNewUser(String user_id,Boolean isAdmin,String pwd,String user_icon,String user_name);
}
