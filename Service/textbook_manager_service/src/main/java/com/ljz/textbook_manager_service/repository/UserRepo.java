package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUserIdAndUserPassword(String userId,String userPassword);
}
