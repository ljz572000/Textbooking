package com.ljz.textbook_manager_service.controller;

import com.ljz.textbook_manager_service.entity.User;
import com.ljz.textbook_manager_service.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    UserRepo userRepository;
    @GetMapping("/allUsers")
    @ResponseBody
    private List<User> allUsers(){
        return userRepository.findAll();
    }
    @PostMapping("/login")
    @ResponseBody
    private User login(@RequestParam(value = "userId",required = false) String userId,@RequestParam(value = "userPassword",required = false) String userPassword){
        return userRepository.findByUserIdAndUserPassword(userId,userPassword);
    }
}
