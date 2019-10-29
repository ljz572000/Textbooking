package com.ljz.textbook_manager_service.controller;

import com.ljz.textbook_manager_service.entity.*;
import com.ljz.textbook_manager_service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private List<User> allUsers()
    {
        return userRepository.findAll();

    }
    @PostMapping("/login")
    @ResponseBody
    private User login(@RequestParam(value = "userId",required = false) String userId,@RequestParam(value = "userPassword",required = false) String userPassword){
        return userRepository.findByUserIdAndUserPassword(userId,userPassword);
    }

//    @GetMapping("/allTextbooks")
//    @ResponseBody
//    private List<TextBook> allTextbooks(){return textBookRepo.findAll();}


    @Autowired
    TextBookRepo textBookRepo;
    @GetMapping("/Textbooks")
    @ResponseBody
    private Page<TextBook> tenTextbooks(
            @RequestParam(value = "pagecount") Integer pagecount,
            @RequestParam(value = "size") Integer size)
    {
        return textBookRepo.findTenTextBooks(PageRequest.of(pagecount,size));
    }

    @Autowired
    MessRepo messRepo;
    @GetMapping("/Messages")
    @ResponseBody
    private Page<Message> messages(
            @RequestParam(value = "pagecount") Integer pagecount,
            @RequestParam(value = "size") Integer size){
        return messRepo.findMessages(PageRequest.of(pagecount,size));
    }

    @Autowired
    ShoppingRepo shoppingRepo;
    @GetMapping("/ShoppingCarts")
    @ResponseBody
    private Page<ShoppingCart> shoppingCarts(
            @RequestParam(value = "pagecount") Integer pagecount,
            @RequestParam(value = "size") Integer size
    ){
        return shoppingRepo.findShoppingCarts(PageRequest.of(pagecount,size));
    }

    @Autowired
    OrderRepo orderRepo;
    @GetMapping("/Orders")
    @ResponseBody
    private Page<Order> orders(
            @RequestParam(value = "pagecount") Integer pagecount,
            @RequestParam(value = "size") Integer size
    ) {
        return orderRepo.findOrders(PageRequest.of(pagecount, size));
    }

    @Autowired
    HistoryRepo historyRepo;

    @GetMapping("/histories")
    @ResponseBody
    private Page<History> histories(
            @RequestParam(value = "pagecount") Integer pagecount,
            @RequestParam(value = "size") Integer size
    ) {
        return historyRepo.findHistories(PageRequest.of(pagecount, size));
    }
}
