package com.ljz.textbook_manager_service.controller;

import com.ljz.textbook_manager_service.BCrypt;
import com.ljz.textbook_manager_service.Mail.Mail;
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

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import java.util.Random;

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
    protected User login(@RequestParam(value = "userId",required = false) String userId){
        return userRepository.findByUserId(userId);
    }

//        private String to = "2373861592@qq.com";
//    private String subject = "修改密码";
//    private String content = "您的密码已修改为xxxx";
    Mail mail = new Mail();
    @GetMapping("/sendMail")
    @ResponseBody
    protected String sendMail(
            @RequestParam(value = "to") String to,
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "content") String content
    ){
        mail.sendMail(to,subject,content);
        return "success";
    }

@Autowired
ResetCodeRepo resetCodeRepo;
    @PostMapping("/goToResetPage")
    @ResponseBody
    private String goToResetPage(
            @RequestParam(value = "userId") String userId
    ){
        User user = login(userId);
        if (user == null){
            return "您输入的账号不存在";
        }
        String code = "";
        Random ran1 = new Random(10000);
        for (int i = 0; i < 10; i++) { code += ran1.nextInt(10) + ""; }
        String link = "https://www.lijinzhou.top:2020/ResetPage?rest_code="+code+"&userNo="+user.getUserNo();

        if (resetCodeRepo.findByUserNo(user.getUserNo())!= null){
            resetCodeRepo.deleteNewCode(user.getUserNo());
        }
        resetCodeRepo.insertNewCode(user.getUserNo(),code);
        sendMail(user.getMail(),"教材管理系统重置密码","请点击链接跳转到重置页面："+link);
        return "success";
    }

    @GetMapping("/ResetPage")
    protected String toResetPage(
            @RequestParam(value = "rest_code") String rest_code,
            @RequestParam(value = "userNo") Integer userNo,
            HttpSession session
    ){
        ResetCode resetCode = resetCodeRepo.findByUserNo(userNo);
        if (rest_code.equals(resetCode.getCode())) {
            session.setAttribute("userNo",userNo);
            return "resetPage";
        }else {
            return "error";
        }
    }

    @PostMapping("/resetPwd")
    private String resetPwd(@RequestParam(value = "password") String pwd,HttpSession session){
        Integer no = (Integer)session.getAttribute("userNo");
        repairPwd(no,BCrypt.hashpw(pwd, BCrypt.gensalt()));
        return "success";
    }

    @PostMapping("/insertAUser")
    @ResponseBody
    private String registered(
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "isAdmin") Boolean isAdmin,
            @RequestParam(value = "userPassword") String userPassword,
            @RequestParam(value = "userIconPath") String userIconPath,
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "money") Double money,
            @RequestParam(value = "major") String major,
            @RequestParam(value = "address") String address,
            @RequestParam(value = "mail") String mail,
            @RequestParam(value = "birth") String birth,
            @RequestParam(value = "isFemale") Boolean isFemale
    ){
        if (userRepository.findByUserId(userId) == null){
            userRepository.insertNewUser(
                    userId,
                    isAdmin,
                    userPassword,
                    userIconPath,
                    userName,
                    money,major,address,mail, birth,isFemale
            );
            return userRepository.findByUserId(userId).toString();
        }else {
            return "学号已经有人使用";
        }
    }

    @PostMapping("/chargeMoney")
    @ResponseBody
    private String chargeMoney(
            @RequestParam(value = "user_no") Integer user_no,
            @RequestParam(value = "money") Double money
    ){
        userRepository.chargeMoney(user_no,money);
        return "success";
    }

    @PostMapping("/repairPwd")
    @ResponseBody
    protected String repairPwd(@RequestParam(value = "user_no") Integer user_no,
                             @RequestParam(value = "user_pwd") String user_pwd)
    {
        userRepository.repairPwd(user_pwd,user_no);
        return "success";
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
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "user_no") Integer user_no

    ){
        return messRepo.findMessages(PageRequest.of(pagecount,size),user_no);
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
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "user_no") Integer user_no
    ){
        return shoppingRepo.findShoppingCarts(PageRequest.of(pagecount,size),user_no);
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
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "user_no") Integer user_no
    ) {
        return orderRepo.findOrders(user_no,PageRequest.of(pagecount, size));
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
