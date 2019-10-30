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

    @PostMapping("/insertAUser")
    @ResponseBody
    private String registered(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "isAdmin") Boolean isAdmin,
            @RequestParam(value = "userPassword") String userPassword,
            @RequestParam(value = "userIconPath") String userIconPath,
            @RequestParam(value = "userName") String userName
    ){
        if (userRepository.findByUserIdAndUserPassword(userId, userPassword) == null){
            userRepository.insertNewUser(
                    userId,isAdmin,userPassword,userIconPath,userName
            );
            return userRepository.findByUserIdAndUserPassword(userId,userPassword).toString();
        }else {
            return "学号已经有人使用";
        }
    }

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

    @GetMapping("/UpdateTextBookNum")
    @ResponseBody
    private String UpdateTextBookNum(
            @RequestParam(value = "buyNum") Integer buyNum,
            @RequestParam(value = "bookNo") Integer bookNo
    )
    {
        TextBook textBook = textBookRepo.findByBookNo(bookNo);
        if (buyNum>textBook.getTotalnum()||textBook.getTotalnum()==0){
            return "false";
        }else {
            textBookRepo.UpdateTextBookNum(textBook.getTotalnum()-buyNum,bookNo);
            return "success";
        }
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

    @GetMapping("/NewMessage")
    @ResponseBody
    private String newMessage(
            @RequestParam(value = "content") String content,
            @RequestParam(value = "user_no") Integer user_no
    )
    {
        messRepo.newMessage(content,user_no);
        return "success";
    }

    @GetMapping("/DeleteMessage")
    @ResponseBody
    private String deleteMessage(
            @RequestParam(value = "")Integer mess_no
    ){
        if (messRepo.findByMessNo(mess_no) == null){
            return "false";
        }
        messRepo.deleteMessage(mess_no);
        return "success";
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

    @GetMapping("/addShoppingCart")
    @ResponseBody
    private String addShoppingCart(
            @RequestParam(value = "book_no") Integer book_no,
            @RequestParam(value = "book_num") Integer book_num,
            @RequestParam(value = "book_values") Double book_values,
            @RequestParam(value = "user_no") Integer user_no
    ){
        shoppingRepo.addShoppingCarts(book_no,book_num,book_values,user_no);
        return "success";
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

    @GetMapping("/AddOrders")
    @ResponseBody
    private String addOrders(
            @RequestParam(value = "book_no") Integer book_no,
            @RequestParam(value = "book_num") Integer book_num,
            @RequestParam(value = "book_values") Double book_values,
            @RequestParam(value = "user_no") Integer user_no
    ) {
        orderRepo.addNewOrder(book_no,book_num,book_values,user_no);
        return "success";
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

    @GetMapping("/addHistories")
    @ResponseBody
    private String addHistories(@RequestParam(value = "order_no") Integer order_no){
        historyRepo.addNewHistories(order_no);
        return "success";
    }
}
