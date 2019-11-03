package com.ljz.textbook_manager_service.repository;


import com.ljz.textbook_manager_service.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface OrderRepo extends JpaRepository<Order,Integer> {
    @Query(
            value = "select * from `order` where `order`.user_no = ?1",
            countQuery = "select count(*) from `order`",
            nativeQuery = true
    )
    Page<Order> findOrders(Integer user_no,Pageable pageable);
    @Transactional
    @Modifying
    @Query(value = "insert into `order`(`book_no`,`book_num`,`book_values`,`user_no`) values(?1,?2,?3,?4);",nativeQuery = true)
    void addNewOrder(Integer book_no,Integer book_num,Double book_values,Integer user_no);
}
